package com.example.qlbhbe.mapper;

import com.example.qlbhbe.controller.request.CreateKhachHangRequest;
import com.example.qlbhbe.controller.request.UpdateKhachHangRequest;
import com.example.qlbhbe.dto.KhachHangDTO;
import com.example.qlbhbe.dto.KhachHangDetailsDTO;
import com.example.qlbhbe.entity.KhachHang;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface KhachHangMapper {

    KhachHangMapper INSTANCE = Mappers.getMapper(KhachHangMapper.class);

    default KhachHang fromId(long id) {
        KhachHang khachHang = new KhachHang();
        khachHang.setId(id);
        return khachHang;
    }

    KhachHangDTO toKhachHangDTO(KhachHang khachHang);

    KhachHangDetailsDTO toKhachHangDetailsDTO(KhachHang khachHang);

    KhachHang update(KhachHangDTO command, @MappingTarget KhachHang entity);

    KhachHang create(KhachHangDTO command);
}
