package com.example.qlbhbe.repo.xuathangchitiet;

import com.example.qlbhbe.controller.request.searchparams.XuatHangChiTietSearchParams;
import com.example.qlbhbe.dto.XuatHangChiTietDTO;
import com.example.qlbhbe.entity.XuatHangChiTiet;
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
public class XuatHangChiTietDTORepoImpl extends DynamicSearchRepo<XuatHangChiTietDTO, XuatHangChiTietSearchParams, XuatHangChiTiet> implements XuatHangChiTietDTORepo {

    @Autowired
    public XuatHangChiTietDTORepoImpl(EntityManager em) {
        super(em, XuatHangChiTietDTO.class, XuatHangChiTiet.class);
    }

    @Override
    public List<Predicate> getPredicates(CriteriaBuilder cb, Root<XuatHangChiTiet> root, XuatHangChiTietSearchParams params) {
        List<Predicate> predicates = new ArrayList<>();
        return predicates;
    }

    @Override
    public Selection[] getSelections(Root<XuatHangChiTiet> root) {
        return new Selection[] {

        };
    }
}
