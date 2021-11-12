package com.example.qlbhbe.mapper;

import com.example.qlbhbe.controller.request.CreateChamCongRequest;
import com.example.qlbhbe.controller.request.UpdateChamCongRequest;
import com.example.qlbhbe.dto.ChamCongDTO;
import com.example.qlbhbe.dto.ChamCongDetailsDTO;
import com.example.qlbhbe.entity.ChamCong;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ChamCongMapper {

    ChamCongMapper INSTANCE = Mappers.getMapper(ChamCongMapper.class);

    default ChamCong fromId(long id) {
        ChamCong chamCong = new ChamCong();
        chamCong.setId(id);
        return chamCong;
    }

    ChamCongDTO toChamCongDTO(ChamCong chamCong);

    ChamCong toChamCongEntity(ChamCongDTO chamCong);


    ChamCong update(ChamCongDTO command, @MappingTarget ChamCong entity);

    ChamCong create(ChamCongDTO command);
}
