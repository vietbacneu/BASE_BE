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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
                    "    n.ten_nha_cung_cap, s.id_thanh_toan, (select t.ten_phuong_thuc from phuong_thuc_thanh_toan t where t.id = s.id_thanh_toan)  ");
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
                            "nguoiThayDoi", "ngayThayDoi", "tenNhaCungCap", "idPhuongThuc", "tenPhuongThuc")
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
        nhapHang.setThanhToan(new PhuongThucThanhToan(nhapHangDTO.getIdPhuongThuc()));
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
    public List<NhapHangDTO> getNhapHangMax(NhapHangDTO sanPhamDTO) throws Exception {
        return null;
    }

    @Override
    public Map<String, String> exportNhapMax(NhapHangDTO sanPhamDTO) throws Exception {
        return null;
    }
}
