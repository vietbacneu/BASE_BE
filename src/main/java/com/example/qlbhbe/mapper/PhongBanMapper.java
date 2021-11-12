package com.example.qlbhbe.mapper;

import com.example.qlbhbe.controller.request.CreatePhongBanRequest;
import com.example.qlbhbe.controller.request.UpdatePhongBanRequest;
import com.example.qlbhbe.dto.PhongBanDTO;
import com.example.qlbhbe.entity.PhongBan;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PhongBanMapper {

    PhongBanMapper INSTANCE = Mappers.getMapper(PhongBanMapper.class);

    default PhongBan fromId(long id) {
        PhongBan phongBan = new PhongBan();
        phongBan.setId(id);
        return phongBan;
    }

    PhongBanDTO toPhongBanDTO(PhongBan phongBan);

    PhongBan toPhongBanEntity(PhongBanDTO phongBan);

    PhongBan update(PhongBanDTO command, @MappingTarget PhongBan entity);

    PhongBan create(PhongBanDTO command);
}
