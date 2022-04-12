package com.example.qlbhbe.service.baocao;

import com.example.qlbhbe.dto.SanPhamDTO;
import com.example.qlbhbe.util.DataUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.FileOutputStream;
import java.util.*;

@Service
public class SanPhamReportImpl implements SanPhamReport {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<SanPhamDTO> getSanPhamTon(SanPhamDTO sanPhamDTO) throws Exception {
        try {
            StringBuilder queryStr = new StringBuilder();
            Map<String, Object> params = new HashMap<>();
            queryStr.append("select s.id,\n" +
                    "       s.ma_nguyen_vat_lieu,\n" +
                    "       s.ten_nguyen_vat_lieu,\n" +
                    "       s.gia_ban_niem_yet,\n" +
                    "       s.gia_nhap_niem_yet,\n" +
                    "       sum(nd.so_luong),\n" +
                    "       nd.gia,\n" +
                    "       s.don_vi\n" +
                    "from nguyen_vat_lieu s,\n" +
                    "     nhap_hang n,\n" +
                    "     nhap_hang_chi_tiet nd\n" +
                    "where s.id = nd.id_nguyen_vat_lieu\n" +
                    "  and n.id = nd.id_nhap_hang");
            if (!DataUtil.isNullOrEmpty(sanPhamDTO.getTenSanPham())) {
                queryStr.append(" and lower(s.ten_nguyen_vat_lieu) like :ten ");
                params.put("ten", '%' + sanPhamDTO.getTenSanPham().toLowerCase(Locale.ROOT) + '%');
            }

            if (!DataUtil.isNullOrEmpty(sanPhamDTO.getMaSanPham())) {
                queryStr.append(" and lower(s.ma_nguyen_vat_lieu) like :ma ");
                params.put("ma", '%' + sanPhamDTO.getMaSanPham().toLowerCase(Locale.ROOT) + '%');
            }
            queryStr.append(" group by  s.id,  s.ma_nguyen_vat_lieu  ");

            Query query = entityManager.createNativeQuery(queryStr.toString());
            for (Map.Entry<String, Object> p : params.entrySet()) {
                query.setParameter(p.getKey(), p.getValue());

            }
            List<Object[]> objects = query.getResultList();


            List<SanPhamDTO> sanPhamDTOSNhapHangTon = DataUtil.convertLsObjectsToClass(Arrays.asList("id", "maSanPham", "tenSanPham", "giaBanNiemYet", "giaNhapNiemYet", "soLuong",
                            "gia", "donVi")
                    , objects, SanPhamDTO.class);

            StringBuilder queryStr1 = new StringBuilder();
            Map<String, Object> params1 = new HashMap<>();
            queryStr1.append("select s.id,\n" +
                    "       s.ma_nguyen_vat_lieu,\n" +
                    "       s.ten_nguyen_vat_lieu,\n" +
                    "       s.gia_ban_niem_yet,\n" +
                    "       s.gia_nhap_niem_yet,\n" +
                    "       sum(nd.so_luong),\n" +
                    "       nd.gia,\n" +
                    "       s.don_vi\n" +
                    "from nguyen_vat_lieu s,\n" +
                    "     xuat_hang n,\n" +
                    "     xuat_hang_chi_tiet nd\n" +
                    "where s.id = nd.id_nguyen_vat_lieu\n" +
                    "  and n.id = nd.id_xuat_hang ");
            if (!DataUtil.isNullOrEmpty(sanPhamDTO.getTenSanPham())) {
                queryStr1.append(" and lower(s.ten_nguyen_vat_lieu) like :ten1 ");
                params1.put("ten1", '%' + sanPhamDTO.getTenSanPham().toLowerCase(Locale.ROOT) + '%');
            }

            if (!DataUtil.isNullOrEmpty(sanPhamDTO.getMaSanPham())) {
                queryStr1.append(" and lower(s.ma_nguyen_vat_lieu) like :ma1 ");
                params1.put("ma1", '%' + sanPhamDTO.getMaSanPham().toLowerCase(Locale.ROOT) + '%');
            }
            queryStr1.append(" group by s.id,  s.ma_nguyen_vat_lieu ");

            queryStr.append("order by sum(nd.so_luong * nd.gia)  desc ");

            Query query1 = entityManager.createNativeQuery(queryStr1.toString());
            for (Map.Entry<String, Object> p : params1.entrySet()) {
                query1.setParameter(p.getKey(), p.getValue());
            }
            List<Object[]> objects1 = query1.getResultList();

            List<SanPhamDTO> sanPhamDTOSXuatHangTon = DataUtil.convertLsObjectsToClass(Arrays.asList("id", "maSanPham", "tenSanPham", "giaBanNiemYet", "giaNhapNiemYet", "soLuong",
                            "gia", "donVi")
                    , objects1, SanPhamDTO.class);

            for (SanPhamDTO phamDTO : sanPhamDTOSNhapHangTon) {
                phamDTO.setSoLuongNhap(phamDTO.getSoLuong());
                phamDTO.setSoLuong(null);
                phamDTO.setGia(null);
                phamDTO.setSoLuongBan(0d);
                phamDTO.setSoLuongTon(phamDTO.getSoLuongNhap());
                if (!sanPhamDTOSXuatHangTon.isEmpty()) {
                    for (SanPhamDTO dto : sanPhamDTOSXuatHangTon) {
                        if (phamDTO.getId().equals(dto.getId())) {
                            phamDTO.setSoLuongBan(dto.getSoLuong());
                            phamDTO.setSoLuongTon(DataUtil.safeToDouble(phamDTO.getSoLuongNhap()) - DataUtil.safeToDouble(dto.getSoLuong()));
                        }
                    }
                }
            }

            return sanPhamDTOSNhapHangTon;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<SanPhamDTO> getSanPhamChiPhiMax(SanPhamDTO sanPhamDTO) throws Exception {
        try {
            StringBuilder queryStr = new StringBuilder();
            Map<String, Object> params = new HashMap<>();
            queryStr.append(" select nd.id_nguyen_vat_lieu, s.ma_nguyen_vat_lieu ,s.ten_nguyen_vat_lieu , s.gia_ban_niem_yet, s.gia_nhap_niem_yet , s.id_danh_muc ," +
                    " (select d.ten_danh_muc from danh_muc d where d.id = s.id_danh_muc) dm , nd.so_luong , nd.gia," +
                    " (select c.ten_cua_hang from cua_hang c where c.id = n.id_cua_hang) ch " +
                    " , sum(nd.so_luong * nd.gia) , s.don_vi from nguyen_vat_lieu s,  nhap_hang_chi_tiet nd, nhap_hang n " +
                    " where n.id = nd.id_nhap_hang and s.id = nd.id_nguyen_vat_lieu ");
            if (!DataUtil.isNullOrEmpty(sanPhamDTO.getTenSanPham())) {
                queryStr.append(" and lower(s.ten_nguyen_vat_lieu) like :ten ");
                params.put("ten", '%' + sanPhamDTO.getTenSanPham().toLowerCase(Locale.ROOT) + '%');
            }

            if (!DataUtil.isNullOrEmpty(sanPhamDTO.getMaSanPham())) {
                queryStr.append(" and lower(s.ma_nguyen_vat_lieu) like :ma ");
                params.put("ma", '%' + sanPhamDTO.getMaSanPham().toLowerCase(Locale.ROOT) + '%');
            }
            if (!DataUtil.isNullOrEmpty(sanPhamDTO.getIdCuaHang())) {
                queryStr.append(" and n.id_cua_hang  = :idcuaHang ");
                params.put("idcuaHang", sanPhamDTO.getIdCuaHang());
            }
            queryStr.append("  group by nd.id_nguyen_vat_lieu, nd.gia  ");
            if (!DataUtil.isNullOrEmpty(sanPhamDTO.getIdCuaHang())) {
                queryStr.append(" , n.id_cua_hang ");
            }
            queryStr.append("order by sum(nd.so_luong * nd.gia)  desc ");

            Query query = entityManager.createNativeQuery(queryStr.toString());
            for (Map.Entry<String, Object> p : params.entrySet()) {
                query.setParameter(p.getKey(), p.getValue());

            }
            List<Object[]> objects = query.getResultList();


            List<SanPhamDTO> sanPhamDTOSNhapHangTon = DataUtil.convertLsObjectsToClass(Arrays.asList("id", "maSanPham", "tenSanPham", "giaBanNiemYet",
                            "giaNhapNiemYet", "idDanhMuc", "tenDanhMuc", "soLuongNhap", "giaNhap", "tenCuaHang", "totalChiPhi", "donVi")
                    , objects, SanPhamDTO.class);

            return sanPhamDTOSNhapHangTon;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<SanPhamDTO> getSanPhamDoanhThuMax(SanPhamDTO sanPhamDTO) throws Exception {
        try {
            StringBuilder queryStr = new StringBuilder();
            Map<String, Object> params = new HashMap<>();
            queryStr.append(" select nd.id_nguyen_vat_lieu, s.ma_nguyen_vat_lieu ,s.ten_nguyen_vat_lieu , s.gia_ban_niem_yet, s.gia_nhap_niem_yet , s.id_danh_muc ," +
                    " (select d.ten_danh_muc from danh_muc d where d.id = s.id_danh_muc) dm, nd.so_luong , nd.gia ," +
                    " (select c.ten_cua_hang from cua_hang c where c.id = n.id_cua_hang) ch " +
                    " , sum(nd.so_luong * nd.gia) , s.don_vi from nguyen_vat_lieu s,  xuat_hang_chi_tiet nd, xuat_hang n" +
                    " where n.id = nd.id_xuat_hang and s.id = nd.id_nguyen_vat_lieu ");
            if (!DataUtil.isNullOrEmpty(sanPhamDTO.getTenSanPham())) {
                queryStr.append(" and lower(s.ten_nguyen_vat_lieu) like :ten ");
                params.put("ten", '%' + sanPhamDTO.getTenSanPham().toLowerCase(Locale.ROOT) + '%');
            }

            if (!DataUtil.isNullOrEmpty(sanPhamDTO.getMaSanPham())) {
                queryStr.append(" and lower(s.ma_nguyen_vat_lieu) like :ma ");
                params.put("ma", '%' + sanPhamDTO.getMaSanPham().toLowerCase(Locale.ROOT) + '%');
            }
            if (!DataUtil.isNullOrEmpty(sanPhamDTO.getIdCuaHang())) {
                queryStr.append(" and n.id_cua_hang  = :idcuaHang ");
                params.put("idcuaHang", sanPhamDTO.getIdCuaHang());
            }
            queryStr.append("  group by nd.id_nguyen_vat_lieu, nd.gia  ");
            if (!DataUtil.isNullOrEmpty(sanPhamDTO.getIdCuaHang())) {
                queryStr.append(" , n.id_cua_hang ");
            }

            Query query = entityManager.createNativeQuery(queryStr.toString());
            for (Map.Entry<String, Object> p : params.entrySet()) {
                query.setParameter(p.getKey(), p.getValue());

            }
            List<Object[]> objects = query.getResultList();


            List<SanPhamDTO> sanPhamDTOSNhapHangTon = DataUtil.convertLsObjectsToClass(Arrays.asList("id", "maSanPham", "tenSanPham", "giaBanNiemYet",
                            "giaNhapNiemYet", "idDanhMuc", "tenDanhMuc", "soLuongBan", "giaBan", "tenCuaHang", "totalDoanhThu", "donVi")
                    , objects, SanPhamDTO.class);

            return sanPhamDTOSNhapHangTon;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Map<String, String> exportSanPhamTon(SanPhamDTO sanPhamDTO) throws Exception {
        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("SanPhamTon");

            Font headerFontTitle = workbook.createFont();
            headerFontTitle.setBold(true);
            headerFontTitle.setFontHeightInPoints((short) 20);
            headerFontTitle.setColor(IndexedColors.RED.getIndex());
            CellStyle headerCellStyle1 = workbook.createCellStyle();
            headerCellStyle1.setFont(headerFontTitle);
            headerCellStyle1.setBorderBottom(BorderStyle.THIN);
            headerCellStyle1.setBorderTop(BorderStyle.THIN);
            headerCellStyle1.setAlignment(HorizontalAlignment.CENTER);
            headerCellStyle1.setWrapText(true);
            Row title = sheet.createRow(0);
            Cell cellTitle = title.createCell(2);
            cellTitle.setCellValue("BÁO CÁO SẢN PHẨM TỒN KHO");
            cellTitle.setCellStyle(headerCellStyle1);
            CellRangeAddress cellMerge = new CellRangeAddress(0, 1, 2, 6);
            sheet.addMergedRegion(cellMerge);

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 14);
            headerFont.setColor(IndexedColors.BLACK.getIndex());
// Create a CellStyle with the font
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);
            headerCellStyle.setBorderBottom(BorderStyle.THIN);
            headerCellStyle.setBorderLeft(BorderStyle.THIN);
            headerCellStyle.setBorderRight(BorderStyle.THIN);
            headerCellStyle.setBorderTop(BorderStyle.THIN);
            headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
            headerCellStyle.setWrapText(true);

            Row headerRow = sheet.createRow(3);
            for (int i = 0; i < 10; i++) {
                sheet.setColumnWidth(i, 8500);
                Cell cell = headerRow.createCell(i);
                if (i == 0) {
                    cell.setCellValue("Mã Sản Phẩm");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 1) {
                    cell.setCellValue("Tên Sản Phẩm");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 2) {
                    cell.setCellValue("Giá Bán Niêm Yết");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 3) {
                    cell.setCellValue("Giá Nhập Niêm Yết");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 4) {
                    cell.setCellValue("Đơn vị");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 5) {
                    cell.setCellValue("Số Lượng Nhập");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 6) {
                    cell.setCellValue("Số Lượng Xuất");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 7) {
                    cell.setCellValue("Số Lượng Tồn");
                    cell.setCellStyle(headerCellStyle);
                }
            }
            int rowNum = 4;
            List<SanPhamDTO> sanPhamDTOList = getSanPhamTon(sanPhamDTO);
            CellStyle cellStyle = workbook.createCellStyle();

            cellStyle.setBorderBottom(BorderStyle.THIN);
            cellStyle.setBorderLeft(BorderStyle.THIN);
            cellStyle.setBorderRight(BorderStyle.THIN);
            cellStyle.setBorderTop(BorderStyle.THIN);
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cellStyle.setWrapText(true);
            for (SanPhamDTO sanPhamDTO1 : sanPhamDTOList) {
                Row row = sheet.createRow(rowNum++);

                Cell cell = row.createCell(0);
                cell.setCellValue(sanPhamDTO1.getMaSanPham());
                cell.setCellStyle(cellStyle);

                Cell cell1 = row.createCell(1);
                cell1.setCellValue(sanPhamDTO1.getTenSanPham());
                cell1.setCellStyle(cellStyle);

                Cell cell3 = row.createCell(2);
                cell3.setCellValue(sanPhamDTO1.getGiaBanNiemYet());
                cell3.setCellStyle(cellStyle);

                Cell cell4 = row.createCell(3);
                cell4.setCellValue(sanPhamDTO1.getGiaNhapNiemYet());
                cell4.setCellStyle(cellStyle);

                Cell cell41 = row.createCell(4);
                cell41.setCellValue(sanPhamDTO1.getDonVi());
                cell41.setCellStyle(cellStyle);

                Cell cell5 = row.createCell(5);
                cell5.setCellValue(sanPhamDTO1.getSoLuongNhap());
                cell5.setCellStyle(cellStyle);

                Cell cell6 = row.createCell(6);
                cell6.setCellValue(sanPhamDTO1.getSoLuongBan());
                cell6.setCellStyle(cellStyle);

                Cell cell7 = row.createCell(7);
                cell7.setCellValue(sanPhamDTO1.getSoLuongTon());
                cell7.setCellStyle(cellStyle);

            }

            String path = "D:/SanPhamTonKho" + System.currentTimeMillis() + ".xlsx";
            FileOutputStream fileOut = new FileOutputStream(path);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            Map<String, String> result = new HashMap<>();
            result.put("path", path);
            return result;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Map<String, String> exportSanPhamDoanhThuMax(SanPhamDTO sanPhamDTO) throws Exception {
        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("BaoCaoXuatKho");

            Font headerFontTitle = workbook.createFont();
            headerFontTitle.setBold(true);
            headerFontTitle.setFontHeightInPoints((short) 20);
            headerFontTitle.setColor(IndexedColors.RED.getIndex());
            CellStyle headerCellStyle1 = workbook.createCellStyle();
            headerCellStyle1.setFont(headerFontTitle);
            headerCellStyle1.setBorderBottom(BorderStyle.THIN);
            headerCellStyle1.setBorderTop(BorderStyle.THIN);
            headerCellStyle1.setAlignment(HorizontalAlignment.CENTER);
            headerCellStyle1.setWrapText(true);
            Row title = sheet.createRow(0);
            Cell cellTitle = title.createCell(2);
            cellTitle.setCellValue("BÁO CÁO HÀNG XUẤT KHO");
            cellTitle.setCellStyle(headerCellStyle1);
            CellRangeAddress cellMerge = new CellRangeAddress(0, 1, 2, 6);
            sheet.addMergedRegion(cellMerge);


            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 14);
            headerFont.setColor(IndexedColors.BLACK.getIndex());

// Create a CellStyle with the font
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);
            headerCellStyle.setBorderBottom(BorderStyle.THIN);
            headerCellStyle.setBorderLeft(BorderStyle.THIN);
            headerCellStyle.setBorderRight(BorderStyle.THIN);
            headerCellStyle.setBorderTop(BorderStyle.THIN);
            headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
            headerCellStyle.setWrapText(true);
            Row headerRow = sheet.createRow(3);

            for (int i = 0; i < 10; i++) {
                sheet.setColumnWidth(i, 8500);
                Cell cell = headerRow.createCell(i);
                if (i == 0) {
                    cell.setCellValue("Mã Sản Phẩm");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 1) {
                    cell.setCellValue("Tên Sản Phẩm");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 2) {
                    cell.setCellValue("Danh Mục");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 3) {
                    cell.setCellValue("Giá Bán Niêm Yết");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 4) {
                    cell.setCellValue("Giá Nhập Niêm Yết");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 5) {
                    cell.setCellValue("Đơn Vị");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 6) {
                    cell.setCellValue("Số Lượng Xuất");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 7) {
                    cell.setCellValue("Giá");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 8) {
                    cell.setCellValue("Thành Tiền");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 9) {
                    cell.setCellValue("Cửa Hàng");
                    cell.setCellStyle(headerCellStyle);
                }
            }
            int rowNum = 4;
            List<SanPhamDTO> sanPhamDTOList = getSanPhamDoanhThuMax(sanPhamDTO);
            CellStyle cellStyle = workbook.createCellStyle();

            cellStyle.setBorderBottom(BorderStyle.THIN);
            cellStyle.setBorderLeft(BorderStyle.THIN);
            cellStyle.setBorderRight(BorderStyle.THIN);
            cellStyle.setBorderTop(BorderStyle.THIN);
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cellStyle.setWrapText(true);
            for (SanPhamDTO sanPhamDTO1 : sanPhamDTOList) {
                Row row = sheet.createRow(rowNum++);

                Cell cell = row.createCell(0);
                cell.setCellValue(sanPhamDTO1.getMaSanPham());
                cell.setCellStyle(cellStyle);

                Cell cell1 = row.createCell(1);
                cell1.setCellValue(sanPhamDTO1.getTenSanPham());
                cell1.setCellStyle(cellStyle);

                Cell cell2 = row.createCell(2);
                cell2.setCellValue(sanPhamDTO1.getTenDanhMuc());
                cell2.setCellStyle(cellStyle);

                Cell cell3 = row.createCell(3);
                cell3.setCellValue(sanPhamDTO1.getGiaBanNiemYet());
                cell3.setCellStyle(cellStyle);

                Cell cell4 = row.createCell(4);
                cell4.setCellValue(sanPhamDTO1.getGiaNhapNiemYet());
                cell4.setCellStyle(cellStyle);

                Cell cell5 = row.createCell(5);
                cell5.setCellValue(sanPhamDTO1.getDonVi());
                cell5.setCellStyle(cellStyle);

                Cell cell51 = row.createCell(6);
                cell51.setCellValue(sanPhamDTO1.getSoLuongBan());
                cell51.setCellStyle(cellStyle);

                Cell cell61 = row.createCell(7);
                cell61.setCellValue(sanPhamDTO1.getGiaBan());
                cell61.setCellStyle(cellStyle);

                Cell cell71 = row.createCell(8);
                cell71.setCellValue(sanPhamDTO1.getTotalDoanhThu());
                cell71.setCellStyle(cellStyle);

                Cell cell81 = row.createCell(9);
                cell81.setCellValue(sanPhamDTO1.getTenCuaHang());
                cell81.setCellStyle(cellStyle);
            }
            String path = "D:/BaoCaoXuatKho" + System.currentTimeMillis() + ".xlsx";
            FileOutputStream fileOut = new FileOutputStream(path);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            Map<String, String> result = new HashMap<>();
            result.put("path", path);
            return result;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Map<String, String> exportSanPhamChiPhiMax(SanPhamDTO sanPhamDTO) throws Exception {
        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("BaoCaoNhapKho");

            Font headerFontTitle = workbook.createFont();
            headerFontTitle.setBold(true);
            headerFontTitle.setFontHeightInPoints((short) 20);
            headerFontTitle.setColor(IndexedColors.RED.getIndex());
            CellStyle headerCellStyle1 = workbook.createCellStyle();
            headerCellStyle1.setFont(headerFontTitle);
            headerCellStyle1.setBorderBottom(BorderStyle.THIN);
            headerCellStyle1.setBorderTop(BorderStyle.THIN);
            headerCellStyle1.setAlignment(HorizontalAlignment.CENTER);
            headerCellStyle1.setWrapText(true);
            Row title = sheet.createRow(0);
            Cell cellTitle = title.createCell(2);
            cellTitle.setCellValue("BÁO CÁO HÀNG NHẬP KHO");
            cellTitle.setCellStyle(headerCellStyle1);
            CellRangeAddress cellMerge = new CellRangeAddress(0, 1, 2, 6);
            sheet.addMergedRegion(cellMerge);

            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 14);
            headerFont.setColor(IndexedColors.BLACK.getIndex());

// Create a CellStyle with the font
            CellStyle headerCellStyle = workbook.createCellStyle();
            headerCellStyle.setFont(headerFont);
            headerCellStyle.setBorderBottom(BorderStyle.THIN);
            headerCellStyle.setBorderLeft(BorderStyle.THIN);
            headerCellStyle.setBorderRight(BorderStyle.THIN);
            headerCellStyle.setBorderTop(BorderStyle.THIN);
            headerCellStyle.setAlignment(HorizontalAlignment.CENTER);
            headerCellStyle.setWrapText(true);
            Row headerRow = sheet.createRow(3);

            for (int i = 0; i < 10; i++) {
                sheet.setColumnWidth(i, 8500);
                Cell cell = headerRow.createCell(i);
                if (i == 0) {
                    cell.setCellValue("Mã Sản Phẩm");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 1) {
                    cell.setCellValue("Tên Sản Phẩm");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 2) {
                    cell.setCellValue("Danh Mục");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 3) {
                    cell.setCellValue("Giá Bán Niêm Yết");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 4) {
                    cell.setCellValue("Giá Nhập Niêm Yết");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 5) {
                    cell.setCellValue("Đơn vị");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 6) {
                    cell.setCellValue("Số Lượng Nhập");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 7) {
                    cell.setCellValue("Giá");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 8) {
                    cell.setCellValue("Thành Tiền");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 9) {
                    cell.setCellValue("Cửa Hàng");
                    cell.setCellStyle(headerCellStyle);
                }
            }
            int rowNum = 4;
            List<SanPhamDTO> sanPhamDTOList = getSanPhamChiPhiMax(sanPhamDTO);
            CellStyle cellStyle = workbook.createCellStyle();

            cellStyle.setBorderBottom(BorderStyle.THIN);
            cellStyle.setBorderLeft(BorderStyle.THIN);
            cellStyle.setBorderRight(BorderStyle.THIN);
            cellStyle.setBorderTop(BorderStyle.THIN);
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cellStyle.setWrapText(true);
            for (SanPhamDTO sanPhamDTO1 : sanPhamDTOList) {
                Row row = sheet.createRow(rowNum++);

                Cell cell = row.createCell(0);
                cell.setCellValue(sanPhamDTO1.getMaSanPham());
                cell.setCellStyle(cellStyle);

                Cell cell1 = row.createCell(1);
                cell1.setCellValue(sanPhamDTO1.getTenSanPham());
                cell1.setCellStyle(cellStyle);

                Cell cell2 = row.createCell(2);
                cell2.setCellValue(sanPhamDTO1.getTenDanhMuc());
                cell2.setCellStyle(cellStyle);

                Cell cell3 = row.createCell(3);
                cell3.setCellValue(sanPhamDTO1.getGiaBanNiemYet());
                cell3.setCellStyle(cellStyle);

                Cell cell4 = row.createCell(4);
                cell4.setCellValue(sanPhamDTO1.getGiaNhapNiemYet());
                cell4.setCellStyle(cellStyle);

                Cell cell41 = row.createCell(5);
                cell41.setCellValue(sanPhamDTO1.getDonVi());
                cell41.setCellStyle(cellStyle);

                Cell cell5 = row.createCell(6);
                cell5.setCellValue(sanPhamDTO1.getSoLuongNhap());
                cell5.setCellStyle(cellStyle);

                Cell cell6 = row.createCell(7);
                cell6.setCellValue(sanPhamDTO1.getGiaNhap());
                cell6.setCellStyle(cellStyle);

                Cell cell7 = row.createCell(8);
                cell7.setCellValue(sanPhamDTO1.getTotalChiPhi());
                cell7.setCellStyle(cellStyle);

                Cell cell8 = row.createCell(9);
                cell8.setCellValue(sanPhamDTO1.getTenCuaHang());
                cell8.setCellStyle(cellStyle);
            }
            String path = "D:/BaoCaoNhapKho" + System.currentTimeMillis() + ".xlsx";
            FileOutputStream fileOut = new FileOutputStream(path);
            workbook.write(fileOut);
            fileOut.close();
            workbook.close();
            Map<String, String> result = new HashMap<>();
            result.put("path", path);
            return result;
        } catch (Exception e) {
            throw e;
        }
    }

    public List<SanPhamDTO> getSanPhamNhap(SanPhamDTO sanPhamDTO) throws Exception {
        try {
            StringBuilder queryStr = new StringBuilder();
            Map<String, Object> params = new HashMap<>();
            queryStr.append(" SELECT  " +
                    "    nd.id_nguyen_vat_lieu, " +
                    "    s.ma_nguyen_vat_lieu, " +
                    "    s.ten_nguyen_vat_lieu, " +
                    "    s.gia_ban_niem_yet, " +
                    "    s.gia_nhap_niem_yet, " +
                    "    s.id_danh_muc, " +
                    "    (SELECT  " +
                    "            d.ten_danh_muc " +
                    "        FROM " +
                    "            danh_muc d " +
                    "        WHERE " +
                    "            d.id = s.id_danh_muc) dm, " +
                    "    nd.so_luong, " +
                    "    nd.gia, " +
                    "    (SELECT  " +
                    "            c.ten_cua_hang " +
                    "        FROM " +
                    "            cua_hang c " +
                    "        WHERE " +
                    "            c.id = n.id_cua_hang) ch, " +
                    "    SUM(nd.so_luong * nd.gia), " +
                    "    n.id_nha_cung_cap , " +
                    "    (SELECT  " +
                    "            ten_nha_cung_cap " +
                    "        FROM " +
                    "            nha_cung_cap c " +
                    "        WHERE " +
                    "            c.id = n.id_nha_cung_cap) tenncc, " +
                    "            nd.ngay_het_han, " +
                    "            n.ngay_nhap, " +
                    "            nd.ngay_san_xuat , s.don_vi" +
                    " FROM " +
                    "    nguyen_vat_lieu s, " +
                    "    nhap_hang_chi_tiet nd, " +
                    "    nhap_hang n " +
                    " WHERE " +
                    "    n.id = nd.id_nhap_hang " +
                    "        AND s.id = nd.id_nguyen_vat_lieu ");

            if (!DataUtil.isNullOrEmpty(sanPhamDTO.getTenSanPham())) {
                queryStr.append(" and lower(s.ten_nguyen_vat_lieu) like :ten ");
                params.put("ten", '%' + sanPhamDTO.getTenSanPham().toLowerCase(Locale.ROOT) + '%');
            }

            if (!DataUtil.isNullOrEmpty(sanPhamDTO.getMaSanPham())) {
                queryStr.append(" and lower(s.ma_nguyen_vat_lieu) like :ma ");
                params.put("ma", '%' + sanPhamDTO.getMaSanPham().toLowerCase(Locale.ROOT) + '%');
            }
            if (!DataUtil.isNullOrEmpty(sanPhamDTO.getIdCuaHang())) {
                queryStr.append(" and n.id_cua_hang  = :idcuaHang ");
                params.put("idcuaHang", sanPhamDTO.getIdCuaHang());
            }
            queryStr.append("  group by  s.id, n.id_nha_cung_cap, n.id  ");
            if (!DataUtil.isNullOrEmpty(sanPhamDTO.getIdCuaHang())) {
                queryStr.append(" , n.id_cua_hang ");
            }
            queryStr.append("order by sum(nd.so_luong * nd.gia)  desc ");

            Query query = entityManager.createNativeQuery(queryStr.toString());
            for (Map.Entry<String, Object> p : params.entrySet()) {
                query.setParameter(p.getKey(), p.getValue());

            }
            List<Object[]> objects = query.getResultList();


            List<SanPhamDTO> sanPhamDTOSNhapHangTon = DataUtil.convertLsObjectsToClass(Arrays.asList("id", "maSanPham", "tenSanPham", "giaBanNiemYet",
                            "giaNhapNiemYet", "idDanhMuc", "tenDanhMuc", "soLuongNhap", "giaNhap", "tenCuaHang", "totalChiPhi", "idNhaCungCap", "tenNhaCungCap", "ngayHetHan", "ngayNhap", "ngaySanXuat", "donVi")
                    , objects, SanPhamDTO.class);

            return sanPhamDTOSNhapHangTon;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<SanPhamDTO> getSanPhamXuat(SanPhamDTO sanPhamDTO) throws Exception {
        try {
            StringBuilder queryStr = new StringBuilder();
            Map<String, Object> params = new HashMap<>();
            queryStr.append(" SELECT  " +
                    "    nd.id_nguyen_vat_lieu, " +
                    "    s.ma_nguyen_vat_lieu, " +
                    "    s.ten_nguyen_vat_lieu, " +
                    "    s.gia_ban_niem_yet, " +
                    "    s.gia_nhap_niem_yet, " +
                    "    s.id_danh_muc, " +
                    "    (SELECT  " +
                    "            d.ten_danh_muc " +
                    "        FROM " +
                    "            danh_muc d " +
                    "        WHERE " +
                    "            d.id = s.id_danh_muc) dm, " +
                    "    nd.so_luong, " +
                    "    nd.gia, " +
                    "    (SELECT  " +
                    "            c.ten_cua_hang " +
                    "        FROM " +
                    "            cua_hang c " +
                    "        WHERE " +
                    "            c.id = n.id_cua_hang) ch, " +
                    "    SUM(nd.so_luong * nd.gia), " +
                    "    n.id_khach_hang , " +
                    "    (SELECT  " +
                    "            ten_khach_hang " +
                    "        FROM " +
                    "            khach_hang c " +
                    "        WHERE " +
                    "            c.id = n.id_khach_hang) tenncc, " +
                    "            nd.ngay_het_han, " +
                    "            n.ngay_xuat, " +
                    "            nd.ngay_san_xuat , s.don_vi " +
                    "FROM " +
                    "    nguyen_vat_lieu s, " +
                    "    xuat_hang_chi_tiet nd, " +
                    "    xuat_hang n " +
                    "WHERE " +
                    "    n.id = nd.id_xuat_hang " +
                    "        AND s.id = nd.id_nguyen_vat_lieu ");

            if (!DataUtil.isNullOrEmpty(sanPhamDTO.getTenSanPham())) {
                queryStr.append(" and lower(s.ten_nguyen_vat_lieu) like :ten ");
                params.put("ten", '%' + sanPhamDTO.getTenSanPham().toLowerCase(Locale.ROOT) + '%');
            }

            if (!DataUtil.isNullOrEmpty(sanPhamDTO.getMaSanPham())) {
                queryStr.append(" and lower(s.ma_nguyen_vat_lieu) like :ma ");
                params.put("ma", '%' + sanPhamDTO.getMaSanPham().toLowerCase(Locale.ROOT) + '%');
            }
            if (!DataUtil.isNullOrEmpty(sanPhamDTO.getIdCuaHang())) {
                queryStr.append(" and n.id_cua_hang  = :idcuaHang ");
                params.put("idcuaHang", sanPhamDTO.getIdCuaHang());
            }
            queryStr.append(" group by s.id, n.id_khach_hang, n.id  ");

            queryStr.append("order by sum(nd.so_luong * nd.gia)  desc ");

            Query query = entityManager.createNativeQuery(queryStr.toString());
            for (Map.Entry<String, Object> p : params.entrySet()) {
                query.setParameter(p.getKey(), p.getValue());

            }
            List<Object[]> objects = query.getResultList();
            List<SanPhamDTO> sanPhamDTOSNhapHangTon = DataUtil.convertLsObjectsToClass(Arrays.asList("id", "maSanPham", "tenSanPham", "giaBanNiemYet",
                            "giaNhapNiemYet", "idDanhMuc", "tenDanhMuc", "soLuongBan", "giaBan", "tenCuaHang", "totalDoanhThu",
                            "idKhachHang", "tenKhachHang", "ngayHetHan", "ngayXuat", "ngaySanXuat", "donVi")
                    , objects, SanPhamDTO.class);

            return sanPhamDTOSNhapHangTon;
        } catch (Exception e) {
            throw e;
        }
    }


}
