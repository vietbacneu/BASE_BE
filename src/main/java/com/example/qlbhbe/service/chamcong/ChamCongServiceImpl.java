package com.example.qlbhbe.service.chamcong;

import com.example.qlbhbe.dto.ChamCongDTO;
import com.example.qlbhbe.entity.ChamCong;
import com.example.qlbhbe.mapper.ChamCongMapper;
import com.example.qlbhbe.repo.chamcong.ChamCongRepo;
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
public class ChamCongServiceImpl extends AbstractService<ChamCong, Long> implements ChamCongService {

    private final ChamCongRepo chamCongRepo;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    public ChamCongServiceImpl(ChamCongRepo chamCongRepo) {
        super(chamCongRepo);
        this.chamCongRepo = chamCongRepo;
    }

    @Override
    public ChamCong update(long id, ChamCongDTO command) {
        Optional<ChamCong> opt = chamCongRepo.findById(id);
        if (opt.isPresent()) {
            ChamCong chamCong = opt.get();
            return ChamCongMapper.INSTANCE.update(command, chamCong);
        }
        return null;
    }

    @Override
    public Page<ChamCongDTO> search(ChamCongDTO command, Pageable pageable) throws Exception {
        try {
            StringBuilder queryStr = new StringBuilder();
            StringBuilder count = new StringBuilder();
            StringBuilder from = new StringBuilder();
            Map<String, Object> params = new HashMap<>();
            queryStr.append(" select n.id as nvId, n.ho , n.ten, " +
                    "(select ten_chuc_vu from chuc_vu c where c.id = n.id_chuc_vu) tenChucvu, " +
                    "(select ten from phong_ban c where c.id = n.id_phong_ban) tenPP, cc.so_gio_lam, cc.ngay_lam, cc.mieu_ta ");

            count.append("select count(*) ");
            from.append(" from nhan_vien n, cham_cong cc where cc.id_nhan_vien = n.id ");
            if (!DataUtil.isNullOrEmpty(command.getTenNhanVien())) {
                from.append("   and concat( lower(n.ho), ' ' ,lower(n.ten)  ) like :ten ");
                params.put("ten", '%' + command.getTenNhanVien().toLowerCase(Locale.ROOT) + '%');
            }
            if (!DataUtil.isNullOrEmpty(command.getNgayLam())) {
                from.append(" and cc.ngay_lam = ").append(command.getNgayLam().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
            }
            queryStr.append(from);
            count.append(" from (").append(queryStr).append(" ) as tmp");
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
            List<ChamCongDTO> danhMucDTOS = DataUtil.convertLsObjectsToClass(Arrays.asList("idNhanVien", "hoNhanVien", "tenNhanVien",
                            "tenChucVu", "tenPhongBan", "soGioLam", "ngayLam","mieuTa")
                    , objects, ChamCongDTO.class);

            return new PageImpl<>(danhMucDTOS, pageable, Long.parseLong(o.toString()));
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Page<ChamCongDTO> searchPhieuLuong(ChamCongDTO command, Pageable pageable) throws Exception {
        try {
            StringBuilder queryStr = new StringBuilder();
            StringBuilder count = new StringBuilder();
            Map<String, Object> params = new HashMap<>();
            queryStr.append(" SELECT  " +
                    "    n.id, " +
                    "    n.ho, " +
                    "    n.ten, " +
                    "    (SELECT  " +
                    "            ten_chuc_vu " +
                    "        FROM " +
                    "            chuc_vu c " +
                    "        WHERE " +
                    "            c.id = n.id_chuc_vu) as tenChucvu, " +
                    "  (SELECT  " +
                    "            he_so_luong " +
                    "        FROM " +
                    "            chuc_vu c " +
                    "        WHERE " +
                    "            c.id = n.id_chuc_vu) as hsl,       " +
                    "    (SELECT  " +
                    "            ten " +
                    "        FROM " +
                    "            phong_ban c " +
                    "        WHERE " +
                    "            c.id = n.id_phong_ban) as tenPP, " +
                    "   sum(cc.so_gio_lam) , " +
                    " (select sum(muc_dong) from nhan_vien_bao_hiem tmp where tmp.id_nhan_vien = n.id and month(tmp.ngay_dong) = month(cc.ngay_lam)  group by tmp.id_nhan_vien  ) as totalbh, " +
                    "    (select sum(muc_thuong) from khen_thuong kt,  nhan_vien_khen_thuong tmp where kt.id = tmp.id_khen_thuong and tmp.id_nhan_vien = n.id and month(tmp.ngay) = month(cc.ngay_lam)  group by tmp.id_nhan_vien) as totalkt, " +
                    " (select sum(muc_phat) from ky_luat kt, nhan_vien_ky_luat tmp where kt.id = tmp.id_ky_luat and tmp.id_nhan_vien = n.id and month(tmp.ngay) = month(cc.ngay_lam) group by tmp.id_nhan_vien ) as totalkl " +
                    "    from nhan_vien n left join cham_cong cc on (cc.id_nhan_vien = n.id ) where 1=1  ");

            count.append("select count(*) ");
            if (!DataUtil.isNullOrEmpty(command.getTenNhanVien())) {
                queryStr.append("   and concat( lower(n.ho), ' ' ,lower(n.ten)  ) like :ten ");
                params.put("ten", '%' + command.getTenNhanVien().toLowerCase(Locale.ROOT) + '%');
            }
            if (!DataUtil.isNullOrEmpty(command.getMonth())) {
                queryStr.append(" and month(cc.ngay_lam) = ").append(command.getMonth().substring(5));
                queryStr.append(" and year(cc.ngay_lam) = ").append(command.getMonth().substring(0,4));
            }
            if (!DataUtil.isNullOrEmpty(command.getIdPhongBan())) {
                queryStr.append(" and n.id_phong_ban = :pb ");
                params.put("pb", command.getIdPhongBan());
            }
            if (!DataUtil.isNullOrEmpty(command.getIdChucVu())) {
                queryStr.append(" and n.id_chuc_vu = :cv ");
                params.put("cv", command.getIdChucVu());
            }
            queryStr.append(" group by n.id ");
            count.append(" from ( ").append(queryStr).append(" ) as tmp");
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
            List<ChamCongDTO> danhMucDTOS = DataUtil.convertLsObjectsToClass(Arrays.asList("idNhanVien", "hoNhanVien", "tenNhanVien",
                            "tenChucVu", "heSoLuong", "tenPhongBan", "soGioLam", "totalBaoHiem", "totalKhenThuong", "totalKyLuat")
                    , objects, ChamCongDTO.class);

            if (!danhMucDTOS.isEmpty()) {
                for (ChamCongDTO danhMucDTO : danhMucDTOS) {
                    if (danhMucDTO.getSoGioLam() != null && danhMucDTO.getHeSoLuong() != null) {
                        danhMucDTO.setTotalLuongAfter(danhMucDTO.getTotalLuongAfter() + danhMucDTO.getTotalKhenThuong() - danhMucDTO.getTotalKyLuat() - danhMucDTO.getTotalBaoHiem());
                        danhMucDTO.setTotalLuongBefore(danhMucDTO.getHeSoLuong() * danhMucDTO.getSoGioLam());

                    }
                }
            }

            return new PageImpl<>(danhMucDTOS, pageable, Long.parseLong(o.toString()));
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Map<String, String> exportPhieuLuong(ChamCongDTO command) throws Exception {
        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("BaoCaoLuongNhanVien");

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
            cellTitle.setCellValue("BÁO CÁO PHIẾU LƯƠNG NHÂN VIÊN");
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
            for (int i = 0; i < 10; i++) {
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
                    cell.setCellValue("Phòng ban");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 3) {
                    cell.setCellValue("Hệ số lương");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 4) {
                    cell.setCellValue("Số giờ làm");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 5) {
                    cell.setCellValue("Bảo hiểm");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 6) {
                    cell.setCellValue("Khen Thưởng");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 7) {
                    cell.setCellValue("Kỷ luật");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 8) {
                    cell.setCellValue("Lương cơ bản");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 9) {
                    cell.setCellValue("Lương thực nhận");
                    cell.setCellStyle(headerCellStyle);
                }
            }
            int rowNum = 4;
            List<ChamCongDTO> nhapHangDTOS = searchPhieuLuong(command, PageRequest.of(0, 1000)).getContent();
            CellStyle cellStyle = workbook.createCellStyle();

            cellStyle.setBorderBottom(BorderStyle.THIN);
            cellStyle.setBorderLeft(BorderStyle.THIN);
            cellStyle.setBorderRight(BorderStyle.THIN);
            cellStyle.setBorderTop(BorderStyle.THIN);
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cellStyle.setWrapText(true);
            for (ChamCongDTO sanPhamDTO1 : nhapHangDTOS) {
                Row row = sheet.createRow(rowNum++);

                Cell cell = row.createCell(0);
                cell.setCellValue(sanPhamDTO1.getHoNhanVien() + " " + sanPhamDTO1.getTenNhanVien());
                cell.setCellStyle(cellStyle);

                Cell cell1 = row.createCell(1);
                cell1.setCellValue(sanPhamDTO1.getTenChucVu());
                cell1.setCellStyle(cellStyle);

                Cell cell2 = row.createCell(2);
                cell2.setCellValue(sanPhamDTO1.getHeSoLuong());
                cell2.setCellStyle(cellStyle);

                Cell cell23 = row.createCell(3);
                cell23.setCellValue(sanPhamDTO1.getTenPhongBan());
                cell23.setCellStyle(cellStyle);

                Cell cell3 = row.createCell(4);
                cell3.setCellValue(sanPhamDTO1.getSoGioLam());
                cell3.setCellStyle(cellStyle);

                Cell cell4 = row.createCell(5);
                cell4.setCellValue(sanPhamDTO1.getTotalBaoHiem());
                cell4.setCellStyle(cellStyle);

                Cell cell41 = row.createCell(6);
                cell41.setCellValue(sanPhamDTO1.getTotalKhenThuong());
                cell41.setCellStyle(cellStyle);

                Cell cell7 = row.createCell(7);
                cell7.setCellValue(sanPhamDTO1.getTotalKyLuat());
                cell7.setCellStyle(cellStyle);

                Cell cell8 = row.createCell(8);
                cell8.setCellValue(sanPhamDTO1.getTotalLuongBefore());
                cell8.setCellStyle(cellStyle);

                Cell cell9 = row.createCell(9);
                cell9.setCellValue(sanPhamDTO1.getTotalLuongAfter());
                cell9.setCellStyle(cellStyle);
            }

            String path = "D:/BaoCaoLuongNhanVien" + System.currentTimeMillis() + ".xlsx";
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
