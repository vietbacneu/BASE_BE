package com.example.qlbhbe.service.khachhang;

import com.example.qlbhbe.dto.KhachHangDTO;
import com.example.qlbhbe.entity.KhachHang;
import com.example.qlbhbe.mapper.KhachHangMapper;
import com.example.qlbhbe.repo.khachhang.KhachHangRepo;
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
public class KhachHangServiceImpl extends AbstractService<KhachHang, Long> implements KhachHangService {

    private final KhachHangRepo khachHangRepo;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    public KhachHangServiceImpl(KhachHangRepo khachHangRepo) {
        super(khachHangRepo);
        this.khachHangRepo = khachHangRepo;
    }

    @Override
    public KhachHang update(long id, KhachHangDTO command) {
        Optional<KhachHang> opt = khachHangRepo.findById(id);
        if (opt.isPresent()) {
            KhachHang khachHang = opt.get();
            return KhachHangMapper.INSTANCE.update(command, khachHang);
        }
        return null;
    }

    @Override
    public Page<KhachHangDTO> search(KhachHangDTO command, Pageable pageable) throws Exception {
        try {
            StringBuilder queryStr = new StringBuilder();
            StringBuilder count = new StringBuilder();
            StringBuilder from = new StringBuilder();
            Map<String, Object> params = new HashMap<>();
            queryStr.append("select id    , " +
                    "    ma_khach_hang  , " +
                    "    ten_khach_hang , " +
                    "    dia_chi       , " +
                    "    sdt            , " +
                    "    email            , " +
                    "    mieu_ta        , " +
                    "    nguoi_tao      , " +
                    "    ngay_tao      , " +
                    "    ngay_thay_doi    , " +
                    "    nguoi_thay_doi     ");
            count.append("select count(*) ");
            from.append(" from khach_hang where 1 = 1 ");
            if (!DataUtil.isNullOrEmpty(command.getTenKhachHang())) {
                from.append(" and lower(ten_khach_hang) like :ten ");
                params.put("ten", '%' + command.getTenKhachHang().toLowerCase(Locale.ROOT) + '%');
            }
            if (!DataUtil.isNullOrEmpty(command.getMaKhachHang())) {
                from.append(" and lower(ma_khach_hang) like :ma ");
                params.put("ma", '%' + command.getMaKhachHang().toLowerCase(Locale.ROOT) + '%');
            }
            if (!DataUtil.isNullOrEmpty(command.getSdt())) {
                from.append(" and lower(sdt) like :sdt ");
                params.put("sdt", '%' + command.getSdt().toLowerCase(Locale.ROOT) + '%');
            }
            if (!DataUtil.isNullOrEmpty(command.getDiaChi())) {
                from.append(" and lower(dia_chi) like :diachi ");
                params.put("diachi", '%' + command.getDiaChi().toLowerCase(Locale.ROOT) + '%');
            }
            if (!DataUtil.isNullOrEmpty(command.getEmail())) {
                from.append(" and lower(email) like :thue ");
                params.put("thue", '%' + command.getEmail().toLowerCase(Locale.ROOT) + '%');
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
            List<KhachHangDTO> danhMucDTOS = DataUtil.convertLsObjectsToClass(Arrays.asList("id", "maKhachHang", "tenKhachHang",
                            "diaChi", "sdt", "email", "mieuTa", "nguoiTao", "ngayTao", "ngayThayDoi", "nguoiThayDoi")
                    , objects, KhachHangDTO.class);

            return new PageImpl<>(danhMucDTOS, pageable, Long.parseLong(o.toString()));
        } catch (Exception e) {
            throw e;
        }
    }
}
