package com.example.qlbhbe.service.kyluat;

import com.example.qlbhbe.dto.KyLuatDTO;
import com.example.qlbhbe.entity.KyLuat;
import com.example.qlbhbe.mapper.KyLuatMapper;
import com.example.qlbhbe.repo.kyluat.KyLuatRepo;
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
public class KyLuatServiceImpl extends AbstractService<KyLuat, Long> implements KyLuatService {

    @PersistenceContext
    EntityManager entityManager;

    private final KyLuatRepo kyLuatRepo;

    @Autowired
    public KyLuatServiceImpl(KyLuatRepo kyLuatRepo) {
        super(kyLuatRepo);
        this.kyLuatRepo = kyLuatRepo;
    }

    @Override
    public KyLuat update(long id, KyLuatDTO command) {
        Optional<KyLuat> opt = kyLuatRepo.findById(id);
        if (opt.isPresent()) {
            KyLuat kyLuat = opt.get();
            return KyLuatMapper.INSTANCE.update(command, kyLuat);
        }
        return null;
    }

    @Override
    public Page<KyLuatDTO> search(KyLuatDTO command, Pageable pageable) throws Exception {
        try {
            StringBuilder queryStr = new StringBuilder();
            StringBuilder count = new StringBuilder();
            StringBuilder from = new StringBuilder();
            Map<String, Object> params = new HashMap<>();
            queryStr.append("select id, ma_ky_luat, " +
                    "       ten_loi, " +
                    "       muc_phat, " +
                    "       mieu_ta ");
            count.append("select count(*) ");
            from.append(" from ky_luat where 1 = 1 ");
            if (!DataUtil.isNullOrEmpty(command.getTenLoi())) {
                from.append(" and lower(ten_loi) like :ten ");
                params.put("ten", '%' + command.getTenLoi().toLowerCase(Locale.ROOT) + '%');
            }
            if (!DataUtil.isNullOrEmpty(command.getMaKyLuat())) {
                from.append(" and lower(ma_ky_luat) like :ma ");
                params.put("ma", '%' + command.getMaKyLuat().toLowerCase(Locale.ROOT) + '%');
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
            List<KyLuatDTO> danhMucDTOS = DataUtil.convertLsObjectsToClass(Arrays.asList("id", "maKyLuat", "tenLoi", "mucPhat", "mieuTa")
                    , objects, KyLuatDTO.class);

            return new PageImpl<>(danhMucDTOS, pageable, Long.parseLong(o.toString()));
        } catch (Exception e) {
            throw e;
        }
    }
}
