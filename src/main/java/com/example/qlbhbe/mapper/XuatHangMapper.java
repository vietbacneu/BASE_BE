package com.example.qlbhbe.mapper;

import com.example.qlbhbe.controller.request.CreateXuatHangRequest;
import com.example.qlbhbe.controller.request.UpdateXuatHangRequest;
import com.example.qlbhbe.dto.XuatHangDTO;
import com.example.qlbhbe.entity.XuatHang;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import com.example.qlbhbe.dto.XuatHangDetailsDTO;

@Mapper
public interface XuatHangMapper {

    XuatHangMapper INSTANCE = Mappers.getMapper(XuatHangMapper.class);

    default XuatHang fromId(long id) {
        XuatHang xuatHang = new XuatHang();
        xuatHang.setId(id);
        return xuatHang;
    }

    XuatHangDTO toXuatHangDTO(XuatHang xuatHang);

    XuatHang update(UpdateXuatHangRequest command, @MappingTarget XuatHang entity);

    XuatHang create(CreateXuatHangRequest command);

    XuatHangDetailsDTO toXuatHangDetailsDTO(XuatHang xuatHang);
}
