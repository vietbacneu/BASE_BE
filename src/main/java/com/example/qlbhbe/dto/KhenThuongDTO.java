package com.example.qlbhbe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Generated at 2021/11/12 14:15:44
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class KhenThuongDTO {

    private Long id;

    private String maKhenThuong;

    private String ten;

    private Double mucThuong;

    private String mieuTa;

    private Long isCount;

    public KhenThuongDTO() {
    }

    public KhenThuongDTO(Long id, String ten, Double mucThuong, String mieuTa) {
        this.id = id;
        this.ten = ten;
        this.mucThuong = mucThuong;
        this.mieuTa = mieuTa;
    }


    public String getMaKhenThuong() {
        return maKhenThuong;
    }

    public void setMaKhenThuong(String maKhenThuong) {
        this.maKhenThuong = maKhenThuong;
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

    public String getTen() {
        return this.ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Double getMucThuong() {
        return this.mucThuong;
    }

    public void setMucThuong(Double mucThuong) {
        this.mucThuong = mucThuong;
    }

    public String getMieuTa() {
        return this.mieuTa;
    }

    public void setMieuTa(String mieuTa) {
        this.mieuTa = mieuTa;
    }
}
