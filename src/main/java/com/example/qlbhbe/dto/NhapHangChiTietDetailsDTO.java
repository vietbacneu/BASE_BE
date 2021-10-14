package com.example.qlbhbe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

/**
 * Generated at 2021/10/14 17:26:26
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NhapHangChiTietDetailsDTO {

    private Long id;

    private Double soLuong;

    private Double gia;

    private String mieuTa;

    private String idNhapHangMaNhapHang;

    private LocalDate idNhapHangNgayNhap;

    private String idNhapHangNguoiTao;

    private LocalDate idNhapHangNgayTao;

    private String idNhapHangNguoiThayDoi;

    private LocalDate idNhapHangNgayThayDoi;

    private String idSanPhamMaSanPham;

    private String idSanPhamTenSanPham;

    private Double idSanPhamGiaSanPham;

    private Double idSanPhamSoLuong;

    private String idSanPhamMieuTa;

    private String idSanPhamNguoiTao;

    private LocalDate idSanPhamNgayTao;

    private LocalDate idSanPhamNguoiThayDoi;

    private String idSanPhamNgayThayDoi;

    public NhapHangChiTietDetailsDTO() {
    }

    public NhapHangChiTietDetailsDTO(Long id, Double soLuong, Double gia, String mieuTa, String idNhapHangMaNhapHang, LocalDate idNhapHangNgayNhap, String idNhapHangNguoiTao, LocalDate idNhapHangNgayTao, String idNhapHangNguoiThayDoi, LocalDate idNhapHangNgayThayDoi, String idSanPhamMaSanPham, String idSanPhamTenSanPham, Double idSanPhamGiaSanPham, Double idSanPhamSoLuong, String idSanPhamMieuTa, String idSanPhamNguoiTao, LocalDate idSanPhamNgayTao, LocalDate idSanPhamNguoiThayDoi, String idSanPhamNgayThayDoi) {
        this.id = id;
        this.soLuong = soLuong;
        this.gia = gia;
        this.mieuTa = mieuTa;
        this.idNhapHangMaNhapHang = idNhapHangMaNhapHang;
        this.idNhapHangNgayNhap = idNhapHangNgayNhap;
        this.idNhapHangNguoiTao = idNhapHangNguoiTao;
        this.idNhapHangNgayTao = idNhapHangNgayTao;
        this.idNhapHangNguoiThayDoi = idNhapHangNguoiThayDoi;
        this.idNhapHangNgayThayDoi = idNhapHangNgayThayDoi;
        this.idSanPhamMaSanPham = idSanPhamMaSanPham;
        this.idSanPhamTenSanPham = idSanPhamTenSanPham;
        this.idSanPhamGiaSanPham = idSanPhamGiaSanPham;
        this.idSanPhamSoLuong = idSanPhamSoLuong;
        this.idSanPhamMieuTa = idSanPhamMieuTa;
        this.idSanPhamNguoiTao = idSanPhamNguoiTao;
        this.idSanPhamNgayTao = idSanPhamNgayTao;
        this.idSanPhamNguoiThayDoi = idSanPhamNguoiThayDoi;
        this.idSanPhamNgayThayDoi = idSanPhamNgayThayDoi;
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

    public String getIdNhapHangMaNhapHang() {
        return this.idNhapHangMaNhapHang;
    }

    public void setIdNhapHangMaNhapHang(String idNhapHangMaNhapHang) {
        this.idNhapHangMaNhapHang = idNhapHangMaNhapHang;
    }

    public LocalDate getIdNhapHangNgayNhap() {
        return this.idNhapHangNgayNhap;
    }

    public void setIdNhapHangNgayNhap(LocalDate idNhapHangNgayNhap) {
        this.idNhapHangNgayNhap = idNhapHangNgayNhap;
    }

    public String getIdNhapHangNguoiTao() {
        return this.idNhapHangNguoiTao;
    }

    public void setIdNhapHangNguoiTao(String idNhapHangNguoiTao) {
        this.idNhapHangNguoiTao = idNhapHangNguoiTao;
    }

    public LocalDate getIdNhapHangNgayTao() {
        return this.idNhapHangNgayTao;
    }

    public void setIdNhapHangNgayTao(LocalDate idNhapHangNgayTao) {
        this.idNhapHangNgayTao = idNhapHangNgayTao;
    }

    public String getIdNhapHangNguoiThayDoi() {
        return this.idNhapHangNguoiThayDoi;
    }

    public void setIdNhapHangNguoiThayDoi(String idNhapHangNguoiThayDoi) {
        this.idNhapHangNguoiThayDoi = idNhapHangNguoiThayDoi;
    }

    public LocalDate getIdNhapHangNgayThayDoi() {
        return this.idNhapHangNgayThayDoi;
    }

    public void setIdNhapHangNgayThayDoi(LocalDate idNhapHangNgayThayDoi) {
        this.idNhapHangNgayThayDoi = idNhapHangNgayThayDoi;
    }

    public String getIdSanPhamMaSanPham() {
        return this.idSanPhamMaSanPham;
    }

    public void setIdSanPhamMaSanPham(String idSanPhamMaSanPham) {
        this.idSanPhamMaSanPham = idSanPhamMaSanPham;
    }

    public String getIdSanPhamTenSanPham() {
        return this.idSanPhamTenSanPham;
    }

    public void setIdSanPhamTenSanPham(String idSanPhamTenSanPham) {
        this.idSanPhamTenSanPham = idSanPhamTenSanPham;
    }

    public Double getIdSanPhamGiaSanPham() {
        return this.idSanPhamGiaSanPham;
    }

    public void setIdSanPhamGiaSanPham(Double idSanPhamGiaSanPham) {
        this.idSanPhamGiaSanPham = idSanPhamGiaSanPham;
    }

    public Double getIdSanPhamSoLuong() {
        return this.idSanPhamSoLuong;
    }

    public void setIdSanPhamSoLuong(Double idSanPhamSoLuong) {
        this.idSanPhamSoLuong = idSanPhamSoLuong;
    }

    public String getIdSanPhamMieuTa() {
        return this.idSanPhamMieuTa;
    }

    public void setIdSanPhamMieuTa(String idSanPhamMieuTa) {
        this.idSanPhamMieuTa = idSanPhamMieuTa;
    }

    public String getIdSanPhamNguoiTao() {
        return this.idSanPhamNguoiTao;
    }

    public void setIdSanPhamNguoiTao(String idSanPhamNguoiTao) {
        this.idSanPhamNguoiTao = idSanPhamNguoiTao;
    }

    public LocalDate getIdSanPhamNgayTao() {
        return this.idSanPhamNgayTao;
    }

    public void setIdSanPhamNgayTao(LocalDate idSanPhamNgayTao) {
        this.idSanPhamNgayTao = idSanPhamNgayTao;
    }

    public LocalDate getIdSanPhamNguoiThayDoi() {
        return this.idSanPhamNguoiThayDoi;
    }

    public void setIdSanPhamNguoiThayDoi(LocalDate idSanPhamNguoiThayDoi) {
        this.idSanPhamNguoiThayDoi = idSanPhamNguoiThayDoi;
    }

    public String getIdSanPhamNgayThayDoi() {
        return this.idSanPhamNgayThayDoi;
    }

    public void setIdSanPhamNgayThayDoi(String idSanPhamNgayThayDoi) {
        this.idSanPhamNgayThayDoi = idSanPhamNgayThayDoi;
    }
}
