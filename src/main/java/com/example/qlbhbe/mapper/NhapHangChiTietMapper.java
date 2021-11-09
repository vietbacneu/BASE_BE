package com.example.qlbhbe.mapper;

import com.example.qlbhbe.controller.request.CreateNhapHangChiTietRequest;
import com.example.qlbhbe.controller.request.UpdateNhapHangChiTietRequest;
import com.example.qlbhbe.dto.NhapHangChiTietDTO;
import com.example.qlbhbe.entity.NhapHangChiTiet;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NhapHangChiTietMapper {

    NhapHangChiTietMapper INSTANCE = Mappers.getMapper(NhapHangChiTietMapper.class);

    default NhapHangChiTiet fromId(long id) {
        NhapHangChiTiet nhapHangChiTiet = new NhapHangChiTiet();
        nhapHangChiTiet.setId(id);
        return nhapHangChiTiet;
    }

    NhapHangChiTiet toEntity(NhapHangChiTietDTO nhapHangChiTietDTO);

    NhapHangChiTiet update(UpdateNhapHangChiTietRequest command, @MappingTarget NhapHangChiTiet entity);

    NhapHangChiTiet create(CreateNhapHangChiTietRequest command);
}
