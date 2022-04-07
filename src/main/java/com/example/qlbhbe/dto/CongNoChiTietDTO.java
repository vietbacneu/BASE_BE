package com.example.qlbhbe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

/**
 * Generated at 2022/04/03 21:29:02
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CongNoChiTietDTO {

    private Long id;

    private Double soTienThanhToan;

    private LocalDate ngayThanhToan;

    private String trangThai;

    private String trangThaiName;

    private String maCongNo;



    public CongNoChiTietDTO() {
    }

    public CongNoChiTietDTO(Long id, Double soTienThanhToan, LocalDate ngayThanhToan) {
        this.id = id;
        this.soTienThanhToan = soTienThanhToan;
        this.ngayThanhToan = ngayThanhToan;
    }

    public String getTrangThaiName() {
        return trangThaiName;
    }

    public void setTrangThaiName(String trangThaiName) {
        this.trangThaiName = trangThaiName;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public String getMaCongNo() {
        return maCongNo;
    }

    public void setMaCongNo(String maCongNo) {
        this.maCongNo = maCongNo;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getSoTienThanhToan() {
        return this.soTienThanhToan;
    }

    public void setSoTienThanhToan(Double soTienThanhToan) {
        this.soTienThanhToan = soTienThanhToan;
    }

    public LocalDate getNgayThanhToan() {
        return this.ngayThanhToan;
    }

    public void setNgayThanhToan(LocalDate ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }
}
