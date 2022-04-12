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
@Table(name = "nguyen_vat_lieu")
@EntityListeners(AuditingEntityListener.class)
public class SanPham implements Serializable {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ma_nguyen_vat_lieu")
    private String maSanPham;

    @Column(name = "ten_nguyen_vat_lieu")
    private String tenSanPham;

    @Column(name = "gia_ban_niem_yet")
    private Double giaBanNiemYet;

    @Column(name = "gia_nhap_niem_yet")
    private Double giaNhapNiemYet;

    @Column(name = "so_luong")
    private Double soLuong;

    @Column(name = "mieu_ta")
    private String mieuTa;

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

    @Column(name = "don_vi")
    private String donVi;

    public Double getGiaBanNiemYet() {
        return giaBanNiemYet;
    }

    public void setGiaBanNiemYet(Double giaBanNiemYet) {
        this.giaBanNiemYet = giaBanNiemYet;
    }

    public Double getGiaNhapNiemYet() {
        return giaNhapNiemYet;
    }

    public void setGiaNhapNiemYet(Double giaNhapNiemYet) {
        this.giaNhapNiemYet = giaNhapNiemYet;
    }

    public String getDonVi() {
        return donVi;
    }

    public void setDonVi(String donVi) {
        this.donVi = donVi;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMaSanPham() {
        return this.maSanPham;
    }

    public void setMaSanPham(String maSanPham) {
        this.maSanPham = maSanPham;
    }

    public String getTenSanPham() {
        return this.tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public Double getSoLuong() {
        return this.soLuong;
    }

    public void setSoLuong(Double soLuong) {
        this.soLuong = soLuong;
    }

    public String getMieuTa() {
        return this.mieuTa;
    }

    public void setMieuTa(String mieuTa) {
        this.mieuTa = mieuTa;
    }

    public LocalDate getNgayThayDoi() {
        return ngayThayDoi;
    }

    public void setNgayThayDoi(LocalDate ngayThayDoi) {
        this.ngayThayDoi = ngayThayDoi;
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

    public java.time.LocalDate getNgayTao() {
        return this.ngayTao;
    }

    public void setNgayTao(java.time.LocalDate ngayTao) {
        this.ngayTao = ngayTao;
    }

}
