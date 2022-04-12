package com.example.qlbhbe.mapper;

import com.example.qlbhbe.controller.request.CreateBanHangRequest;
import com.example.qlbhbe.controller.request.UpdateBanHangRequest;
import com.example.qlbhbe.dto.BanHangDTO;
import com.example.qlbhbe.entity.BanHang;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BanHangMapper {

    BanHangMapper INSTANCE = Mappers.getMapper(BanHangMapper.class);

    default BanHang fromId(long id) {
        BanHang banHang = new BanHang();
        banHang.setId(id);
        return banHang;
    }

    BanHangDTO toBanHangDTO(BanHang banHang);

    BanHang update(BanHangDTO command, @MappingTarget BanHang entity);

    BanHang create(BanHangDTO command);
}
