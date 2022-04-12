package com.example.qlbhbe.service.nhaphangchitiet;

import com.example.qlbhbe.dto.NhapHangChiTietDTO;
import com.example.qlbhbe.entity.NhapHangChiTiet;
import com.example.qlbhbe.repo.nhaphangchitiet.NhapHangChiTietRepo;
import com.example.qlbhbe.service.AbstractService;
import com.example.qlbhbe.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class NhapHangChiTietServiceImpl extends AbstractService<NhapHangChiTiet, Long> implements NhapHangChiTietService {

    private final NhapHangChiTietRepo nhapHangChiTietRepo;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    public NhapHangChiTietServiceImpl(NhapHangChiTietRepo nhapHangChiTietRepo) {
        super(nhapHangChiTietRepo);
        this.nhapHangChiTietRepo = nhapHangChiTietRepo;
    }

    @Override
    public List<NhapHangChiTietDTO> search(NhapHangChiTietDTO command) throws Exception {
        try {
            StringBuilder queryStr = new StringBuilder();
            StringBuilder count = new StringBuilder();
            StringBuilder from = new StringBuilder();
            Map<String, Object> params = new HashMap<>();
            queryStr.append("select id ," +
                    "    id_nhap_hang," +
                    "    id_nguyen_vat_lieu ," +
                    "    so_luong ," +
                    "    gia," +
                    "    mieu_ta , " +
                    " (select ten_nguyen_vat_lieu from nguyen_vat_lieu s where s.id = id_nguyen_vat_lieu) tenSanpham, so_luong*gia  ");

            count.append("select count(*) ");
            from.append(" from nhap_hang_chi_tiet where 1=1 ");
            if (!DataUtil.isNullOrEmpty(command.getId())) {
                from.append(" and id = :id ");
                params.put("id", command.getId());
            }
            if (!DataUtil.isNullOrEmpty(command.getIdNhapHang())) {
                from.append(" and id_nhap_hang like :idNhapHang ");
                params.put("idNhapHang", command.getIdNhapHang());
            }
            queryStr.append(from);
            count.append(from);
            Query query = entityManager.createNativeQuery(queryStr.toString());
            Query countQuery = entityManager.createNativeQuery(count.toString());
            for (Map.Entry<String, Object> p : params.entrySet()) {
                query.setParameter(p.getKey(), p.getValue());
                countQuery.setParameter(p.getKey(), p.getValue());
            }
            List<Object[]> objects = query.getResultList();
            Object o = countQuery.getSingleResult();
            List<NhapHangChiTietDTO> danhMucDTOS = DataUtil.convertLsObjectsToClass(
                    Arrays.asList("id", "idNhapHang", "idSanPham", "soLuong", "gia", "mieuTa", "tenSanPham", "tongTien"),
                    objects, NhapHangChiTietDTO.class);
            return danhMucDTOS;
        } catch (Exception e) {
            throw e;
        }
    }
}
