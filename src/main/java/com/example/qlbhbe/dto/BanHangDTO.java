package com.example.qlbhbe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.util.List;

/**
 * Generated at 2022/04/12 16:12:37
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BanHangDTO {

    private Long id;

    private String maBanHang;

    private LocalDate ngayBan;

    private String nguoiTao;

    private LocalDate ngayTao;

    private String nguoiThayDoi;

    private LocalDate ngayThayDoi;

    private String trangThai;

    List<BanHangChiTietDTO> banHangChiTietDTOS;

    private Double soTien;

    private String khachHangTen;

    private Long idKhachHang;

    private String tenPhuongThuc;

    private String trangThaiName;

    private String ghiChu;

    private Long idPhuongThuc;

    private Long isCount;

    private LocalDate startDate;

    private LocalDate endDate;

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Long getIsCount() {
        return isCount;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public String getTrangThaiName() {
        return trangThaiName;
    }

    public void setTrangThaiName(String trangThaiName) {
        this.trangThaiName = trangThaiName;
    }

    public void setIsCount(Long isCount) {
        this.isCount = isCount;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public List<BanHangChiTietDTO> getBanHangChiTietDTOS() {
        return banHangChiTietDTOS;
    }

    public void setBanHangChiTietDTOS(List<BanHangChiTietDTO> banHangChiTietDTOS) {
        this.banHangChiTietDTOS = banHangChiTietDTOS;
    }

    public Double getSoTien() {
        return soTien;
    }

    public void setSoTien(Double soTien) {
        this.soTien = soTien;
    }

    public String getKhachHangTen() {
        return khachHangTen;
    }

    public void setKhachHangTen(String khachHangTen) {
        this.khachHangTen = khachHangTen;
    }

    public Long getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(Long idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public String getTenPhuongThuc() {
        return tenPhuongThuc;
    }

    public void setTenPhuongThuc(String tenPhuongThuc) {
        this.tenPhuongThuc = tenPhuongThuc;
    }

    public Long getIdPhuongThuc() {
        return idPhuongThuc;
    }

    public void setIdPhuongThuc(Long idPhuongThuc) {
        this.idPhuongThuc = idPhuongThuc;
    }

    public BanHangDTO() {
    }

    public BanHangDTO(Long id, String maBanHang, LocalDate ngayBan, String nguoiTao, LocalDate ngayTao, String nguoiThayDoi, LocalDate ngayThayDoi) {
        this.id = id;
        this.maBanHang = maBanHang;
        this.ngayBan = ngayBan;
        this.nguoiTao = nguoiTao;
        this.ngayTao = ngayTao;
        this.nguoiThayDoi = nguoiThayDoi;
        this.ngayThayDoi = ngayThayDoi;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaBanHang() {
        return this.maBanHang;
    }

    public void setMaBanHang(String maBanHang) {
        this.maBanHang = maBanHang;
    }

    public LocalDate getNgayBan() {
        return this.ngayBan;
    }

    public void setNgayBan(LocalDate ngayBan) {
        this.ngayBan = ngayBan;
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

    public String getNguoiThayDoi() {
        return this.nguoiThayDoi;
    }

    public void setNguoiThayDoi(String nguoiThayDoi) {
        this.nguoiThayDoi = nguoiThayDoi;
    }

    public LocalDate getNgayThayDoi() {
        return this.ngayThayDoi;
    }

    public void setNgayThayDoi(LocalDate ngayThayDoi) {
        this.ngayThayDoi = ngayThayDoi;
    }
}
