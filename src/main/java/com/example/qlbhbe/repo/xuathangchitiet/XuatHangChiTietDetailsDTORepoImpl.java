package com.example.qlbhbe.repo.xuathangchitiet;

import com.example.qlbhbe.dto.XuatHangChiTietDetailsDTO;
import com.example.qlbhbe.entity.XuatHangChiTiet;
import com.example.qlbhbe.repo.AbstractDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;

@Repository
public class XuatHangChiTietDetailsDTORepoImpl extends AbstractDetailsRepo<XuatHangChiTietDetailsDTO, XuatHangChiTiet, Long> implements XuatHangChiTietDetailsDTORepo {

    @Autowired
    public XuatHangChiTietDetailsDTORepoImpl(EntityManager em) {
        super(em, XuatHangChiTietDetailsDTO.class, XuatHangChiTiet.class);
    }

    @Override
    public Selection[] getDetailsSelections(Root<XuatHangChiTiet> root) {
        return new Selection[] {

        };
    }
}
