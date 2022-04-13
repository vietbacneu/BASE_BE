package com.example.qlbhbe.service.banhang;

import com.example.qlbhbe.dto.BanHangChiTietDTO;
import com.example.qlbhbe.dto.BanHangDTO;
import com.example.qlbhbe.dto.MessageDTO;
import com.example.qlbhbe.entity.*;
import com.example.qlbhbe.mapper.BanHangChiTietMapper;
import com.example.qlbhbe.mapper.BanHangMapper;
import com.example.qlbhbe.repo.banhang.BanHangRepo;
import com.example.qlbhbe.repo.banhangchitiet.BanHangChiTietRepo;
import com.example.qlbhbe.repo.thucdon.ThucDonRepo;
import com.example.qlbhbe.service.AbstractService;
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
import java.time.format.DateTimeFormatter;
import java.util.*;


@Service
@Transactional
public class BanHangServiceImpl extends AbstractService<BanHang, Long> implements BanHangService {

    private final BanHangRepo banHangRepo;

    @Autowired
    private BanHangChiTietMapper banHangChiTietMapper;

    @Autowired
    private BanHangChiTietRepo banHangChiTietRepo;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    ThucDonRepo thucDonRepo;

    @Autowired
    public BanHangServiceImpl(BanHangRepo banHangRepo) {
        super(banHangRepo);
        this.banHangRepo = banHangRepo;
    }

    @Override
    public BanHang update(long id, BanHangDTO command) {
        Optional<BanHang> opt = banHangRepo.findById(id);
        if (opt.isPresent()) {
            banHangChiTietRepo.deleteSanPham(id);
            banHangRepo.deleteSanPham(id);
        }
        command.setId(null);
        for (BanHangChiTietDTO nhapHangChiTietDTO : command.getBanHangChiTietDTOS()) {
            nhapHangChiTietDTO.setId(null);
            nhapHangChiTietDTO.setIdBanHang(null);
        }
        save(command);
        return new BanHang();
    }

    @Override
    public MessageDTO save(BanHangDTO command) {
        BanHang banHang = BanHangMapper.INSTANCE.create(command);
        KhachHang khachHang = new KhachHang();
        khachHang.setId(command.getIdKhachHang());
        banHang.setThanhToan(new PhuongThucThanhToan(command.getIdPhuongThuc()));
        banHang.setKhachHang(khachHang);
        banHangRepo.save(banHang);
        List<BanHangChiTietDTO> ls = command.getBanHangChiTietDTOS();
        for (BanHangChiTietDTO l : ls) {
            BanHangChiTiet nhapHangChiTiet = banHangChiTietMapper.toBanHangEntity(l);
            ThucDon sanPham = new ThucDon();
            sanPham.setId(l.getIdThucDon());
            nhapHangChiTiet.setThucDon(sanPham);
            nhapHangChiTiet.setBanHang(banHang);
            banHangChiTietRepo.save(nhapHangChiTiet);
        }
        return new MessageDTO("Thêm mới thành công", 200l);
    }

