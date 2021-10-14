package com.example.qlbhbe.repo.nhacungcap;

import com.example.qlbhbe.controller.request.searchparams.NhaCungCapSearchParams;
import com.example.qlbhbe.dto.NhaCungCapDTO;
import com.example.qlbhbe.entity.NhaCungCap;
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
public class NhaCungCapDTORepoImpl extends DynamicSearchRepo<NhaCungCapDTO, NhaCungCapSearchParams, NhaCungCap> implements NhaCungCapDTORepo {

    @Autowired
    public NhaCungCapDTORepoImpl(EntityManager em) {
        super(em, NhaCungCapDTO.class, NhaCungCap.class);
    }

    @Override
    public List<Predicate> getPredicates(CriteriaBuilder cb, Root<NhaCungCap> root, NhaCungCapSearchParams params) {
        List<Predicate> predicates = new ArrayList<>();
        return predicates;
    }

    @Override
    public Selection[] getSelections(Root<NhaCungCap> root) {
        return new Selection[] {

        };
    }
}
