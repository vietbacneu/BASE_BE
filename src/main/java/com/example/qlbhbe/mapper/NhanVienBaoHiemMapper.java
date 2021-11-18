package com.example.qlbhbe.mapper;

import com.example.qlbhbe.controller.request.CreateNhanVienBaoHiemRequest;
import com.example.qlbhbe.controller.request.UpdateNhanVienBaoHiemRequest;
import com.example.qlbhbe.dto.NhanVienBaoHiemDTO;
import com.example.qlbhbe.entity.NhanVienBaoHiem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NhanVienBaoHiemMapper {

    NhanVienBaoHiemMapper INSTANCE = Mappers.getMapper(NhanVienBaoHiemMapper.class);

    default NhanVienBaoHiem fromId(long id) {
        NhanVienBaoHiem nhanVienBaoHiem = new NhanVienBaoHiem();
        nhanVienBaoHiem.setId(id);
        return nhanVienBaoHiem;
    }

    NhanVienBaoHiemDTO toNhanVienBaoHiemDTO(NhanVienBaoHiem nhanVienBaoHiem);

    NhanVienBaoHiem toNhanVienBaoHiemEnity(NhanVienBaoHiemDTO nhanVienBaoHiem);

    NhanVienBaoHiem update(NhanVienBaoHiemDTO command, @MappingTarget NhanVienBaoHiem entity);

    NhanVienBaoHiem create(NhanVienBaoHiemDTO command);
}
