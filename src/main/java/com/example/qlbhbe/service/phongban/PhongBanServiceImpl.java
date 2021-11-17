package com.example.qlbhbe.service.phongban;

import com.example.qlbhbe.dto.PhongBanDTO;
import com.example.qlbhbe.entity.PhongBan;
import com.example.qlbhbe.mapper.PhongBanMapper;
import com.example.qlbhbe.repo.phongban.PhongBanRepo;
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
public class PhongBanServiceImpl extends AbstractService<PhongBan, Long> implements PhongBanService {

    private final PhongBanRepo phongBanRepo;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    public PhongBanServiceImpl(PhongBanRepo phongBanRepo) {
        super(phongBanRepo);
        this.phongBanRepo = phongBanRepo;
    }

    @Override
    public PhongBan update(long id, PhongBanDTO command) {
        Optional<PhongBan> opt = phongBanRepo.findById(id);
        if (opt.isPresent()) {
            PhongBan phongBan = opt.get();
            return PhongBanMapper.INSTANCE.update(command, phongBan);
        }
        return null;
    }

    @Override
    public Page<PhongBanDTO> search(PhongBanDTO command, Pageable pageable) throws Exception {
        try {
            StringBuilder queryStr = new StringBuilder();
            StringBuilder count = new StringBuilder();
            StringBuilder from = new StringBuilder();
            Map<String, Object> params = new HashMap<>();
            queryStr.append("select id, ma_phong_ban, " +
                    "       ten, " +
                    "       mieu_ta ");
            count.append("select count(*) ");
            from.append(" from phong_ban where 1 = 1 ");
            if (!DataUtil.isNullOrEmpty(command.getTen())) {
                from.append(" and lower(ten) like :ten ");
                params.put("ten", '%' + command.getTen().toLowerCase(Locale.ROOT) + '%');
            }
            if (!DataUtil.isNullOrEmpty(command.getMaPhongBan())) {
                from.append(" and lower(ma_phong_ban) like :ma ");
                params.put("ma", '%' + command.getMaPhongBan().toLowerCase(Locale.ROOT) + '%');
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
            List<PhongBanDTO> danhMucDTOS = DataUtil.convertLsObjectsToClass(Arrays.asList("id", "maPhongBan", "ten", "mieuTa")
                    , objects, PhongBanDTO.class);

            return new PageImpl<>(danhMucDTOS, pageable, Long.parseLong(o.toString()));
        } catch (Exception e) {
            throw e;
        }
    }
}
