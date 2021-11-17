package com.example.qlbhbe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDateTime;

/**
 * Generated at 2021/11/12 14:13:53
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChamCongDTO {

    private Long id;

    private Double soGioLam;

    private LocalDateTime ngayLam;

    private String mieuTa;

    private Long idNhanVien;
    private String hoNhanVien;
    private String tenNhanVien;
    private String tenChucVu;
    private String tenPhongBan;
    private Long isCount;
    private Long idPhongBan;

    public ChamCongDTO() {
    }

    public ChamCongDTO(Long id, Double soGioLam, LocalDateTime ngayLam, String mieuTa) {
        this.id = id;
        this.soGioLam = soGioLam;
        this.ngayLam = ngayLam;
        this.mieuTa = mieuTa;
    }

    public Long getIdNhanVien() {
        return idNhanVien;
    }

    public Long getIdPhongBan() {
        return idPhongBan;
    }

    public void setIdPhongBan(Long idPhongBan) {
        this.idPhongBan = idPhongBan;
    }

    public Long getIsCount() {
        return isCount;
    }

    public void setIsCount(Long isCount) {
        this.isCount = isCount;
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

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getSoGioLam() {
        return this.soGioLam;
    }

    public void setSoGioLam(Double soGioLam) {
        this.soGioLam = soGioLam;
    }

    public LocalDateTime getNgayLam() {
        return this.ngayLam;
    }

    public void setNgayLam(LocalDateTime ngayLam) {
        this.ngayLam = ngayLam;
    }

    public String getMieuTa() {
        return this.mieuTa;
    }

    public void setMieuTa(String mieuTa) {
        this.mieuTa = mieuTa;
    }
}
