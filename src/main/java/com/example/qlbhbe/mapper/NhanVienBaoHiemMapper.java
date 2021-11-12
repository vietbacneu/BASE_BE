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
    @Mapping(source = "baoHiem.id", target = "baoHiemId")
    @Mapping(source = "baoHiem.maSo", target = "baoHiemMaSo")
    @Mapping(source = "baoHiem.ten", target = "baoHiemTen")
    @Mapping(source = "baoHiem.mucDong", target = "baoHiemMucDong")
    @Mapping(source = "baoHiem.thongTin", target = "baoHiemThongTin")
    NhanVienBaoHiemDTO toNhanVienBaoHiemDTO(NhanVienBaoHiem nhanVienBaoHiem);

    NhanVienBaoHiem toNhanVienBaoHiemEnity(NhanVienBaoHiemDTO nhanVienBaoHiem);

    NhanVienBaoHiem update(NhanVienBaoHiemDTO command, @MappingTarget NhanVienBaoHiem entity);

    NhanVienBaoHiem create(NhanVienBaoHiemDTO command);
}
