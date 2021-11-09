package com.example.qlbhbe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

/**
 * Generated at 2021/10/14 17:18:07
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class XuatHangChiTietDTO {

    private Long id;

    private Double soLuong;

    private Double gia;

    private String mieuTa;

    private LocalDate ngayHetHan;

    private LocalDate ngaySanXuat;

    private Long idXuatHang;

    private Long idSanPham;

    private String tenSanPham;



    public XuatHangChiTietDTO() {
    }

    public XuatHangChiTietDTO(Long id, Double soLuong, Double gia, String mieuTa) {
        this.id = id;
        this.soLuong = soLuong;
        this.gia = gia;
        this.mieuTa = mieuTa;
    }

    public Long getIdXuatHang() {
        return idXuatHang;
    }

    public void setIdXuatHang(Long idXuatHang) {
        this.idXuatHang = idXuatHang;
    }

    public LocalDate getNgayHetHan() {
        return ngayHetHan;
    }

    public void setNgayHetHan(LocalDate ngayHetHan) {
        this.ngayHetHan = ngayHetHan;
    }

    public LocalDate getNgaySanXuat() {
        return ngaySanXuat;
    }

    public void setNgaySanXuat(LocalDate ngaySanXuat) {
        this.ngaySanXuat = ngaySanXuat;
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

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public Long getIdSanPham() {
        return idSanPham;
    }

    public void setIdSanPham(Long idSanPham) {
        this.idSanPham = idSanPham;
    }
}
