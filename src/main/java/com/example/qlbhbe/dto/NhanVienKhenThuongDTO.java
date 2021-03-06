package com.example.qlbhbe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

/**
 * Generated at 2021/11/12 14:16:44
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NhanVienKhenThuongDTO {

    private Long id;

    private LocalDate ngay;

    private String mieuTa;

    private Long idNhanVien;

    private String  hoNhanVien;

    private String tenNhanVien;

    private String sdtNhanVien;

    private String emailNhanVien;

    private String gioiTinhNhanVien;

    private String diaChiNhanVien;

    private LocalDate ngaySinhNhanVien;

    private LocalDate ngayBatDauNhanVien;

    private LocalDate ngayKetThucNhanVien;

    private Long idKhenThuong;

    private String tenKhenThuong;

    private Double khenThuongMucThuong;

    private String khenThuongMieuta;

    private Long isCount;

    private String tenChucVu;

    private String tenPhongBan;

    private String month;

    public NhanVienKhenThuongDTO() {
    }

    public NhanVienKhenThuongDTO(Long id, LocalDate ngay, String mieuTa, Long idNhanVien, String  hoNhanVien, String tenNhanVien, String sdtNhanVien, String emailNhanVien, String gioiTinhNhanVien, String diaChiNhanVien, LocalDate ngaySinhNhanVien, LocalDate ngayBatDauNhanVien, LocalDate ngayKetThucNhanVien, Long idKhenThuong, String tenKhenThuong, Double khenThuongMucThuong, String khenThuongMieuta) {
        this.id = id;
        this.ngay = ngay;
        this.mieuTa = mieuTa;
        this.idNhanVien = idNhanVien;
        this.hoNhanVien =  hoNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.sdtNhanVien = sdtNhanVien;
        this.emailNhanVien = emailNhanVien;
        this.gioiTinhNhanVien = gioiTinhNhanVien;
        this.diaChiNhanVien = diaChiNhanVien;
        this.ngaySinhNhanVien = ngaySinhNhanVien;
        this.ngayBatDauNhanVien = ngayBatDauNhanVien;
        this.ngayKetThucNhanVien = ngayKetThucNhanVien;
        this.idKhenThuong = idKhenThuong;
        this.tenKhenThuong = tenKhenThuong;
        this.khenThuongMucThuong = khenThuongMucThuong;
        this.khenThuongMieuta = khenThuongMieuta;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getTenPhongBan() {
        return tenPhongBan;
    }

    public void setTenPhongBan(String tenPhongBan) {
        this.tenPhongBan = tenPhongBan;
    }

    public String getTenChucVu() {
        return tenChucVu;
    }

    public void setTenChucVu(String tenChucVu) {
        this.tenChucVu = tenChucVu;
    }

    public Long getIsCount() {
        return isCount;
    }

    public void setIsCount(Long isCount) {
        this.isCount = isCount;
    }

    public Long getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(Long idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getNgay() {
        return this.ngay;
    }

    public void setNgay(LocalDate ngay) {
        this.ngay = ngay;
    }

    public String getMieuTa() {
        return this.mieuTa;
    }

    public void setMieuTa(String mieuTa) {
        this.mieuTa = mieuTa;
    }

    public Long getNhanVienId() {
        return this.idNhanVien;
    }

    public void setNhanVienId(Long idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public String getHoNhanVien() {
        return this.hoNhanVien;
    }

    public void setHoNhanVien(String  hoNhanVien) {
        this.hoNhanVien =  hoNhanVien;
    }

    public String getTenNhanVien() {
        return this.tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getSdtNhanVien() {
        return this.sdtNhanVien;
    }

    public void setSdtNhanVien(String sdtNhanVien) {
        this.sdtNhanVien = sdtNhanVien;
    }

    public String getEmailNhanVien() {
        return this.emailNhanVien;
    }

    public void setEmailNhanVien(String emailNhanVien) {
        this.emailNhanVien = emailNhanVien;
    }

    public String getGioiTinhNhanVien() {
        return this.gioiTinhNhanVien;
    }

    public void setGioiTinhNhanVien(String gioiTinhNhanVien) {
        this.gioiTinhNhanVien = gioiTinhNhanVien;
    }

    public String getDiaChiNhanVien() {
        return this.diaChiNhanVien;
    }

    public void setDiaChiNhanVien(String diaChiNhanVien) {
        this.diaChiNhanVien = diaChiNhanVien;
    }

    public LocalDate getNgaySinhNhanVien() {
        return this.ngaySinhNhanVien;
    }

    public void setNgaySinhNhanVien(LocalDate ngaySinhNhanVien) {
        this.ngaySinhNhanVien = ngaySinhNhanVien;
    }

    public LocalDate getNgayBatDauNhanVien() {
        return this.ngayBatDauNhanVien;
    }

    public void setNgayBatDauNhanVien(LocalDate ngayBatDauNhanVien) {
        this.ngayBatDauNhanVien = ngayBatDauNhanVien;
    }

    public LocalDate getNgayKetThucNhanVien() {
        return this.ngayKetThucNhanVien;
    }

    public void setNgayKetThucNhanVien(LocalDate ngayKetThucNhanVien) {
        this.ngayKetThucNhanVien = ngayKetThucNhanVien;
    }

    public Long getIdKhenThuong() {
        return this.idKhenThuong;
    }

    public void setIdKhenThuong(Long idKhenThuong) {
        this.idKhenThuong = idKhenThuong;
    }

    public String getTenKhenThuong() {
        return this.tenKhenThuong;
    }

    public void setTenKhenThuong(String tenKhenThuong) {
        this.tenKhenThuong = tenKhenThuong;
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
