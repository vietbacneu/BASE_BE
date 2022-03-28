package com.example.qlbhbe.mapper;

import com.example.qlbhbe.controller.request.CreateTroCapRequest;
import com.example.qlbhbe.controller.request.UpdateTroCapRequest;
import com.example.qlbhbe.dto.TroCapDTO;
import com.example.qlbhbe.entity.TroCap;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TroCapMapper {

    TroCapMapper INSTANCE = Mappers.getMapper(TroCapMapper.class);

    default TroCap fromId(long id) {
        TroCap troCap = new TroCap();
        troCap.setId(id);
        return troCap;
    }

    TroCapDTO toTroCapDTO(TroCap troCap);

    TroCap update(TroCapDTO command, @MappingTarget TroCap entity);

    TroCap create(TroCapDTO command);
}
