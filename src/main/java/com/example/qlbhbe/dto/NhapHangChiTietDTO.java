package com.example.qlbhbe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import java.time.LocalDate;

/**
 * Generated at 2021/10/14 17:17:51
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NhapHangChiTietDTO {

    private Long id;

    private Double soLuong;

    private Double gia;

    private String mieuTa;

    private Long idNhapHang;

    private LocalDate ngayHetHan;

    private LocalDate ngaySanXuat;

    public NhapHangChiTietDTO() {
    }

    public NhapHangChiTietDTO(Long id, Double soLuong, Double gia, String mieuTa, Long idNhaCungCap) {
        this.id = id;
        this.soLuong = soLuong;
        this.gia = gia;
        this.mieuTa = mieuTa;
        this.idNhapHang = idNhaCungCap;
    }

    public LocalDate getNgaySanXuat() {
        return ngaySanXuat;
    }

    public void setNgaySanXuat(LocalDate ngaySanXuat) {
        this.ngaySanXuat = ngaySanXuat;
    }

    public LocalDate getNgayHetHan() {
        return ngayHetHan;
    }

    public void setNgayHetHan(LocalDate ngayHetHan) {
        this.ngayHetHan = ngayHetHan;
    }

    public Long getIdNhapHang() {
        return idNhapHang;
    }

    public void setIdNhapHang(Long idNhapHang) {
        this.idNhapHang = idNhapHang;
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
