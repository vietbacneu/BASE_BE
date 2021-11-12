package com.example.qlbhbe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Generated at 2021/11/12 14:17:04
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PhongBanDTO {

    private Long id;

    private String ten;

    private String thongTin;

    private String mieuTa;

    public PhongBanDTO() {
    }

    public PhongBanDTO(Long id, String ten, String thongTin, String mieuTa) {
        this.id = id;
        this.ten = ten;
        this.thongTin = thongTin;
        this.mieuTa = mieuTa;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTen() {
        return this.ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getThongTin() {
        return this.thongTin;
    }

    public void setThongTin(String thongTin) {
        this.thongTin = thongTin;
    }

    public String getMieuTa() {
        return this.mieuTa;
    }

    public void setMieuTa(String mieuTa) {
        this.mieuTa = mieuTa;
    }
}
