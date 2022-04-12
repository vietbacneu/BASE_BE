package com.example.qlbhbe.service.banhang;

import com.example.qlbhbe.dto.BanHangChiTietDTO;
import com.example.qlbhbe.dto.BanHangDTO;
import com.example.qlbhbe.dto.MessageDTO;
import com.example.qlbhbe.entity.*;
import com.example.qlbhbe.mapper.BanHangChiTietMapper;
import com.example.qlbhbe.mapper.BanHangMapper;
import com.example.qlbhbe.repo.banhang.BanHangRepo;
import com.example.qlbhbe.repo.banhangchitiet.BanHangChiTietRepo;
import com.example.qlbhbe.repo.thucdon.ThucDonRepo;
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
public class BanHangServiceImpl extends AbstractService<BanHang, Long> implements BanHangService {

    private final BanHangRepo banHangRepo;

    @Autowired
    private BanHangChiTietMapper banHangChiTietMapper;

    @Autowired
    private BanHangChiTietRepo banHangChiTietRepo;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    ThucDonRepo thucDonRepo;

    @Autowired
    public BanHangServiceImpl(BanHangRepo banHangRepo) {
        super(banHangRepo);
        this.banHangRepo = banHangRepo;
    }

    @Override
    public BanHang update(long id, BanHangDTO command) {
        Optional<BanHang> opt = banHangRepo.findById(id);
        if (opt.isPresent()) {
            banHangChiTietRepo.deleteSanPham(id);
            banHangRepo.deleteSanPham(id);
        }
        command.setId(null);
        for (BanHangChiTietDTO nhapHangChiTietDTO : command.getBanHangChiTietDTOS()) {
            nhapHangChiTietDTO.setId(null);
            nhapHangChiTietDTO.setIdBanHang(null);
        }
        save(command);
        return new BanHang();
    }

    @Override
    public MessageDTO save(BanHangDTO command) {
        BanHang banHang = BanHangMapper.INSTANCE.create(command);
        KhachHang khachHang = new KhachHang();
        khachHang.setId(command.getIdKhachHang());
        banHang.setThanhToan(new PhuongThucThanhToan(command.getIdPhuongThuc()));
        banHang.setKhachHang(khachHang);
        banHangRepo.save(banHang);
        List<BanHangChiTietDTO> ls = command.getBanHangChiTietDTOS();
        for (BanHangChiTietDTO l : ls) {
            BanHangChiTiet nhapHangChiTiet = banHangChiTietMapper.toBanHangEntity(l);
            ThucDon sanPham = new ThucDon();
            sanPham.setId(l.getIdThucDon());
            nhapHangChiTiet.setThucDon(sanPham);
            nhapHangChiTiet.setBanHang(banHang);
            banHangChiTietRepo.save(nhapHangChiTiet);
        }
        return new MessageDTO("Thêm mới thành công", 200l);
    }

    @Override
    public Page<BanHangDTO> search(BanHangDTO command, Pageable pageable) throws Exception {
        try {
            StringBuilder queryStr = new StringBuilder();
            StringBuilder count = new StringBuilder();
            StringBuilder from = new StringBuilder();
            Map<String, Object> params = new HashMap<>();
            queryStr.append("select s.id              ," +
                    "    s.ma_ban_hang    ," +
                    "    s.id_khach_hang ," +
                    "    s.ngay_ban       ," +
                    "    s.nguoi_tao       ," +
                    "    s.ngay_tao        ," +
                    "    s.nguoi_thay_doi  ," +
                    "    s.ngay_thay_doi   ," +
                    "    n.ten_khach_hang, s.id_thanh_toan, (select t.ten_phuong_thuc from phuong_thuc_thanh_toan t where t.id = s.id_thanh_toan) ," +
                    "    s.trang_thai, case when s.trang_thai = 'chuathanhtoan' then 'Chưa thanh toán' else 'Đã thanh toán' end trangThaiName    ");
            count.append("select count(*) ");
            from.append(" from ban_hang s, khach_hang n where s.id_khach_hang = n.id  ");
            if (!DataUtil.isNullOrEmpty(command.getKhachHangTen())) {
                from.append(" and lower(n.ten_khach_hang) like :ten ");
                params.put("ten", '%' + command.getKhachHangTen().toLowerCase(Locale.ROOT) + '%');
            }
            if (!DataUtil.isNullOrEmpty(command.getMaBanHang())) {
                from.append(" and lower(s.ma_ban_hang) like :ma ");
                params.put("ma", '%' + command.getMaBanHang().toLowerCase(Locale.ROOT) + '%');
            }

            queryStr.append(from).append("order by ngay_ban desc ");
            count.append(from);
            Query query = entityManager.createNativeQuery(queryStr.toString());
            Query countQuery = entityManager.createNativeQuery(count.toString());
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
            List<BanHangDTO> danhMucDTOS = DataUtil.convertLsObjectsToClass(Arrays.asList("id", "maBanHang", "idKhachHang", "ngayBan", "nguoiTao", "ngayTao",
                            "nguoiThayDoi", "ngayThayDoi", "khachHangTen", "idPhuongThuc", "tenPhuongThuc", "trangThai", "trangThaiName")
                    , objects, BanHangDTO.class);
            Double total = 0d;
            for (BanHangDTO danhMucDTO : danhMucDTOS) {
                List<BanHangChiTiet> banHangChiTiets = banHangChiTietRepo.getList(danhMucDTO.getId());
                List<BanHangChiTietDTO> banHangChiTietDTOS = new ArrayList<>();
                for (BanHangChiTiet banHangChiTiet : banHangChiTiets) {
                    BanHangChiTietDTO banHangChiTietDTO = new BanHangChiTietDTO();
                    banHangChiTietDTO.setIdThucDon(banHangChiTiet.getThucDon().getId());
                    Optional<ThucDon> thucDon = thucDonRepo.findById(banHangChiTiet.getThucDon().getId());
                    if (thucDon.isPresent()) {
                        banHangChiTietDTO.setTenThucDon(thucDon.get().getTenThucDon());
                    }
                    banHangChiTietDTO.setIdBanHang(banHangChiTiet.getBanHang().getId());
                    banHangChiTietDTO.setId(banHangChiTiet.getId());
                    banHangChiTietDTO.setSoLuong(banHangChiTiet.getSoLuong());
                    banHangChiTietDTO.setGia(banHangChiTiet.getGia());
                    banHangChiTietDTO.setMieuTa(banHangChiTiet.getMieuTa());
                    banHangChiTietDTO.setTongTien(banHangChiTiet.getSoLuong() * banHangChiTiet.getGia());
                    banHangChiTietDTOS.add(banHangChiTietDTO);
                    total += banHangChiTiet.getSoLuong() * banHangChiTiet.getGia();
                }
                danhMucDTO.setBanHangChiTietDTOS(banHangChiTietDTOS);
                danhMucDTO.setSoTien(total);
            }

            return new PageImpl<>(danhMucDTOS, pageable, Long.parseLong(o.toString()));
        } catch (Exception e) {
            throw e;
        }
    }
}
