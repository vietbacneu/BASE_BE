package com.example.qlbhbe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;

/**
 * Generated at 2021/11/12 14:16:57
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NhanVienKyLuatDTO {

    private Long id;

    private String mieuta;

    private LocalDateTime ngay;

    private Long nhanVienId;

    private String nhanVienHo;

    private String nhanVienTen;

    private String nhanVienSdt;

    private String nhanVienEmail;

    private String nhanVienGioiTinh;

    private String nhanVienDiaChi;

    private LocalDateTime nhanVienNgaySinh;

    private LocalDateTime nhanVienNgayBatDau;

    private LocalDateTime nhanVienNgayKetThuc;

    private Long kyLuatId;

    private String kyLuatTenLoi;

    private Double kyLuatMucPhat;

    private String kyLuatMieuTa;

    public NhanVienKyLuatDTO() {
    }

    public NhanVienKyLuatDTO(Long id, String mieuta, LocalDateTime ngay, Long nhanVienId, String nhanVienHo, String nhanVienTen, String nhanVienSdt, String nhanVienEmail, String nhanVienGioiTinh, String nhanVienDiaChi, LocalDateTime nhanVienNgaySinh, LocalDateTime nhanVienNgayBatDau, LocalDateTime nhanVienNgayKetThuc, Long kyLuatId, String kyLuatTenLoi, Double kyLuatMucPhat, String kyLuatMieuTa) {
        this.id = id;
        this.mieuta = mieuta;
        this.ngay = ngay;
        this.nhanVienId = nhanVienId;
        this.nhanVienHo = nhanVienHo;
        this.nhanVienTen = nhanVienTen;
        this.nhanVienSdt = nhanVienSdt;
        this.nhanVienEmail = nhanVienEmail;
        this.nhanVienGioiTinh = nhanVienGioiTinh;
        this.nhanVienDiaChi = nhanVienDiaChi;
        this.nhanVienNgaySinh = nhanVienNgaySinh;
        this.nhanVienNgayBatDau = nhanVienNgayBatDau;
        this.nhanVienNgayKetThuc = nhanVienNgayKetThuc;
        this.kyLuatId = kyLuatId;
        this.kyLuatTenLoi = kyLuatTenLoi;
        this.kyLuatMucPhat = kyLuatMucPhat;
        this.kyLuatMieuTa = kyLuatMieuTa;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMieuta() {
        return this.mieuta;
    }

    public void setMieuta(String mieuta) {
        this.mieuta = mieuta;
    }

    public LocalDateTime getNgay() {
        return this.ngay;
    }

    public void setNgay(LocalDateTime ngay) {
        this.ngay = ngay;
    }

    public Long getNhanVienId() {
        return this.nhanVienId;
    }

    public void setNhanVienId(Long nhanVienId) {
        this.nhanVienId = nhanVienId;
    }

    public String getNhanVienHo() {
        return this.nhanVienHo;
    }

    public void setNhanVienHo(String nhanVienHo) {
        this.nhanVienHo = nhanVienHo;
    }

    public String getNhanVienTen() {
        return this.nhanVienTen;
    }

    public void setNhanVienTen(String nhanVienTen) {
        this.nhanVienTen = nhanVienTen;
    }

    public String getNhanVienSdt() {
        return this.nhanVienSdt;
    }

    public void setNhanVienSdt(String nhanVienSdt) {
        this.nhanVienSdt = nhanVienSdt;
    }

    public String getNhanVienEmail() {
        return this.nhanVienEmail;
    }

    public void setNhanVienEmail(String nhanVienEmail) {
        this.nhanVienEmail = nhanVienEmail;
    }

    public String getNhanVienGioiTinh() {
        return this.nhanVienGioiTinh;
    }

    public void setNhanVienGioiTinh(String nhanVienGioiTinh) {
        this.nhanVienGioiTinh = nhanVienGioiTinh;
    }

    public String getNhanVienDiaChi() {
        return this.nhanVienDiaChi;
    }

    public void setNhanVienDiaChi(String nhanVienDiaChi) {
        this.nhanVienDiaChi = nhanVienDiaChi;
    }

    public LocalDateTime getNhanVienNgaySinh() {
        return this.nhanVienNgaySinh;
    }

    public void setNhanVienNgaySinh(LocalDateTime nhanVienNgaySinh) {
        this.nhanVienNgaySinh = nhanVienNgaySinh;
    }

    public LocalDateTime getNhanVienNgayBatDau() {
        return this.nhanVienNgayBatDau;
    }

    public void setNhanVienNgayBatDau(LocalDateTime nhanVienNgayBatDau) {
        this.nhanVienNgayBatDau = nhanVienNgayBatDau;
    }

    public LocalDateTime getNhanVienNgayKetThuc() {
        return this.nhanVienNgayKetThuc;
    }

    public void setNhanVienNgayKetThuc(LocalDateTime nhanVienNgayKetThuc) {
        this.nhanVienNgayKetThuc = nhanVienNgayKetThuc;
    }

    public Long getKyLuatId() {
        return this.kyLuatId;
    }

    public void setKyLuatId(Long kyLuatId) {
        this.kyLuatId = kyLuatId;
    }

    public String getKyLuatTenLoi() {
        return this.kyLuatTenLoi;
    }

    public void setKyLuatTenLoi(String kyLuatTenLoi) {
        this.kyLuatTenLoi = kyLuatTenLoi;
    }

    public Double getKyLuatMucPhat() {
        return this.kyLuatMucPhat;
    }

    public void setKyLuatMucPhat(Double kyLuatMucPhat) {
        this.kyLuatMucPhat = kyLuatMucPhat;
    }

    public String getKyLuatMieuTa() {
        return this.kyLuatMieuTa;
    }

    public void setKyLuatMieuTa(String kyLuatMieuTa) {
        this.kyLuatMieuTa = kyLuatMieuTa;
    }
}
