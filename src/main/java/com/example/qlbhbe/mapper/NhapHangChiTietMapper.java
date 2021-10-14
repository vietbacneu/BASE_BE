package com.example.qlbhbe.mapper;

import com.example.qlbhbe.controller.request.CreateNhapHangChiTietRequest;
import com.example.qlbhbe.controller.request.UpdateNhapHangChiTietRequest;
import com.example.qlbhbe.dto.NhapHangChiTietDTO;
import com.example.qlbhbe.entity.NhapHangChiTiet;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapping;
import com.example.qlbhbe.dto.NhapHangChiTietDetailsDTO;

@Mapper
public interface NhapHangChiTietMapper {

    NhapHangChiTietMapper INSTANCE = Mappers.getMapper(NhapHangChiTietMapper.class);

    default NhapHangChiTiet fromId(long id) {
        NhapHangChiTiet nhapHangChiTiet = new NhapHangChiTiet();
        nhapHangChiTiet.setId(id);
        return nhapHangChiTiet;
    }

    NhapHangChiTietDTO toNhapHangChiTietDTO(NhapHangChiTiet nhapHangChiTiet);

    NhapHangChiTiet update(UpdateNhapHangChiTietRequest command, @MappingTarget NhapHangChiTiet entity);

    NhapHangChiTiet create(CreateNhapHangChiTietRequest command);

    @Mapping(source = "idNhapHang.maNhapHang", target = "idNhapHangMaNhapHang")
    @Mapping(source = "idNhapHang.ngayNhap", target = "idNhapHangNgayNhap")
    @Mapping(source = "idNhapHang.nguoiTao", target = "idNhapHangNguoiTao")
    @Mapping(source = "idNhapHang.ngayTao", target = "idNhapHangNgayTao")
    @Mapping(source = "idNhapHang.nguoiThayDoi", target = "idNhapHangNguoiThayDoi")
    @Mapping(source = "idNhapHang.ngayThayDoi", target = "idNhapHangNgayThayDoi")
    @Mapping(source = "idSanPham.maSanPham", target = "idSanPhamMaSanPham")
    @Mapping(source = "idSanPham.tenSanPham", target = "idSanPhamTenSanPham")
    @Mapping(source = "idSanPham.giaSanPham", target = "idSanPhamGiaSanPham")
    @Mapping(source = "idSanPham.soLuong", target = "idSanPhamSoLuong")
    @Mapping(source = "idSanPham.mieuTa", target = "idSanPhamMieuTa")
    @Mapping(source = "idSanPham.nguoiTao", target = "idSanPhamNguoiTao")
    @Mapping(source = "idSanPham.ngayTao", target = "idSanPhamNgayTao")
    @Mapping(source = "idSanPham.nguoiThayDoi", target = "idSanPhamNguoiThayDoi")
    @Mapping(source = "idSanPham.ngayThayDoi", target = "idSanPhamNgayThayDoi")
    NhapHangChiTietDetailsDTO toNhapHangChiTietDetailsDTO(NhapHangChiTiet nhapHangChiTiet);
}
