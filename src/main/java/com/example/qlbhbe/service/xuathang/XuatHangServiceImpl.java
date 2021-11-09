package com.example.qlbhbe.service.xuathang;

import com.example.qlbhbe.controller.request.UpdateXuatHangRequest;
import com.example.qlbhbe.dto.NhapHangChiTietDTO;
import com.example.qlbhbe.dto.NhapHangDTO;
import com.example.qlbhbe.dto.XuatHangDTO;
import com.example.qlbhbe.entity.XuatHang;
import com.example.qlbhbe.mapper.XuatHangMapper;
import com.example.qlbhbe.repo.xuathang.XuatHangRepo;
import com.example.qlbhbe.service.AbstractService;
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
public class XuatHangServiceImpl extends AbstractService<XuatHang, Long> implements XuatHangService {

    private final XuatHangRepo xuatHangRepo;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    public XuatHangServiceImpl(XuatHangRepo xuatHangRepo) {
        super(xuatHangRepo);
        this.xuatHangRepo = xuatHangRepo;
    }

    @Override
    public XuatHang update(long id, UpdateXuatHangRequest command) {
        Optional<XuatHang> opt = xuatHangRepo.findById(id);
        if (opt.isPresent()) {
            XuatHang xuatHang = opt.get();
            return XuatHangMapper.INSTANCE.update(command, xuatHang);
        }
        return null;
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
                    " (select ten_cua_hang from cua_hang c where c.id =  s.id_cua_hang )  tencuahang");
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
            List<XuatHangDTO> danhMucDTOS = DataUtil.convertLsObjectsToClass(Arrays.asList("id", "maXuatHang", "idKhachHang", "idCuaHang", "nguoiTao", "ngayTao",
                            "nguoiThayDoi", "ngayThayDoi", "tenKhachHang","tencuahang")
                    , objects, XuatHangDTO.class);
//            for (XuatHangDTO danhMucDTO : danhMucDTOS) {
//                NhapHangChiTietDTO nhapHangChiTietDTO = new NhapHangChiTietDTO();
//                nhapHangChiTietDTO.setIdNhapHang(danhMucDTO.getId());
//                List<NhapHangChiTietDTO> tm = nhapHangChiTietService.search(nhapHangChiTietDTO);
//                danhMucDTO.setNhapHangChiTietDTOList(tm);
//            }
            return new PageImpl<>(danhMucDTOS, pageable, Long.parseLong(o.toString()));
        } catch (Exception e) {
            throw e;
        }
    }
}
