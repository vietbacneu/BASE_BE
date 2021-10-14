package com.example.qlbhbe.repo.sanpham;

import com.example.qlbhbe.controller.request.searchparams.SanPhamSearchParams;
import com.example.qlbhbe.dto.SanPhamDTO;
import com.example.qlbhbe.entity.SanPham;
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
public class SanPhamDTORepoImpl extends DynamicSearchRepo<SanPhamDTO, SanPhamSearchParams, SanPham> implements SanPhamDTORepo {

    @Autowired
    public SanPhamDTORepoImpl(EntityManager em) {
        super(em, SanPhamDTO.class, SanPham.class);
    }

    @Override
    public List<Predicate> getPredicates(CriteriaBuilder cb, Root<SanPham> root, SanPhamSearchParams params) {
        List<Predicate> predicates = new ArrayList<>();
        return predicates;
    }

    @Override
    public Selection[] getSelections(Root<SanPham> root) {
        return new Selection[] {

        };
    }
}
