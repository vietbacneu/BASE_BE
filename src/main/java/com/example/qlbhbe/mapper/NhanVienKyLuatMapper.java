package com.example.qlbhbe.mapper;

import com.example.qlbhbe.controller.request.CreateNhanVienKyLuatRequest;
import com.example.qlbhbe.controller.request.UpdateNhanVienKyLuatRequest;
import com.example.qlbhbe.dto.NhanVienKyLuatDTO;
import com.example.qlbhbe.entity.NhanVienKyLuat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NhanVienKyLuatMapper {

    NhanVienKyLuatMapper INSTANCE = Mappers.getMapper(NhanVienKyLuatMapper.class);

    default NhanVienKyLuat fromId(long id) {
        NhanVienKyLuat nhanVienKyLuat = new NhanVienKyLuat();
        nhanVienKyLuat.setId(id);
        return nhanVienKyLuat;
    }

    NhanVienKyLuatDTO toNhanVienKyLuatDTO(NhanVienKyLuat nhanVienKyLuat);

    NhanVienKyLuat toNhanVienKyLuatEnity(NhanVienKyLuatDTO nhanVienKyLuat);

    NhanVienKyLuat update(NhanVienKyLuatDTO command, @MappingTarget NhanVienKyLuat entity);

    NhanVienKyLuat create(NhanVienKyLuatDTO command);
}
