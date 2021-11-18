package com.example.qlbhbe.mapper;

import com.example.qlbhbe.controller.request.CreateKhenThuongRequest;
import com.example.qlbhbe.controller.request.UpdateKhenThuongRequest;
import com.example.qlbhbe.dto.KhenThuongDTO;
import com.example.qlbhbe.entity.KhenThuong;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface KhenThuongMapper {

    KhenThuongMapper INSTANCE = Mappers.getMapper(KhenThuongMapper.class);

    default KhenThuong fromId(long id) {
        KhenThuong khenThuong = new KhenThuong();
        khenThuong.setId(id);
        return khenThuong;
    }

    KhenThuongDTO toKhenThuongDTO(KhenThuong khenThuong);

    KhenThuong toKhenThuongEntity(KhenThuongDTO khenThuong);

    KhenThuong update(KhenThuongDTO command, @MappingTarget KhenThuong entity);

    KhenThuong create(KhenThuongDTO command);
}
