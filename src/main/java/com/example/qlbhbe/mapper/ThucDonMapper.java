package com.example.qlbhbe.mapper;

import com.example.qlbhbe.controller.request.CreateThucDonRequest;
import com.example.qlbhbe.controller.request.UpdateThucDonRequest;
import com.example.qlbhbe.dto.ThucDonDTO;
import com.example.qlbhbe.entity.ThucDon;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ThucDonMapper {

    ThucDonMapper INSTANCE = Mappers.getMapper(ThucDonMapper.class);

    default ThucDon fromId(long id) {
        ThucDon thucDon = new ThucDon();
        thucDon.setId(id);
        return thucDon;
    }

    ThucDonDTO toThucDonDTO(ThucDon thucDon);

    ThucDon update(ThucDonDTO command, @MappingTarget ThucDon entity);

    ThucDon create(ThucDonDTO command);
}
