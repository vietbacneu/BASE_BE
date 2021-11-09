package com.example.qlbhbe.repo.khachhang;

import com.example.qlbhbe.dto.KhachHangDetailsDTO;
import com.example.qlbhbe.entity.KhachHang_;
import com.example.qlbhbe.entity.KhachHang;
import com.example.qlbhbe.repo.AbstractDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

@Repository
public class KhachHangDetailsDTORepoImpl extends AbstractDetailsRepo<KhachHangDetailsDTO, KhachHang, Long> implements KhachHangDetailsDTORepo {

    @Autowired
    public KhachHangDetailsDTORepoImpl(EntityManager em) {
        super(em, KhachHangDetailsDTO.class, KhachHang.class);
    }

    @Override
    public Selection[] getDetailsSelections(Root<KhachHang> root) {
        return new Selection[] {
            root.get(KhachHang_.ID),
            root.get(KhachHang_.MA_KHACH_HANG),
            root.get(KhachHang_.TEN_KHACH_HANG),
            root.get(KhachHang_.EMAIL),
            root.get(KhachHang_.SDT),
            root.get(KhachHang_.DIA_CHI),
            root.get(KhachHang_.TRANG_THAI),
            root.get(KhachHang_.MIEU_TA),
            root.get(KhachHang_.NGAY_TAO),
            root.get(KhachHang_.NGUOI_TAO),
            root.get(KhachHang_.NGAY_THAY_DOI),
            root.get(KhachHang_.NGUOI_THAY_DOI)
        };
    }
}
