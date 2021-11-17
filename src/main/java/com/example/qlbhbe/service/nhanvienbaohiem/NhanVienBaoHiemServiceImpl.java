package com.example.qlbhbe.service.nhanvienbaohiem;

import com.example.qlbhbe.dto.NhanVienBaoHiemDTO;
import com.example.qlbhbe.entity.BaoHiem;
import com.example.qlbhbe.entity.NhanVien;
import com.example.qlbhbe.entity.NhanVienBaoHiem;
import com.example.qlbhbe.mapper.NhanVienBaoHiemMapper;
import com.example.qlbhbe.repo.nhanvienbaohiem.NhanVienBaoHiemRepo;
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
public class NhanVienBaoHiemServiceImpl extends AbstractService<NhanVienBaoHiem, Long> implements NhanVienBaoHiemService {

    private final NhanVienBaoHiemRepo nhanVienBaoHiemRepo;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    public NhanVienBaoHiemServiceImpl(NhanVienBaoHiemRepo nhanVienBaoHiemRepo) {
        super(nhanVienBaoHiemRepo);
        this.nhanVienBaoHiemRepo = nhanVienBaoHiemRepo;
    }

    @Override
    public NhanVienBaoHiem update(long id, NhanVienBaoHiemDTO command) {
        Optional<NhanVienBaoHiem> opt = nhanVienBaoHiemRepo.findById(id);
        if (opt.isPresent()) {
            NhanVienBaoHiem nhanVienBaoHiem = opt.get();
            NhanVien nhanVien = new NhanVien();
            nhanVien.setId(command.getNhanVienId());
            nhanVienBaoHiem.setNhanVien(nhanVien);
            BaoHiem baoHiem = new BaoHiem();
            baoHiem.setId(command.getidBaoHiem());
            nhanVienBaoHiem.setBaoHiem(baoHiem);
            return NhanVienBaoHiemMapper.INSTANCE.update(command, nhanVienBaoHiem);
        }
        return null;
    }

    @Override
    public Page<NhanVienBaoHiemDTO> search(NhanVienBaoHiemDTO command, Pageable pageable) throws Exception {
        try {
            StringBuilder queryStr = new StringBuilder();
            StringBuilder count = new StringBuilder();
            StringBuilder from = new StringBuilder();
            Map<String, Object> params = new HashMap<>();
            queryStr.append(" select n.id, n.ho , n.ten, " +
                    "(select ten_chuc_vu from chuc_vu c where c.id = n.id_chuc_vu) tenChucvu, " +
                    "(select ten from phong_ban c where c.id = n.id_phong_ban) tenPP, " +
                    " bh.ten as tenBh, bh.muc_dong, nbh.ngay_dong, nbh.mieu_ta ");

            count.append("select count(*) ");
            from.append(" from nhan_vien n, nhan_vien_bao_hiem nbh, bao_hiem bh  " +
                    "   where n.id = nbh.id_nhan_vien and nbh.id_bao_hiem = bh.id ");
            if (!DataUtil.isNullOrEmpty(command.getTenNhanVien())) {
                from.append(" and ( lower(n.ten) like :ten or lower(n.ho) like :ten ) ");
                params.put("ten", '%' + command.getTenNhanVien().toLowerCase(Locale.ROOT) + '%');
            }
            if (!DataUtil.isNullOrEmpty(command.getidBaoHiem())) {
                from.append(" and bh.id = :ma ");
                params.put("ma", command.getidBaoHiem());
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
            List<NhanVienBaoHiemDTO> danhMucDTOS = DataUtil.convertLsObjectsToClass(Arrays.asList("idNhanVien", " hoNhanVien", "tenNhanVien",
                            "tenChucVu", "tenPhongBan", "tenBaoHiem", "mucDong", "ngayDong","mieuTa")
                    , objects, NhanVienBaoHiemDTO.class);

            return new PageImpl<>(danhMucDTOS, pageable, Long.parseLong(o.toString()));
        } catch (Exception e) {
            throw e;
        }
    }
}
