package com.example.qlbhbe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;

/**
 * Generated at 2021/11/12 14:16:44
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NhanVienKhenThuongDTO {

    private Long id;

    private LocalDateTime ngay;

    private String mieuta;

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

    private Long khenThuongId;

    private String khenThuongTen;

    private Double khenThuongMucThuong;

    private String khenThuongMieuta;

    public NhanVienKhenThuongDTO() {
    }

    public NhanVienKhenThuongDTO(Long id, LocalDateTime ngay, String mieuta, Long nhanVienId, String nhanVienHo, String nhanVienTen, String nhanVienSdt, String nhanVienEmail, String nhanVienGioiTinh, String nhanVienDiaChi, LocalDateTime nhanVienNgaySinh, LocalDateTime nhanVienNgayBatDau, LocalDateTime nhanVienNgayKetThuc, Long khenThuongId, String khenThuongTen, Double khenThuongMucThuong, String khenThuongMieuta) {
        this.id = id;
        this.ngay = ngay;
        this.mieuta = mieuta;
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
        this.khenThuongId = khenThuongId;
        this.khenThuongTen = khenThuongTen;
        this.khenThuongMucThuong = khenThuongMucThuong;
        this.khenThuongMieuta = khenThuongMieuta;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getNgay() {
        return this.ngay;
    }

    public void setNgay(LocalDateTime ngay) {
        this.ngay = ngay;
    }

    public String getMieuta() {
        return this.mieuta;
    }

    public void setMieuta(String mieuta) {
        this.mieuta = mieuta;
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

    public Long getKhenThuongId() {
        return this.khenThuongId;
    }

    public void setKhenThuongId(Long khenThuongId) {
        this.khenThuongId = khenThuongId;
    }

    public String getKhenThuongTen() {
        return this.khenThuongTen;
    }

    public void setKhenThuongTen(String khenThuongTen) {
        this.khenThuongTen = khenThuongTen;
    }

    public Double getKhenThuongMucThuong() {
        return this.khenThuongMucThuong;
    }

    public void setKhenThuongMucThuong(Double khenThuongMucThuong) {
        this.khenThuongMucThuong = khenThuongMucThuong;
    }

    public String getKhenThuongMieuta() {
        return this.khenThuongMieuta;
    }

    public void setKhenThuongMieuta(String khenThuongMieuta) {
        this.khenThuongMieuta = khenThuongMieuta;
    }
}
