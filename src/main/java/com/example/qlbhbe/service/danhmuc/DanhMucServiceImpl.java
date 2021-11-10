package com.example.qlbhbe.service.danhmuc;

import com.example.qlbhbe.controller.request.UpdateDanhMucRequest;
import com.example.qlbhbe.dto.DanhMucDTO;
import com.example.qlbhbe.entity.DanhMuc;
import com.example.qlbhbe.mapper.DanhMucMapper;
import com.example.qlbhbe.repo.danhmuc.DanhMucRepo;
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
public class DanhMucServiceImpl extends AbstractService<DanhMuc, Long> implements DanhMucService {

    private final DanhMucRepo danhMucRepo;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    public DanhMucServiceImpl(DanhMucRepo danhMucRepo) {
        super(danhMucRepo);
        this.danhMucRepo = danhMucRepo;
    }

    @Override
    public DanhMuc update(long id, DanhMucDTO command) {
        Optional<DanhMuc> opt = danhMucRepo.findById(id);
        if (opt.isPresent()) {
            DanhMuc danhMuc = opt.get();
            return DanhMucMapper.INSTANCE.update(command, danhMuc);
        }
        return null;
    }

    @Override
    public Page<DanhMucDTO> searchAllDanhMuc(DanhMucDTO danhMucDTO, Pageable pageable) throws Exception {
        try {
            StringBuilder queryStr = new StringBuilder();
            StringBuilder count = new StringBuilder();
            StringBuilder from = new StringBuilder();
            Map<String, Object> params = new HashMap<>();
            queryStr.append("select id, " +
                    "       ma_danh_muc, " +
                    "       ten_danh_muc, " +
                    "       thong_tin, " +
                    "       ngay_tao, " +
                    "       nguoi_tao, " +
                    "       ngay_thay_doi, " +
                    "       nguoi_thay_doi ");
            count.append("select count(*) ");
            from.append(" from danh_muc where 1 = 1 ");
            if (!DataUtil.isNullOrEmpty(danhMucDTO.getTenDanhMuc())) {
                from.append(" and lower(ten_danh_muc) like :ten ");
                params.put("ten", '%' + danhMucDTO.getTenDanhMuc().toLowerCase(Locale.ROOT) + '%' );
            }
            if (!DataUtil.isNullOrEmpty(danhMucDTO.getMaDanhMuc())) {
                from.append(" and lower(ma_danh_muc) like :ma ");
                params.put("ma", '%' + danhMucDTO.getMaDanhMuc().toLowerCase(Locale.ROOT) + '%' );
            }
            queryStr.append(from);
            count.append(from);
            Query query = entityManager.createNativeQuery(queryStr.toString());
            Query countQuery = entityManager.createNativeQuery(count.toString());
            for (Map.Entry<String, Object> p : params.entrySet()) {
                query.setParameter(p.getKey(), p.getValue());
                countQuery.setParameter(p.getKey(), p.getValue());
            }
            if (!DataUtil.isNullOrEmpty(danhMucDTO.getIsCount()) && danhMucDTO.getIsCount() == 1) {
                query.setFirstResult((int) pageable.getOffset());
                query.setMaxResults(pageable.getPageSize());
            }
            List<Object[]> objects = query.getResultList();
            Object o = countQuery.getSingleResult();
            List<DanhMucDTO> danhMucDTOS = DataUtil.convertLsObjectsToClass(Arrays.asList("id", "maDanhMuc", "tenDanhMuc", "thongTin",
                            "ngayTao", "nguoiTao", "ngayThayDoi", "nguoiThayDoi")
                    , objects, DanhMucDTO.class);

            return new PageImpl<>(danhMucDTOS, pageable, Long.parseLong(o.toString()));
        } catch (Exception e) {
            throw e;
        }
    }
}
