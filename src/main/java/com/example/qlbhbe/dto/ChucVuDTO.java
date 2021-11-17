package com.example.qlbhbe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Generated at 2021/11/12 14:15:23
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChucVuDTO {

    private Long id;

    private String maChucVu;

    private String tenChucVu;

    private String mieuTa;

    private Double heSoLuong;

    private Long isCount;

    public ChucVuDTO() {
    }

    public ChucVuDTO(Long id, String tenChucVu, String mieuTa, Double heSoLuong) {
        this.id = id;
        this.tenChucVu = tenChucVu;
        this.mieuTa = mieuTa;
        this.heSoLuong = heSoLuong;
    }

    public String getMaChucVu() {
        return maChucVu;
    }

    public void setMaChucVu(String maChucVu) {
        this.maChucVu = maChucVu;
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

    public String getTenChucVu() {
        return this.tenChucVu;
    }

    public void setTenChucVu(String tenChucVu) {
        this.tenChucVu = tenChucVu;
    }

    public String getMieuTa() {
        return this.mieuTa;
    }

    public void setMieuTa(String mieuTa) {
        this.mieuTa = mieuTa;
    }

    public Double getHeSoLuong() {
        return this.heSoLuong;
    }

    public void setHeSoLuong(Double heSoLuong) {
        this.heSoLuong = heSoLuong;
    }
}
