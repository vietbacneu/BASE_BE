package com.example.qlbhbe.service.nhanvientrocap;

import com.example.qlbhbe.dto.NhanVienTroCapDTO;
import com.example.qlbhbe.entity.NhanVien;
import com.example.qlbhbe.entity.NhanVienTroCap;
import com.example.qlbhbe.entity.TroCap;
import com.example.qlbhbe.mapper.NhanVienTroCapMapper;
import com.example.qlbhbe.repo.trocap.NhanVienTroCapRepo;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
@Transactional
public class NhanVienTroCapServiceImpl extends AbstractService<NhanVienTroCap, Long> implements NhanVienTroCapService {

    private final NhanVienTroCapRepo nhanVienTroCapRepo;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    public NhanVienTroCapServiceImpl(NhanVienTroCapRepo nhanVienTroCapRepo) {
        super(nhanVienTroCapRepo);
        this.nhanVienTroCapRepo = nhanVienTroCapRepo;
    }

    @Override
    public NhanVienTroCap update(Long id, NhanVienTroCapDTO command) throws ParseException {
        Optional<NhanVienTroCap> opt = nhanVienTroCapRepo.findById(id);
        if (opt.isPresent()) {
            NhanVienTroCap nhanVienTroCap = opt.get();
            NhanVien nhanVien = new NhanVien();
            nhanVien.setId(command.getIdNhanVien());
            nhanVienTroCap.setNhanVien(nhanVien);
            TroCap kt = new TroCap();
            kt.setId(command.getTroCap());
            nhanVienTroCap.setIdTroCap(kt);
            return NhanVienTroCapMapper.INSTANCE.update(command, nhanVienTroCap);
        }
        return null;
    }

    @Override
    public Page<NhanVienTroCapDTO> search(NhanVienTroCapDTO command, Pageable pageable) throws Exception {
        try {
            StringBuilder queryStr = new StringBuilder();
            StringBuilder count = new StringBuilder();
            StringBuilder from = new StringBuilder();
            Map<String, Object> params = new HashMap<>();
            queryStr.append(" select nvtc.id asNvbh, n.id as nvId, n.ho , n.ten, " +
                    "(select ten_chuc_vu from chuc_vu c where c.id = n.id_chuc_vu) tenChucvu, " +
                    "(select ten from phong_ban c where c.id = n.id_phong_ban) tenPP, " +
                    " tc.ten as tenBh, tc.muc_tro_cap, nvtc.tu_ngay, nvtc.mieu_ta, tc.id, nvtc.den_ngay ");

            count.append("select count(*) ");
            from.append("  from nhan_vien n, nhan_vien_tro_cap nvtc, tro_cap tc " +
                    " where n.id = nvtc.id_nhan_vien and nvtc.id_tro_cap = tc.id");
            if (!DataUtil.isNullOrEmpty(command.getTenNhanVien())) {
                String tmp = '%' + command.getTenNhanVien().toLowerCase(Locale.ROOT) + '%';
                from.append("   and lower(concat( n.ho, ' ' ,n.ten) ) like '").append(tmp).append("'");
            }
            if (!DataUtil.isNullOrEmpty(command.getTroCap())) {
                from.append(" and tc.id = :ma ");
                params.put("ma", command.getTroCap());
            }
            if (!DataUtil.isNullOrEmpty(command.getTuNgay())) {
                from.append(" and nvtc.tu_ngay >= :tuNgay  ");
                params.put("tuNgay", command.getTuNgay());
            }
            if (!DataUtil.isNullOrEmpty(command.getDenNgay())) {
                from.append(" and nvtc.den_ngay <= :denNgay  ");
                params.put("denNgay", command.getDenNgay());
            }
            from.append("  order by nvtc.id desc");
            queryStr.append(from);
            count.append(from);
            Query query = entityManager.createNativeQuery(queryStr.toString());
            Query countQuery = entityManager.createNativeQuery(count.toString());

            for (Map.Entry<String, Object> p : params.entrySet()) {
                query.setParameter(p.getKey(), p.getValue());
                countQuery.setParameter(p.getKey(), p.getValue());
            }
            query.setFirstResult((int) pageable.getOffset());
            query.setMaxResults(pageable.getPageSize());
            List<Object[]> objects = query.getResultList();
            Object o = countQuery.getSingleResult();
            List<NhanVienTroCapDTO> danhMucDTOS = DataUtil.convertLsObjectsToClass(Arrays.asList("id", "idNhanVien", "hoNhanVien", "tenNhanVien",
                            "tenChucVu", "tenPhongBan", "tenTroCap", "mucTroCap", "tuNgay", "mieuTa","troCap", "denNgay")
                    , objects, NhanVienTroCapDTO.class);
            return new PageImpl<>(danhMucDTOS, pageable, Long.parseLong(o.toString()));
        } catch (Exception e) {
            throw e;
        }
    }
}
