package com.example.qlbhbe.service.baocao;

import com.example.qlbhbe.dto.SanPhamDTO;
import com.example.qlbhbe.util.DataUtil;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;

@Service
public class SanPhamReportImpl implements SanPhamReport {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public List<SanPhamDTO> getSanPhamTon(SanPhamDTO sanPhamDTO) throws Exception {
        try {
            StringBuilder queryStr = new StringBuilder();
            Map<String, Object> params = new HashMap<>();
            queryStr.append("select s.id,  s.ma_san_pham, s.ten_san_pham, s.gia_ban_niem_yet, s.gia_nhap_niem_yet ," +
                    " sum(nd.so_luong), nd.gia, n.id_cua_hang , (select c.ten_cua_hang from cua_hang c where c.id = n.id_cua_hang) ch, " +
                    " s.id_danh_muc , (select d.ten_danh_muc from danh_muc d where d.id = s.id_danh_muc) dm " +
                    " from san_pham s , nhap_hang n , nhap_hang_chi_tiet nd " +
                    " where s.id = nd.id_san_pham and n.id = nd.id_nhap_hang");
            if (!DataUtil.isNullOrEmpty(sanPhamDTO.getTenSanPham())) {
                queryStr.append(" and lower(s.ten_san_pham) like :ten ");
                params.put("ten", '%' + sanPhamDTO.getTenSanPham().toLowerCase(Locale.ROOT) + '%');
            }

            if (!DataUtil.isNullOrEmpty(sanPhamDTO.getMaSanPham())) {
                queryStr.append(" and lower(s.ma_san_pham) like :ma ");
                params.put("ma", '%' + sanPhamDTO.getMaSanPham().toLowerCase(Locale.ROOT) + '%');
            }
            if (!DataUtil.isNullOrEmpty(sanPhamDTO.getIdCuaHang())) {
                queryStr.append(" and n.id_cua_hang = :cuahang ");
                params.put("cuahang", sanPhamDTO.getIdCuaHang());
            }
            if (!DataUtil.isNullOrEmpty(sanPhamDTO.getIsTonKho()) && sanPhamDTO.getIsTonKho() == 1) {
                queryStr.append(" and n.ngay_het_han > now() ");
            }
            queryStr.append(" group by  s.id,  s.ma_san_pham ");

            Query query = entityManager.createNativeQuery(queryStr.toString());
            for (Map.Entry<String, Object> p : params.entrySet()) {
                query.setParameter(p.getKey(), p.getValue());

            }
            List<Object[]> objects = query.getResultList();


            List<SanPhamDTO> sanPhamDTOSNhapHangTon = DataUtil.convertLsObjectsToClass(Arrays.asList("id", "maSanPham", "tenSanPham", "giaBanNiemYet", "giaNhapNiemYet", "soLuong",
                            "gia", "idCuaHang", "tenCuaHang", "idDanhMuc", "tenDanhMuc")
                    , objects, SanPhamDTO.class);

            StringBuilder queryStr1 = new StringBuilder();
            Map<String, Object> params1 = new HashMap<>();
            queryStr1.append(" select s.id,  s.ma_san_pham, s.ten_san_pham, s.gia_ban_niem_yet, s.gia_nhap_niem_yet ," +
                    " nd.so_luong, nd.gia, n.id_cua_hang , (select c.ten_cua_hang from cua_hang c where c.id = n.id_cua_hang) ch," +
                    "    s.id_danh_muc , (select d.ten_danh_muc from danh_muc d where d.id = s.id_danh_muc) dm " +
                    " from san_pham s , xuat_hang n , xuat_hang_chi_tiet nd" +
                    " where s.id = nd.id_san_pham and n.id = nd.id_xuat_hang");
            if (!DataUtil.isNullOrEmpty(sanPhamDTO.getTenSanPham())) {
                queryStr1.append(" and lower(s.ten_san_pham) like :ten ");
                params1.put("ten", '%' + sanPhamDTO.getTenSanPham().toLowerCase(Locale.ROOT) + '%');
            }

            if (!DataUtil.isNullOrEmpty(sanPhamDTO.getMaSanPham())) {
                queryStr1.append(" and lower(s.ma_san_pham) like :ma ");
                params1.put("ma", '%' + sanPhamDTO.getMaSanPham().toLowerCase(Locale.ROOT) + '%');
            }
            if (!DataUtil.isNullOrEmpty(sanPhamDTO.getIdCuaHang())) {
                queryStr1.append(" and n.id_cua_hang = :cuahang ");
                params1.put("cuahang", sanPhamDTO.getIdCuaHang());
            }
            queryStr1.append(" group by  s.id,  s.ma_san_pham ");

            Query query1 = entityManager.createNativeQuery(queryStr1.toString());
            for (Map.Entry<String, Object> p : params1.entrySet()) {
                query.setParameter(p.getKey(), p.getValue());
            }
            List<Object[]> objects1 = query1.getResultList();

            List<SanPhamDTO> sanPhamDTOSXuatHangTon = DataUtil.convertLsObjectsToClass(Arrays.asList("id", "maSanPham", "tenSanPham", "giaBanNiemYet", "giaNhapNiemYet", "soLuong",
                            "gia", "idCuaHang", "tenCuaHang", "idDanhMuc", "tenDanhMuc")
                    , objects1, SanPhamDTO.class);

            for (SanPhamDTO phamDTO : sanPhamDTOSNhapHangTon) {
                phamDTO.setSoLuongNhap(phamDTO.getSoLuong());
                phamDTO.setSoLuong(null);
                phamDTO.setGia(null);
                phamDTO.setSoLuongBan(0d);
                phamDTO.setSoLuongTon(0d);
                for (SanPhamDTO dto : sanPhamDTOSXuatHangTon) {
                    if (phamDTO.getId().equals(dto.getId())) {
                        phamDTO.setSoLuongBan(dto.getSoLuong());
                        phamDTO.setSoLuongTon(DataUtil.safeToDouble(phamDTO.getSoLuongNhap()) - DataUtil.safeToDouble(dto.getSoLuong()));
                    }
                }
            }

            return sanPhamDTOSNhapHangTon;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<SanPhamDTO> getSanPhamChiPhiMax(SanPhamDTO sanPhamDTO) throws Exception {
        try {
            StringBuilder queryStr = new StringBuilder();
            Map<String, Object> params = new HashMap<>();
            queryStr.append(" select nd.id_san_pham, s.ma_san_pham ,s.ten_san_pham , s.gia_ban_niem_yet, s.gia_nhap_niem_yet , s.id_danh_muc ," +
                    " (select d.ten_danh_muc from danh_muc d where d.id = s.id_danh_muc) dm " +
                    " , sum(nd.so_luong * nd.gia) from san_pham s,  nhap_hang_chi_tiet nd, nhap_hang n " +
                    " where n.id = nd.id_nhap_hang and s.id = nd.id_san_pham ");
            if (!DataUtil.isNullOrEmpty(sanPhamDTO.getTenSanPham())) {
                queryStr.append(" and lower(s.ten_san_pham) like :ten ");
                params.put("ten", '%' + sanPhamDTO.getTenSanPham().toLowerCase(Locale.ROOT) + '%');
            }

            if (!DataUtil.isNullOrEmpty(sanPhamDTO.getMaSanPham())) {
                queryStr.append(" and lower(s.ma_san_pham) like :ma ");
                params.put("ma", '%' + sanPhamDTO.getMaSanPham().toLowerCase(Locale.ROOT) + '%');
            }
            if (!DataUtil.isNullOrEmpty(sanPhamDTO.getIdCuaHang())) {
                queryStr.append(" and n.id_cua_hang  = :idcuaHang ");
                params.put("idcuaHang", sanPhamDTO.getIdCuaHang());
            }
            queryStr.append("  group by nd.id_san_pham  ");

            Query query = entityManager.createNativeQuery(queryStr.toString());
            for (Map.Entry<String, Object> p : params.entrySet()) {
                query.setParameter(p.getKey(), p.getValue());

            }
            List<Object[]> objects = query.getResultList();


            List<SanPhamDTO> sanPhamDTOSNhapHangTon = DataUtil.convertLsObjectsToClass(Arrays.asList("id", "maSanPham", "tenSanPham", "giaBanNiemYet",
                            "giaNhapNiemYet", "idDanhMuc", "tenDanhMuc", "totalChiPhi")
                    , objects, SanPhamDTO.class);

            return sanPhamDTOSNhapHangTon;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<SanPhamDTO> getSanPhamDoanhThuMax(SanPhamDTO sanPhamDTO) throws Exception {
        try {
            StringBuilder queryStr = new StringBuilder();
            Map<String, Object> params = new HashMap<>();
            queryStr.append(" select nd.id_san_pham, s.ma_san_pham ,s.ten_san_pham , s.gia_ban_niem_yet, s.gia_nhap_niem_yet , s.id_danh_muc ," +
                    " (select d.ten_danh_muc from danh_muc d where d.id = s.id_danh_muc) dm " +
                    " , sum(nd.so_luong * nd.gia) from san_pham s,  xuat_hang_chi_tiet nd, xuat_hang n " +
                    " where n.id = nd.id_xuat_hang and s.id = nd.id_san_pham ");
            if (!DataUtil.isNullOrEmpty(sanPhamDTO.getTenSanPham())) {
                queryStr.append(" and lower(s.ten_san_pham) like :ten ");
                params.put("ten", '%' + sanPhamDTO.getTenSanPham().toLowerCase(Locale.ROOT) + '%');
            }

            if (!DataUtil.isNullOrEmpty(sanPhamDTO.getMaSanPham())) {
                queryStr.append(" and lower(s.ma_san_pham) like :ma ");
                params.put("ma", '%' + sanPhamDTO.getMaSanPham().toLowerCase(Locale.ROOT) + '%');
            }
            if (!DataUtil.isNullOrEmpty(sanPhamDTO.getIdCuaHang())) {
                queryStr.append(" and n.id_cua_hang  = :idcuaHang ");
                params.put("idcuaHang", sanPhamDTO.getIdCuaHang());
            }
            queryStr.append("  group by nd.id_san_pham  ");

            Query query = entityManager.createNativeQuery(queryStr.toString());
            for (Map.Entry<String, Object> p : params.entrySet()) {
                query.setParameter(p.getKey(), p.getValue());

            }
            List<Object[]> objects = query.getResultList();


            List<SanPhamDTO> sanPhamDTOSNhapHangTon = DataUtil.convertLsObjectsToClass(Arrays.asList("id", "maSanPham", "tenSanPham", "giaBanNiemYet",
                            "giaNhapNiemYet", "idDanhMuc", "tenDanhMuc", "totalDoanhThu")
                    , objects, SanPhamDTO.class);

            return sanPhamDTOSNhapHangTon;
        } catch (Exception e) {
            throw e;
        }
    }
}
