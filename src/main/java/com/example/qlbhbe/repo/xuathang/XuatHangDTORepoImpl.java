package com.example.qlbhbe.repo.xuathang;

import com.example.qlbhbe.controller.request.searchparams.XuatHangSearchParams;
import com.example.qlbhbe.dto.XuatHangDTO;
import com.example.qlbhbe.entity.XuatHang;
import com.example.qlbhbe.repo.DynamicSearchRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import java.util.ArrayList;
import java.util.List;

@Component
public class XuatHangDTORepoImpl extends DynamicSearchRepo<XuatHangDTO, XuatHangSearchParams, XuatHang> implements XuatHangDTORepo {

    @Autowired
    public XuatHangDTORepoImpl(EntityManager em) {
        super(em, XuatHangDTO.class, XuatHang.class);
    }

    @Override
    public List<Predicate> getPredicates(CriteriaBuilder cb, Root<XuatHang> root, XuatHangSearchParams params) {
        List<Predicate> predicates = new ArrayList<>();
        return predicates;
    }

    @Override
    public Selection[] getSelections(Root<XuatHang> root) {
        return new Selection[] {

        };
    }
}
