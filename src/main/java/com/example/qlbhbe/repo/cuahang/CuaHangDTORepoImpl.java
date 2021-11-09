package com.example.qlbhbe.repo.cuahang;

import com.example.qlbhbe.controller.request.searchparams.CuaHangSearchParams;
import com.example.qlbhbe.repo.DynamicSearchRepo;
import com.example.qlbhbe.dto.CuaHangDTO;
import com.example.qlbhbe.entity.CuaHang;
import com.example.qlbhbe.entity.CuaHang_;
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
public class CuaHangDTORepoImpl extends DynamicSearchRepo<CuaHangDTO, CuaHangSearchParams, CuaHang> implements CuaHangDTORepo {

    @Autowired
    public CuaHangDTORepoImpl(EntityManager em) {
        super(em, CuaHangDTO.class, CuaHang.class);
    }

    @Override
    public List<Predicate> getPredicates(CriteriaBuilder cb, Root<CuaHang> root, CuaHangSearchParams params) {
        List<Predicate> predicates = new ArrayList<>();
        return predicates;
    }

    @Override
    public Selection[] getSelections(Root<CuaHang> root) {
        return new Selection[] {
            root.get(CuaHang_.ID),
            root.get(CuaHang_.MA_CUA_HANG),
            root.get(CuaHang_.TEN_CUA_HANG),
            root.get(CuaHang_.DIA_CHI),
            root.get(CuaHang_.TRANG_THAI),
            root.get(CuaHang_.NGUOI_TAO),
            root.get(CuaHang_.NGAY_TAO),
            root.get(CuaHang_.NGUOI_THAY_DOI),
            root.get(CuaHang_.NGAY_THAY_DOI)
        };
    }
}
