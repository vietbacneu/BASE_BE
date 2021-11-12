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

    @Mapping(source = "nhanVien.id", target = "nhanVienId")
    @Mapping(source = "nhanVien.ho", target = "nhanVienHo")
    @Mapping(source = "nhanVien.ten", target = "nhanVienTen")
    @Mapping(source = "nhanVien.sdt", target = "nhanVienSdt")
    @Mapping(source = "nhanVien.email", target = "nhanVienEmail")
    @Mapping(source = "nhanVien.gioiTinh", target = "nhanVienGioiTinh")
    @Mapping(source = "nhanVien.diaChi", target = "nhanVienDiaChi")
    @Mapping(source = "nhanVien.ngaySinh", target = "nhanVienNgaySinh")
    @Mapping(source = "nhanVien.ngayBatDau", target = "nhanVienNgayBatDau")
    @Mapping(source = "nhanVien.ngayKetThuc", target = "nhanVienNgayKetThuc")
    @Mapping(source = "khenThuong.id", target = "khenThuongId")
    @Mapping(source = "khenThuong.ten", target = "khenThuongTen")
    @Mapping(source = "khenThuong.mucThuong", target = "khenThuongMucThuong")
    @Mapping(source = "khenThuong.mieuta", target = "khenThuongMieuta")
    NhanVienKhenThuongDTO toNhanVienKhenThuongDTO(NhanVienKhenThuong nhanVienKhenThuong);

    NhanVienKhenThuong toNhanVienKhenThuongEntity(NhanVienKhenThuongDTO nhanVienKhenThuong);

    NhanVienKhenThuong update(NhanVienKhenThuongDTO command, @MappingTarget NhanVienKhenThuong entity);

    NhanVienKhenThuong create(NhanVienKhenThuongDTO command);
}
