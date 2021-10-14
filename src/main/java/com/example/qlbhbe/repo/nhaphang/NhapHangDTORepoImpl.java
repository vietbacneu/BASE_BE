package com.example.qlbhbe.repo.nhaphang;

import com.example.qlbhbe.controller.request.searchparams.NhapHangSearchParams;
import com.example.qlbhbe.dto.NhapHangDTO;
import com.example.qlbhbe.entity.NhapHang;
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
public class NhapHangDTORepoImpl extends DynamicSearchRepo<NhapHangDTO, NhapHangSearchParams, NhapHang> implements NhapHangDTORepo {

    @Autowired
    public NhapHangDTORepoImpl(EntityManager em) {
        super(em, NhapHangDTO.class, NhapHang.class);
    }

    @Override
    public List<Predicate> getPredicates(CriteriaBuilder cb, Root<NhapHang> root, NhapHangSearchParams params) {
        List<Predicate> predicates = new ArrayList<>();
        return predicates;
    }

    @Override
    public Selection[] getSelections(Root<NhapHang> root) {
        return new Selection[] {

        };
    }
}
