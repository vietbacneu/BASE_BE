package com.example.qlbhbe.mapper;

import com.example.qlbhbe.controller.request.CreateNhapHangRequest;
import com.example.qlbhbe.controller.request.UpdateNhapHangRequest;
import com.example.qlbhbe.dto.NhapHangDTO;
import com.example.qlbhbe.dto.NhapHangDetailsDTO;
import com.example.qlbhbe.entity.NhapHang;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NhapHangMapper {

    NhapHangMapper INSTANCE = Mappers.getMapper(NhapHangMapper.class);

    default NhapHang fromId(long id) {
        NhapHang nhapHang = new NhapHang();
        nhapHang.setId(id);
        return nhapHang;
    }

    @Mapping(source = "idNhaCungCap.id", target = "idNhaCungCap")
    NhapHangDTO toNhapHangDTO(NhapHang nhapHang);

    @Mapping(target = "idNhaCungCap.id", source = "idNhaCungCap")
    NhapHang toNhapHangENTITY(NhapHangDTO nhapHang);

    NhapHang update(UpdateNhapHangRequest command, @MappingTarget NhapHang entity);

    NhapHang create(CreateNhapHangRequest command);

    NhapHangDetailsDTO toNhapHangDetailsDTO(NhapHang nhapHang);
}
