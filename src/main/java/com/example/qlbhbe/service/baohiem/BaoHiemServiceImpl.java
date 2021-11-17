package com.example.qlbhbe.service.baohiem;

import com.example.qlbhbe.dto.BaoHiemDTO;
import com.example.qlbhbe.entity.BaoHiem;
import com.example.qlbhbe.mapper.BaoHiemMapper;
import com.example.qlbhbe.repo.baohiem.BaoHiemRepo;
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
public class BaoHiemServiceImpl extends AbstractService<BaoHiem, Long> implements BaoHiemService {

    private final BaoHiemRepo baoHiemRepo;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    public BaoHiemServiceImpl(BaoHiemRepo baoHiemRepo) {
        super(baoHiemRepo);
        this.baoHiemRepo = baoHiemRepo;
    }

    @Override
    public BaoHiem update(long id, BaoHiemDTO command) {
        Optional<BaoHiem> opt = baoHiemRepo.findById(id);
        if (opt.isPresent()) {
            BaoHiem baoHiem = opt.get();
            return BaoHiemMapper.INSTANCE.update(command, baoHiem);
        }
        return null;
    }

    @Override
    public Page<BaoHiemDTO> search(BaoHiemDTO command, Pageable pageable) throws Exception {
        try {
            StringBuilder queryStr = new StringBuilder();
            StringBuilder count = new StringBuilder();
            StringBuilder from = new StringBuilder();
            Map<String, Object> params = new HashMap<>();
            queryStr.append("select id, ma_bao_hiem, " +
                    "       ma_so, " +
                    "       ten, " +
                    "       thong_tin ");
            count.append("select count(*) ");
            from.append(" from bao_hiem where 1 = 1 ");
            if (!DataUtil.isNullOrEmpty(command.getTen())) {
                from.append(" and lower(ten) like :ten ");
                params.put("ten", command.getTen().toLowerCase(Locale.ROOT));
            }
            if (!DataUtil.isNullOrEmpty(command.getMaSo())) {
                from.append(" and lower(ma_so) like :ma ");
                params.put("ma", command.getMaSo().toLowerCase(Locale.ROOT));
            }
            if (!DataUtil.isNullOrEmpty(command.getMaBaoHiem())) {
                from.append(" and lower(ma_bao_hiem) like :ma ");
                params.put("ma", command.getMaBaoHiem().toLowerCase(Locale.ROOT));
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
            List<BaoHiemDTO> danhMucDTOS = DataUtil.convertLsObjectsToClass(Arrays.asList("id", "maBaoHiem","maSo", "ten", "thongTin")
                    , objects, BaoHiemDTO.class);

            return new PageImpl<>(danhMucDTOS, pageable, Long.parseLong(o.toString()));
        } catch (Exception e) {
            throw e;
        }
    }
}
