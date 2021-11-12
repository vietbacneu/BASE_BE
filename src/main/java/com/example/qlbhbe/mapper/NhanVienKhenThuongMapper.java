package com.example.qlbhbe.mapper;

import com.example.qlbhbe.controller.request.CreateNhanVienKhenThuongRequest;
import com.example.qlbhbe.controller.request.UpdateNhanVienKhenThuongRequest;
import com.example.qlbhbe.dto.NhanVienKhenThuongDTO;
import com.example.qlbhbe.entity.NhanVienKhenThuong;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NhanVienKhenThuongMapper {

    NhanVienKhenThuongMapper INSTANCE = Mappers.getMapper(NhanVienKhenThuongMapper.class);

    default NhanVienKhenThuong fromId(long id) {
        NhanVienKhenThuong nhanVienKhenThuong = new NhanVienKhenThuong();
        nhanVienKhenThuong.setId(id);
        return nhanVienKhenThuong;
    }


    NhanVienKhenThuongDTO toNhanVienKhenThuongDTO(NhanVienKhenThuong nhanVienKhenThuong);

    NhanVienKhenThuong toNhanVienKhenThuongEntity(NhanVienKhenThuongDTO nhanVienKhenThuong);

    NhanVienKhenThuong update(NhanVienKhenThuongDTO command, @MappingTarget NhanVienKhenThuong entity);

    NhanVienKhenThuong create(NhanVienKhenThuongDTO command);
}
