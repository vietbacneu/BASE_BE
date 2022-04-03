package com.example.qlbhbe.service.nhaphang;

import com.example.qlbhbe.dto.MessageDTO;
import com.example.qlbhbe.dto.NhapHangChiTietDTO;
import com.example.qlbhbe.dto.NhapHangDTO;
import com.example.qlbhbe.entity.*;
import com.example.qlbhbe.mapper.NhapHangChiTietMapper;
import com.example.qlbhbe.mapper.NhapHangMapper;
import com.example.qlbhbe.repo.nhaphang.NhapHangRepo;
import com.example.qlbhbe.repo.nhaphangchitiet.NhapHangChiTietRepo;
import com.example.qlbhbe.service.AbstractService;
import com.example.qlbhbe.service.nhaphangchitiet.NhapHangChiTietService;
import com.example.qlbhbe.util.DataUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Service
@Transactional
public class NhapHangServiceImpl extends AbstractService<NhapHang, Long> implements NhapHangService {

    private final NhapHangRepo nhapHangRepo;

    @Autowired
    NhapHangChiTietRepo nhapHangChiTietRepo;

    @Autowired
    NhapHangMapper nhapHangMapper;

    @Autowired
    NhapHangChiTietMapper nhapHangChiTietMapper;

    @Autowired
    NhapHangChiTietService nhapHangChiTietService;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    public NhapHangServiceImpl(NhapHangRepo nhapHangRepo) {
        super(nhapHangRepo);
        this.nhapHangRepo = nhapHangRepo;
    }

    @Override
    @Transactional
    public MessageDTO update(long id, NhapHangDTO command) {
        Optional<NhapHang> opt = nhapHangRepo.findById(id);
        if (opt.isPresent()) {
            nhapHangChiTietRepo.deleteSanPham(id);
            nhapHangRepo.deleteSanPham(id);
        }
        command.setId(null);
        for (NhapHangChiTietDTO nhapHangChiTietDTO : command.getNhapHangChiTietDTOList()) {
            nhapHangChiTietDTO.setId(null);
            nhapHangChiTietDTO.setIdNhapHang(null);
        }
        save(command);
        return new MessageDTO("Cập nhật thành công", 200l);
    }

