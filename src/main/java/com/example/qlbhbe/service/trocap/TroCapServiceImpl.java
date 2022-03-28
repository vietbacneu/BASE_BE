package com.example.qlbhbe.service.trocap;

import com.example.qlbhbe.controller.request.UpdateTroCapRequest;
import com.example.qlbhbe.dto.KhenThuongDTO;
import com.example.qlbhbe.dto.TroCapDTO;
import com.example.qlbhbe.entity.TroCap;
import com.example.qlbhbe.mapper.TroCapMapper;
import com.example.qlbhbe.repo.trocap.TroCapRepo;
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
public class TroCapServiceImpl extends AbstractService<TroCap, Long> implements TroCapService {

    private final TroCapRepo troCapRepo;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    public TroCapServiceImpl(TroCapRepo troCapRepo) {
        super(troCapRepo);
        this.troCapRepo = troCapRepo;
    }

    @Override
    public TroCap update(long id, TroCapDTO command) {
        Optional<TroCap> opt = troCapRepo.findById(id);
        if (opt.isPresent()) {
            TroCap troCap = opt.get();
            return TroCapMapper.INSTANCE.update(command, troCap);
        }
        return null;
    }

    @Override
    public Page<TroCapDTO> search(TroCapDTO command, Pageable pageable) throws Exception {
        try {
            StringBuilder queryStr = new StringBuilder();
            StringBuilder count = new StringBuilder();
            StringBuilder from = new StringBuilder();
            Map<String, Object> params = new HashMap<>();
            queryStr.append("select id, ma_tro_cap, " +
                    "       ten, " +
                    "       muc_tro_cap, " +
                    "       mieu_ta ");
            count.append(" select count(*) ");
            from.append(" from tro_cap where 1 = 1 ");
            if (!DataUtil.isNullOrEmpty(command.getTen())) {
                from.append(" and lower(ten) like :ten ");
                params.put("ten", '%' + command.getTen().toLowerCase(Locale.ROOT) + '%');
            }
            if (!DataUtil.isNullOrEmpty(command.getMaTroCap())) {
                from.append(" and lower(ma_tro_cap) like :ma ");
                params.put("ma", '%' + command.getMaTroCap().toLowerCase(Locale.ROOT) +'%');
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
            List<TroCapDTO> danhMucDTOS = DataUtil.convertLsObjectsToClass(Arrays.asList("id", "maTroCap" ,"ten", "mucTroCap", "mieuTa")
                    , objects, TroCapDTO.class);

            return new PageImpl<>(danhMucDTOS, pageable, Long.parseLong(o.toString()));
        } catch (Exception e) {
            throw e;
        }
    }
}
