package com.example.qlbhbe.service.nhanvienkhenthuong;

import com.example.qlbhbe.dto.NhanVienKhenThuongDTO;
import com.example.qlbhbe.entity.KhenThuong;
import com.example.qlbhbe.entity.NhanVien;
import com.example.qlbhbe.entity.NhanVienKhenThuong;
import com.example.qlbhbe.mapper.NhanVienKhenThuongMapper;
import com.example.qlbhbe.repo.nhanvienkhenthuong.NhanVienKhenThuongRepo;
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
public class NhanVienKhenThuongServiceImpl extends AbstractService<NhanVienKhenThuong, Long> implements NhanVienKhenThuongService {

    private final NhanVienKhenThuongRepo nhanVienKhenThuongRepo;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    public NhanVienKhenThuongServiceImpl(NhanVienKhenThuongRepo nhanVienKhenThuongRepo) {
        super(nhanVienKhenThuongRepo);
        this.nhanVienKhenThuongRepo = nhanVienKhenThuongRepo;
    }

    @Override
    public NhanVienKhenThuong update(long id, NhanVienKhenThuongDTO command) {
        Optional<NhanVienKhenThuong> opt = nhanVienKhenThuongRepo.findById(id);
        if (opt.isPresent()) {
            NhanVienKhenThuong nhanVienKhenThuong = opt.get();
            NhanVien nhanVien = new NhanVien();
            nhanVien.setId(command.getNhanVienId());
            nhanVienKhenThuong.setNhanVien(nhanVien);
            KhenThuong kt = new KhenThuong();
            kt.setId(command.getIdKhenThuong());
            nhanVienKhenThuong.setKhenThuong(kt);
            return NhanVienKhenThuongMapper.INSTANCE.update(command, nhanVienKhenThuong);
        }
        return null;
    }

    @Override
    public Page<NhanVienKhenThuongDTO> search(NhanVienKhenThuongDTO command, Pageable pageable) throws Exception {
        try {
            StringBuilder queryStr = new StringBuilder();
            StringBuilder count = new StringBuilder();
            StringBuilder from = new StringBuilder();
            Map<String, Object> params = new HashMap<>();
            queryStr.append(" select nbh.id asNvbh, n.id as nvId, n.ho , n.ten, " +
                    "(select ten_chuc_vu from chuc_vu c where c.id = n.id_chuc_vu) tenChucvu, " +
                    "(select ten from phong_ban c where c.id = n.id_phong_ban) tenPP, " +
                    " bh.ten as tenBh, bh.muc_thuong, nbh.ngay, nbh.mieu_ta, bh.id ");

            count.append("select count(*) ");
            from.append("  from nhan_vien n, nhan_vien_khen_thuong nbh, khen_thuong bh " +
                    " where n.id = nbh.id_nhan_vien and nbh.id_khen_thuong = bh.id");
            if (!DataUtil.isNullOrEmpty(command.getTenNhanVien())) {
                from.append("   and concat( lower(n.ho), ' ' ,lower(n.ten)  ) like :ten ");
                params.put("ten", '%' + command.getTenNhanVien().toLowerCase(Locale.ROOT) + '%');
            }
            if (!DataUtil.isNullOrEmpty(command.getIdKhenThuong())) {
                from.append(" and bh.id = :ma ");
                params.put("ma", command.getIdKhenThuong());
            }
            if (!DataUtil.isNullOrEmpty(command.getMonth())) {
                from.append(" and month(nbh.ngay) = :month ");
                params.put("month", command.getMonth().substring(5));
                from.append(" and year(nbh.ngay) = :year ");
                params.put("year", command.getMonth().substring(0,4));
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
            List<NhanVienKhenThuongDTO> danhMucDTOS = DataUtil.convertLsObjectsToClass(Arrays.asList("id","idNhanVien", "hoNhanVien", "tenNhanVien",
                            "tenChucVu", "tenPhongBan", "tenKhenThuong", "khenThuongMucThuong", "ngay", "mieuTa","idKhenThuong")
                    , objects, NhanVienKhenThuongDTO.class);

            return new PageImpl<>(danhMucDTOS, pageable, Long.parseLong(o.toString()));
        } catch (Exception e) {
            throw e;
        }
    }
}
