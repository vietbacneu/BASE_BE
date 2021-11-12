package com.example.qlbhbe.mapper;

import com.example.qlbhbe.controller.request.CreateNhanVienKyLuatRequest;
import com.example.qlbhbe.controller.request.UpdateNhanVienKyLuatRequest;
import com.example.qlbhbe.dto.NhanVienKyLuatDTO;
import com.example.qlbhbe.entity.NhanVienKyLuat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface NhanVienKyLuatMapper {

    NhanVienKyLuatMapper INSTANCE = Mappers.getMapper(NhanVienKyLuatMapper.class);

    default NhanVienKyLuat fromId(long id) {
        NhanVienKyLuat nhanVienKyLuat = new NhanVienKyLuat();
        nhanVienKyLuat.setId(id);
        return nhanVienKyLuat;
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
    @Mapping(source = "kyLuat.id", target = "kyLuatId")
    @Mapping(source = "kyLuat.tenLoi", target = "kyLuatTenLoi")
    @Mapping(source = "kyLuat.mucPhat", target = "kyLuatMucPhat")
    @Mapping(source = "kyLuat.mieuTa", target = "kyLuatMieuTa")
    NhanVienKyLuatDTO toNhanVienKyLuatDTO(NhanVienKyLuat nhanVienKyLuat);

    NhanVienKyLuat toNhanVienKyLuatEnity(NhanVienKyLuatDTO nhanVienKyLuat);

    NhanVienKyLuat update(NhanVienKyLuatDTO command, @MappingTarget NhanVienKyLuat entity);

    NhanVienKyLuat create(NhanVienKyLuatDTO command);
}
