package com.example.qlbhbe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

/**
 * Generated at 2022/04/12 16:12:37
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BanHangDTO {

    private Long id;

    private String maBanHang;

    private LocalDate ngayXuat;

    private String nguoiTao;

    private LocalDate ngayTao;

    private String nguoiThayDoi;

    private LocalDate ngayThayDoi;

    public BanHangDTO() {
    }

    public BanHangDTO(Long id, String maBanHang, LocalDate ngayXuat, String nguoiTao, LocalDate ngayTao, String nguoiThayDoi, LocalDate ngayThayDoi) {
        this.id = id;
        this.maBanHang = maBanHang;
        this.ngayXuat = ngayXuat;
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

    public LocalDate getNgayXuat() {
        return this.ngayXuat;
    }

    public void setNgayXuat(LocalDate ngayXuat) {
        this.ngayXuat = ngayXuat;
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
