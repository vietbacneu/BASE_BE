package com.example.qlbhbe.service.congno;

import com.example.qlbhbe.controller.response.CreatedIdResponse;
import com.example.qlbhbe.dto.CongNoChiTietDTO;
import com.example.qlbhbe.dto.CongNoDTO;
import com.example.qlbhbe.entity.CongNo;
import com.example.qlbhbe.entity.CongNoChiTiet;
import com.example.qlbhbe.entity.NhanVien;
import com.example.qlbhbe.mapper.CongNoChiTietMapper;
import com.example.qlbhbe.mapper.CongNoMapper;
import com.example.qlbhbe.repo.congno.CongNoRepo;
import com.example.qlbhbe.repo.congnochitiet.CongNoChiTietRepo;
import com.example.qlbhbe.service.AbstractService;
import com.example.qlbhbe.util.DataUtil;
import com.example.qlbhbe.util.Utils;
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
import java.util.*;


@Service
@Transactional
public class CongNoServiceImpl extends AbstractService<CongNo, Long> implements CongNoService {

    private final CongNoRepo congNoRepo;

    @Autowired
    CongNoChiTietMapper congNoChiTietMapper;

    @Autowired
    CongNoChiTietRepo congNoChiTietRepo;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    public CongNoServiceImpl(CongNoRepo congNoRepo) {
        super(congNoRepo);
        this.congNoRepo = congNoRepo;
    }

    @Override
    public CongNo update(long id, CongNoDTO command) {
        Optional<CongNo> opt = congNoRepo.findById(id);
        if (opt.isPresent()) {
            congNoChiTietRepo.deleteCongNo(id);
            congNoRepo.deleteCongNo(id);
        }
        command.setId(null);
        for (CongNoChiTietDTO nhapHangChiTietDTO : command.getCongNoChiTietDTOS()) {
            nhapHangChiTietDTO.setId(null);
        }
        create(command);
        return new CongNo();
    }

    @Override
    public void delete(long id) {
        congNoChiTietRepo.deleteCongNo(id);
        congNoRepo.deleteCongNo(id);
    }

    @Override
    public CreatedIdResponse create(CongNoDTO command) {
        CongNo congNo = CongNoMapper.INSTANCE.create(command);
        NhanVien nhanVien = new NhanVien();
        nhanVien.setId(command.getIdNhanVien());
        congNo.setNhanVien(nhanVien);
        congNoRepo.save(congNo);
        List<CongNoChiTietDTO> congNoChiTietList = command.getCongNoChiTietDTOS();
        for (CongNoChiTietDTO congNoChiTietDTO : congNoChiTietList) {
            CongNoChiTiet congNoChiTiet = congNoChiTietMapper.toCongNoChiTiet(congNoChiTietDTO);
            congNoChiTiet.setIdCongNo(congNo);
            if (DataUtil.isNullOrEmpty(congNoChiTiet.getTrangThai())){
                congNoChiTiet.setTrangThai("chuathanhtoan");
            }else {
                congNoChiTiet.setTrangThai(congNoChiTietDTO.getTrangThai());
            }
            congNoChiTietRepo.save(congNoChiTiet);
        }
        return new CreatedIdResponse(congNo.getId());
    }

