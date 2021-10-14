package com.example.qlbhbe.mapper;

import com.example.qlbhbe.controller.request.CreateXuatHangChiTietRequest;
import com.example.qlbhbe.controller.request.UpdateXuatHangChiTietRequest;
import com.example.qlbhbe.dto.XuatHangChiTietDTO;
import com.example.qlbhbe.entity.XuatHangChiTiet;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import com.example.qlbhbe.dto.XuatHangChiTietDetailsDTO;

@Mapper
public interface XuatHangChiTietMapper {

    XuatHangChiTietMapper INSTANCE = Mappers.getMapper(XuatHangChiTietMapper.class);

    default XuatHangChiTiet fromId(long id) {
        XuatHangChiTiet xuatHangChiTiet = new XuatHangChiTiet();
        xuatHangChiTiet.setId(id);
        return xuatHangChiTiet;
    }

    XuatHangChiTietDTO toXuatHangChiTietDTO(XuatHangChiTiet xuatHangChiTiet);

    XuatHangChiTiet update(UpdateXuatHangChiTietRequest command, @MappingTarget XuatHangChiTiet entity);

    XuatHangChiTiet create(CreateXuatHangChiTietRequest command);

    XuatHangChiTietDetailsDTO toXuatHangChiTietDetailsDTO(XuatHangChiTiet xuatHangChiTiet);
}
