package com.example.qlbhbe.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "nha_cung_cap")
@EntityListeners(AuditingEntityListener.class)
public class NhaCungCap implements Serializable {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ma_nha_cung_cap")
    private String maNhaCungCap;

    @Column(name = "ten_nha_cung_cap")
    private String tenNhaCungCap;

    @Column(name = "ma_so_thue")
    private String maSoThue;

    @Column(name = "dia_chi")
    private String diaChi;

    @Column(name = "sdt")
    private String sdt;

    @Column(name = "thong_tin")
    private String thongTin;

     @CreatedDate
    @Column(name = "ngay_tao")
    private LocalDate ngayTao = LocalDate.now();

    @CreatedBy
    @Column(name = "nguoi_tao")
    private String nguoiTao = "admin";

    @LastModifiedDate
    @Column(name = "ngay_thay_doi")
    private LocalDate ngayThayDoi = LocalDate.now();

    @LastModifiedBy
    @Column(name = "nguoi_thay_doi")
    private String nguoiThayDoi = "admin";

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMaNhaCungCap() {
        return this.maNhaCungCap;
    }

    public void setMaNhaCungCap(String maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }

    public String getTenNhaCungCap() {
        return this.tenNhaCungCap;
    }

    public void setTenNhaCungCap(String tenNhaCungCap) {
        this.tenNhaCungCap = tenNhaCungCap;
    }

    public String getMaSoThue() {
        return this.maSoThue;
    }

    public void setMaSoThue(String maSoThue) {
        this.maSoThue = maSoThue;
    }

    public String getDiaChi() {
        return this.diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return this.sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getThongTin() {
        return this.thongTin;
    }

    public void setThongTin(String thongTin) {
        this.thongTin = thongTin;
    }

    public java.time.LocalDate getNgayTao() {
        return this.ngayTao;
    }

    public void setNgayTao(java.time.LocalDate ngayTao) {
        this.ngayTao = ngayTao;
    }

    public java.time.LocalDate getNgayThayDoi() {
        return this.ngayThayDoi;
    }

    public void setNgayThayDoi(java.time.LocalDate ngayThayDoi) {
        this.ngayThayDoi = ngayThayDoi;
    }


    public NhaCungCap(long id) {
        this.id = id;
    }

    public NhaCungCap() {
    }

    public String getNguoiTao() {
        return nguoiTao;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public String getNguoiThayDoi() {
        return nguoiThayDoi;
    }

    public void setNguoiThayDoi(String nguoiThayDoi) {
        this.nguoiThayDoi = nguoiThayDoi;
    }
}
