package com.example.qlbhbe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDate;

/**
 * Generated at 2021/10/14 17:35:48
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class DanhMucDetailsDTO {

    private Long id;

    private String maDanhMuc;

    private String tenDanhMuc;

    private String thongTin;

    private LocalDate ngayTao = LocalDate.now();

    private String nguoiTao = "admin";

    private LocalDate ngayThayDoi = LocalDate.now();

    private String nguoiThayDoi = "admin";

    public DanhMucDetailsDTO() {
    }

    public DanhMucDetailsDTO(Long id, String maDanhMuc, String tenDanhMuc, String thongTin, LocalDate ngayTao, String nguoiTao, LocalDate ngayThayDoi, String nguoiThayDoi) {
        this.id = id;
        this.maDanhMuc = maDanhMuc;
        this.tenDanhMuc = tenDanhMuc;
        this.thongTin = thongTin;
        this.ngayTao = ngayTao;
        this.nguoiTao = nguoiTao;
        this.ngayThayDoi = ngayThayDoi;
        this.nguoiThayDoi = nguoiThayDoi;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaDanhMuc() {
        return this.maDanhMuc;
    }

    public void setMaDanhMuc(String maDanhMuc) {
        this.maDanhMuc = maDanhMuc;
    }

    public String getTenDanhMuc() {
        return this.tenDanhMuc;
    }

    public void setTenDanhMuc(String tenDanhMuc) {
        this.tenDanhMuc = tenDanhMuc;
    }

    public String getThongTin() {
        return this.thongTin;
    }

    public void setThongTin(String thongTin) {
        this.thongTin = thongTin;
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
}
