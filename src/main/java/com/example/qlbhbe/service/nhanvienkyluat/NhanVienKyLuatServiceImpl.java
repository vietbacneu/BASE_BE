package com.example.qlbhbe.service.nhanvienkyluat;

import com.example.qlbhbe.dto.NhanVienBaoHiemDTO;
import com.example.qlbhbe.dto.NhanVienKyLuatDTO;
import com.example.qlbhbe.entity.KyLuat;
import com.example.qlbhbe.entity.NhanVien;
import com.example.qlbhbe.entity.NhanVienKyLuat;
import com.example.qlbhbe.mapper.NhanVienKyLuatMapper;
import com.example.qlbhbe.repo.nhanvienkyluat.NhanVienKyLuatRepo;
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
public class NhanVienKyLuatServiceImpl extends AbstractService<NhanVienKyLuat, Long> implements NhanVienKyLuatService {

    private final NhanVienKyLuatRepo nhanVienKyLuatRepo;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    public NhanVienKyLuatServiceImpl(NhanVienKyLuatRepo nhanVienKyLuatRepo) {
        super(nhanVienKyLuatRepo);
        this.nhanVienKyLuatRepo = nhanVienKyLuatRepo;
    }

    @Override
    public NhanVienKyLuat update(long id, NhanVienKyLuatDTO command) {
        Optional<NhanVienKyLuat> opt = nhanVienKyLuatRepo.findById(id);
        if (opt.isPresent()) {
            NhanVienKyLuat nhanVienKyLuat = opt.get();
            NhanVien nhanVien = new NhanVien();
            nhanVien.setId(command.getIdNhanVien());
            nhanVienKyLuat.setNhanVien(nhanVien);
            KyLuat kt = new KyLuat();
            kt.setId(command.getIdKyLuat());
            nhanVienKyLuat.setKyLuat(kt);
            return NhanVienKyLuatMapper.INSTANCE.update(command, nhanVienKyLuat);
        }
        return null;
    }

    @Override
    public Page<NhanVienKyLuatDTO> search(NhanVienKyLuatDTO command, Pageable pageable) throws Exception {
        try {
            StringBuilder queryStr = new StringBuilder();
            StringBuilder count = new StringBuilder();
            StringBuilder from = new StringBuilder();
            Map<String, Object> params = new HashMap<>();
            queryStr.append(" select n.id as nvId, n.ho , n.ten, " +
                    "(select ten_chuc_vu from chuc_vu c where c.id = n.id_chuc_vu) tenChucvu, " +
                    "(select ten from phong_ban c where c.id = n.id_phong_ban) tenPP, " +
                    " bh.ten_loi as tenBh, bh.muc_phat, nbh.ngay, nbh.mieu_ta, bh.id ");
            count.append("select count(*) ");
            from.append(" from nhan_vien n, nhan_vien_ky_luat nbh, ky_luat bh  " +
                    "   where n.id = nbh.id_nhan_vien and nbh.id_ky_luat = bh.id ");

            if (!DataUtil.isNullOrEmpty(command.getTenNhanVien())) {
                from.append("   and concat( lower(n.ho), ' ' ,lower(n.ten)  ) like :ten ");
                params.put("ten", '%' + command.getTenNhanVien().toLowerCase(Locale.ROOT) + '%');
            }
            if (!DataUtil.isNullOrEmpty(command.getIdKyLuat())) {
                from.append(" and bh.id = :ma ");
                params.put("ma", command.getIdKyLuat());
            }
            from.append("  order by nbh.id desc");
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
            List<NhanVienKyLuatDTO> danhMucDTOS = DataUtil.convertLsObjectsToClass(Arrays.asList("idNhanVien", "hoNhanVien", "tenNhanVien",
                            "tenChucVu", "tenPhongBan", "tenLoi", "mucPhat", "ngay", "nhanVienMieuTa","idKyLuat")
                    , objects, NhanVienKyLuatDTO.class);

            return new PageImpl<>(danhMucDTOS, pageable, Long.parseLong(o.toString()));
        } catch (Exception e) {
            throw e;
        }
    }
}
