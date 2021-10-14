package com.example.qlbhbe.mapper;

import com.example.qlbhbe.controller.request.CreateSanPhamRequest;
import com.example.qlbhbe.controller.request.UpdateSanPhamRequest;
import com.example.qlbhbe.dto.SanPhamDTO;
import com.example.qlbhbe.entity.SanPham;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import com.example.qlbhbe.dto.SanPhamDetailsDTO;

@Mapper
public interface SanPhamMapper {

    SanPhamMapper INSTANCE = Mappers.getMapper(SanPhamMapper.class);

    default SanPham fromId(long id) {
        SanPham sanPham = new SanPham();
        sanPham.setId(id);
        return sanPham;
    }

    SanPhamDTO toSanPhamDTO(SanPham sanPham);

    SanPham update(UpdateSanPhamRequest command, @MappingTarget SanPham entity);

    SanPham create(CreateSanPhamRequest command);

    SanPhamDetailsDTO toSanPhamDetailsDTO(SanPham sanPham);
}
