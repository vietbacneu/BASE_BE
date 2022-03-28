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

    @Mapping(source = "idNhanVien.id", target = "idNhanVienId")
    @Mapping(source = "idNhanVien.ho", target = "idNhanVienHo")
    @Mapping(source = "idNhanVien.ten", target = "idNhanVienTen")
    @Mapping(source = "idNhanVien.sdt", target = "idNhanVienSdt")
    @Mapping(source = "idNhanVien.email", target = "idNhanVienEmail")
    @Mapping(source = "idNhanVien.gioiTinh", target = "idNhanVienGioiTinh")
    @Mapping(source = "idNhanVien.diaChi", target = "idNhanVienDiaChi")
    @Mapping(source = "idNhanVien.ngaySinh", target = "idNhanVienNgaySinh")
    @Mapping(source = "idNhanVien.ngayBatDau", target = "idNhanVienNgayBatDau")
    @Mapping(source = "idNhanVien.trinhDo", target = "idNhanVienTrinhDo")
    @Mapping(source = "idTroCap.id", target = "idTroCapId")
    @Mapping(source = "idTroCap.ten", target = "idTroCapTen")
    @Mapping(source = "idTroCap.mucTroCap", target = "idTroCapMucTroCap")
    @Mapping(source = "idTroCap.mieuTa", target = "idTroCapMieuTa")
    @Mapping(source = "idTroCap.maTroCap", target = "idTroCapMaTroCap")
    NhanVienTroCapDTO toNhanVienTroCapDTO(NhanVienTroCap nhanVienTroCap);

    NhanVienTroCap update(UpdateNhanVienTroCapRequest command, @MappingTarget NhanVienTroCap entity);

    NhanVienTroCap create(CreateNhanVienTroCapRequest command);
}
