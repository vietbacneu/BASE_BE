package com.example.qlbhbe.service.xuathang;

import com.example.qlbhbe.dto.MessageDTO;
import com.example.qlbhbe.dto.NhapHangDTO;
import com.example.qlbhbe.dto.XuatHangChiTietDTO;
import com.example.qlbhbe.dto.XuatHangDTO;
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
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.FileOutputStream;
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
                    "    s.ma_xuat_hang    ," +
                    "    s.id_khach_hang ," +
                    "    s.id_cua_hang       ," +
                    "    s.nguoi_tao       ," +
                    "    s.ngay_tao        ," +
                    "    s.nguoi_thay_doi  ," +
                    "    s.ngay_thay_doi   ," +
                    "    n.ten_khach_hang ," +
                    " (select ten_cua_hang from cua_hang c where c.id =  s.id_cua_hang )  tencuahang , " +
                    " s.ngay_xuat , " +
                    "   id_thanh_toan, (select ten_phuong_thuc from phuong_thuc_thanh_toan c where c.id =  s.id_thanh_toan )  ten_phuong_thuc  ");

            count.append("select count(*) ");
            from.append(" from xuat_hang s, khach_hang n  where s.id_khach_hang = n.id  ");
            if (!DataUtil.isNullOrEmpty(command.getTenKhachHang())) {
                from.append(" and lower(n.ten_khach_hang) like :ten ");
                params.put("ten", '%' + command.getTenKhachHang().toLowerCase(Locale.ROOT) + '%');
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
            query.setFirstResult((int) pageable.getOffset());
            query.setMaxResults(pageable.getPageSize());
            List<Object[]> objects = query.getResultList();
            Object o = countQuery.getSingleResult();
            List<XuatHangDTO> danhMucDTOS = DataUtil.convertLsObjectsToClass(Arrays.asList("id", "maXuatHang", "idKhachHang", "idCuaHang", "nguoiTao", "ngayTao",
                            "nguoiThayDoi", "ngayThayDoi", "tenKhachHang", "tenCuaHang", "ngayXuat", "idPhuongThuc", "tenPhuongThuc")
                    , objects, XuatHangDTO.class);
            for (XuatHangDTO danhMucDTO : danhMucDTOS) {
                XuatHangChiTietDTO xuatHangChiTietDTO = new XuatHangChiTietDTO();
                xuatHangChiTietDTO.setIdXuatHang(danhMucDTO.getId());
                List<XuatHangChiTietDTO> tm = xuatHangChiTietService.search(xuatHangChiTietDTO);
                danhMucDTO.setXuatHangChiTietDTOList(tm);
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
        nhapHang.setCuaHang(new CuaHang(xuatHangDTO.getIdCuaHang()));
        PhuongThucThanhToan phuongThucThanhToan = new PhuongThucThanhToan();
        phuongThucThanhToan.setId(xuatHangDTO.getIdPhuongThuc());
        nhapHang.setThanhToan(phuongThucThanhToan);
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
            queryStr.append("select n.id,n.ma_xuat_hang, " +
                    "n.id_khach_hang, " +
                    "(select ten_khach_hang from khach_hang ncc where ncc.id = n.id_khach_hang) tenncc, " +
                    "n.id_cua_hang, " +
                    "(select ten_cua_hang from cua_hang ncc where ncc.id = n.id_cua_hang) tench,  " +
                    " sum(nd.so_luong*nd.gia) , n.ngay_xuat , n.ngay_tao , n.nguoi_tao, id_thanh_toan,  " +
                    "   (select ten_phuong_thuc from phuong_thuc_thanh_toan ncc where ncc.id = n.id_thanh_toan) tt " +
                    " from xuat_hang n , xuat_hang_chi_tiet nd  " +
                    "where n.id = nd.id_xuat_hang ");

            if (!DataUtil.isNullOrEmpty(command.getTenKhachHang())) {
                from.append(" and lower(n.ten_khach_hang) like :ten ");
                params.put("ten", '%' + command.getTenKhachHang().toLowerCase(Locale.ROOT) + '%');
            }
            if (!DataUtil.isNullOrEmpty(command.getMaXuatHang())) {
                from.append(" and lower(s.ma_xuat_hang) like :ma ");
                params.put("ma", '%' + command.getMaXuatHang().toLowerCase(Locale.ROOT) + '%');
            }
            from.append("group by n.id " +
                    "order by sum(nd.so_luong*nd.gia) desc   ");
            queryStr.append(from);
            Query query = entityManager.createNativeQuery(queryStr.toString());
            for (Map.Entry<String, Object> p : params.entrySet()) {
                query.setParameter(p.getKey(), p.getValue());
            }
            List<Object[]> objects = query.getResultList();

            List<XuatHangDTO> danhMucDTOS = DataUtil.convertLsObjectsToClass(Arrays.asList("id", "maXuatHang", "idKhachHang", "tenKhachHang", "idCuaHang", "tenCuaHang",
                            "totalDT", "ngayNhap", "ngayTao", "nguoiTao", "idPhuongThuc", "tenPhuongThuc")
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
            Sheet sheet = workbook.createSheet("HoaDonBanHang");

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
            cellTitle.setCellValue("BÁO CÁO HÓA ĐƠN BÁN HÀNG");
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
            for (int i = 0; i < 6; i++) {
                sheet.setColumnWidth(i, 8500);
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
            List<XuatHangDTO> nhapHangDTOS = searchXuatMax(command);
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
                cell4.setCellValue(sanPhamDTO1.getNgayXuat());
                cell4.setCellStyle(cellStyle);

                Cell cell41 = row.createCell(6);
                cell41.setCellValue(sanPhamDTO1.getNgayTao());
                cell41.setCellStyle(cellStyle);
            }

            String path = "D:/HoaDonXuatHang" + System.currentTimeMillis() + ".xlsx";
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
