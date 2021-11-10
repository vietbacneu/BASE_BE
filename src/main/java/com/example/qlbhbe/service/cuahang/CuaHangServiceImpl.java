package com.example.qlbhbe.service.cuahang;

import com.example.qlbhbe.dto.CuaHangDTO;
import com.example.qlbhbe.dto.NhaCungCapDTO;
import com.example.qlbhbe.entity.CuaHang;
import com.example.qlbhbe.mapper.CuaHangMapper;
import com.example.qlbhbe.repo.cuahang.CuaHangRepo;
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
public class CuaHangServiceImpl extends AbstractService<CuaHang, Long> implements CuaHangService {

    private final CuaHangRepo cuaHangRepo;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    public CuaHangServiceImpl(CuaHangRepo cuaHangRepo) {
        super(cuaHangRepo);
        this.cuaHangRepo = cuaHangRepo;
    }

    @Override
    public CuaHang update(long id, CuaHangDTO command) {
        Optional<CuaHang> opt = cuaHangRepo.findById(id);
        if (opt.isPresent()) {
            CuaHang cuaHang = opt.get();
            return CuaHangMapper.INSTANCE.update(command, cuaHang);
        }
        return null;
    }

    @Override
    public Page<CuaHangDTO> search(CuaHangDTO command, Pageable pageable) throws Exception {
        try {
            StringBuilder queryStr = new StringBuilder();
            StringBuilder count = new StringBuilder();
            StringBuilder from = new StringBuilder();
            Map<String, Object> params = new HashMap<>();
            queryStr.append("select id    , " +
                    "    ma_cua_hang  , " +
                    "    ten_cua_hang , " +
                    "    dia_chi       ") ;
            count.append("select count(*) ");
            from.append(" from cua_hang where 1 = 1 ");
            if (!DataUtil.isNullOrEmpty(command.getTenCuaHang())) {
                from.append(" and lower(ten_cua_hang) like :ten ");
                params.put("ten", '%' + command.getTenCuaHang().toLowerCase(Locale.ROOT) +'%');
            }
            if (!DataUtil.isNullOrEmpty(command.getMaCuaHang())) {
                from.append(" and lower(ma_cua_hang) like :ma ");
                params.put("ma", '%' + command.getMaCuaHang().toLowerCase(Locale.ROOT)+'%');
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
            List<CuaHangDTO> danhMucDTOS = DataUtil.convertLsObjectsToClass(Arrays.asList("id", "maCuaHang", "tenCuaHang", "diaChi")
                    , objects, CuaHangDTO.class);
            return new PageImpl<>(danhMucDTOS, pageable, Long.parseLong(o.toString()));
        } catch (Exception e) {
            throw e;
        }
    }
}
