package com.example.qlbhbe.mapper;

import com.example.qlbhbe.controller.request.CreateNhanVienRequest;
import com.example.qlbhbe.controller.request.UpdateNhanVienRequest;
import com.example.qlbhbe.dto.NhanVienDTO;
import com.example.qlbhbe.entity.NhanVien;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NhanVienMapper {

    NhanVienMapper INSTANCE = Mappers.getMapper(NhanVienMapper.class);

    default NhanVien fromId(long id) {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setId(id);
        return nhanVien;
    }

    NhanVienDTO toNhanVienDTO(NhanVien nhanVien);

    NhanVien toNhanVienEntity(NhanVienDTO nhanVien);

    NhanVien update(NhanVienDTO command, @MappingTarget NhanVien entity);

    NhanVien create(NhanVienDTO command);
}
