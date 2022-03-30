package com.example.qlbhbe.service.khenthuong;

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
            queryStr.append("select id, ma_danh_gia, " +
                    "       ten, " +
                    "       so_tien, " +
                    "       mieu_ta, loai ");
            count.append(" select count(*) ");
            from.append(" from khen_thuong_ky_luat where 1 = 1 ");
            if (!DataUtil.isNullOrEmpty(command.getTen())) {
                from.append(" and lower(ten) like :ten ");
                params.put("ten", '%' + command.getTen().toLowerCase(Locale.ROOT) + '%');
            }
            if (!DataUtil.isNullOrEmpty(command.getMaDanhGia())) {
                from.append(" and lower(ma_danh_gia) like :ma ");
                params.put("ma", '%' + command.getMaDanhGia().toLowerCase(Locale.ROOT) + '%');
            }
            if (!DataUtil.isNullOrEmpty(command.getLoai())) {
                from.append(" and lower(loai) like :loai ");
                params.put("loai", '%' + command.getLoai().toLowerCase(Locale.ROOT) + '%');
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
            List<KhenThuongDTO> danhMucDTOS = DataUtil.convertLsObjectsToClass(Arrays.asList("id", "maDanhGia", "ten", "soTien", "mieuTa", "loai")
                    , objects, KhenThuongDTO.class);
            for (KhenThuongDTO danhMucDTO : danhMucDTOS) {
                if (danhMucDTO.getLoai() != null && danhMucDTO.getLoai().equals("khenthuong")) {
                    danhMucDTO.setTenLoai("Khen thưởng");
                } else if (danhMucDTO.getLoai() != null && danhMucDTO.getLoai().equals("kyluat")) {
                    danhMucDTO.setTenLoai("Kỷ luật");
                }
            }
            return new PageImpl<>(danhMucDTOS, pageable, Long.parseLong(o.toString()));
        } catch (Exception e) {
            throw e;
        }
    }
}
