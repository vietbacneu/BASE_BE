package com.example.qlbhbe.mapper;

import com.example.qlbhbe.controller.request.CreateCuaHangRequest;
import com.example.qlbhbe.controller.request.UpdateCuaHangRequest;
import com.example.qlbhbe.dto.CuaHangDTO;
import com.example.qlbhbe.entity.CuaHang;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CuaHangMapper {

    CuaHangMapper INSTANCE = Mappers.getMapper(CuaHangMapper.class);

    default CuaHang fromId(long id) {
        CuaHang cuaHang = new CuaHang();
        cuaHang.setId(id);
        return cuaHang;
    }

    CuaHangDTO toCuaHangDTO(CuaHang cuaHang);

    CuaHang update(CuaHangDTO command, @MappingTarget CuaHang entity);

    CuaHang create(CuaHangDTO command);
}
