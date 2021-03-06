package com.example.qlbhbe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDate;

/**
 * Generated at 2021/11/12 14:16:57
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NhanVienKyLuatDTO {

    private Long id;

    private String mieuTa;

    private LocalDate ngay;

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

    private String tenChucVu;

    private String tenPhongBan;

    private Long idKyLuat;

    private String tenLoi;

    private Double mucPhat;

    private String nhanVienMieuTa;

    private String month;

    private Long isCount;


    public NhanVienKyLuatDTO() {
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMieuTa() {
        return mieuTa;
    }

    public void setMieuTa(String mieuTa) {
        this.mieuTa = mieuTa;
    }

    public LocalDate getNgay() {
        return ngay;
    }

    public void setNgay(LocalDate ngay) {
        this.ngay = ngay;
    }

    public Long getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(Long idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public String getHoNhanVien() {
        return hoNhanVien;
    }

    public void setHoNhanVien(String hoNhanVien) {
        this.hoNhanVien = hoNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getSdtNhanVien() {
        return sdtNhanVien;
    }

    public void setSdtNhanVien(String sdtNhanVien) {
        this.sdtNhanVien = sdtNhanVien;
    }

    public String getEmailNhanVien() {
        return emailNhanVien;
    }

    public void setEmailNhanVien(String emailNhanVien) {
        this.emailNhanVien = emailNhanVien;
    }

    public String getGioiTinhNhanVien() {
        return gioiTinhNhanVien;
    }

    public void setGioiTinhNhanVien(String gioiTinhNhanVien) {
        this.gioiTinhNhanVien = gioiTinhNhanVien;
    }

    public String getDiaChiNhanVien() {
        return diaChiNhanVien;
    }

    public void setDiaChiNhanVien(String diaChiNhanVien) {
        this.diaChiNhanVien = diaChiNhanVien;
    }

    public LocalDate getNgaySinhNhanVien() {
        return ngaySinhNhanVien;
    }

    public void setNgaySinhNhanVien(LocalDate ngaySinhNhanVien) {
        this.ngaySinhNhanVien = ngaySinhNhanVien;
    }

    public LocalDate getNgayBatDauNhanVien() {
        return ngayBatDauNhanVien;
    }

    public void setNgayBatDauNhanVien(LocalDate ngayBatDauNhanVien) {
        this.ngayBatDauNhanVien = ngayBatDauNhanVien;
    }

    public LocalDate getNgayKetThucNhanVien() {
        return ngayKetThucNhanVien;
    }

    public void setNgayKetThucNhanVien(LocalDate ngayKetThucNhanVien) {
        this.ngayKetThucNhanVien = ngayKetThucNhanVien;
    }

    public String getTenChucVu() {
        return tenChucVu;
    }

    public void setTenChucVu(String tenChucVu) {
        this.tenChucVu = tenChucVu;
    }

    public String getTenPhongBan() {
        return tenPhongBan;
    }

    public void setTenPhongBan(String tenPhongBan) {
        this.tenPhongBan = tenPhongBan;
    }

    public Long getIdKyLuat() {
        return idKyLuat;
    }

    public void setIdKyLuat(Long idKyLuat) {
        this.idKyLuat = idKyLuat;
    }

    public String getTenLoi() {
        return tenLoi;
    }

    public void setTenLoi(String tenLoi) {
        this.tenLoi = tenLoi;
    }

    public Double getMucPhat() {
        return mucPhat;
    }

    public void setMucPhat(Double mucPhat) {
        this.mucPhat = mucPhat;
    }

    public String getNhanVienMieuTa() {
        return nhanVienMieuTa;
    }

    public void setNhanVienMieuTa(String nhanVienMieuTa) {
        this.nhanVienMieuTa = nhanVienMieuTa;
    }

    public Long getIsCount() {
        return isCount;
    }

    public void setIsCount(Long isCount) {
        this.isCount = isCount;
    }
}
