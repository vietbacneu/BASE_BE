package com.example.qlbhbe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

/**
 * Generated at 2021/11/11 21:30:52
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PhuongThucThanhToanDTO {

    private Long id;

    private String maPhuongThuc;

    private String tenPhuongThuc;

    private String status;

    private String mieuTa;

    private LocalDate ngayTao;

    private String nguoiTao;

    private String nguoiThayDoi;

    private LocalDate ngayThayDoi;

    private Long isCount;

    public PhuongThucThanhToanDTO() {
    }

    public PhuongThucThanhToanDTO(Long id, String maPhuongThuc, String tenPhuongThuc, String status, String mieuTa, LocalDate ngayTao, String nguoiTao, String nguoiThayDoi, LocalDate ngayThayDoi) {
        this.id = id;
        this.maPhuongThuc = maPhuongThuc;
        this.tenPhuongThuc = tenPhuongThuc;
        this.status = status;
        this.mieuTa = mieuTa;
        this.ngayTao = ngayTao;
        this.nguoiTao = nguoiTao;
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

    public String getMaPhuongThuc() {
        return this.maPhuongThuc;
    }

    public void setMaPhuongThuc(String maPhuongThuc) {
        this.maPhuongThuc = maPhuongThuc;
    }

    public String getTenPhuongThuc() {
        return this.tenPhuongThuc;
    }

    public void setTenPhuongThuc(String tenPhuongThuc) {
        this.tenPhuongThuc = tenPhuongThuc;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMieuTa() {
        return this.mieuTa;
    }

    public void setMieuTa(String mieuTa) {
        this.mieuTa = mieuTa;
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
