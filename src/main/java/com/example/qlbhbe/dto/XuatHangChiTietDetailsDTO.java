package com.example.qlbhbe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Generated at 2021/10/14 17:26:54
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class XuatHangChiTietDetailsDTO {

    private Long id;

    private Double soLuong;

    private Double gia;

    private String mieuTa;

    public XuatHangChiTietDetailsDTO() {
    }

    public XuatHangChiTietDetailsDTO(Long id, Double soLuong, Double gia, String mieuTa) {
        this.id = id;
        this.soLuong = soLuong;
        this.gia = gia;
        this.mieuTa = mieuTa;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getSoLuong() {
        return this.soLuong;
    }

    public void setSoLuong(Double soLuong) {
        this.soLuong = soLuong;
    }

    public Double getGia() {
        return this.gia;
    }

    public void setGia(Double gia) {
        this.gia = gia;
    }

    public String getMieuTa() {
        return this.mieuTa;
    }

    public void setMieuTa(String mieuTa) {
        this.mieuTa = mieuTa;
    }
}