    @Override
    public Page<CongNoDTO> search(CongNoDTO command, Pageable pageable) throws Exception {
        try {
            StringBuilder queryStr = new StringBuilder();
            StringBuilder count = new StringBuilder();
            StringBuilder from = new StringBuilder();
            Map<String, Object> params = new HashMap<>();
            queryStr.append("select distinct cn.id,\n" +
                    "       cn.ma_cong_no,\n" +
                    "       cn.loai_hop_dong,\n" +
                    "       case\n" +
                    "           when cn.loai_hop_dong = 'nhaphang' then 'Nhập hàng'\n" +
                    "           when cn.loai_hop_dong = 'xuathang' then 'Xuất hàng' end                                   loaiHopDongName,\n" +
                    "       cn.id_hop_dong,\n" +
                    "       case\n" +
                    "           when cn.loai_hop_dong = 'nhaphang' then (select hop_dong_nhap_hang.ma_nhap_hang\n" +
                    "                                                    from hop_dong_nhap_hang\n" +
                    "                                                    where hop_dong_nhap_hang.id = cn.id_hop_dong)\n" +
                    "           when cn.loai_hop_dong = 'xuathang' then (select hop_dong_ban_hang.ma_hop_dong_ban_hang\n" +
                    "                                                    from hop_dong_ban_hang\n" +
                    "                                                    where hop_dong_ban_hang.id = cn.id_hop_dong) end maHopDong,\n" +
                    "       cn.trang_thai_thanh_toan,\n" +
                    "       case\n" +
                    "           when cn.trang_thai_thanh_toan = 'chuathanhtoan' then 'Chưa thanh toán'\n" +
                    "           when cn.trang_thai_thanh_toan = 'dathanhtoan' then 'Đã thanh toán' end                    trangThaiName,\n" +
                    "       cn.id_nhan_vien,  \n" +
                    "       cn.so_tien\n, (select ten_nhan_vien from nhan_vien where id = cn.id_nhan_vien) ten_nhan_vien " +
                    " from cong_no cn,\n" +
                    "     cong_no_chi_tiet cnd\n" +
                    " where cn.id = cnd.id_cong_no   ");


            count.append("");
            if (!DataUtil.isNullOrEmpty(command.getLoaiHopDong())) {
                from.append(" and lower(cn.loai_hop_dong) like :ten ");
                params.put("ten", '%' + command.getLoaiHopDong().toLowerCase(Locale.ROOT) + '%');
            }
            if (!DataUtil.isNullOrEmpty(command.getMaCongNo())) {
                from.append(" and lower(cn.ma_cong_no) like :ma ");
                params.put("ma", '%' + command.getMaCongNo().toLowerCase(Locale.ROOT) + '%');
            }
//            from.append("group by id ");
            queryStr.append(from);
            Query query = entityManager.createNativeQuery(queryStr.toString());
            Query countQuery = entityManager.createNativeQuery("select count(*) from (" + queryStr.toString() + ") tmp");
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
            List<CongNoDTO> danhMucDTOS = DataUtil.convertLsObjectsToClass(Arrays.asList("id", "maCongNo", "loaiHopDong", "loaiHopDongName",
                            "idHopDong", "maHopDong", "trangThaiThanhToan", "trangThaiName", "idNhanVien", "soTien", "tenNhanVien")
                    , objects, CongNoDTO.class);

            for (CongNoDTO danhMucDTO : danhMucDTOS) {
                List<CongNoChiTietDTO> congNoChiTietDTO = new ArrayList<>();
                List<CongNoChiTiet> tmp = congNoChiTietRepo.searchByCongNoId(danhMucDTO.getId());
                for (CongNoChiTiet congNoChiTiet : tmp) {
                    congNoChiTietDTO.add(congNoChiTietMapper.toCongNoChiTietDTO(congNoChiTiet));
                }
                danhMucDTO.setCongNoChiTietDTOS(congNoChiTietDTO);
            }
            return new PageImpl<>(danhMucDTOS, pageable, Long.parseLong(o.toString()));
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Page<CongNoDTO> searchForExport(CongNoDTO command, Pageable pageable) throws Exception {
        try {
            StringBuilder queryStr = new StringBuilder();
            StringBuilder count = new StringBuilder();
            StringBuilder from = new StringBuilder();
            Map<String, Object> params = new HashMap<>();
            queryStr.append("select\n" +
                    "    cn.id,\n" +
                    "    cn.ma_cong_no,\n" +
                    "    cn.loai_hop_dong,\n" +
                    "    case\n" +
                    "        when cn.loai_hop_dong = 'nhaphang' then 'Nhập hàng'\n" +
                    "        when cn.loai_hop_dong = 'xuathang' then 'Xuất hàng'\n" +
                    "        end                                   loaiHopDongName,\n" +
                    "    cn.id_hop_dong,\n" +
                    "    case\n" +
                    "        when cn.loai_hop_dong = 'nhaphang' then (select\n" +
                    "                                                     hop_dong_nhap_hang.ma_nhap_hang\n" +
                    "                                                 from\n" +
                    "                                                     hop_dong_nhap_hang\n" +
                    "                                                 where\n" +
                    "                                                         hop_dong_nhap_hang.id = cn.id_hop_dong)\n" +
                    "        when cn.loai_hop_dong = 'xuathang' then (select\n" +
                    "                                                     hop_dong_ban_hang.ma_hop_dong_ban_hang\n" +
                    "                                                 from\n" +
                    "                                                     hop_dong_ban_hang\n" +
                    "                                                 where\n" +
                    "                                                         hop_dong_ban_hang.id = cn.id_hop_dong)\n" +
                    "        end maHopDong,\n" +
                    "    cn.trang_thai_thanh_toan,\n" +
                    "    case\n" +
                    "        when cn.so_tien - ifnull( (select sum(so_tien_thanh_toan) from cong_no_chi_tiet where cong_no_chi_tiet.id_cong_no = cn.id and cong_no_chi_tiet.trang_thai = 'dathanhtoan' ) ,0) > 0\n" +
                    "            and                 cn.so_tien - ifnull((select sum(so_tien_thanh_toan) from cong_no_chi_tiet where cong_no_chi_tiet.id_cong_no = cn.id and cong_no_chi_tiet.trang_thai = 'dathanhtoan' ),\n" +
                    "                                                    0) < cn.so_tien then 'Đang thanh toán'\n" +
                    "        when cn.so_tien - ifnull((select sum(so_tien_thanh_toan) from cong_no_chi_tiet where cong_no_chi_tiet.id_cong_no = cn.id and cong_no_chi_tiet.trang_thai = 'dathanhtoan' ),\n" +
                    "                                 0) = cn.so_tien then 'Đang thanh toán'\n" +
                    "        when cn.so_tien - ifnull((select sum(so_tien_thanh_toan) from cong_no_chi_tiet where cong_no_chi_tiet.id_cong_no = cn.id and cong_no_chi_tiet.trang_thai = 'dathanhtoan' ),\n" +
                    "                                 0) <= 0 then 'Đã thanh toán'\n" +
                    "        end    trangThaiName,\n" +
                    "    cn.id_nhan_vien,\n" +
                    "    cn.so_tien,\n" +
                    "           ifnull((select sum(so_tien_thanh_toan)\n" +
                    "               from cong_no_chi_tiet\n" +
                    "               where cong_no_chi_tiet.id_cong_no = cn.id\n" +
                    "                 and cong_no_chi_tiet.trang_thai = 'dathanhtoan'), 0) as soTienDaThanhToan,\n" +
                    "    cn.so_tien - ifnull((select sum(so_tien_thanh_toan) from cong_no_chi_tiet where cong_no_chi_tiet.id_cong_no = cn.id and cong_no_chi_tiet.trang_thai = 'dathanhtoan' ),\n" +
                    "                        0) as                                        soTienConLai ,\n" +
                    "    (select\n" +
                    "         ten_nhan_vien\n" +
                    "     from\n" +
                    "         nhan_vien\n" +
                    "     where\n" +
                    "             id = id_nhan_vien) as tenNhanVien\n" +
                    "from\n" +
                    "    cong_no cn,\n" +
                    "    cong_no_chi_tiet cnd " +
                    " where cn.id = cnd.id_cong_no  ");


            count.append("");
            if (!DataUtil.isNullOrEmpty(command.getLoaiHopDong())) {
                queryStr.append(" and lower(cn.loai_hop_dong) like :ten ");
                params.put("ten", '%' + command.getLoaiHopDong().toLowerCase(Locale.ROOT) + '%');
            }
            if (!DataUtil.isNullOrEmpty(command.getMaCongNo())) {
                queryStr.append(" and lower(cn.ma_cong_no) like :ma ");
                params.put("ma", '%' + command.getMaCongNo().toLowerCase(Locale.ROOT) + '%');
            }
            queryStr.append("group by id ");
            Query query = entityManager.createNativeQuery(queryStr.toString());
            Query countQuery = entityManager.createNativeQuery("select count(*) from (" + queryStr.toString() + ") tmp");
            for (Map.Entry<String, Object> p : params.entrySet()) {
                query.setParameter(p.getKey(), p.getValue());
                countQuery.setParameter(p.getKey(), p.getValue());
            }
            List<Object[]> objects = query.getResultList();
            Object o = countQuery.getSingleResult();

            List<CongNoDTO> danhMucDTOS = DataUtil.convertLsObjectsToClass(Arrays.asList("id", "maCongNo", "loaiHopDong", "loaiHopDongName",
                            "idHopDong", "maHopDong", "trangThaiThanhToan", "trangThaiName", "idNhanVien", "soTien", "soTienDaThanhToan", "soTienConLai", "tenNhanVien")
                    , objects, CongNoDTO.class);

            for (CongNoDTO danhMucDTO : danhMucDTOS) {
                if (danhMucDTO.getId()==null){
                    danhMucDTOS = new ArrayList<>();
                }
                List<CongNoChiTietDTO> congNoChiTietDTO = new ArrayList<>();
                List<CongNoChiTiet> tmp = congNoChiTietRepo.searchByCongNoId(danhMucDTO.getId());
                for (CongNoChiTiet congNoChiTiet : tmp) {
                    congNoChiTietDTO.add(congNoChiTietMapper.toCongNoChiTietDTO(congNoChiTiet));
                }
                danhMucDTO.setCongNoChiTietDTOS(congNoChiTietDTO);
            }
            return new PageImpl<>(danhMucDTOS, pageable, Long.parseLong(o.toString()));
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Map<String, String> export(CongNoDTO command) throws Exception {
        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("BaoCaoCongNo");

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

            setColumn(sheet, headerCellStyle2, 0, 0, "Đơn vị: Công ty Nhân Hòa");
            mergeCell(sheet, 0, 0, 0, 6);
            setColumn(sheet, headerCellStyle2, 1, 0, "Địa chỉ: Tầng 4, Tòa nhà 97-99 Láng Hạ, Đống Đa, TP Hà Nội");
            mergeCell(sheet, 1, 1, 0, 6);
            setColumn(sheet, headerCellStyle2, 2, 0, "Mã số thuế:  0101289966");
            mergeCell(sheet, 2, 2, 0, 6);
            
            setColumn(sheet, headerCellStyle1, 4, 0, "BÁO CÁO CÔNG NỢ");

            mergeCell(sheet, 4, 5, 0, 6);

            setColumn(sheet, headerCellStyle3, 6, 0, "Từ ngày: 01/" + (LocalDateTime.now().getMonthValue() - 1) + "/" +
                    LocalDateTime.now().getYear() + " - Đến ngày: 01/" + LocalDateTime.now().getMonthValue() + "/" + LocalDateTime.now().getYear());
            mergeCell(sheet, 6, 6, 0, 6);

            Row headerRow = sheet.createRow(8);
            for (int i = 0; i < 7; i++) {
                sheet.setColumnWidth(i, 8000);
                Cell cell = headerRow.createCell(i);
                if (i == 0) {
                    cell.setCellValue("Mã công nợ");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 1) {
                    cell.setCellValue("Mã hợp đồng");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 2) {
                    cell.setCellValue("Trạng thái thanh toán");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 3) {
                    cell.setCellValue("Số tiền");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 4) {
                    cell.setCellValue("Số tiền đã trả");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 5) {
                    cell.setCellValue("Số tiền còn lại");
                    cell.setCellStyle(headerCellStyle);
                }
                if (i == 6) {
                    cell.setCellValue("Nhân viên");
                    cell.setCellStyle(headerCellStyle);
                }
            }
            int rowNum = 9;
            List<CongNoDTO> nhapHangDTOS = searchForExport(command, Utils.getDefaultSortPageable(PageRequest.of(0,122))).getContent();
            CellStyle cellStyle = workbook.createCellStyle();

            cellStyle.setBorderBottom(BorderStyle.THIN);
            cellStyle.setBorderLeft(BorderStyle.THIN);
            cellStyle.setBorderRight(BorderStyle.THIN);
            cellStyle.setBorderTop(BorderStyle.THIN);
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cellStyle.setWrapText(true);
            Double total = 0d;
            for (CongNoDTO sanPhamDTO1 : nhapHangDTOS) {
                Row row = sheet.createRow(rowNum++);

                Cell cell = row.createCell(0);
                cell.setCellValue(sanPhamDTO1.getMaCongNo());
                cell.setCellStyle(cellStyle);

                Cell cell1 = row.createCell(1);
                cell1.setCellValue(sanPhamDTO1.getMaHopDong());
                cell1.setCellStyle(cellStyle);

                Cell cell2 = row.createCell(2);
                cell2.setCellValue(sanPhamDTO1.getTrangThaiName());
                cell2.setCellStyle(cellStyle);

                Cell cell23 = row.createCell(3);
                cell23.setCellValue(DataUtil.safeToDouble(sanPhamDTO1.getSoTien()));
                cell23.setCellStyle(cellStyle);

                Cell cell3 = row.createCell(4);
                cell3.setCellValue(DataUtil.safeToDouble(sanPhamDTO1.getSoTienDaThanhToan()));
                cell3.setCellStyle(cellStyle);
                ;
                Cell cell4 = row.createCell(5);
                cell4.setCellValue(DataUtil.safeToDouble(sanPhamDTO1.getSoTienConLai()));
                cell4.setCellStyle(cellStyle);

                Cell cell41 = row.createCell(6);
                cell41.setCellValue(sanPhamDTO1.getTenNhanVien());
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


            String path = "C:/CDTT/HoaDonNhapHang" + System.currentTimeMillis() + ".xlsx";
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
