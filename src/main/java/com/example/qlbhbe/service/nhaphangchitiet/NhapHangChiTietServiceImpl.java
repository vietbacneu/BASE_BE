package com.example.qlbhbe.service.nhaphangchitiet;

import com.example.qlbhbe.controller.request.UpdateNhapHangChiTietRequest;
import com.example.qlbhbe.dto.NhapHangChiTietDTO;
import com.example.qlbhbe.entity.NhapHangChiTiet;
import com.example.qlbhbe.mapper.NhapHangChiTietMapper;
import com.example.qlbhbe.repo.nhaphangchitiet.NhapHangChiTietRepo;
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
    public NhapHangChiTiet update(long id, UpdateNhapHangChiTietRequest command) {
        Optional<NhapHangChiTiet> opt = nhapHangChiTietRepo.findById(id);
        if (opt.isPresent()) {
            NhapHangChiTiet nhapHangChiTiet = opt.get();
            return NhapHangChiTietMapper.INSTANCE.update(command, nhapHangChiTiet);
        }
        return null;
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
                    "    id_san_pham ," +
                    "    so_luong ," +
                    "    gia," +
                    "    mieu_ta , ngay_het_han , ngay_san_xuat ");
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
                    Arrays.asList("id", "idNhapHang", "idSanPham", "soLuong", "mieuTa","ngayHetHan","ngaySanXuat"),
                    objects, NhapHangChiTietDTO.class);
            return danhMucDTOS;
        } catch (Exception e) {
            throw e;
        }
    }
}
