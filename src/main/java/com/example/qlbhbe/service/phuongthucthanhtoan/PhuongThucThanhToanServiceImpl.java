package com.example.qlbhbe.service.phuongthucthanhtoan;

import com.example.qlbhbe.controller.request.UpdatePhuongThucThanhToanRequest;
import com.example.qlbhbe.dto.DanhMucDTO;
import com.example.qlbhbe.dto.PhuongThucThanhToanDTO;
import com.example.qlbhbe.entity.PhuongThucThanhToan;
import com.example.qlbhbe.mapper.PhuongThucThanhToanMapper;
import com.example.qlbhbe.repo.phuongthucthanhtoan.PhuongThucThanhToanRepo;
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
public class PhuongThucThanhToanServiceImpl extends AbstractService<PhuongThucThanhToan, Long> implements PhuongThucThanhToanService {

    private final PhuongThucThanhToanRepo phuongThucThanhToanRepo;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    public PhuongThucThanhToanServiceImpl(PhuongThucThanhToanRepo phuongThucThanhToanRepo) {
        super(phuongThucThanhToanRepo);
        this.phuongThucThanhToanRepo = phuongThucThanhToanRepo;
    }

    @Override
    public PhuongThucThanhToan update(long id, PhuongThucThanhToanDTO command) {
        Optional<PhuongThucThanhToan> opt = phuongThucThanhToanRepo.findById(id);
        if (opt.isPresent()) {
            PhuongThucThanhToan phuongThucThanhToan = opt.get();
            return PhuongThucThanhToanMapper.INSTANCE.update(command, phuongThucThanhToan);
        }
        return null;
    }

    @Override
    public Page<PhuongThucThanhToanDTO> searchAllPPTT(PhuongThucThanhToanDTO danhMucDTO, Pageable pageable) throws Exception {
        try {
            StringBuilder queryStr = new StringBuilder();
            StringBuilder count = new StringBuilder();
            StringBuilder from = new StringBuilder();
            Map<String, Object> params = new HashMap<>();
            queryStr.append("select id, " +
                    "       ma_phuong_thuc, " +
                    "       ten_phuong_thuc, " +
                    "       mieu_ta, " +
                    "       ngay_tao, " +
                    "       nguoi_tao, " +
                    "       ngay_thay_doi, " +
                    "       nguoi_thay_doi ");
            count.append("select count(*) ");
            from.append(" from danh_muc where 1 = 1 ");
            if (!DataUtil.isNullOrEmpty(danhMucDTO.getTenPhuongThuc())) {
                from.append(" and lower(ten_phuong_thuc) like :ten ");
                params.put("ten", '%' + danhMucDTO.getTenPhuongThuc().toLowerCase(Locale.ROOT) + '%' );
            }
            if (!DataUtil.isNullOrEmpty(danhMucDTO.getMaPhuongThuc())) {
                from.append(" and lower(ma_phuong_thuc) like :ma ");
                params.put("ma", '%' + danhMucDTO.getMaPhuongThuc().toLowerCase(Locale.ROOT) + '%' );
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
            List<PhuongThucThanhToanDTO> danhMucDTOS = DataUtil.convertLsObjectsToClass(Arrays.asList("id", "maPhuongThuc", "tenPhuongThuc", "mieuTa",
                            "ngayTao", "nguoiTao", "ngayThayDoi", "nguoiThayDoi")
                    , objects, PhuongThucThanhToanDTO.class);

            return new PageImpl<>(danhMucDTOS, pageable, Long.parseLong(o.toString()));
        } catch (Exception e) {
            throw e;
        }
    }
}
