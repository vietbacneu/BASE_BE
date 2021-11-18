package com.example.qlbhbe.mapper;

import com.example.qlbhbe.controller.request.CreateChucVuRequest;
import com.example.qlbhbe.controller.request.UpdateChucVuRequest;
import com.example.qlbhbe.dto.ChucVuDTO;
import com.example.qlbhbe.entity.ChucVu;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ChucVuMapper {

    ChucVuMapper INSTANCE = Mappers.getMapper(ChucVuMapper.class);

    default ChucVu fromId(long id) {
        ChucVu chucVu = new ChucVu();
        chucVu.setId(id);
        return chucVu;
    }

    ChucVuDTO toChucVuDTO(ChucVu chucVu);

    ChucVu toChucVuEntity(ChucVuDTO chucVu);

    ChucVu update(ChucVuDTO command, @MappingTarget ChucVu entity);

    ChucVu create(ChucVuDTO command);
}
