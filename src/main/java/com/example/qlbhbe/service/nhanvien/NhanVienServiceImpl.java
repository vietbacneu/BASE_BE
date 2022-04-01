package com.example.qlbhbe.service.nhanvien;

import com.example.qlbhbe.dto.NhanVienDTO;
import com.example.qlbhbe.entity.ChucVu;
import com.example.qlbhbe.entity.NhanVien;
import com.example.qlbhbe.entity.PhongBan;
import com.example.qlbhbe.mapper.NhanVienMapper;
import com.example.qlbhbe.repo.nhanvien.NhanVienRepo;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


@Service
@Transactional
public class NhanVienServiceImpl extends AbstractService<NhanVien, Long> implements NhanVienService {

    private final NhanVienRepo nhanVienRepo;

    @PersistenceContext
    EntityManager entityManager;

    public static void main(String[] args) {
        System.out.printf(Base64.getEncoder().encodeToString("hello nguyễn việt bắc".getBytes()));
    }

    @Autowired
    public NhanVienServiceImpl(NhanVienRepo nhanVienRepo) {
        super(nhanVienRepo);
        this.nhanVienRepo = nhanVienRepo;
    }

    @Override
    public NhanVien update(long id, NhanVienDTO command) {
        Optional<NhanVien> opt = nhanVienRepo.findById(id);
        if (opt.isPresent()) {
            NhanVien nhanVien = opt.get();
            ChucVu chucVu = new ChucVu();
            chucVu.setId(command.getChucVuId());
            nhanVien.setChucVu(chucVu);
            PhongBan phongBan = new PhongBan();
            phongBan.setId(command.getPhongBanId());
            nhanVien.setPhongBan(phongBan);
            nhanVien.setSdt(command.getSdt());
            nhanVien.setNgayBatDau(command.getNgayBatDau());
            return NhanVienMapper.INSTANCE.update(command, nhanVien);
        }
        return null;
    }

