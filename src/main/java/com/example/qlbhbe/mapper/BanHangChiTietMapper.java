package com.example.qlbhbe.mapper;

import com.example.qlbhbe.controller.request.CreateBanHangChiTietRequest;
import com.example.qlbhbe.controller.request.UpdateBanHangChiTietRequest;
import com.example.qlbhbe.dto.BanHangChiTietDTO;
import com.example.qlbhbe.entity.BanHangChiTiet;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BanHangChiTietMapper {

    BanHangChiTietMapper INSTANCE = Mappers.getMapper(BanHangChiTietMapper.class);

    default BanHangChiTiet fromId(long id) {
        BanHangChiTiet banHangChiTiet = new BanHangChiTiet();
        banHangChiTiet.setId(id);
        return banHangChiTiet;
    }

    BanHangChiTietDTO toBanHangChiTietDTO(BanHangChiTiet banHangChiTiet);

    BanHangChiTiet update(BanHangChiTietDTO command, @MappingTarget BanHangChiTiet entity);

    BanHangChiTiet create(BanHangChiTietDTO command);
}
