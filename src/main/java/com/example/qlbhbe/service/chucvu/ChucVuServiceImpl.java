package com.example.qlbhbe.service.chucvu;

import com.example.qlbhbe.dto.BaoHiemDTO;
import com.example.qlbhbe.dto.ChucVuDTO;
import com.example.qlbhbe.entity.ChucVu;
import com.example.qlbhbe.mapper.ChucVuMapper;
import com.example.qlbhbe.repo.chucvu.ChucVuRepo;
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
public class ChucVuServiceImpl extends AbstractService<ChucVu, Long> implements ChucVuService {

    private final ChucVuRepo chucVuRepo;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    public ChucVuServiceImpl(ChucVuRepo chucVuRepo) {
        super(chucVuRepo);
        this.chucVuRepo = chucVuRepo;
    }

    @Override
    public ChucVu update(long id, ChucVuDTO command) {
        Optional<ChucVu> opt = chucVuRepo.findById(id);
        if (opt.isPresent()) {
            ChucVu chucVu = opt.get();
            return ChucVuMapper.INSTANCE.update(command, chucVu);
        }
        return null;
    }

    @Override
    public Page<ChucVuDTO> search(ChucVuDTO command, Pageable pageable) throws Exception {
        try {
            StringBuilder queryStr = new StringBuilder();
            StringBuilder count = new StringBuilder();
            StringBuilder from = new StringBuilder();
            Map<String, Object> params = new HashMap<>();
            queryStr.append("select id," +
                    "       ma_chuc_vu, " +
                    "       ten_chuc_vu, " +
                    "       mieu_ta, " +
                    "       he_so_luong ");
            count.append("select count(*) ");
            from.append(" from chuc_vu where 1 = 1 ");
            if (!DataUtil.isNullOrEmpty(command.getTenChucVu())) {
                from.append(" and lower(ten_chuc_vu) like :ten ");
                params.put("ten", command.getTenChucVu().toLowerCase(Locale.ROOT));
            }
            if (!DataUtil.isNullOrEmpty(command.getMaChucVu())) {
                from.append(" and lower(ma_chuc_vu) like :ma ");
                params.put("ma", command.getMaChucVu().toLowerCase(Locale.ROOT));
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
            List<ChucVuDTO> danhMucDTOS = DataUtil.convertLsObjectsToClass(Arrays.asList("id", "maChucVu","tenChucVu", "mieuTa", "heSoLuong")
                    , objects, ChucVuDTO.class);

            return new PageImpl<>(danhMucDTOS, pageable, Long.parseLong(o.toString()));
        } catch (Exception e) {
            throw e;
        }
    }
}
