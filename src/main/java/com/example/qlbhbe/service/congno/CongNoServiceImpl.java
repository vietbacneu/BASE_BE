package com.example.qlbhbe.service.congno;

import com.example.qlbhbe.controller.response.CreatedIdResponse;
import com.example.qlbhbe.dto.CongNoChiTietDTO;
import com.example.qlbhbe.dto.CongNoDTO;
import com.example.qlbhbe.entity.CongNo;
import com.example.qlbhbe.entity.CongNoChiTiet;
import com.example.qlbhbe.entity.NhanVien;
import com.example.qlbhbe.mapper.CongNoChiTietMapper;
import com.example.qlbhbe.mapper.CongNoMapper;
import com.example.qlbhbe.repo.congno.CongNoRepo;
import com.example.qlbhbe.repo.congnochitiet.CongNoChiTietRepo;
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
public class CongNoServiceImpl extends AbstractService<CongNo, Long> implements CongNoService {

    private final CongNoRepo congNoRepo;

    @Autowired
    CongNoChiTietMapper congNoChiTietMapper;

    @Autowired
    CongNoChiTietRepo congNoChiTietRepo;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    public CongNoServiceImpl(CongNoRepo congNoRepo) {
        super(congNoRepo);
        this.congNoRepo = congNoRepo;
    }

    @Override
    public CongNo update(long id, CongNoDTO command) {
        Optional<CongNo> opt = congNoRepo.findById(id);
        if (opt.isPresent()) {
            congNoRepo.deleteCongNo(id);
            congNoChiTietRepo.deleteCongNo(id);
        }
        command.setId(null);
        for (CongNoChiTietDTO nhapHangChiTietDTO : command.getCongNoChiTietDTOS()) {
            nhapHangChiTietDTO.setId(null);
        }
        create(command);
        return opt.get();
    }

    @Override
    public CreatedIdResponse create(CongNoDTO command) {
        CongNo congNo = CongNoMapper.INSTANCE.create(command);
        NhanVien nhanVien = new NhanVien();
        nhanVien.setId(command.getIdNhanVien());
        congNo.setIdNhanVien(nhanVien);
        congNoRepo.save(congNo);
        List<CongNoChiTietDTO> congNoChiTietList = command.getCongNoChiTietDTOS();
        for (CongNoChiTietDTO congNoChiTietDTO : congNoChiTietList) {
            CongNoChiTiet congNoChiTiet = congNoChiTietMapper.toCongNoChiTiet(congNoChiTietDTO);
            congNoChiTietRepo.save(congNoChiTiet);
        }
        return new CreatedIdResponse(congNo.getId());
    }

    @Override
    public Page<CongNoDTO> search(CongNoDTO command, Pageable pageable) throws Exception {
        try {
            StringBuilder queryStr = new StringBuilder();
            StringBuilder count = new StringBuilder();
            StringBuilder from = new StringBuilder();
            Map<String, Object> params = new HashMap<>();
            queryStr.append("select cn.id,\n" +
                    "       cn.id,\n" +
                    "       cn.ma_cong_no,\n" +
                    "       cn.load_hop_dong,\n" +
                    "       case\n" +
                    "           when cn.load_hop_dong = 'nhaphang' then 'Nhập hàng'\n" +
                    "           when cn.load_hop_dong = 'xuathang' then 'Xuất hàng' end                                   loaiHopDongName,\n" +
                    "       cn.id_hop_dong,\n" +
                    "       case\n" +
                    "           when cn.load_hop_dong = 'nhaphang' then (select hop_dong_nhap_hang.ma_nhap_hang\n" +
                    "                                                    from hop_dong_nhap_hang\n" +
                    "                                                    where hop_dong_nhap_hang.id = cn.id_hop_dong)\n" +
                    "           when cn.load_hop_dong = 'xuathang' then (select hop_dong_ban_hang.ma_hop_dong_ban_hang\n" +
                    "                                                    from hop_dong_ban_hang\n" +
                    "                                                    where hop_dong_ban_hang.id = cn.id_hop_dong) end maHopDong,\n" +
                    "       cn.trang_thai_thanh_toan,\n" +
                    "       case\n" +
                    "           when cn.trang_thai_thanh_toan = 'chuathanhtoan' then 'Chưa thanh toán'\n" +
                    "           when cn.trang_thai_thanh_toan = 'dathanhtoan' then 'Đã thanh toán' end                    trangThaiName,\n" +
                    "       cn.id_nhan_vien,\n" +
                    "       cn.so_tien\n" +
                    "from cong_no cn,\n" +
                    "     cong_no_chi_tiet cnd\n" +
                    "where cn.id = cnd.id_cong_no   ");


            count.append("");
            if (!DataUtil.isNullOrEmpty(command.getLoadHopDong())) {
                from.append(" and lower(cn.load_hop_dong) like :ten ");
                params.put("ten", '%' + command.getLoadHopDong().toLowerCase(Locale.ROOT) + '%');
            }
            if (!DataUtil.isNullOrEmpty(command.getMaCongNo())) {
                from.append(" and lower(s.ma_cong_no) like :ma ");
                params.put("ma", '%' + command.getMaCongNo().toLowerCase(Locale.ROOT) + '%');
            }
            queryStr.append(from);
            Query query = entityManager.createNativeQuery(queryStr.toString());
            Query countQuery = entityManager.createNativeQuery("select count(*) from (" + queryStr.toString() + ") tmp");
            for (Map.Entry<String, Object> p : params.entrySet()) {
                query.setParameter(p.getKey(), p.getValue());
                countQuery.setParameter(p.getKey(), p.getValue());
            }
            if (!DataUtil.isNullOrEmpty(command.getIsCount())) {
                query.setFirstResult((int) pageable.getOffset());
                query.setMaxResults(pageable.getPageSize());
            }
            List<Object[]> objects = query.getResultList();
            Object o = countQuery.getSingleResult();
            List<CongNoDTO> danhMucDTOS = DataUtil.convertLsObjectsToClass(Arrays.asList("id", "maCongNo", "loaiHopDong", "loaiHopDongName",
                            "idHopDong", "maHopDong", "trangThaiThanhToan", "idNhanVien", "soTien")
                    , objects, CongNoDTO.class);

            for (CongNoDTO danhMucDTO : danhMucDTOS) {
                List<CongNoChiTietDTO> congNoChiTietDTO = congNoChiTietMapper.toCongNoChiTietDTOList(congNoChiTietRepo.searchByCongNoId(danhMucDTO.getId()));
                danhMucDTO.setCongNoChiTietDTOS(congNoChiTietDTO);
            }
            return new PageImpl<>(danhMucDTOS, pageable, Long.parseLong(o.toString()));
        } catch (Exception e) {
            throw e;
        }
    }


}
