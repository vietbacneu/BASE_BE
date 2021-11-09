package com.example.qlbhbe.mapper;

import com.example.qlbhbe.controller.request.CreateNhaCungCapRequest;
import com.example.qlbhbe.controller.request.UpdateNhaCungCapRequest;
import com.example.qlbhbe.dto.NhaCungCapDTO;
import com.example.qlbhbe.entity.NhaCungCap;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import com.example.qlbhbe.dto.NhaCungCapDetailsDTO;

@Mapper
public interface NhaCungCapMapper {

    NhaCungCapMapper INSTANCE = Mappers.getMapper(NhaCungCapMapper.class);

    default NhaCungCap fromId(long id) {
        NhaCungCap nhaCungCap = new NhaCungCap();
        nhaCungCap.setId(id);
        return nhaCungCap;
    }

    NhaCungCapDTO toNhaCungCapDTO(NhaCungCap nhaCungCap);

    NhaCungCap update(NhaCungCapDTO command, @MappingTarget NhaCungCap entity);

    NhaCungCap create(NhaCungCapDTO command);

    NhaCungCapDetailsDTO toNhaCungCapDetailsDTO(NhaCungCap nhaCungCap);
}
