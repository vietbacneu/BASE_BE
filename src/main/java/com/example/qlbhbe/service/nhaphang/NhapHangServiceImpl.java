package com.example.qlbhbe.service.nhaphang;

import com.example.qlbhbe.dto.MessageDTO;
import com.example.qlbhbe.dto.NhapHangChiTietDTO;
import com.example.qlbhbe.dto.NhapHangDTO;
import com.example.qlbhbe.dto.SanPhamDTO;
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
                    "    s.nguoi_tao       ," +
                    "    s.ngay_tao        ," +
                    "    s.nguoi_thay_doi  ," +
                    "    s.ngay_thay_doi   ," +
                    "    n.ten_nha_cung_cap, id_cua_hang, (select ten_cua_hang from cua_hang c where c.id =  s.id_cua_hang )  tencuahang ," +
                    "   id_thanh_toan, (select ten_phuong_thuc from phuong_thuc_thanh_toan c where c.id =  s.id_thanh_toan )  ten_phuong_thuc  ");
            count.append("select count(*) ");
            from.append(" from nhap_hang s, nha_cung_cap n where s.id_nha_cung_cap = n.id  ");
            if (!DataUtil.isNullOrEmpty(command.getTenNhaCungCap())) {
                from.append(" and lower(n.ten_nha_cung_cap) like :ten ");
                params.put("ten", '%' + command.getTenNhaCungCap().toLowerCase(Locale.ROOT) + '%');
            }
            if (!DataUtil.isNullOrEmpty(command.getMaNhapHang())) {
                from.append(" and lower(s.ma_nhap_hang) like :ma ");
                params.put("ma", '%' + command.getMaNhapHang().toLowerCase(Locale.ROOT) + '%');
            }
            if (!DataUtil.isNullOrEmpty(command.getIdCuaHang())) {
                from.append(" and s.id_cua_hang = :ch ");
                params.put("ch", command.getIdCuaHang());
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
            List<NhapHangDTO> danhMucDTOS = DataUtil.convertLsObjectsToClass(Arrays.asList("id", "maNhapHang", "idNhaCungCap", "ngayNhap", "nguoiTao", "ngayTao",
                            "nguoiThayDoi", "ngayThayDoi", "tenNhaCungCap", "idCuaHang", "tenCuaHang", "idPhuongThuc", "tenPhuongThuc")
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
        nhapHang.setCuaHang(new CuaHang(nhapHangDTO.getIdCuaHang()));
        PhuongThucThanhToan phuongThucThanhToan = new PhuongThucThanhToan();
        phuongThucThanhToan.setId(nhapHangDTO.getIdPhuongThuc());
        nhapHang.setThanhToan(phuongThucThanhToan);
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
                params.put("ten",command.getIdNhaCungCap());
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
                            "totalDT", "ngayNhap", "ngayTao", "nguoiTao","idPhuongThuc","tenPhuongThuc")
                    , objects, NhapHangDTO.class);

            return danhMucDTOS;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Map<String, String> exportNhapMax(NhapHangDTO sanPhamDTO) throws Exception {
        try {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("HoaDonNhapHang");

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
            cellTitle.setCellValue("BÁO CÁO HÓA ĐƠN NHẬP HÀNG");
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
            for (int i = 0; i < 7; i++) {
                sheet.setColumnWidth(i, 8500);
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
            int rowNum = 4;
            List<NhapHangDTO> nhapHangDTOS = getNhapHangMax(sanPhamDTO);
            CellStyle cellStyle = workbook.createCellStyle();

            cellStyle.setBorderBottom(BorderStyle.THIN);
            cellStyle.setBorderLeft(BorderStyle.THIN);
            cellStyle.setBorderRight(BorderStyle.THIN);
            cellStyle.setBorderTop(BorderStyle.THIN);
            cellStyle.setAlignment(HorizontalAlignment.CENTER);
            cellStyle.setWrapText(true);
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

                Cell cell4 = row.createCell(5);
                if (!DataUtil.isNullOrEmpty(sanPhamDTO1.getNgayNhap())) {
                    cell4.setCellValue(sanPhamDTO1.getNgayNhap().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                }
                cell4.setCellStyle(cellStyle);

                Cell cell41 = row.createCell(6);
                cell41.setCellValue(sanPhamDTO1.getNguoiTao());
                cell41.setCellStyle(cellStyle);
            }

            String path = "D:/HoaDonNhapHang" + System.currentTimeMillis() + ".xlsx";
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
