package com.example.qlbhbe.repo.danhmuc;

import com.example.qlbhbe.controller.request.searchparams.DanhMucSearchParams;
import com.example.qlbhbe.dto.DanhMucDTO;
import com.example.qlbhbe.entity.DanhMuc;
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
public class DanhMucDTORepoImpl extends DynamicSearchRepo<DanhMucDTO, DanhMucSearchParams, DanhMuc> implements DanhMucDTORepo {

    @Autowired
    public DanhMucDTORepoImpl(EntityManager em) {
        super(em, DanhMucDTO.class, DanhMuc.class);
    }

    @Override
    public List<Predicate> getPredicates(CriteriaBuilder cb, Root<DanhMuc> root, DanhMucSearchParams params) {
        List<Predicate> predicates = new ArrayList<>();
        return predicates;
    }

    @Override
    public Selection[] getSelections(Root<DanhMuc> root) {
        return new Selection[] {

        };
    }
}
