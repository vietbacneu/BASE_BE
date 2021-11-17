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
import java.time.format.DateTimeFormatter;
import java.util.*;


@Service
@Transactional
public class NhanVienServiceImpl extends AbstractService<NhanVien, Long> implements NhanVienService {

    private final NhanVienRepo nhanVienRepo;

    @PersistenceContext
    EntityManager entityManager;

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
            queryStr.append(" select n.id, n.ho , n.ten, " +
                    " (select ten_chuc_vu from chuc_vu c where c.id = n.id_chuc_vu) tenChucvu, " +
                    " (select ten from phong_ban c where c.id = n.id_phong_ban) tenPP, " +
                    " n.sdt, n.email, n.gioi_tinh, n.dia_chi, n.ngay_sinh, n.trinh_do, n.quoc_tich, n.ngay_bat_dau");

            count.append("select count(*) ");
            from.append(" from nhan_vien n  " +
                    "   where 1=1 ");
            if (!DataUtil.isNullOrEmpty(command.getTen())) {
                from.append(" and ( lower(n.ten) like :ten or lower(n.ho) like :ten ) ");
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
            List<NhanVienDTO> danhMucDTOS = DataUtil.convertLsObjectsToClass(Arrays.asList("idNhanVien", " hoNhanVien", "tenNhanVien",
                            "tenChucVu", "tenPhongBan", "sdt", "email", "gioiTinh", "diaChi", "ngaySinh", "trinhDo", "quocTich", "ngayBatDau")
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
            headerFontTitle.setColor(IndexedColors.RED.getIndex());
            CellStyle headerCellStyle1 = workbook.createCellStyle();
            headerCellStyle1.setFont(headerFontTitle);
            headerCellStyle1.setBorderBottom(BorderStyle.THIN);
            headerCellStyle1.setBorderTop(BorderStyle.THIN);
            headerCellStyle1.setAlignment(HorizontalAlignment.CENTER);
            headerCellStyle1.setWrapText(true);
            Row title = sheet.createRow(0);
            Cell cellTitle = title.createCell(1);
            cellTitle.setCellValue("BÁO CÁO NHÂN VIÊN");
            cellTitle.setCellStyle(headerCellStyle1);
            CellRangeAddress cellMerge = new CellRangeAddress(0, 1, 1, 6);
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
            for (int i = 0; i < 11; i++) {
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
            }
            int rowNum = 4;
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
                cell1.setCellValue(sanPhamDTO1.getChucVuTenChucVu());
                cell1.setCellStyle(cellStyle);

                Cell cell2 = row.createCell(2);
                cell2.setCellValue(sanPhamDTO1.getPhongBanTen());
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

            }

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
}
