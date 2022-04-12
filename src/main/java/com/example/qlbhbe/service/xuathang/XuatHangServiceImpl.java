package com.example.qlbhbe.service.xuathang;

import com.example.qlbhbe.dto.*;
import com.example.qlbhbe.entity.*;
import com.example.qlbhbe.mapper.XuatHangChiTietMapper;
import com.example.qlbhbe.mapper.XuatHangMapper;
import com.example.qlbhbe.repo.xuathang.XuatHangRepo;
import com.example.qlbhbe.repo.xuathangchitiet.XuatHangChiTietRepo;
import com.example.qlbhbe.service.AbstractService;
import com.example.qlbhbe.service.xuathangchitiet.XuatHangChiTietService;
import com.example.qlbhbe.util.DataUtil;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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
public class XuatHangServiceImpl extends AbstractService<XuatHang, Long> implements XuatHangService {

    private final XuatHangRepo xuatHangRepo;

    @Autowired
    XuatHangMapper xuatHangMapper;

    @Autowired
    XuatHangChiTietRepo xuatHangChiTietRepo;

    @Autowired
    XuatHangChiTietMapper xuatHangChiTietMapper;

    @Autowired
    XuatHangChiTietService xuatHangChiTietService;


    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    public XuatHangServiceImpl(XuatHangRepo xuatHangRepo) {
        super(xuatHangRepo);
        this.xuatHangRepo = xuatHangRepo;
    }

    @Override
    @Transactional
    public MessageDTO update(long id, XuatHangDTO command) {
        Optional<XuatHang> opt = xuatHangRepo.findById(id);
        if (opt.isPresent()) {
            xuatHangChiTietRepo.deleteSanPham(id);
            xuatHangRepo.deleteSanPham(id);
        }
        command.setId(null);
        for (XuatHangChiTietDTO nhapHangChiTietDTO : command.getXuatHangChiTietDTOList()) {
            nhapHangChiTietDTO.setId(null);
            nhapHangChiTietDTO.setIdXuatHang(null);
        }
        save(command);
        return new MessageDTO("Cập nhật thành công", 200l);
    }

