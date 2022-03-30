package com.example.qlbhbe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;

/**
 * Generated at 2021/11/12 14:15:44
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class KhenThuongDTO {

    private Long id;

    private String maDanhGia;

    private String ten;

    private Double soTien;

    private String mieuTa;

    private String loai;

    private String tenLoai;

    private Long isCount;

    public KhenThuongDTO() {
    }

    public Long getIsCount() {
        return isCount;
    }

    public String getTenLoai() {
        return tenLoai;
    }

    public void setTenLoai(String tenLoai) {
        this.tenLoai = tenLoai;
    }

    public void setIsCount(Long isCount) {
        this.isCount = isCount;
    }

    public Double getSoTien() {
        return soTien;
    }

    public void setSoTien(Double soTien) {
        this.soTien = soTien;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaDanhGia() {
        return maDanhGia;
    }

    public void setMaDanhGia(String maDanhGia) {
        this.maDanhGia = maDanhGia;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }


    public String getMieuTa() {
        return mieuTa;
    }

    public void setMieuTa(String mieuTa) {
        this.mieuTa = mieuTa;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }
}
