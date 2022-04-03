package com.example.qlbhbe.service.nhanvien;

import com.example.qlbhbe.dto.NhanVienDTO;
import com.example.qlbhbe.entity.NhanVien;
import com.example.qlbhbe.mapper.NhanVienMapper;
import com.example.qlbhbe.repo.nhanvien.NhanVienRepo;
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
public class NhanVienServiceImpl extends AbstractService<NhanVien, Long> implements NhanVienService {

    private final NhanVienRepo nhanVienRepo;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    public NhanVienServiceImpl(NhanVienRepo nhanVienRepo) {
        super(nhanVienRepo);
        this.nhanVienRepo = nhanVienRepo;
    }

    @Override
    public NhanVien update(long id, NhanVienDTO command) {
        Optional<NhanVien> opt = nhanVienRepo.findById(id);
        if (opt.isPresent()) {
            NhanVien nhanVien = opt.get();
            return NhanVienMapper.INSTANCE.update(command, nhanVien);
        }
        return null;
    }

    @Override
    public Page<NhanVienDTO> search(NhanVienDTO command, Pageable pageable) throws Exception {
        try {
            StringBuilder queryStr = new StringBuilder();
            StringBuilder count = new StringBuilder();
            StringBuilder from = new StringBuilder();
            Map<String, Object> params = new HashMap<>();
            queryStr.append("select id    , " +
                    "    ma_nhan_vien  , " +
                    "    ten_nhan_vien , " +
                    "    dia_chi       ");
            count.append("select count(*) ");
            from.append(" from nhan_vien where 1 = 1 ");
            if (!DataUtil.isNullOrEmpty(command.getTenNhanVien())) {
                from.append(" and lower(ten_nhan_vien) like :ten ");
                params.put("ten", '%' + command.getTenNhanVien().toLowerCase(Locale.ROOT) + '%');
            }
            if (!DataUtil.isNullOrEmpty(command.getMaNhanVien())) {
                from.append(" and lower(ma_nhan_vien) like :ma ");
                params.put("ma", '%' + command.getMaNhanVien().toLowerCase(Locale.ROOT) + '%');
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
            List<NhanVienDTO> danhMucDTOS = DataUtil.convertLsObjectsToClass(Arrays.asList("id", "maNhanVien", "tenNhanVien", "diaChi")
                    , objects, NhanVienDTO.class);
            return new PageImpl<>(danhMucDTOS, pageable, Long.parseLong(o.toString()));
        } catch (Exception e) {
            throw e;
        }
    }
}
