package com.example.qlbhbe.service.nhaphang;

import com.example.qlbhbe.controller.request.UpdateNhapHangRequest;
import com.example.qlbhbe.dto.NhapHangDTO;
import com.example.qlbhbe.entity.NhapHang;
import com.example.qlbhbe.mapper.NhapHangMapper;
import com.example.qlbhbe.repo.nhaphang.NhapHangRepo;
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
public class NhapHangServiceImpl extends AbstractService<NhapHang, Long> implements NhapHangService {

    private final NhapHangRepo nhapHangRepo;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    public NhapHangServiceImpl(NhapHangRepo nhapHangRepo) {
        super(nhapHangRepo);
        this.nhapHangRepo = nhapHangRepo;
    }

    @Override
    public NhapHang update(long id, UpdateNhapHangRequest command) {
        Optional<NhapHang> opt = nhapHangRepo.findById(id);
        if (opt.isPresent()) {
            NhapHang nhapHang = opt.get();
            return NhapHangMapper.INSTANCE.update(command, nhapHang);
        }
        return null;
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
                    "    n.ten_nha_cung_cap  ");
            count.append("select count(*) ");
            from.append(" from nhap_hang s, nha_cung_cap n d where s.id_danh_muc = d.id  ");
            if (!DataUtil.isNullOrEmpty(command.getTenNhaCungCap())) {
                from.append(" and lower(n.ten_nha_cung_cap) like :ten ");
                params.put("ten", command.getMaNhapHang().toLowerCase(Locale.ROOT));
            }
            if (!DataUtil.isNullOrEmpty(command.getMaNhapHang())) {
                from.append(" and lower(s.ma_nhap_hang) like :ma ");
                params.put("ma", command.getMaNhapHang().toLowerCase(Locale.ROOT));
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
                            "nguoiThayDoi", "ngayThayDoi", "tenNhaCungCap")
                    , objects, NhapHangDTO.class);

            return new PageImpl<>(danhMucDTOS, pageable, Long.parseLong(o.toString()));
        } catch (Exception e) {
            throw e;
        }
    }
}
