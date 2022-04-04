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

    public CongNoChiTietDTO() {
    }

    public CongNoChiTietDTO(Long id, Double soTienThanhToan, LocalDate ngayThanhToan) {
        this.id = id;
        this.soTienThanhToan = soTienThanhToan;
        this.ngayThanhToan = ngayThanhToan;
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
