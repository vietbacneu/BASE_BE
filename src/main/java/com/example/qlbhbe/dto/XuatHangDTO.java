package com.example.qlbhbe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDate;

/**
 * Generated at 2021/10/14 17:18:02
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class XuatHangDTO {

    private Long id;

    private String nguoiTao;

    private LocalDate ngayTao;

    private String nguoiThayDoi;

    private LocalDate ngayThayDoi;

    private String maXuatHang;

    private LocalDate ngayNhap;

    public XuatHangDTO() {
    }

    public XuatHangDTO(Long id, String nguoiTao, LocalDate ngayTao, String nguoiThayDoi, LocalDate ngayThayDoi, String maXuatHang, LocalDate ngayNhap) {
        this.id = id;
        this.nguoiTao = nguoiTao;
        this.ngayTao = ngayTao;
        this.nguoiThayDoi = nguoiThayDoi;
        this.ngayThayDoi = ngayThayDoi;
        this.maXuatHang = maXuatHang;
        this.ngayNhap = ngayNhap;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getMaXuatHang() {
        return this.maXuatHang;
    }

    public void setMaXuatHang(String maXuatHang) {
        this.maXuatHang = maXuatHang;
    }

    public LocalDate getNgayNhap() {
        return this.ngayNhap;
    }

    public void setNgayNhap(LocalDate ngayNhap) {
        this.ngayNhap = ngayNhap;
    }
}