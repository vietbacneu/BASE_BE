package com.example.qlbhbe.mapper;

import com.example.qlbhbe.controller.request.CreateNhanVienTroCapRequest;
import com.example.qlbhbe.controller.request.UpdateNhanVienTroCapRequest;
import com.example.qlbhbe.dto.NhanVienTroCapDTO;
import com.example.qlbhbe.entity.NhanVienTroCap;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NhanVienTroCapMapper {

    NhanVienTroCapMapper INSTANCE = Mappers.getMapper(NhanVienTroCapMapper.class);

    default NhanVienTroCap fromId(long id) {
        NhanVienTroCap nhanVienTroCap = new NhanVienTroCap();
        nhanVienTroCap.setId(id);
        return nhanVienTroCap;
    }

    NhanVienTroCap update(NhanVienTroCapDTO command, @MappingTarget NhanVienTroCap entity);

    NhanVienTroCap create(NhanVienTroCapDTO command);
}
