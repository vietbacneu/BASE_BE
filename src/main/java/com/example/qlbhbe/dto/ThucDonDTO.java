package com.example.qlbhbe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

/**
 * Generated at 2022/04/12 16:12:49
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ThucDonDTO {

    private Long id;

    private String maThucDon;

    private String tenThucDon;

    private String thongTin;

    private LocalDate ngayTao;

    private String nguoiTao;

    private LocalDate ngayThayDoi;

    private String nguoiThayDoi;

    private Double gia;

    private Long isCount;

    public ThucDonDTO() {
    }

    public ThucDonDTO(Long id, String maThucDon, String tenThucDon, String thongTin, LocalDate ngayTao, String nguoiTao, LocalDate ngayThayDoi, String nguoiThayDoi) {
        this.id = id;
        this.maThucDon = maThucDon;
        this.tenThucDon = tenThucDon;
        this.thongTin = thongTin;
        this.ngayTao = ngayTao;
        this.nguoiTao = nguoiTao;
        this.ngayThayDoi = ngayThayDoi;
        this.nguoiThayDoi = nguoiThayDoi;
    }

    public Double getGia() {
        return gia;
    }

    public Long getIsCount() {
        return isCount;
    }

    public void setIsCount(Long isCount) {
        this.isCount = isCount;
    }

    public void setGia(Double gia) {
        this.gia = gia;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaThucDon() {
        return this.maThucDon;
    }

    public void setMaThucDon(String maThucDon) {
        this.maThucDon = maThucDon;
    }

    public String getTenThucDon() {
        return this.tenThucDon;
    }

    public void setTenThucDon(String tenThucDon) {
        this.tenThucDon = tenThucDon;
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