    @Override
    public Page<BanHangDTO> search(BanHangDTO command, Pageable pageable) throws Exception {
        try {
            StringBuilder queryStr = new StringBuilder();
            StringBuilder count = new StringBuilder();
            StringBuilder from = new StringBuilder();
            Map<String, Object> params = new HashMap<>();
            queryStr.append("select s.id              ," +
                    "    s.ma_ban_hang    ," +
                    "    s.id_khach_hang ," +
                    "    s.ngay_ban       ," +
                    "    s.nguoi_tao       ," +
                    "    s.ngay_tao        ," +
                    "    s.nguoi_thay_doi  ," +
                    "    s.ngay_thay_doi   ," +
                    "    n.ten_khach_hang, s.id_thanh_toan, (select t.ten_phuong_thuc from phuong_thuc_thanh_toan t where t.id = s.id_thanh_toan) ," +
                    "    s.trang_thai, case when s.trang_thai = 'chuathanhtoan' then 'Chưa thanh toán' else 'Đã thanh toán' end trangThaiName    ");
            count.append("select count(*) ");
            from.append(" from ban_hang s, khach_hang n where s.id_khach_hang = n.id  ");
            if (!DataUtil.isNullOrEmpty(command.getKhachHangTen())) {
                from.append(" and lower(n.ten_khach_hang) like :ten ");
                params.put("ten", '%' + command.getKhachHangTen().toLowerCase(Locale.ROOT) + '%');
            }
            if (!DataUtil.isNullOrEmpty(command.getIdKhachHang())) {
                from.append(" and lower(n.id) = :idKH ");
                params.put("idKH", command.getIdKhachHang());
            }
            if (!DataUtil.isNullOrEmpty(command.getMaBanHang())) {
                from.append(" and lower(s.ma_ban_hang) like :ma ");
                params.put("ma", '%' + command.getMaBanHang().toLowerCase(Locale.ROOT) + '%');
            }
            if (!DataUtil.isNullOrEmpty(command.getTrangThai())) {
                from.append(" and lower(s.trang_thai) like :tt ");
                params.put("tt", '%' + command.getTrangThai().toLowerCase(Locale.ROOT) + '%');
            }
            if (!DataUtil.isNullOrEmpty(command.getStartDate())) {
                from.append(" and s.ngay_ban >= :startDate");
                params.put("startDate", command.getStartDate());
            }
            if (!DataUtil.isNullOrEmpty(command.getEndDate())) {
                from.append(" and s.ngay_ban <= date_add(:endDate , interval 1 day) ");
                params.put("endDate", command.getEndDate());
            }

            queryStr.append(from).append("order by ngay_ban desc ");
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
            List<BanHangDTO> danhMucDTOS = DataUtil.convertLsObjectsToClass(Arrays.asList("id", "maBanHang", "idKhachHang", "ngayBan", "nguoiTao", "ngayTao",
                            "nguoiThayDoi", "ngayThayDoi", "khachHangTen", "idPhuongThuc", "tenPhuongThuc", "trangThai", "trangThaiName")
                    , objects, BanHangDTO.class);
            Double total = 0d;
            for (BanHangDTO danhMucDTO : danhMucDTOS) {
                List<BanHangChiTiet> banHangChiTiets = banHangChiTietRepo.getList(danhMucDTO.getId());
                List<BanHangChiTietDTO> banHangChiTietDTOS = new ArrayList<>();
                for (BanHangChiTiet banHangChiTiet : banHangChiTiets) {
                    BanHangChiTietDTO banHangChiTietDTO = new BanHangChiTietDTO();
                    banHangChiTietDTO.setIdThucDon(banHangChiTiet.getThucDon().getId());
                    Optional<ThucDon> thucDon = thucDonRepo.findById(banHangChiTiet.getThucDon().getId());
                    if (thucDon.isPresent()) {
                        banHangChiTietDTO.setTenThucDon(thucDon.get().getTenThucDon());
                    }
                    banHangChiTietDTO.setIdBanHang(banHangChiTiet.getBanHang().getId());
                    banHangChiTietDTO.setId(banHangChiTiet.getId());
                    banHangChiTietDTO.setSoLuong(banHangChiTiet.getSoLuong());
                    banHangChiTietDTO.setGia(banHangChiTiet.getGia());
                    banHangChiTietDTO.setMieuTa(banHangChiTiet.getMieuTa());
                    banHangChiTietDTO.setTongTien(banHangChiTiet.getSoLuong() * banHangChiTiet.getGia());
                    banHangChiTietDTOS.add(banHangChiTietDTO);
                    total += banHangChiTiet.getSoLuong() * banHangChiTiet.getGia();
                }
                danhMucDTO.setBanHangChiTietDTOS(banHangChiTietDTOS);
                danhMucDTO.setSoTien(total);
            }

            return new PageImpl<>(danhMucDTOS, pageable, Long.parseLong(o.toString()));
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Map<String, String> export(BanHangDTO command) throws Exception {
        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("DoanhThuBanHang");

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


            setColumn(sheet, headerCellStyle2, 0, 0, "Đơn vị: Công ty Cổ phần Nông Nghiệp và Thực Phẩm Lang Liêu");
            mergeCell(sheet, 0, 0, 0, 6);
            setColumn(sheet, headerCellStyle2, 1, 0, "Địa chỉ: Số nhà 2B, ngõ 389 Trương Định, Phường Tân Mai, Quận Hoàng Mai, Thành phố Hà Nội");
            mergeCell(sheet, 1, 1, 0, 6);
            setColumn(sheet, headerCellStyle2, 2, 0, "Mã số thuế:  0109736359");
            mergeCell(sheet, 2, 2, 0, 6);

            setColumn(sheet, headerCellStyle1, 4, 0, "BÁO CÁO DOANH THU BÁN HÀNG");
            mergeCell(sheet, 4, 5, 0, 5);


            if (!DataUtil.isNullOrEmpty(command.getStartDate()) && !DataUtil.isNullOrEmpty(command.getEndDate())) {
                setColumn(sheet, headerCellStyle3, 6, 0, "Từ ngày: " + command.getStartDate().getDayOfMonth() + "/" + command.getStartDate().getMonthValue() + "/" +
                        command.getStartDate().getYear() + " - Đến ngày: "
                        + command.getEndDate().getDayOfMonth() + "/" + command.getEndDate().getMonthValue() + "/" + command.getEndDate().getYear());
                mergeCell(sheet, 6, 6, 0, 5);
            }

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
                    cell.setCellValue("Ngày Bán");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 5) {
                    cell.setCellValue("Trạng thái");
                    cell.setCellStyle(headerCellStyle);
                }

            }
            int rowNum = 9;
            Double total = 0d;
            command.setIsCount(null);
            List<BanHangDTO> nhapHangDTOS = search(command, PageRequest.of(0, 1)).getContent();
            CellStyle cellStyle = workbook.createCellStyle();

            cellStyle.setBorderBottom(BorderStyle.THIN);
            cellStyle.setBorderLeft(BorderStyle.THIN);
            cellStyle.setBorderRight(BorderStyle.THIN);
            cellStyle.setBorderTop(BorderStyle.THIN);
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cellStyle.setWrapText(true);
            for (BanHangDTO command1 : nhapHangDTOS) {
                Row row = sheet.createRow(rowNum++);

                Cell cell = row.createCell(0);
                cell.setCellValue(command1.getMaBanHang());
                cell.setCellStyle(cellStyle);

                Cell cell1 = row.createCell(1);
                cell1.setCellValue(command1.getKhachHangTen());
                cell1.setCellStyle(cellStyle);

                Cell cell23 = row.createCell(2);
                cell23.setCellValue(command1.getTenPhuongThuc());
                cell23.setCellStyle(cellStyle);

                Cell cell3 = row.createCell(3);
                cell3.setCellValue(command1.getSoTien());
                cell3.setCellStyle(cellStyle);

                if (command1.getSoTien() != null)
                    total += command1.getSoTien();

                Cell cell4 = row.createCell(4);
                if (!DataUtil.isNullOrEmpty(command1.getNgayBan())) {
                    cell4.setCellValue(command1.getNgayBan().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                }
                cell4.setCellStyle(cellStyle);

                Cell cell41 = row.createCell(5);
                cell41.setCellValue(command1.getTrangThaiName());
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

            String path = "D:/DoanhThuBanHang" + System.currentTimeMillis() + ".xlsx";
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
