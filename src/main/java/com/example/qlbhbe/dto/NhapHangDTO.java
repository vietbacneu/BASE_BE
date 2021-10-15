package com.example.qlbhbe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDate;

/**
 * Generated at 2021/10/14 17:17:46
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NhapHangDTO {

    private Long id;

    private LocalDate ngayTao;

    private String nguoiTao;

    private LocalDate ngayThayDoi;

    private String nguoiThayDoi;

    private String maNhapHang;

    private LocalDate ngayNhap;

    private Long idNhaCungCap;

    private String tenNhaCungCap;

    private String startDate;

    private String endDate;


    public NhapHangDTO() {
    }

    public NhapHangDTO(Long id, LocalDate ngayTao, String nguoiTao, LocalDate ngayThayDoi, String nguoiThayDoi, String maNhapHang, LocalDate ngayNhap, String tenNhaCungCap) {
        this.id = id;
        this.ngayTao = ngayTao;
        this.nguoiTao = nguoiTao;
        this.ngayThayDoi = ngayThayDoi;
        this.nguoiThayDoi = nguoiThayDoi;
        this.maNhapHang = maNhapHang;
        this.ngayNhap = ngayNhap;
        this.tenNhaCungCap = tenNhaCungCap;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Long getIdNhaCungCap() {
        return idNhaCungCap;
    }

    public void setIdNhaCungCap(Long idNhaCungCap) {
        this.idNhaCungCap = idNhaCungCap;
    }

    public String getTenNhaCungCap() {
        return tenNhaCungCap;
    }

    public void setTenNhaCungCap(String tenNhaCungCap) {
        this.tenNhaCungCap = tenNhaCungCap;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getNgayTao() {
        return this.ngayTao;
    }

    public void setNgayTao(LocalDate ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getNguoiTao() {
        return this.nguoiTao;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public LocalDate getNgayThayDoi() {
        return this.ngayThayDoi;
    }

    public void setNgayThayDoi(LocalDate ngayThayDoi) {
        this.ngayThayDoi = ngayThayDoi;
    }

    public String getNguoiThayDoi() {
        return this.nguoiThayDoi;
    }

    public void setNguoiThayDoi(String nguoiThayDoi) {
        this.nguoiThayDoi = nguoiThayDoi;
    }

    public String getMaNhapHang() {
        return this.maNhapHang;
    }

    public void setMaNhapHang(String maNhapHang) {
        this.maNhapHang = maNhapHang;
    }

    public LocalDate getNgayNhap() {
        return this.ngayNhap;
    }

    public void setNgayNhap(LocalDate ngayNhap) {
        this.ngayNhap = ngayNhap;
    }
}