    @Override
    public Page<NhapHangDTO> search(NhapHangDTO command, Pageable pageable) throws Exception {
        try {
            StringBuilder queryStr = new StringBuilder();
            StringBuilder count = new StringBuilder();
            StringBuilder from = new StringBuilder();
            Map<String, Object> params = new HashMap<>();
            queryStr.append("select s.id              ," +
                    "    s.ma_nhap_hang    ," +
                    "    s.id_nha_cung_cap ," +
                    "    s.ngay_nhap       ," +
                    "    n.ten_nha_cung_cap," +
                    "   id_thanh_toan, (select ten_phuong_thuc from phuong_thuc_thanh_toan c where c.id =  s.id_thanh_toan )  ten_phuong_thuc," +
                    "   hop_dong_dinh_kem, duong_dan, id_nhan_vien," +
                    "  (select ten_nhan_vien from nhan_vien c where c.id =  s.id_nhan_vien )  ten_nhan_vien  ");
            count.append("select count(*) ");
            from.append(" from hop_dong_nhap_hang s, nha_cung_cap n where s.id_nha_cung_cap = n.id  ");
            if (!DataUtil.isNullOrEmpty(command.getTenNhaCungCap())) {
                from.append(" and lower(n.ten_nha_cung_cap) like :ten ");
                params.put("ten", '%' + command.getTenNhaCungCap().toLowerCase(Locale.ROOT) + '%');
            }
            if (!DataUtil.isNullOrEmpty(command.getMaNhapHang())) {
                from.append(" and lower(s.ma_nhap_hang) like :ma ");
                params.put("ma", '%' + command.getMaNhapHang().toLowerCase(Locale.ROOT) + '%');
            }
            if (!DataUtil.isNullOrEmpty(command.getIdNhaCungCap())) {
                from.append(" and n.id = :ch ");
                params.put("ch", command.getIdNhaCungCap());
            }
            if (!DataUtil.isNullOrEmpty(command.getStartDate())) {
                from.append(" and s.ngay_nhap >= to_date(:startDate,'dd/MM/yyyy') ");
                params.put("startDate", command.getStartDate());
            }
            if (!DataUtil.isNullOrEmpty(command.getEndDate())) {
                from.append(" and s.ngay_nhap <= to_date(:endDate,'dd/MM/yyyy') + 1 ");
                params.put("endDate", command.getEndDate());
            }
            queryStr.append(from);
            count.append(from);
            Query query = entityManager.createNativeQuery(queryStr.toString());
            Query countQuery = entityManager.createNativeQuery(count.toString());
            for (Map.Entry<String, Object> p : params.entrySet()) {
                query.setParameter(p.getKey(), p.getValue());
                countQuery.setParameter(p.getKey(), p.getValue());
            }
            query.setFirstResult((int) pageable.getOffset());
            query.setMaxResults(pageable.getPageSize());
            List<Object[]> objects = query.getResultList();
            Object o = countQuery.getSingleResult();
            List<NhapHangDTO> danhMucDTOS = DataUtil.convertLsObjectsToClass(Arrays.asList("id", "maNhapHang", "idNhaCungCap", "ngayNhap",
                            "tenNhaCungCap","idPhuongThuc", "tenPhuongThuc", "hopDongDinhKem", "duongDan", "idNhanVien", "tenNhanVien")
                    , objects, NhapHangDTO.class);
            for (NhapHangDTO danhMucDTO : danhMucDTOS) {
                NhapHangChiTietDTO nhapHangChiTietDTO = new NhapHangChiTietDTO();
                nhapHangChiTietDTO.setIdNhapHang(danhMucDTO.getId());
                List<NhapHangChiTietDTO> tm = nhapHangChiTietService.search(nhapHangChiTietDTO);
                danhMucDTO.setNhapHangChiTietDTOList(tm);
            }
            return new PageImpl<>(danhMucDTOS, pageable, Long.parseLong(o.toString()));
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional
    public MessageDTO save(NhapHangDTO nhapHangDTO) {
        NhapHang nhapHang = nhapHangMapper.toNhapHangENTITY(nhapHangDTO);
        nhapHang.setNhaCungCap(new NhaCungCap(nhapHangDTO.getIdNhaCungCap()));
        PhuongThucThanhToan phuongThucThanhToan = new PhuongThucThanhToan();
        phuongThucThanhToan.setId(nhapHangDTO.getIdPhuongThuc());
        nhapHang.setThanhToan(phuongThucThanhToan);
        NhanVien nhanVien = new NhanVien();
        nhanVien.setId(nhapHangDTO.getIdNhanVien());
        nhapHang.setNhanVien(nhanVien);
        NhapHang newNH = nhapHangRepo.save(nhapHang);
        List<NhapHangChiTietDTO> ls = nhapHangDTO.getNhapHangChiTietDTOList();
        for (NhapHangChiTietDTO l : ls) {
            NhapHangChiTiet nhapHangChiTiet = nhapHangChiTietMapper.toEntity(l);
            SanPham sanPham = new SanPham();
            sanPham.setId(l.getIdSanPham());
            nhapHangChiTiet.setSanPham(sanPham);
            nhapHangChiTiet.setNhapHang(newNH);
            nhapHangChiTietRepo.save(nhapHangChiTiet);
        }
        return new MessageDTO("Thêm mới thành công", 200l);
    }

    @Override
    public List<NhapHangDTO> getNhapHangMax(NhapHangDTO command) throws Exception {
        try {
            StringBuilder queryStr = new StringBuilder();
            StringBuilder from = new StringBuilder();
            Map<String, Object> params = new HashMap<>();
            queryStr.append("select n.id,n.ma_nhap_hang, " +
                    "n.id_nha_cung_cap, " +
                    "(select ten_nha_cung_cap from nha_cung_cap ncc where ncc.id = n.id_nha_cung_cap) tenncc, " +
                    "n.id_cua_hang, " +
                    "(select ten_cua_hang from cua_hang ncc where ncc.id = n.id_cua_hang) tench,  " +
                    " sum(nd.so_luong*nd.gia) , n.ngay_nhap , n.ngay_tao , n.nguoi_tao, id_thanh_toan,  " +
                    "   (select ten_phuong_thuc from phuong_thuc_thanh_toan ncc where ncc.id = n.id_thanh_toan) tt " +
                    " from nhap_hang n , nhap_hang_chi_tiet nd  " +
                    "where n.id = nd.id_nhap_hang ");

            if (!DataUtil.isNullOrEmpty(command.getIdNhaCungCap())) {
                from.append(" and n.id_nha_cung_cap = :ten ");
                params.put("ten", command.getIdNhaCungCap());
            }
            if (!DataUtil.isNullOrEmpty(command.getMaNhapHang())) {
                from.append(" and lower(n.ma_nhap_hang) like :ma ");
                params.put("ma", '%' + command.getMaNhapHang().toLowerCase(Locale.ROOT) + '%');
            }
            from.append("group by n.id " +
                    "order by sum(nd.so_luong*nd.gia) desc   ");
            queryStr.append(from);
            Query query = entityManager.createNativeQuery(queryStr.toString());
            for (Map.Entry<String, Object> p : params.entrySet()) {
                query.setParameter(p.getKey(), p.getValue());
            }
            List<Object[]> objects = query.getResultList();

            List<NhapHangDTO> danhMucDTOS = DataUtil.convertLsObjectsToClass(Arrays.asList("id", "maNhapHang", "idNhaCungCap", "tenNhaCungCap", "idCuaHang", "tenCuaHang",
                            "totalDT", "ngayNhap", "ngayTao", "nguoiTao", "idPhuongThuc", "tenPhuongThuc")
                    , objects, NhapHangDTO.class);

            return danhMucDTOS;
        } catch (Exception e) {
            throw e;
        }
    }


    private void setColumn(Sheet sheet, CellStyle headerCellStyle1, int row, int column, String content) {
        Row title = sheet.createRow(row);
        Cell cellTitle = title.createCell(column);
        cellTitle.setCellValue(content);
        cellTitle.setCellStyle(headerCellStyle1);
    }

    private void setColumnWithRow(Row title, Sheet sheet, CellStyle headerCellStyle1, int row, int column, String content) {
        Cell cellTitle = title.createCell(column);
        cellTitle.setCellValue(content);
        cellTitle.setCellStyle(headerCellStyle1);
    }

    @Override
    public Map<String, String> exportNhapMax(NhapHangDTO sanPhamDTO) throws Exception {
        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("HoaDonNhapHang");

            Font headerFontTitle = workbook.createFont();
            headerFontTitle.setBold(true);
            headerFontTitle.setFontHeightInPoints((short) 20);
            headerFontTitle.setColor(IndexedColors.BLACK.getIndex());
            CellStyle headerCellStyle1 = workbook.createCellStyle();
            headerCellStyle1.setFont(headerFontTitle);
            headerCellStyle1.setAlignment(HorizontalAlignment.CENTER);
            headerCellStyle1.setWrapText(true);
            Font headerFont = workbook.createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 13);
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

            Font headerFont2 = workbook.createFont();
            headerFont2.setItalic(true);
            headerFont2.setFontHeightInPoints((short) 12);
            headerFont2.setColor(IndexedColors.BLACK.getIndex());

            CellStyle headerCellStyle2 = workbook.createCellStyle();
            headerCellStyle2.setFont(headerFont2);
            headerCellStyle2.setAlignment(HorizontalAlignment.LEFT);
            headerCellStyle2.setWrapText(true);

            CellStyle headerCellStyle3 = workbook.createCellStyle();
            headerCellStyle3.setFont(headerFont2);
            headerCellStyle3.setAlignment(HorizontalAlignment.CENTER);
            headerCellStyle3.setWrapText(true);

            setColumn(sheet, headerCellStyle2, 0, 0, "Đơn vị: Công ty Cổ phần Nông Nghiệp và Thực Phẩm Lang Liêu");
            mergeCell(sheet, 0, 0, 0, 6);
            setColumn(sheet, headerCellStyle2, 1, 0, "Địa chỉ: Số nhà 2B, ngõ 389 Trương Định, Phường Tân Mai, Quận Hoàng Mai, Thành phố Hà Nội");
            mergeCell(sheet, 1, 1, 0, 6);
            setColumn(sheet, headerCellStyle2, 2, 0, "Mã số thuế:  0109736359");
            mergeCell(sheet, 2, 2, 0, 6);

            setColumn(sheet, headerCellStyle1, 4, 0, "BÁO CÁO NHẬP HÀNG");

            mergeCell(sheet, 4, 5, 0, 6);

            setColumn(sheet, headerCellStyle3, 6, 0, "Từ ngày: 01/"+ (LocalDateTime.now().getMonthValue() - 1) + "/"+
                    LocalDateTime.now().getYear() + " - Đến ngày: 01/" + LocalDateTime.now().getMonthValue() + "/"+ LocalDateTime.now().getYear());
            mergeCell(sheet, 6, 6, 0, 6);

            Row headerRow = sheet.createRow(8);
            for (int i = 0; i < 7; i++) {
                sheet.setColumnWidth(i, 8000);
                Cell cell = headerRow.createCell(i);
                if (i == 0) {
                    cell.setCellValue("Mã Nhập Hàng");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 1) {
                    cell.setCellValue("Tên Nhà Cung Cấp");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 2) {
                    cell.setCellValue("Tên Cửa Hàng");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 3) {
                    cell.setCellValue("Phương Thức Thanh Toán");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 4) {
                    cell.setCellValue("Tổng Tiền");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 5) {
                    cell.setCellValue("Ngày Nhập");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 6) {
                    cell.setCellValue("Người Nhập");
                    cell.setCellStyle(headerCellStyle);
                }
            }
            int rowNum = 9;
            List<NhapHangDTO> nhapHangDTOS = getNhapHangMax(sanPhamDTO);
            CellStyle cellStyle = workbook.createCellStyle();

            cellStyle.setBorderBottom(BorderStyle.THIN);
            cellStyle.setBorderLeft(BorderStyle.THIN);
            cellStyle.setBorderRight(BorderStyle.THIN);
            cellStyle.setBorderTop(BorderStyle.THIN);
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cellStyle.setWrapText(true);
            Double total = 0d;
            for (NhapHangDTO sanPhamDTO1 : nhapHangDTOS) {
                Row row = sheet.createRow(rowNum++);

                Cell cell = row.createCell(0);
                cell.setCellValue(sanPhamDTO1.getMaNhapHang());
                cell.setCellStyle(cellStyle);

                Cell cell1 = row.createCell(1);
                cell1.setCellValue(sanPhamDTO1.getTenNhaCungCap());
                cell1.setCellStyle(cellStyle);

                Cell cell2 = row.createCell(2);
                cell2.setCellValue(sanPhamDTO1.getTenCuaHang());
                cell2.setCellStyle(cellStyle);

                Cell cell23 = row.createCell(3);
                cell23.setCellValue(sanPhamDTO1.getTenPhuongThuc());
                cell23.setCellStyle(cellStyle);

                Cell cell3 = row.createCell(4);
                cell3.setCellValue(sanPhamDTO1.getTotalDT());
                cell3.setCellStyle(cellStyle);
                if (sanPhamDTO1.getTotalDT() != null)
                    total += sanPhamDTO1.getTotalDT();
                Cell cell4 = row.createCell(5);
                if (!DataUtil.isNullOrEmpty(sanPhamDTO1.getNgayNhap())) {
                    cell4.setCellValue(sanPhamDTO1.getNgayNhap().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                }
                cell4.setCellStyle(cellStyle);

                Cell cell41 = row.createCell(6);
                cell41.setCellValue(sanPhamDTO1.getNguoiTao());
                cell41.setCellStyle(cellStyle);
            }
            setColumn(sheet, headerCellStyle2, rowNum, 4, "Tổng:  " + renderDouble(total));
            mergeCell(sheet, rowNum, rowNum, 4, 5);
            rowNum++;
            setColumn(sheet, headerCellStyle3, rowNum + 1, 4, "Ngày …… Tháng …… Năm ……");
            mergeCell(sheet, rowNum + 1, rowNum + 1, 4, 5);
            rowNum++;
            rowNum++;
            Row sign = sheet.createRow(rowNum + 1);
            setColumnWithRow(sign, sheet, headerCellStyle3, rowNum + 1, 1, "Người lập phiếu");
            setColumnWithRow(sign, sheet, headerCellStyle3, rowNum + 1, 3, "Kế toán trưởng");
            setColumnWithRow(sign, sheet, headerCellStyle3, rowNum + 1, 5, "Giám đốc");
            rowNum++;
            Row sign2 = sheet.createRow(rowNum + 1);
            setColumnWithRow(sign2, sheet, headerCellStyle3, rowNum + 1, 1, "(Ký, họ tên)");
            setColumnWithRow(sign2, sheet, headerCellStyle3, rowNum + 1, 3, "(Ký, họ tên)");
            setColumnWithRow(sign2, sheet, headerCellStyle3, rowNum + 1, 5, "(Ký, họ tên)");


            String path = "./HoaDonNhapHang" + System.currentTimeMillis() + ".xlsx";
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

    private void mergeCell(Sheet sheet, int row, int lastRow, int column, int lastColumn) {
        CellRangeAddress cellMerge = new CellRangeAddress(row, lastRow, column, lastColumn);
        sheet.addMergedRegion(cellMerge);
    }

    String renderDouble(Double myvalue) {
        return String.format("%.2f", myvalue);
    }
}
