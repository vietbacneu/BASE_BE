package com.example.qlbhbe.mapper;

import com.example.qlbhbe.controller.request.CreateKyLuatRequest;
import com.example.qlbhbe.controller.request.UpdateKyLuatRequest;
import com.example.qlbhbe.dto.KyLuatDTO;
import com.example.qlbhbe.entity.KyLuat;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface KyLuatMapper {

    KyLuatMapper INSTANCE = Mappers.getMapper(KyLuatMapper.class);

    default KyLuat fromId(long id) {
        KyLuat kyLuat = new KyLuat();
        kyLuat.setId(id);
        return kyLuat;
    }

    KyLuatDTO toKyLuatDTO(KyLuat kyLuat);

    KyLuat toKyLuatEntity(KyLuatDTO kyLuat);

    KyLuat update(KyLuatDTO command, @MappingTarget KyLuat entity);

    KyLuat create(KyLuatDTO command);
}
