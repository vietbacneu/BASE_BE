package com.example.qlbhbe.mapper;

import com.example.qlbhbe.controller.request.CreateCongNoChiTietRequest;
import com.example.qlbhbe.controller.request.UpdateCongNoChiTietRequest;
import com.example.qlbhbe.dto.CongNoChiTietDTO;
import com.example.qlbhbe.entity.CongNoChiTiet;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CongNoChiTietMapper {

    CongNoChiTietMapper INSTANCE = Mappers.getMapper(CongNoChiTietMapper.class);

    default CongNoChiTiet fromId(long id) {
        CongNoChiTiet congNoChiTiet = new CongNoChiTiet();
        congNoChiTiet.setId(id);
        return congNoChiTiet;
    }

    CongNoChiTietDTO toCongNoChiTietDTO(CongNoChiTiet congNoChiTiet);

   List<CongNoChiTietDTO> toCongNoChiTietDTOList(List<CongNoChiTiet> congNoChiTiet);

    CongNoChiTiet toCongNoChiTiet(CongNoChiTietDTO congNoChiTiet);

    List<CongNoChiTiet> toCongNoChiTietList(List<CongNoChiTietDTO> congNoChiTiet);

    CongNoChiTiet update(CongNoChiTietDTO command, @MappingTarget CongNoChiTiet entity);

    CongNoChiTiet create(CongNoChiTietDTO command);
}
