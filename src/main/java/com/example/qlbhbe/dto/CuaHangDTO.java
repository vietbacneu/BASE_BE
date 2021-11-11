package com.example.qlbhbe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDate;

/**
 * Generated at 2021/11/09 17:47:54
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CuaHangDTO {

    private Long id;

    private String maCuaHang;

    private String tenCuaHang;

    private String diaChi;

    private String trangThai;

    private LocalDate ngayTao = LocalDate.now();

    private String nguoiTao = "admin";

    private LocalDate ngayThayDoi = LocalDate.now();

    private String nguoiThayDoi = "admin";

    private Long isCount;

    public CuaHangDTO() {
    }

    public CuaHangDTO(Long id, String maCuaHang, String tenCuaHang, String diaChi, String trangThai, String nguoiTao, LocalDate ngayTao, String nguoiThayDoi, LocalDate ngayThayDoi) {
        this.id = id;
        this.maCuaHang = maCuaHang;
        this.tenCuaHang = tenCuaHang;
        this.diaChi = diaChi;
        this.trangThai = trangThai;
        this.nguoiTao = nguoiTao;
        this.ngayTao = ngayTao;
        this.nguoiThayDoi = nguoiThayDoi;
        this.ngayThayDoi = ngayThayDoi;
    }

    public Long getIsCount() {
        return isCount;
    }

    public void setIsCount(Long isCount) {
        this.isCount = isCount;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMaCuaHang() {
        return this.maCuaHang;
    }

    public void setMaCuaHang(String maCuaHang) {
        this.maCuaHang = maCuaHang;
    }

    public String getTenCuaHang() {
        return this.tenCuaHang;
    }

    public void setTenCuaHang(String tenCuaHang) {
        this.tenCuaHang = tenCuaHang;
    }

    public String getDiaChi() {
        return this.diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getTrangThai() {
        return this.trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
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
