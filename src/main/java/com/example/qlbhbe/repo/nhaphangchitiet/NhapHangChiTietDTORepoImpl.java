package com.example.qlbhbe.repo.nhaphangchitiet;

import com.example.qlbhbe.controller.request.searchparams.NhapHangChiTietSearchParams;
import com.example.qlbhbe.dto.NhapHangChiTietDTO;
import com.example.qlbhbe.entity.NhapHangChiTiet;
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
public class NhapHangChiTietDTORepoImpl extends DynamicSearchRepo<NhapHangChiTietDTO, NhapHangChiTietSearchParams, NhapHangChiTiet> implements NhapHangChiTietDTORepo {

    @Autowired
    public NhapHangChiTietDTORepoImpl(EntityManager em) {
        super(em, NhapHangChiTietDTO.class, NhapHangChiTiet.class);
    }

    @Override
    public List<Predicate> getPredicates(CriteriaBuilder cb, Root<NhapHangChiTiet> root, NhapHangChiTietSearchParams params) {
        List<Predicate> predicates = new ArrayList<>();
        return predicates;
    }

    @Override
    public Selection[] getSelections(Root<NhapHangChiTiet> root) {
        return new Selection[] {
        };
    }
}
