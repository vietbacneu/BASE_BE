package com.example.qlbhbe.mapper;

import com.example.qlbhbe.dto.BaoHiemDTO;
import com.example.qlbhbe.entity.BaoHiem;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BaoHiemMapper {

    BaoHiemMapper INSTANCE = Mappers.getMapper(BaoHiemMapper.class);

    default BaoHiem fromId(long id) {
        BaoHiem baoHiem = new BaoHiem();
        baoHiem.setId(id);
        return baoHiem;
    }

    BaoHiemDTO toBaoHiemDTO(BaoHiem baoHiem);

    BaoHiem toBaoHiemEntity(BaoHiemDTO baoHiem);

    BaoHiem update(BaoHiemDTO command, @MappingTarget BaoHiem entity);

    BaoHiem create(BaoHiemDTO command);
}
