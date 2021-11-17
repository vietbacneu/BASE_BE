package com.example.qlbhbe.service.chamcong;

import com.example.qlbhbe.dto.ChamCongDTO;
import com.example.qlbhbe.entity.ChamCong;
import com.example.qlbhbe.mapper.ChamCongMapper;
import com.example.qlbhbe.repo.chamcong.ChamCongRepo;
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
import java.time.format.DateTimeFormatter;
import java.util.*;


@Service
@Transactional
public class ChamCongServiceImpl extends AbstractService<ChamCong, Long> implements ChamCongService {

    private final ChamCongRepo chamCongRepo;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    public ChamCongServiceImpl(ChamCongRepo chamCongRepo) {
        super(chamCongRepo);
        this.chamCongRepo = chamCongRepo;
    }

    @Override
    public ChamCong update(long id, ChamCongDTO command) {
        Optional<ChamCong> opt = chamCongRepo.findById(id);
        if (opt.isPresent()) {
            ChamCong chamCong = opt.get();
            return ChamCongMapper.INSTANCE.update(command, chamCong);
        }
        return null;
    }

    @Override
    public Page<ChamCongDTO> search(ChamCongDTO command, Pageable pageable) throws Exception {
        try {
            StringBuilder queryStr = new StringBuilder();
            StringBuilder count = new StringBuilder();
            StringBuilder from = new StringBuilder();
            Map<String, Object> params = new HashMap<>();
            queryStr.append(" select n.id, n.ho , n.ten, " +
                    "(select ten_chuc_vu from chuc_vu c where c.id = n.id_chuc_vu) tenChucvu, " +
                    "(select ten from phong_ban c where c.id = n.id_phong_ban) tenPP, cc.so_gio_lam, cc.ngay_lam ");

            count.append("select count(*) ");
            from.append(" from nhan_vien n, cham_cong cc where cc.id_nhan_vien = n.id ");
            if (!DataUtil.isNullOrEmpty(command.getTenNhanVien())) {
                from.append(" and ( lower(n.ten) like :ten or lower(n.ho) like :ten ) ");
                params.put("ten", '%' + command.getTenNhanVien().toLowerCase(Locale.ROOT) + '%');
            }
            if (!DataUtil.isNullOrEmpty(command.getNgayLam())) {
                from.append(" and cc.ngay_lam = ").append(command.getNgayLam().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
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
            List<ChamCongDTO> danhMucDTOS = DataUtil.convertLsObjectsToClass(Arrays.asList("idNhanVien", " hoNhanVien", "tenNhanVien",
                            "tenChucVu", "tenPhongBan", "soSoLam", "ngayLam")
                    , objects, ChamCongDTO.class);

            return new PageImpl<>(danhMucDTOS, pageable, Long.parseLong(o.toString()));
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Page<ChamCongDTO> searchPhieuLuong(ChamCongDTO command, Pageable pageable) throws Exception {
        try {
            StringBuilder queryStr = new StringBuilder();
            StringBuilder count = new StringBuilder();
            Map<String, Object> params = new HashMap<>();
            queryStr.append(" SELECT  " +
                    "    n.id, " +
                    "    n.ho, " +
                    "    n.ten, " +
                    "    (SELECT  " +
                    "            ten_chuc_vu " +
                    "        FROM " +
                    "            chuc_vu c " +
                    "        WHERE " +
                    "            c.id = n.id_chuc_vu) tenChucvu, " +
                    "  (SELECT  " +
                    "            he_so_luong " +
                    "        FROM " +
                    "            chuc_vu c " +
                    "        WHERE " +
                    "            c.id = n.id_chuc_vu) hsl,           " +
                    "    (SELECT  " +
                    "            ten " +
                    "        FROM " +
                    "            phong_ban c " +
                    "        WHERE " +
                    "            c.id = n.id_phong_ban) tenPP, " +
                    "   sum(cc.so_gio_lam) , " +
                    " (select sum(muc_dong) from nhan_vien_bao_hiem tmp where tmp.id_nhan_vien = n.id and month(tmp.ngay_dong) = month(cc.ngay_lam)  group by tmp.id_nhan_vien ) totalbh, " +
                    "    (select sum(muc_thuong) from khen_thuong kt,  nhan_vien_khen_thuong tmp where kt.id = tmp.id_khen_thuong and tmp.id_nhan_vien = n.id and month(tmp.ngay) = month(cc.ngay_lam)  group by tmp.id_nhan_vien) totalkt, " +
                    " (select sum(muc_phat) from ky_luat kt, nhan_vien_ky_luat tmp where kt.id = tmp.id_ky_luat and tmp.id_nhan_vien = n.id and month(tmp.ngay) = month(cc.ngay_lam) group by tmp.id_nhan_vien ) totalkl " +
                    "    from nhan_vien n left join cham_cong cc on (cc.id_nhan_vien = n.id ) where 1=1  ");

            count.append("select count(*) ");
            if (!DataUtil.isNullOrEmpty(command.getTenNhanVien())) {
                queryStr.append(" and ( lower(n.ten) like :ten or lower(n.ho) like :ten ) ");
                params.put("ten", '%' + command.getTenNhanVien().toLowerCase(Locale.ROOT) + '%');
            }
            if (!DataUtil.isNullOrEmpty(command.getNgayLam())) {
                queryStr.append(" and month(cc.ngay_lam) = ").append(command.getNgayLam().getMonth());
            }
            if (!DataUtil.isNullOrEmpty(command.getIdPhongBan())) {
                queryStr.append(" and n.id_phong_ban :pb ");
                params.put("pb", command.getIdPhongBan());
            }
            queryStr.append(" group by n.id ");
            count.append(" from ( ").append(queryStr).append(" )");
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
            List<ChamCongDTO> danhMucDTOS = DataUtil.convertLsObjectsToClass(Arrays.asList("idNhanVien", " hoNhanVien", "tenNhanVien",
                            "tenChucVu", "tenPhongBan", "soSoLam", "ngayLam")
                    , objects, ChamCongDTO.class);

            return new PageImpl<>(danhMucDTOS, pageable, Long.parseLong(o.toString()));
        } catch (Exception e) {
            throw e;
        }
    }
}
