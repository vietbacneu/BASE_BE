package com.example.qlbhbe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Column;
import java.time.LocalDate;

/**
 * Generated at 2021/10/14 17:17:58
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class SanPhamDTO {

    private Long id;

    private Double soLuong;

    private String mieuTa;

    private String maSanPham;

    private String tenSanPham;

    private Double giaSanPham;

    private String nguoiTao;

    private LocalDate ngayTao;

    private LocalDate nguoiThayDoi;

    private String ngayThayDoi;

    private String tenDanhMuc;

    private String hanSanPham;

    private String donVi ;
    private Double giaBanNiemYet;

    private Double giaNhapNiemYet;

    public Double getGiaBanNiemYet() {
        return giaBanNiemYet;
    }

    public void setGiaBanNiemYet(Double giaBanNiemYet) {
        this.giaBanNiemYet = giaBanNiemYet;
    }

    public Double getGiaNhapNiemYet() {
        return giaNhapNiemYet;
    }

    public void setGiaNhapNiemYet(Double giaNhapNiemYet) {
        this.giaNhapNiemYet = giaNhapNiemYet;
    }

    public String getHanSanPham() {
        return hanSanPham;
    }

    public void setHanSanPham(String hanSanPham) {
        this.hanSanPham = hanSanPham;
    }

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public SanPhamDTO() {
    }

    public SanPhamDTO(Long id, Double soLuong, String mieuTa, String maSanPham, String tenSanPham, Double giaSanPham, String nguoiTao, LocalDate ngayTao, LocalDate nguoiThayDoi, String ngayThayDoi, String tenDanhMuc) {
        this.id = id;
        this.soLuong = soLuong;
        this.mieuTa = mieuTa;
        this.maSanPham = maSanPham;
        this.tenSanPham = tenSanPham;
        this.giaSanPham = giaSanPham;
        this.nguoiTao = nguoiTao;
        this.ngayTao = ngayTao;
        this.nguoiThayDoi = nguoiThayDoi;
        this.ngayThayDoi = ngayThayDoi;
        this.tenDanhMuc = tenDanhMuc;
    }

    public String getTenDanhMuc() {
        return tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
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

    public String getMieuTa() {
        return this.mieuTa;
    }

    public void setMieuTa(String mieuTa) {
        this.mieuTa = mieuTa;
    }

    public String getMaSanPham() {
        return this.maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return this.tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public Double getGiaSanPham() {
        return this.giaSanPham;
    }

    public void setGiaSanPham(Double giaSanPham) {
        this.giaSanPham = giaSanPham;
    }

    public String getNguoiTao() {
        return this.nguoiTao;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public LocalDate getNgayTao() {
        return this.ngayTao;
    }

    public void setNgayTao(LocalDate ngayTao) {
        this.ngayTao = ngayTao;
    }

    public LocalDate getNguoiThayDoi() {
        return this.nguoiThayDoi;
    }

    public void setNguoiThayDoi(LocalDate nguoiThayDoi) {
        this.nguoiThayDoi = nguoiThayDoi;
    }

    public String getNgayThayDoi() {
        return this.ngayThayDoi;
    }

    public void setNgayThayDoi(String ngayThayDoi) {
        this.ngayThayDoi = ngayThayDoi;
    }
}
