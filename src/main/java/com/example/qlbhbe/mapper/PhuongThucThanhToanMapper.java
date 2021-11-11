package com.example.qlbhbe.mapper;

import com.example.qlbhbe.controller.request.CreatePhuongThucThanhToanRequest;
import com.example.qlbhbe.controller.request.UpdatePhuongThucThanhToanRequest;
import com.example.qlbhbe.dto.PhuongThucThanhToanDTO;
import com.example.qlbhbe.entity.PhuongThucThanhToan;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PhuongThucThanhToanMapper {

    PhuongThucThanhToanMapper INSTANCE = Mappers.getMapper(PhuongThucThanhToanMapper.class);

    default PhuongThucThanhToan fromId(long id) {
        PhuongThucThanhToan phuongThucThanhToan = new PhuongThucThanhToan();
        phuongThucThanhToan.setId(id);
        return phuongThucThanhToan;
    }

    PhuongThucThanhToanDTO toPhuongThucThanhToanDTO(PhuongThucThanhToan phuongThucThanhToan);

    PhuongThucThanhToan toPhuongThucThanhToanEntity(PhuongThucThanhToanDTO phuongThucThanhToan);

    PhuongThucThanhToan update(PhuongThucThanhToanDTO command, @MappingTarget PhuongThucThanhToan entity);

    PhuongThucThanhToan create(PhuongThucThanhToanDTO command);
}
