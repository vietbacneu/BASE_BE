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

    private Long idNhanVien;

    private String  hoNhanVien;

    private String tenNhanVien;

    private String sdtNhanVien;

    private String emailNhanVien;

    private String gioiTinhNhanVien;

    private String diaChiNhanVien;

    private LocalDateTime ngaySinhNhanVien;

    private LocalDateTime ngayBatDauNhanVien;

    private LocalDateTime ngayKetThucNhanVien;

    private String tenChucVu;

    private String tenPhongBan;

    private Long idKyLuat;

    private String tenLoi;

    private Double mucPhat;

    private String nhanVienMieuTa;

    private Long isCount;


    public NhanVienKyLuatDTO() {
    }

    public NhanVienKyLuatDTO(Long id, String mieuta, LocalDateTime ngay, Long idNhanVien, String  hoNhanVien, String tenNhanVien, String sdtNhanVien, String emailNhanVien, String gioiTinhNhanVien, String diaChiNhanVien, LocalDateTime ngaySinhNhanVien, LocalDateTime ngayBatDauNhanVien, LocalDateTime ngayKetThucNhanVien, Long idKyLuat, String tenLoi, Double mucPhat, String mieuTa) {
        this.id = id;
        this.mieuta = mieuta;
        this.ngay = ngay;
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
        this.idKyLuat = idKyLuat;
        this.tenLoi = tenLoi;
        this.mucPhat = mucPhat;
        this.nhanVienMieuTa = mieuTa;
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

    public Long getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(Long idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public Long getIdKyLuat() {
        return idKyLuat;
    }

    public void setMucPhat(Double mucPhat) {
        this.mucPhat = mucPhat;
    }

    public Long getIsCount() {
        return isCount;
    }

    public void setIsCount(Long isCount) {
        this.isCount = isCount;
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

    public LocalDateTime getNgaySinhNhanVien() {
        return this.ngaySinhNhanVien;
    }

    public void setNgaySinhNhanVien(LocalDateTime ngaySinhNhanVien) {
        this.ngaySinhNhanVien = ngaySinhNhanVien;
    }

    public LocalDateTime getNgayBatDauNhanVien() {
        return this.ngayBatDauNhanVien;
    }

    public void setNgayBatDauNhanVien(LocalDateTime ngayBatDauNhanVien) {
        this.ngayBatDauNhanVien = ngayBatDauNhanVien;
    }

    public LocalDateTime getNgayKetThucNhanVien() {
        return this.ngayKetThucNhanVien;
    }

    public void setNgayKetThucNhanVien(LocalDateTime ngayKetThucNhanVien) {
        this.ngayKetThucNhanVien = ngayKetThucNhanVien;
    }

    public Long getidKyLuat() {
        return this.idKyLuat;
    }

    public void setIdKyLuat(Long idKyLuat) {
        this.idKyLuat = idKyLuat;
    }

    public String getTenLoi() {
        return this.tenLoi;
    }

    public void setTenLoi(String tenLoi) {
        this.tenLoi = tenLoi;
    }

    public Double getMucPhat() {
        return this.mucPhat;
    }

    public void setmucphat(Double mucPhat) {
        this.mucPhat = mucPhat;
    }

    public String getNhanVienMieuTa() {
        return this.nhanVienMieuTa;
    }

    public void setNhanVienMieuTa(String nhanVienMieuTa) {
        this.nhanVienMieuTa = nhanVienMieuTa;
    }
}
