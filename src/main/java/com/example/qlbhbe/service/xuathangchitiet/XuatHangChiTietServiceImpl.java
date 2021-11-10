package com.example.qlbhbe.service.xuathangchitiet;

import com.example.qlbhbe.controller.request.UpdateXuatHangChiTietRequest;
import com.example.qlbhbe.dto.NhapHangChiTietDTO;
import com.example.qlbhbe.dto.XuatHangChiTietDTO;
import com.example.qlbhbe.entity.XuatHangChiTiet;
import com.example.qlbhbe.mapper.XuatHangChiTietMapper;
import com.example.qlbhbe.repo.xuathangchitiet.XuatHangChiTietRepo;
import com.example.qlbhbe.service.AbstractService;
import com.example.qlbhbe.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;


@Service
@Transactional
public class XuatHangChiTietServiceImpl extends AbstractService<XuatHangChiTiet, Long> implements XuatHangChiTietService {

    private final XuatHangChiTietRepo xuatHangChiTietRepo;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    public XuatHangChiTietServiceImpl(XuatHangChiTietRepo xuatHangChiTietRepo) {
        super(xuatHangChiTietRepo);
        this.xuatHangChiTietRepo = xuatHangChiTietRepo;
    }

    @Override
    public XuatHangChiTiet update(long id, UpdateXuatHangChiTietRequest command) {
        Optional<XuatHangChiTiet> opt = xuatHangChiTietRepo.findById(id);
        if (opt.isPresent()) {
            XuatHangChiTiet xuatHangChiTiet = opt.get();
            return XuatHangChiTietMapper.INSTANCE.update(command, xuatHangChiTiet);
        }
        return null;
    }

    @Override
    public List<XuatHangChiTietDTO> search(XuatHangChiTietDTO command) throws Exception {
        try {
            StringBuilder queryStr = new StringBuilder();
            StringBuilder count = new StringBuilder();
            StringBuilder from = new StringBuilder();
            Map<String, Object> params = new HashMap<>();
            queryStr.append("select id ," +
                    "    id_xuat_hang ," +
                    "    id_san_pham ," +
                    "    so_luong ," +
                    "    gia," +
                    "    mieu_ta , ngay_het_han , ngay_san_xuat, (select ten_san_pham from san_pham s where s.id = id_san_pham) tenSanpham , so_luong*gia ");


            count.append("select count(*) ");
            from.append(" from xuat_hang_chi_tiet where 1=1 ");
            if (!DataUtil.isNullOrEmpty(command.getId())) {
                from.append(" and id = :id ");
                params.put("id", command.getId());
            }
            if (!DataUtil.isNullOrEmpty(command.getIdXuatHang())) {
                from.append(" and id_xuat_hang like :idNhapHang ");
                params.put("idNhapHang", command.getIdXuatHang());
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
            List<XuatHangChiTietDTO> danhMucDTOS = DataUtil.convertLsObjectsToClass(
                    Arrays.asList("id", "idXuatHang", "idSanPham", "soLuong", "gia", "mieuTa","ngayHetHan","ngaySanXuat","tenSanPham", "tongTien"),
                    objects, XuatHangChiTietDTO.class);
            return danhMucDTOS;
        } catch (Exception e) {
            throw e;
        }
    }
}
