package com.example.qlbhbe.repo.xuathang;

import com.example.qlbhbe.dto.XuatHangDetailsDTO;
import com.example.qlbhbe.entity.XuatHang;
import com.example.qlbhbe.repo.AbstractDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

@Repository
public class XuatHangDetailsDTORepoImpl extends AbstractDetailsRepo<XuatHangDetailsDTO, XuatHang, Long> implements XuatHangDetailsDTORepo {

    @Autowired
    public XuatHangDetailsDTORepoImpl(EntityManager em) {
        super(em, XuatHangDetailsDTO.class, XuatHang.class);
    }

    @Override
    public Selection[] getDetailsSelections(Root<XuatHang> root) {
        return new Selection[] {

        };
    }
}