    @Override
    public Page<XuatHangDTO> search(XuatHangDTO command, Pageable pageable) throws Exception {
        try {
            StringBuilder queryStr = new StringBuilder();
            StringBuilder count = new StringBuilder();
            StringBuilder from = new StringBuilder();
            Map<String, Object> params = new HashMap<>();
            queryStr.append("select s.id              ," +
                    "    s.ma_hop_dong_ban_hang    ," +
                    "    s.id_khach_hang ," +
                    "    n.ten_khach_hang ," +
                        " s.ngay_xuat , " +
                    "   id_thanh_toan, (select ten_phuong_thuc from phuong_thuc_thanh_toan c where c.id =  s.id_thanh_toan )  ten_phuong_thuc," +
                    "   id_nhan_vien,  (select ten_nhan_vien from nhan_vien c where c.id =  s.id_nhan_vien ) tenNhanVien ,hop_dong_dinh_kem, duong_dan  ");

            count.append("select count(*) ");
            from.append(" from hop_dong_ban_hang s, khach_hang n  where s.id_khach_hang = n.id  ");
            if (!DataUtil.isNullOrEmpty(command.getTenKhachHang())) {
                from.append(" and lower(n.ten_khach_hang) like :ten ");
                params.put("ten", '%' + command.getTenKhachHang().toLowerCase(Locale.ROOT) + '%');
            }
            if (!DataUtil.isNullOrEmpty(command.getIdKhachHang())) {
                from.append(" and lower(s.id_khach_hang) = :idKH ");
                params.put("idKH",  command.getIdKhachHang());
            }

            if (!DataUtil.isNullOrEmpty(command.getMaXuatHang())) {
                from.append(" and lower(s.ma_xuat_hang) like :ma ");
                params.put("ma", '%' + command.getMaXuatHang().toLowerCase(Locale.ROOT) + '%');
            }
            if (!DataUtil.isNullOrEmpty(command.getStartDate())) {
                from.append(" and s.ngay_xuat >= to_date(:startDate,'dd/MM/yyyy') ");
                params.put("startDate", command.getStartDate());
            }
            if (!DataUtil.isNullOrEmpty(command.getEndDate())) {
                from.append(" and s.ngay_xuat <= to_date(:endDate,'dd/MM/yyyy') + 1 ");
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
            if (!DataUtil.isNullOrEmpty(command.getIsCount())) {
                query.setFirstResult((int) pageable.getOffset());
                query.setMaxResults(pageable.getPageSize());
            }
            List<Object[]> objects = query.getResultList();
            Object o = countQuery.getSingleResult();
            List<XuatHangDTO> danhMucDTOS = DataUtil.convertLsObjectsToClass(Arrays.asList("id", "maXuatHang", "idKhachHang","tenKhachHang",
                            "ngayXuat", "idPhuongThuc", "tenPhuongThuc", "idNhanVien", "tenNhanVien", "hopDongDinhKem","duongDan" )
                    , objects, XuatHangDTO.class);
            for (XuatHangDTO danhMucDTO : danhMucDTOS) {
                XuatHangChiTietDTO xuatHangChiTietDTO = new XuatHangChiTietDTO();
                xuatHangChiTietDTO.setIdXuatHang(danhMucDTO.getId());
                List<XuatHangChiTietDTO> tm = xuatHangChiTietService.search(xuatHangChiTietDTO);
                danhMucDTO.setXuatHangChiTietDTOList(tm);
            }
            for (XuatHangDTO danhMucDTO : danhMucDTOS) {
                Double total = 0d;
                for (XuatHangChiTietDTO nhapHangChiTietDTO : danhMucDTO.getXuatHangChiTietDTOList()) {
                    total += nhapHangChiTietDTO.getGia() * nhapHangChiTietDTO.getSoLuong();
                }
                danhMucDTO.setSoTien(total);
            }

            return new PageImpl<>(danhMucDTOS, pageable, Long.parseLong(o.toString()));
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional
    public MessageDTO save(XuatHangDTO xuatHangDTO) {
        XuatHang nhapHang = xuatHangMapper.toXuatHangEntity(xuatHangDTO);
        nhapHang.setKhachHang(new KhachHang(xuatHangDTO.getIdKhachHang()));
        PhuongThucThanhToan phuongThucThanhToan = new PhuongThucThanhToan();
        phuongThucThanhToan.setId(xuatHangDTO.getIdPhuongThuc());
        nhapHang.setThanhToan(phuongThucThanhToan);
        NhanVien nhanVien = new NhanVien();
        nhanVien.setId(xuatHangDTO.getIdNhanVien());
        nhapHang.setNhanVien(nhanVien);
        XuatHang newNH = xuatHangRepo.save(nhapHang);
        List<XuatHangChiTietDTO> ls = xuatHangDTO.getXuatHangChiTietDTOList();
        for (XuatHangChiTietDTO l : ls) {
            XuatHangChiTiet xuatHangChiTiet = xuatHangChiTietMapper.toXuatHangChiTietEntity(l);
            SanPham sanPham = new SanPham();
            sanPham.setId(l.getIdSanPham());
            xuatHangChiTiet.setSanPham(sanPham);
            xuatHangChiTiet.setXuatHang(newNH);
            xuatHangChiTietRepo.save(xuatHangChiTiet);
        }
        return new MessageDTO("Thêm mới thành công", 200l);
    }

    @Override
    public List<XuatHangDTO> searchXuatMax(XuatHangDTO command) throws Exception {
        try {
            StringBuilder queryStr = new StringBuilder();
            StringBuilder from = new StringBuilder();
            Map<String, Object> params = new HashMap<>();
            queryStr.append("select n.id , n.ma_xuat_hang, " +
                    "n.id_khach_hang, " +
                    "(select ten_khach_hang from khach_hang ncc where ncc.id = n.id_khach_hang) tenncc, " +
                    "n.id_cua_hang, " +
                    "(select ten_cua_hang from cua_hang ncc where ncc.id = n.id_cua_hang) tench,  " +
                    " sum(nd.so_luong*nd.gia) , n.ngay_xuat , n.ngay_tao , n.nguoi_tao, id_thanh_toan,  " +
                    "   (select ten_phuong_thuc from phuong_thuc_thanh_toan ncc where ncc.id = n.id_thanh_toan) tt " +
                    " from xuat_hang n , xuat_hang_chi_tiet nd  " +
                    "where n.id = nd.id_xuat_hang ");

            if (!DataUtil.isNullOrEmpty(command.getIdKhachHang())) {
                from.append(" and n.id_khach_hang = :ten ");
                params.put("ten", command.getIdKhachHang());
            }
            if (!DataUtil.isNullOrEmpty(command.getMaXuatHang())) {
                from.append(" and lower(n.ma_xuat_hang) like :ma ");
                params.put("ma", '%' + command.getMaXuatHang().toLowerCase(Locale.ROOT) + '%');
            }
            from.append("group by n.id " +
                    "order by sum(nd.so_luong*nd.gia) desc  ");
            queryStr.append(from);
            Query query = entityManager.createNativeQuery(queryStr.toString());
            for (Map.Entry<String, Object> p : params.entrySet()) {
                query.setParameter(p.getKey(), p.getValue());
            }
            List<Object[]> objects = query.getResultList();

            List<XuatHangDTO> danhMucDTOS = DataUtil.convertLsObjectsToClass(Arrays.asList("id", "maXuatHang", "idKhachHang", "tenKhachHang", "idCuaHang", "tenCuaHang",
                            "totalDT", "ngayXuat", "ngayTao", "nguoiTao", "idPhuongThuc", "tenPhuongThuc")
                    , objects, XuatHangDTO.class);

            return danhMucDTOS;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Map<String, String> exportXuatMax(XuatHangDTO command) throws Exception {
        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("HopDongXuatHang");

            Font headerFontTitle = workbook.createFont();
            headerFontTitle.setBold(true);
            headerFontTitle.setFontHeightInPoints((short) 20);
            headerFontTitle.setColor(IndexedColors.BLACK.getIndex());
            CellStyle headerCellStyle1 = workbook.createCellStyle();
            headerCellStyle1.setFont(headerFontTitle);
            headerCellStyle1.setAlignment(HorizontalAlignment.CENTER);
            headerCellStyle1.setWrapText(true);


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


            setColumn(sheet, headerCellStyle2, 0, 0, "Đơn vị: Công ty Nhân Hòa");
            mergeCell(sheet, 0, 0, 0, 6);
            setColumn(sheet, headerCellStyle2, 1, 0, "Địa chỉ: Tầng 4, Tòa nhà 97-99 Láng Hạ, Đống Đa, TP Hà Nội");
            mergeCell(sheet, 1, 1, 0, 6);
            setColumn(sheet, headerCellStyle2, 2, 0, "Mã số thuế:  0101289966");
            mergeCell(sheet, 2, 2, 0, 6);

            setColumn(sheet, headerCellStyle1, 4, 0, "BÁO CÁO HỢP ĐỒNG XUẤT HÀNG");
            mergeCell(sheet, 4, 5, 0, 6);
            setColumn(sheet, headerCellStyle3, 6, 0, "Từ ngày: 01/"+ (LocalDateTime.now().getMonthValue() - 1) + "/"+
                    LocalDateTime.now().getYear() + " - Đến ngày: 01/" + LocalDateTime.now().getMonthValue() + "/"+ LocalDateTime.now().getYear());
            mergeCell(sheet, 6, 6, 0, 6);

            Row headerRow = sheet.createRow(8);
            for (int i = 0; i < 7; i++) {
                sheet.setColumnWidth(i, 8000);
                Cell cell = headerRow.createCell(i);
                if (i == 0) {
                    cell.setCellValue("Mã Bán Hàng");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 1) {
                    cell.setCellValue("Tên Khách Hàng");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 2) {
                    cell.setCellValue("Phương Thức Thanh Toán");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 3) {
                    cell.setCellValue("Tổng Tiền");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 4) {
                    cell.setCellValue("Ngày Nhập");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 5) {
                    cell.setCellValue("Nhân viên");
                    cell.setCellStyle(headerCellStyle);
                }
            }
            int rowNum = 9;
            Double total = 0d;
            command.setIsCount(null);
            List<XuatHangDTO> nhapHangDTOS = search(command, PageRequest.of(0,1)).getContent();
            CellStyle cellStyle = workbook.createCellStyle();

            cellStyle.setBorderBottom(BorderStyle.THIN);
            cellStyle.setBorderLeft(BorderStyle.THIN);
            cellStyle.setBorderRight(BorderStyle.THIN);
            cellStyle.setBorderTop(BorderStyle.THIN);
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cellStyle.setWrapText(true);
            for (XuatHangDTO sanPhamDTO1 : nhapHangDTOS) {
                Row row = sheet.createRow(rowNum++);

                Cell cell = row.createCell(0);
                cell.setCellValue(sanPhamDTO1.getMaXuatHang());
                cell.setCellStyle(cellStyle);

                Cell cell1 = row.createCell(1);
                cell1.setCellValue(sanPhamDTO1.getTenKhachHang());
                cell1.setCellStyle(cellStyle);

                Cell cell23 = row.createCell(2);
                cell23.setCellValue(sanPhamDTO1.getTenPhuongThuc());
                cell23.setCellStyle(cellStyle);

                Cell cell3 = row.createCell(3);
                cell3.setCellValue(sanPhamDTO1.getSoTien());
                cell3.setCellStyle(cellStyle);
                if (sanPhamDTO1.getSoTien() != null)
                    total += sanPhamDTO1.getSoTien();

                Cell cell4 = row.createCell(4);
                if (!DataUtil.isNullOrEmpty(sanPhamDTO1.getNgayXuat())) {
                    cell4.setCellValue(sanPhamDTO1.getNgayXuat().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                }
                cell4.setCellStyle(cellStyle);

                Cell cell41 = row.createCell(4);
                cell41.setCellValue(sanPhamDTO1.getNguoiTao());
                cell41.setCellStyle(cellStyle);
            }
            setColumn(sheet, headerCellStyle2, rowNum, 3, "Tổng:  " + renderDouble(total));
            mergeCell(sheet, rowNum, rowNum, 3, 4);
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

            String path = "C:/CDTT/HopDongXuatHang" + System.currentTimeMillis() + ".xlsx";
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

    private void mergeCell(Sheet sheet, int row, int lastRow, int column, int lastColumn) {
        CellRangeAddress cellMerge = new CellRangeAddress(row, lastRow, column, lastColumn);
        sheet.addMergedRegion(cellMerge);
    }

    String renderDouble(Double myvalue) {
        return String.format("%.2f", myvalue);
    }
}
