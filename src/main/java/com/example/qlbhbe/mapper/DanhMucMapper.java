package com.example.qlbhbe.mapper;

import com.example.qlbhbe.controller.request.CreateDanhMucRequest;
import com.example.qlbhbe.controller.request.UpdateDanhMucRequest;
import com.example.qlbhbe.dto.DanhMucDTO;
import com.example.qlbhbe.dto.DanhMucDetailsDTO;
import com.example.qlbhbe.entity.DanhMuc;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DanhMucMapper {

    DanhMucMapper INSTANCE = Mappers.getMapper(DanhMucMapper.class);

    default DanhMuc fromId(long id) {
        DanhMuc danhMuc = new DanhMuc();
        danhMuc.setId(id);
        return danhMuc;
    }

    DanhMucDTO toDanhMucDTO(DanhMuc danhMuc);

    DanhMuc update(DanhMucDTO command, @MappingTarget DanhMuc entity);

    DanhMuc create(DanhMucDTO command);

    DanhMucDetailsDTO toDanhMucDetailsDTO(DanhMuc danhMuc);
}