    @Override
    public Page<NhanVienDTO> search(NhanVienDTO command, Pageable pageable) throws Exception {
        try {
            StringBuilder queryStr = new StringBuilder();
            StringBuilder count = new StringBuilder();
            StringBuilder from = new StringBuilder();
            Map<String, Object> params = new HashMap<>();
            queryStr.append(" select n.id as nvId, n.ho , n.ten, " +
                    " (select ten_chuc_vu from chuc_vu c where c.id = n.id_chuc_vu) tenChucvu, " +
                    " (select ten from phong_ban c where c.id = n.id_phong_ban) tenPP, " +
                    " n.sdt, n.email, n.gioi_tinh, n.dia_chi, n.ngay_sinh, n.trinh_do, n.quoc_tich, n.ngay_bat_dau, " +
                    "  (select he_so_luong from chuc_vu c where c.id = n.id_chuc_vu) hsl, n.id_chuc_vu , n.id_phong_ban ");

            count.append("select count(*) ");
            from.append(" from nhan_vien n  " +
                    "   where 1=1 ");
            if (!DataUtil.isNullOrEmpty(command.getTen())) {
                from.append("   and lower(concat( n.ho, ' ' ,n.ten) ) like :ten ");
                params.put("ten", '%' + command.getTen().toLowerCase(Locale.ROOT) + '%');
            }
            if (!DataUtil.isNullOrEmpty(command.getChucVuId())) {
                from.append(" and n.id_chuc_vu = :cv ");
                params.put("cv", command.getChucVuId());
            }
            if (!DataUtil.isNullOrEmpty(command.getPhongBanId())) {
                from.append(" and n.id_phong_ban = :pb ");
                params.put("pb", command.getPhongBanId());
            }
            queryStr.append(from);
            count.append(from);
            Query query = entityManager.createNativeQuery(queryStr.toString());
            Query countQuery = entityManager.createNativeQuery(count.toString());

            for (Map.Entry<String, Object> p : params.entrySet()) {
                query.setParameter(p.getKey(), p.getValue());
                countQuery.setParameter(p.getKey(), p.getValue());
            }
            if (!DataUtil.isNullOrEmpty(command.getIsCount()) && command.getIsCount() == 1) {
                query.setFirstResult((int) pageable.getOffset());
                query.setMaxResults(pageable.getPageSize());
            }
            List<Object[]> objects = query.getResultList();
            Object o = countQuery.getSingleResult();
            List<NhanVienDTO> danhMucDTOS = DataUtil.convertLsObjectsToClass(Arrays.asList("id", "ho", "ten",
                            "tenChucVu", "tenPhongBan", "sdt", "email", "gioiTinh", "diaChi", "ngaySinh", "trinhDo", "quocTich", "ngayBatDau", "heSoLuong", "chucVuId", "phongBanId")
                    , objects, NhanVienDTO.class);

            return new PageImpl<>(danhMucDTOS, pageable, Long.parseLong(o.toString()));
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Map<String, String> exportNhanVien(NhanVienDTO command) throws Exception {
        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("BaoCaoNhanVien");

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


            setColumn(sheet, headerCellStyle2, 0, 0, "Đơn vị: Trường THPT chuyên Hoàng Văn Thụ - Sở Giáo Dục và Đào Tạo tỉnh Hòa Bình ");
            mergeCell(sheet, 0, 0, 0, 6);
            setColumn(sheet, headerCellStyle2, 1, 0, "Địa chỉ: Phường Thịnh Lang – Thành phố Hòa Bình – Tỉnh Hòa Bình ");
            mergeCell(sheet, 1, 1, 0, 6);
            setColumn(sheet, headerCellStyle2, 2, 0, "Email: th.hov@hoabinhedu.vn");
            mergeCell(sheet, 2, 2, 0, 6);

            setColumn(sheet, headerCellStyle1, 4, 0, "BÁO CÁO NHÂN VIÊN");
            mergeCell(sheet, 4, 5, 0, 11);

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
            Row headerRow = sheet.createRow(7);
            for (int i = 0; i < 12; i++) {
                sheet.setColumnWidth(i, 8500);
                Cell cell = headerRow.createCell(i);
                if (i == 0) {
                    cell.setCellValue("Họ và Tên");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 1) {
                    cell.setCellValue("Chức vụ");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 2) {
                    cell.setCellValue("Phòng Ban");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 3) {
                    cell.setCellValue("Số Điện Thoại");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 4) {
                    cell.setCellValue("Email");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 5) {
                    cell.setCellValue("Giới Tính");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 6) {
                    cell.setCellValue("Địa chỉ");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 7) {
                    cell.setCellValue("Ngày Sinh");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 8) {
                    cell.setCellValue("Trình độ");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 9) {
                    cell.setCellValue("Quốc tịch");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 10) {
                    cell.setCellValue("Ngày bắt đầu làm");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 11) {
                    cell.setCellValue("Hệ số lương");
                    cell.setCellStyle(headerCellStyle);
                }
            }
            int rowNum = 8;
            List<NhanVienDTO> nhapHangDTOS = search(command, PageRequest.of(0, 1000)).getContent();
            CellStyle cellStyle = workbook.createCellStyle();

            cellStyle.setBorderBottom(BorderStyle.THIN);
            cellStyle.setBorderLeft(BorderStyle.THIN);
            cellStyle.setBorderRight(BorderStyle.THIN);
            cellStyle.setBorderTop(BorderStyle.THIN);
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cellStyle.setWrapText(true);
            for (NhanVienDTO sanPhamDTO1 : nhapHangDTOS) {
                Row row = sheet.createRow(rowNum++);

                Cell cell = row.createCell(0);
                cell.setCellValue(sanPhamDTO1.getHo() + " " + sanPhamDTO1.getTen());
                cell.setCellStyle(cellStyle);

                Cell cell1 = row.createCell(1);
                cell1.setCellValue(sanPhamDTO1.getTenChucVu());
                cell1.setCellStyle(cellStyle);

                Cell cell2 = row.createCell(2);
                cell2.setCellValue(sanPhamDTO1.getTenPhongBan());
                cell2.setCellStyle(cellStyle);

                Cell cell23 = row.createCell(3);
                cell23.setCellValue(sanPhamDTO1.getSdt());
                cell23.setCellStyle(cellStyle);

                Cell cell3 = row.createCell(4);
                cell3.setCellValue(sanPhamDTO1.getEmail());
                cell3.setCellStyle(cellStyle);

                Cell cell4 = row.createCell(5);
                cell4.setCellValue(sanPhamDTO1.getGioiTinh());
                cell4.setCellStyle(cellStyle);

                Cell cell41 = row.createCell(6);
                cell41.setCellValue(sanPhamDTO1.getDiaChi());
                cell41.setCellStyle(cellStyle);

                Cell cell7 = row.createCell(7);
                if (!DataUtil.isNullOrEmpty(sanPhamDTO1.getNgaySinh())) {
                    cell7.setCellValue(sanPhamDTO1.getNgaySinh().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                }
                cell7.setCellStyle(cellStyle);

                Cell cell8 = row.createCell(8);
                cell8.setCellValue(sanPhamDTO1.getTrinhDo());
                cell8.setCellStyle(cellStyle);

                Cell cell9 = row.createCell(9);
                cell9.setCellValue(sanPhamDTO1.getQuocTich());
                cell9.setCellStyle(cellStyle);

                Cell cell10 = row.createCell(10);
                if (!DataUtil.isNullOrEmpty(sanPhamDTO1.getNgayBatDau())) {
                    cell10.setCellValue(sanPhamDTO1.getNgayBatDau().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                }
                cell10.setCellStyle(cellStyle);

                Cell cell11 = row.createCell(11);
                cell11.setCellValue(sanPhamDTO1.getHeSoLuong());
                cell11.setCellStyle(cellStyle);
            }
            setColumn(sheet, headerCellStyle3, rowNum + 1, 9, "Ngày …… Tháng …… Năm ……");
            mergeCell(sheet, rowNum + 1, rowNum + 1, 9, 10);
            rowNum++;
            rowNum++;
            Row sign = sheet.createRow(rowNum + 1);
            setColumnWithRow(sign, sheet, headerCellStyle3, rowNum + 1, 1, "Người lập phiếu");
            setColumnWithRow(sign, sheet, headerCellStyle3, rowNum + 1, 6, "Kế toán trưởng");
            setColumnWithRow(sign, sheet, headerCellStyle3, rowNum + 1, 10, "Giám đốc");
            rowNum++;
            Row sign2 = sheet.createRow(rowNum + 1);
            setColumnWithRow(sign2, sheet, headerCellStyle3, rowNum + 1, 1, "(Ký, họ tên)");
            setColumnWithRow(sign2, sheet, headerCellStyle3, rowNum + 1, 6, "(Ký, họ tên)");
            setColumnWithRow(sign2, sheet, headerCellStyle3, rowNum + 1, 10, "(Ký, họ tên)");
            String path = "D:/BaoCaoNhanVien" + System.currentTimeMillis() + ".xlsx";
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
    public Page<NhanVienDTO> danhGia(NhanVienDTO command, Pageable pageable) throws Exception {
        try {
            StringBuilder queryStr = new StringBuilder();
            StringBuilder count = new StringBuilder();
            StringBuilder from = new StringBuilder();
            Map<String, Object> params = new HashMap<>();
            count.append("select count(*) ");
            queryStr.append(" SELECT n.id,\n" +
                    "       n.ho,\n" +
                    "       n.ten,\n" +
                    "       (SELECT ten_chuc_vu FROM chuc_vu c WHERE c.id = n.id_chuc_vu) tenChucvu,\n" +
                    "       (SELECT ten FROM phong_ban c WHERE c.id = n.id_phong_ban)     tenPP,\n" +
                    "       0      as                                                     tenLoi,\n" +
                    "       0      as                                                     mucPhat,\n" +
                    "       kt.ten as                                                     tenThuong,\n" +
                    "       sum(nvkt.so_tien),\n" +
                    "       'KT'   as                                                     type\n" +
                    "from nhan_vien n,\n" +
                    "     nhan_vien_khen_thuong_ky_luat nvkt,\n" +
                    "     khen_thuong_ky_luat kt\n" +
                    "where nvkt.id_nhan_vien = n.id\n" +
                    "  and nvkt.id_danh_gia = kt.id and kt.loai = 'khenthuong'  ");
            if (!DataUtil.isNullOrEmpty(command.getTen())) {
                queryStr.append("   and lower(concat( n.ho, ' ' ,n.ten) ) like :ten ");
                params.put("ten", '%' + command.getTen().toLowerCase(Locale.ROOT) + '%');
            }
            if (!DataUtil.isNullOrEmpty(command.getChucVuId())) {
                queryStr.append(" and n.id_chuc_vu = :cv ");
                params.put("cv", command.getChucVuId());
            }
            if (!DataUtil.isNullOrEmpty(command.getPhongBanId())) {
                queryStr.append(" and n.id_phong_ban = :pb ");
                params.put("pb", command.getPhongBanId());
            }
            if (!DataUtil.isNullOrEmpty(command.getMonth())) {
                queryStr.append(" and month(nvkt.ngay) = :month ");
                params.put("month", command.getMonth().substring(5,7));
                queryStr.append(" and year(nvkt.ngay) = :year ");
                params.put("year", command.getMonth().substring(0,4));
            }
            queryStr.append(" group by n.id, kt.id " +
                    " union all " +
                    " SELECT n.id,\n" +
                    "       n.ho,\n" +
                    "       n.ten,\n" +
                    "       (SELECT ten_chuc_vu FROM chuc_vu c WHERE c.id = n.id_chuc_vu) tenChucvu,\n" +
                    "       (SELECT ten FROM phong_ban c WHERE c.id = n.id_phong_ban)     tenPP,\n" +
                    "       kl.ten as                                                 tenLoi,\n" +
                    "       sum(nvkl.so_tien),\n" +
                    "       0          as                                                 tenThuong,\n" +
                    "       0          as                                                 mucThuong,\n" +
                    "       'KL'       as                                                 type\n" +
                    "from nhan_vien n\n" +
                    "         left join nhan_vien_khen_thuong_ky_luat nvkl on (nvkl.id_nhan_vien = n.id)\n" +
                    "         left join khen_thuong_ky_luat kl on (nvkl.id_danh_gia = kl.id)\n" +
                    "where 1 = 1 and kl.loai = 'kyluat' ");
            if (!DataUtil.isNullOrEmpty(command.getTen())) {
                queryStr.append("  and lower(concat( n.ho, ' ' ,n.ten) ) like :ten1 ");
                params.put("ten1", '%' + command.getTen().toLowerCase(Locale.ROOT) + '%');
            }
            if (!DataUtil.isNullOrEmpty(command.getChucVuId())) {
                queryStr.append(" and n.id_chuc_vu = :cv1 ");
                params.put("cv1", command.getChucVuId());
            }
            if (!DataUtil.isNullOrEmpty(command.getPhongBanId())) {
                queryStr.append(" and n.id_phong_ban = :pb1 ");
                params.put("pb1", command.getPhongBanId());
            }
            if (!DataUtil.isNullOrEmpty(command.getMonth())) {
                queryStr.append(" and month(nvkl.ngay) = :month1 ");
                params.put("month1", command.getMonth().substring(5,7));
                queryStr.append(" and year(nvkl.ngay) = :year1 ");
                params.put("year1", command.getMonth().substring(0,4));
            }
            queryStr.append(" group by n.id , kl.id");

            queryStr.append(from);
            count.append(" from (").append(queryStr).append(" ) tmp");
            Query query = entityManager.createNativeQuery(queryStr.toString());
            Query countQuery = entityManager.createNativeQuery(count.toString());

            for (Map.Entry<String, Object> p : params.entrySet()) {
                query.setParameter(p.getKey(), p.getValue());
                countQuery.setParameter(p.getKey(), p.getValue());
            }
//            if (!DataUtil.isNullOrEmpty(command.getIsCount()) && command.getIsCount() == 1) {
//                query.setFirstResult((int) pageable.getOffset());
//                query.setMaxResults(pageable.getPageSize());
//            }
            List<Object[]> objects = query.getResultList();
            Object o = countQuery.getSingleResult();
            List<NhanVienDTO> danhMucDTOS = DataUtil.convertLsObjectsToClass(Arrays.asList("id", "ho", "ten",
                            "tenChucVu", "tenPhongBan", "tenLoi", "mucPhat", "tenThuong", "mucThuong", "type")
                    , objects, NhanVienDTO.class);
            List<NhanVienDTO> result = new ArrayList<>();
            for (NhanVienDTO danhMucDTO : danhMucDTOS) {
                if (danhMucDTO.getTenThuong() != null) {
                    if (danhMucDTO.getType().equals("KT")) {
                        NhanVienDTO nhanVienDTO = danhMucDTO;
                        nhanVienDTO.setTenThuongPhat(danhMucDTO.getTenThuong());
                        nhanVienDTO.setValueThuongPhat(danhMucDTO.getMucThuong());
                        result.add(nhanVienDTO);
                    }
                }
                if (danhMucDTO.getType().equals("KL")) {
                    if (danhMucDTO.getTenLoi() != null) {
                        NhanVienDTO nhanVienDTO = danhMucDTO;
                        nhanVienDTO.setTenThuongPhat(danhMucDTO.getTenLoi());
                        nhanVienDTO.setValueThuongPhat(danhMucDTO.getMucPhat() * -1);
                        result.add(nhanVienDTO);
                    }
                }
            }
            return new PageImpl<>(result, pageable, Long.parseLong(o.toString()));
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Map<String, String> exportDanhGia(NhanVienDTO command) throws Exception {
        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("BaoCaoDanhGiaNhanVien");

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


            setColumn(sheet, headerCellStyle2, 0, 0, "Đơn vị: Trường THPT chuyên Hoàng Văn Thụ - Sở Giáo Dục và Đào Tạo tỉnh Hòa Bình ");
            mergeCell(sheet, 0, 0, 0, 6);
            setColumn(sheet, headerCellStyle2, 1, 0, "Địa chỉ: Phường Thịnh Lang – Thành phố Hòa Bình – Tỉnh Hòa Bình ");
            mergeCell(sheet, 1, 1, 0, 6);
            setColumn(sheet, headerCellStyle2, 2, 0, "Email: th.hov@hoabinhedu.vn");
            mergeCell(sheet, 2, 2, 0, 6);

            setColumn(sheet, headerCellStyle1, 4, 0, "BÁO CÁO ĐÁNH GIÁ NHÂN VIÊN");
            mergeCell(sheet, 4, 5, 0, 4);

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
            Row headerRow = sheet.createRow(7);
            for (int i = 0; i < 5; i++) {
                sheet.setColumnWidth(i, 8500);
                Cell cell = headerRow.createCell(i);

                if (i == 0) {
                    cell.setCellValue("Họ và Tên");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 1) {
                    cell.setCellValue("Chức vụ");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 2) {
                    cell.setCellValue("Phòng Ban");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 3) {
                    cell.setCellValue("Tên Lỗi/Thưởng");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 4) {
                    cell.setCellValue("Mức Phạt/Thưởng");
                    cell.setCellStyle(headerCellStyle);
                }
            }
            int rowNum = 8;
            List<NhanVienDTO> nhapHangDTOS = danhGia(command, PageRequest.of(0, 1000)).getContent();
            CellStyle cellStyle = workbook.createCellStyle();

            cellStyle.setBorderBottom(BorderStyle.THIN);
            cellStyle.setBorderLeft(BorderStyle.THIN);
            cellStyle.setBorderRight(BorderStyle.THIN);
            cellStyle.setBorderTop(BorderStyle.THIN);
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cellStyle.setWrapText(true);
            for (NhanVienDTO sanPhamDTO1 : nhapHangDTOS) {
                Row row = sheet.createRow(rowNum++);

                Cell cell = row.createCell(0);
                cell.setCellValue(sanPhamDTO1.getHo() + " " + sanPhamDTO1.getTen());
                cell.setCellStyle(cellStyle);

                Cell cell1 = row.createCell(1);
                cell1.setCellValue(sanPhamDTO1.getTenChucVu());
                cell1.setCellStyle(cellStyle);

                Cell cell2 = row.createCell(2);
                cell2.setCellValue(sanPhamDTO1.getTenPhongBan());
                cell2.setCellStyle(cellStyle);

                Cell cell23 = row.createCell(3);
                cell23.setCellValue(sanPhamDTO1.getTenThuongPhat());
                cell23.setCellStyle(cellStyle);

                Cell cell3 = row.createCell(4);
                if (!DataUtil.isNullOrEmpty(sanPhamDTO1.getValueThuongPhat()))
                    cell3.setCellValue(sanPhamDTO1.getValueThuongPhat());
                cell3.setCellStyle(cellStyle);

            }
            setColumn(sheet, headerCellStyle3, rowNum + 1, 3, "Ngày …… Tháng …… Năm ……");
            mergeCell(sheet, rowNum + 1, rowNum + 1, 3, 4);
            rowNum++;
            rowNum++;
            Row sign = sheet.createRow(rowNum + 1);
            setColumnWithRow(sign, sheet, headerCellStyle3, rowNum + 1, 0, "Người lập phiếu");
            setColumnWithRow(sign, sheet, headerCellStyle3, rowNum + 1, 2, "Kế toán trưởng");
            setColumnWithRow(sign, sheet, headerCellStyle3, rowNum + 1, 4, "Giám đốc");
            rowNum++;
            Row sign2 = sheet.createRow(rowNum + 1);
            setColumnWithRow(sign2, sheet, headerCellStyle3, rowNum + 1, 0, "(Ký, họ tên)");
            setColumnWithRow(sign2, sheet, headerCellStyle3, rowNum + 1, 2, "(Ký, họ tên)");
            setColumnWithRow(sign2, sheet, headerCellStyle3, rowNum + 1, 4, "(Ký, họ tên)");
            String path = "D:/BaoCaoDanhGiaNhanVien" + System.currentTimeMillis() + ".xlsx";
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
