package com.example.qlbhbe.mapper;

import com.example.qlbhbe.controller.request.CreateCongNoRequest;
import com.example.qlbhbe.controller.request.UpdateCongNoRequest;
import com.example.qlbhbe.dto.CongNoDTO;
import com.example.qlbhbe.entity.CongNo;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CongNoMapper {

    CongNoMapper INSTANCE = Mappers.getMapper(CongNoMapper.class);

    default CongNo fromId(long id) {
        CongNo congNo = new CongNo();
        congNo.setId(id);
        return congNo;
    }

    CongNoDTO toCongNoDTO(CongNo congNo);

    CongNo update(CongNoDTO command, @MappingTarget CongNo entity);

    CongNo create(CongNoDTO command);
}
