package com.example.qlbhbe.service.xuathang;

import com.example.qlbhbe.dto.MessageDTO;
import com.example.qlbhbe.dto.XuatHangChiTietDTO;
import com.example.qlbhbe.dto.XuatHangDTO;
import com.example.qlbhbe.entity.*;
import com.example.qlbhbe.mapper.XuatHangChiTietMapper;
import com.example.qlbhbe.mapper.XuatHangMapper;
import com.example.qlbhbe.repo.xuathang.XuatHangRepo;
import com.example.qlbhbe.repo.xuathangchitiet.XuatHangChiTietRepo;
import com.example.qlbhbe.service.AbstractService;
import com.example.qlbhbe.service.xuathangchitiet.XuatHangChiTietService;
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
public class XuatHangServiceImpl extends AbstractService<XuatHang, Long> implements XuatHangService {

    private final XuatHangRepo xuatHangRepo;

    @Autowired
    XuatHangMapper xuatHangMapper;

    @Autowired
    XuatHangChiTietRepo xuatHangChiTietRepo;

    @Autowired
    XuatHangChiTietMapper xuatHangChiTietMapper;

    @Autowired
    XuatHangChiTietService xuatHangChiTietService;


    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    public XuatHangServiceImpl(XuatHangRepo xuatHangRepo) {
        super(xuatHangRepo);
        this.xuatHangRepo = xuatHangRepo;
    }

    @Override
    @Transactional
    public MessageDTO update(long id, XuatHangDTO command) {
        Optional<XuatHang> opt = xuatHangRepo.findById(id);
        if (opt.isPresent()) {
            xuatHangChiTietRepo.deleteSanPham(id);
            xuatHangRepo.deleteSanPham(id);
        }
        command.setId(null);
        for (XuatHangChiTietDTO nhapHangChiTietDTO : command.getXuatHangChiTietDTOList()) {
            nhapHangChiTietDTO.setId(null);
            nhapHangChiTietDTO.setIdXuatHang(null);
        }
        save(command);
        return new MessageDTO("Cập nhật thành công", 200l);
    }

    @Override
    public Page<XuatHangDTO> search(XuatHangDTO command, Pageable pageable) throws Exception {
        try {
            StringBuilder queryStr = new StringBuilder();
            StringBuilder count = new StringBuilder();
            StringBuilder from = new StringBuilder();
            Map<String, Object> params = new HashMap<>();
            queryStr.append("select s.id              ," +
                    "    s.ma_xuat_hang    ," +
                    "    s.nguoi_tao       ," +
                    "    s.ngay_tao        ," +
                    "    s.nguoi_thay_doi  ," +
                    "    s.ngay_thay_doi   ," +
                    "    s.ngay_xuat  ");

            count.append("select count(*) ");
            from.append(" from xuat_hang s ");
            if (!DataUtil.isNullOrEmpty(command.getMaXuatHang())) {
                from.append(" and lower(s.ma_xuat_hang) like :ma ");
                params.put("ma", '%' + command.getMaXuatHang().toLowerCase(Locale.ROOT) + '%');
            }
            if (!DataUtil.isNullOrEmpty(command.getStartDate())) {
                from.append(" and s.ngay_xuat >= to_date(:startDate,'dd/MM/yyyy') ");
                params.put("startDate", command.getStartDate());
            }
            if (!DataUtil.isNullOrEmpty(command.getEndDate())) {
                from.append(" and s.ngay_xuat <= to_date(:endDate,'dd/MM/yyyy') + 1 ");
                params.put("endDate", command.getEndDate());
            }
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
            List<XuatHangDTO> danhMucDTOS = DataUtil.convertLsObjectsToClass(Arrays.asList("id", "maXuatHang", "nguoiTao", "ngayTao",
                            "nguoiThayDoi", "ngayThayDoi","ngayXuat")
                    , objects, XuatHangDTO.class);
            for (XuatHangDTO danhMucDTO : danhMucDTOS) {
                XuatHangChiTietDTO xuatHangChiTietDTO = new XuatHangChiTietDTO();
                xuatHangChiTietDTO.setIdXuatHang(danhMucDTO.getId());
                List<XuatHangChiTietDTO> tm = xuatHangChiTietService.search(xuatHangChiTietDTO);
                danhMucDTO.setXuatHangChiTietDTOList(tm);
            }
            return new PageImpl<>(danhMucDTOS, pageable, Long.parseLong(o.toString()));
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional
    public MessageDTO save(XuatHangDTO xuatHangDTO) {
        XuatHang nhapHang = xuatHangMapper.toXuatHangEntity(xuatHangDTO);
        XuatHang newNH = xuatHangRepo.save(nhapHang);
        List<XuatHangChiTietDTO> ls = xuatHangDTO.getXuatHangChiTietDTOList();
        for (XuatHangChiTietDTO l : ls) {
            XuatHangChiTiet xuatHangChiTiet = xuatHangChiTietMapper.toXuatHangChiTietEntity(l);
            SanPham sanPham = new SanPham();
            sanPham.setId(l.getIdSanPham());
            xuatHangChiTiet.setSanPham(sanPham);
            xuatHangChiTiet.setXuatHang(newNH);
            xuatHangChiTietRepo.save(xuatHangChiTiet);
        }
        return new MessageDTO("Thêm mới thành công", 200l);
    }

    @Override
    public List<XuatHangDTO> searchXuatMax(XuatHangDTO command) throws Exception {
        return null;
    }

    @Override
    public Map<String, String> exportXuatMax(XuatHangDTO command) throws Exception {
        return null;
    }
}
