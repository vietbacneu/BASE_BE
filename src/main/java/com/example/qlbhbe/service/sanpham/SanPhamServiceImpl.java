package com.example.qlbhbe.service.sanpham;

import com.example.qlbhbe.dto.SanPhamDTO;
import com.example.qlbhbe.entity.SanPham;
import com.example.qlbhbe.mapper.SanPhamMapper;
import com.example.qlbhbe.repo.sanpham.SanPhamRepo;
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
public class SanPhamServiceImpl extends AbstractService<SanPham, Long> implements SanPhamService {

    private final SanPhamRepo sanPhamRepo;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    public SanPhamServiceImpl(SanPhamRepo sanPhamRepo) {
        super(sanPhamRepo);
        this.sanPhamRepo = sanPhamRepo;
    }

    @Override
    public SanPham update(long id, SanPhamDTO command) {
        Optional<SanPham> opt = sanPhamRepo.findById(id);
        if (opt.isPresent()) {
            SanPham sanPham = opt.get();
            return SanPhamMapper.INSTANCE.update(command, sanPham);
        }
        return null;
    }

    @Override
    public Page<SanPhamDTO> search(SanPhamDTO command, Pageable pageable) throws Exception {
        try {
            StringBuilder queryStr = new StringBuilder();
            StringBuilder count = new StringBuilder();
            StringBuilder from = new StringBuilder();
            Map<String, Object> params = new HashMap<>();
            queryStr.append("select s.id ,   " +
                    "    s.ma_san_pham    ," +
                    "    s.ten_san_pham   ," +
                    "    s.id_danh_muc   ," +
                    "    s.gia_ban_niem_yet   ," +
                    "    s.so_luong       ," +
                    "    s.mieu_ta        ," +
                    "    s.nguoi_tao      ," +
                    "    s.ngay_tao       ," +
                    "    s.nguoi_thay_doi ," +
                    "    s.ngay_thay_doi  , " +
                    "    s.gia_nhap_niem_yet   ," +
                    "     d.ten_danh_muc  ");
            count.append("select count(*) ");
            from.append(" from san_pham s, danh_muc d where s.id_danh_muc = d.id  ");
            if (!DataUtil.isNullOrEmpty(command.getTenSanPham())) {
                from.append(" and lower(s.ten_san_pham) like :ten ");
                params.put("ten", '%' + command.getTenSanPham().toLowerCase(Locale.ROOT) + '%');
            }
            if (!DataUtil.isNullOrEmpty(command.getMaSanPham())) {
                from.append(" and lower(s.ma_san_pham) like :ma ");
                params.put("ma", '%' + command.getMaSanPham().toLowerCase(Locale.ROOT) + '%');
            }
            if (!DataUtil.isNullOrEmpty(command.getTenDanhMuc())) {
                from.append(" and lower(d.ten_danh_muc) like :dm ");
                params.put("dm", '%' + command.getTenDanhMuc().toLowerCase(Locale.ROOT) + '%');
            }
            if (!DataUtil.isNullOrEmpty(command.getIdDanhMuc())) {
                from.append(" and d.id = :id ");
                params.put("id", command.getIdDanhMuc());
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
            List<SanPhamDTO> danhMucDTOS = DataUtil.convertLsObjectsToClass(Arrays.asList("id", "maSanPham", "tenSanPham", "idDanhMuc",
                            "giaBanNiemYet", "soLuong", "mieuTa", "nguoiTao", "ngayTao", "nguoiThayDoi", "ngayThayDoi", "giaNhapNiemYet", "tenDanhMuc")
                    , objects, SanPhamDTO.class);

            return new PageImpl<>(danhMucDTOS, pageable, Long.parseLong(o.toString()));
        } catch (Exception e) {
            throw e;
        }
    }
}
