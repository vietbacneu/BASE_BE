package com.example.qlbhbe.service.thucdon;

import com.example.qlbhbe.dto.CuaHangDTO;
import com.example.qlbhbe.dto.ThucDonDTO;
import com.example.qlbhbe.entity.ThucDon;
import com.example.qlbhbe.mapper.ThucDonMapper;
import com.example.qlbhbe.repo.thucdon.ThucDonRepo;
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
public class ThucDonServiceImpl extends AbstractService<ThucDon, Long> implements ThucDonService {

    private final ThucDonRepo thucDonRepo;

    @PersistenceContext
    EntityManager entityManager;
    
    @Autowired
    public ThucDonServiceImpl(ThucDonRepo thucDonRepo) {
        super(thucDonRepo);
        this.thucDonRepo = thucDonRepo;
    }

    @Override
    public ThucDon update(long id, ThucDonDTO command) {
        Optional<ThucDon> opt = thucDonRepo.findById(id);
        if (opt.isPresent()) {
            ThucDon thucDon = opt.get();
            return ThucDonMapper.INSTANCE.update(command, thucDon);
        }
        return null;
    }

    @Override
    public Page<ThucDonDTO> search(ThucDonDTO command, Pageable pageable) throws Exception {
        try {
            StringBuilder queryStr = new StringBuilder();
            StringBuilder count = new StringBuilder();
            StringBuilder from = new StringBuilder();
            Map<String, Object> params = new HashMap<>();
            queryStr.append("select id    , " +
                    "    ma_thuc_don  , " +
                    "    ten_thuc_don , " +
                    "    gia       ") ;
            count.append("select count(*) ");
            from.append(" from thuc_don where 1 = 1 ");
            if (!DataUtil.isNullOrEmpty(command.getTenThucDon())) {
                from.append(" and lower(ten_thuc_don) like :ten ");
                params.put("ten", '%' + command.getTenThucDon().toLowerCase(Locale.ROOT) +'%');
            }
            if (!DataUtil.isNullOrEmpty(command.getMaThucDon())) {
                from.append(" and lower(ma_thuc_don) like :ma ");
                params.put("ma", '%' + command.getMaThucDon().toLowerCase(Locale.ROOT)+'%');
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
            List<ThucDonDTO> danhMucDTOS = DataUtil.convertLsObjectsToClass(Arrays.asList("id", "maThucDon", "tenThucDon", "gia")
                    , objects, ThucDonDTO.class);
            return new PageImpl<>(danhMucDTOS, pageable, Long.parseLong(o.toString()));
        } catch (Exception e) {
            throw e;
        }
    }
}
