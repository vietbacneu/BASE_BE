package com.example.qlbhbe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

/**
 * Generated at 2021/10/14 17:17:58
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SanPhamDTO {

    private Long id;

    private Double soLuong = 0d;

    private String mieuTa;

    private String maSanPham = "";

    private String tenSanPham = "";

    private Double giaSanPham = 0d;

   private LocalDate ngayTao = LocalDate.now();

    private String nguoiTao = "admin";

    private LocalDate ngayThayDoi = LocalDate.now();

    private String nguoiThayDoi = "admin";

    private String tenDanhMuc;

    private String hanSanPham = "";

    private String donVi = "";

    private Double giaBanNiemYet = 0d;

    private Double giaBan = 0d;

    private Double giaNhapNiemYet = 0d;

    private Double giaNhap = 0d;

    private LocalDate ngayHetHan;

    private LocalDate ngaySanXuat;

    private Long idCuaHang;

    private String tenCuaHang = "";

    private String tenNhaCungCap = "";

    private Long idNhaCungCap;

    private String tenKhachHang = "";

    private Long idKhachHang;

    private String ngayXuat = "";

    private String ngayNhap = "";

    private Long isTonKho;

    private Double gia = 0d;

    private Double soLuongTon = 0d;

    private Double soLuongNhap = 0d;

    private Double soLuongBan = 0d;

    private Long idDanhMuc;

    private Double totalChiPhi = 0d;

    private Double totalDoanhThu = 0d;

    private Long isCount;

    public Long getIdNhaCungCap() {
        return idNhaCungCap;
    }

    public void setIdNhaCungCap(Long idNhaCungCap) {
        this.idNhaCungCap = idNhaCungCap;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public Long getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(Long idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public String getNgayXuat() {
        return ngayXuat;
    }

    public void setNgayXuat(String ngayXuat) {
        this.ngayXuat = ngayXuat;
    }

    public Long getIsCount() {
        return isCount;
    }

    public void setIsCount(Long isCount) {
        this.isCount = isCount;
    }

    public String getTenNhaCungCap() {
        return tenNhaCungCap;
    }

    public void setTenNhaCungCap(String tenNhaCungCap) {
        this.tenNhaCungCap = tenNhaCungCap;
    }


    public String getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(String ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public Double getTotalDoanhThu() {
        return totalDoanhThu;
    }

    public void setTotalDoanhThu(Double totalDoanhThu) {
        this.totalDoanhThu = totalDoanhThu;
    }

    public Long getIdDanhMuc() {
        return idDanhMuc;
    }

    public void setIdDanhMuc(Long idDanhMuc) {
        this.idDanhMuc = idDanhMuc;
    }

    public Double getTotalChiPhi() {
        return totalChiPhi;
    }

    public void setTotalChiPhi(Double totalChiPhi) {
        this.totalChiPhi = totalChiPhi;
    }

    public Double getSoLuongNhap() {
        return soLuongNhap;
    }

    public void setSoLuongNhap(Double soLuongNhap) {
        this.soLuongNhap = soLuongNhap;
    }

    public Double getSoLuongBan() {
        return soLuongBan;
    }

    public void setSoLuongBan(Double soLuongBan) {
        this.soLuongBan = soLuongBan;
    }

    public Double getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(Double soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public Double getGia() {
        return gia;
    }

    public void setGia(Double gia) {
        this.gia = gia;
    }

    public Long getIsTonKho() {
        return isTonKho;
    }

    public void setIsTonKho(Long isTonKho) {
        this.isTonKho = isTonKho;
    }

    public Double getGiaBan() {
        return giaBan;
    }

    public void setGiaBan(Double giaBan) {
        this.giaBan = giaBan;
    }

    public Double getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(Double giaNhap) {
        this.giaNhap = giaNhap;
    }

    public LocalDate getNgayHetHan() {
        return ngayHetHan;
    }

    public void setNgayHetHan(LocalDate ngayHetHan) {
        this.ngayHetHan = ngayHetHan;
    }

    public LocalDate getNgaySanXuat() {
        return ngaySanXuat;
    }

    public void setNgaySanXuat(LocalDate ngaySanXuat) {
        this.ngaySanXuat = ngaySanXuat;
    }

    public Long getIdCuaHang() {
        return idCuaHang;
    }

    public void setIdCuaHang(Long idCuaHang) {
        this.idCuaHang = idCuaHang;
    }

    public String getTenCuaHang() {
        return tenCuaHang;
    }

    public void setTenCuaHang(String tenCuaHang) {
        this.tenCuaHang = tenCuaHang;
    }

    public Double getGiaBanNiemYet() {
        return giaBanNiemYet;
    }

    public void setGiaBanNiemYet(Double giaBanNiemYet) {
        this.giaBanNiemYet = giaBanNiemYet;
    }

    public Double getGiaNhapNiemYet() {
        return giaNhapNiemYet;
    }

    public void setGiaNhapNiemYet(Double giaNhapNiemYet) {
        this.giaNhapNiemYet = giaNhapNiemYet;
    }

    public String getHanSanPham() {
        return hanSanPham;
    }

    public void setHanSanPham(String hanSanPham) {
        this.hanSanPham = hanSanPham;
    }

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public SanPhamDTO() {
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getSoLuong() {
        return this.soLuong;
    }

    public void setSoLuong(Double soLuong) {
        this.soLuong = soLuong;
    }

    public String getMieuTa() {
        return this.mieuTa;
    }

    public void setMieuTa(String mieuTa) {
        this.mieuTa = mieuTa;
    }

    public String getMaSanPham() {
        return this.maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return this.tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public Double getGiaSanPham() {
        return this.giaSanPham;
    }

    public void setGiaSanPham(Double giaSanPham) {
        this.giaSanPham = giaSanPham;
    }

    public String getNguoiTao() {
        return this.nguoiTao;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public LocalDate getNgayTao() {
        return this.ngayTao;
    }

    public void setNgayTao(LocalDate ngayTao) {
        this.ngayTao = ngayTao;
    }

    public LocalDate getNgayThayDoi() {
        return ngayThayDoi;
    }

    public void setNgayThayDoi(LocalDate ngayThayDoi) {
        this.ngayThayDoi = ngayThayDoi;
    }

    public String getNguoiThayDoi() {
        return nguoiThayDoi;
    }

    public void setNguoiThayDoi(String nguoiThayDoi) {
        this.nguoiThayDoi = nguoiThayDoi;
    }
}
