package com.example.qlbhbe.service.khenthuong;

import com.example.qlbhbe.dto.ChucVuDTO;
import com.example.qlbhbe.dto.KhenThuongDTO;
import com.example.qlbhbe.entity.KhenThuong;
import com.example.qlbhbe.mapper.KhenThuongMapper;
import com.example.qlbhbe.repo.khenthuong.KhenThuongRepo;
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
public class KhenThuongServiceImpl extends AbstractService<KhenThuong, Long> implements KhenThuongService {

    private final KhenThuongRepo khenThuongRepo;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    public KhenThuongServiceImpl(KhenThuongRepo khenThuongRepo) {
        super(khenThuongRepo);
        this.khenThuongRepo = khenThuongRepo;
    }

    @Override
    public KhenThuong update(long id, KhenThuongDTO command) {
        Optional<KhenThuong> opt = khenThuongRepo.findById(id);
        if (opt.isPresent()) {
            KhenThuong khenThuong = opt.get();
            return KhenThuongMapper.INSTANCE.update(command, khenThuong);
        }
        return null;
    }

    @Override
    public Page<KhenThuongDTO> search(KhenThuongDTO command, Pageable pageable) throws Exception {
        try {
            StringBuilder queryStr = new StringBuilder();
            StringBuilder count = new StringBuilder();
            StringBuilder from = new StringBuilder();
            Map<String, Object> params = new HashMap<>();
            queryStr.append("select id, ma_khen_thuong, " +
                    "       ten, " +
                    "       muc_thuong, " +
                    "       mieu_ta ");
            count.append(" select count(*) ");
            from.append(" from khen_thuong where 1 = 1 ");
            if (!DataUtil.isNullOrEmpty(command.getTen())) {
                from.append(" and lower(ten) like :ten ");
                params.put("ten", command.getTen().toLowerCase(Locale.ROOT));
            }
            if (!DataUtil.isNullOrEmpty(command.getMaKhenThuong())) {
                from.append(" and lower(ma_khen_thuong) like :ma ");
                params.put("ma", command.getMaKhenThuong().toLowerCase(Locale.ROOT));
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
            List<KhenThuongDTO> danhMucDTOS = DataUtil.convertLsObjectsToClass(Arrays.asList("id", "maKhenThuong" ,"ten", "mucThuong", "mieuTa")
                    , objects, KhenThuongDTO.class);

            return new PageImpl<>(danhMucDTOS, pageable, Long.parseLong(o.toString()));
        } catch (Exception e) {
            throw e;
        }
    }
}
