package com.example.qlbhbe.repo.khachhang;

import com.example.qlbhbe.controller.request.searchparams.KhachHangSearchParams;
import com.example.qlbhbe.repo.DynamicSearchRepo;
import com.example.qlbhbe.dto.KhachHangDTO;
import com.example.qlbhbe.entity.KhachHang;
import com.example.qlbhbe.entity.KhachHang_;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.List;
import java.util.ArrayList;

@Component
public class KhachHangDTORepoImpl extends DynamicSearchRepo<KhachHangDTO, KhachHangSearchParams, KhachHang> implements KhachHangDTORepo {

    @Autowired
    public KhachHangDTORepoImpl(EntityManager em) {
        super(em, KhachHangDTO.class, KhachHang.class);
    }

    @Override
    public List<Predicate> getPredicates(CriteriaBuilder cb, Root<KhachHang> root, KhachHangSearchParams params) {
        List<Predicate> predicates = new ArrayList<>();
        return predicates;
    }

    @Override
    public Selection[] getSelections(Root<KhachHang> root) {
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
