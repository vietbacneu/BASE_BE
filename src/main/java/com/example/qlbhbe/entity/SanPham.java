package com.example.qlbhbe.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "san_pham")
@EntityListeners(AuditingEntityListener.class)
public class SanPham implements Serializable {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ma_san_pham")
    private String maSanPham;

    @Column(name = "ten_san_pham")
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
    private LocalDate ngayTao;

    @CreatedBy
    @Column(name = "nguoi_tao")
    private String nguoiTao;

    @LastModifiedDate
    @Column(name = "ngay_thay_doi")
    private LocalDate ngayThayDoi;

    @LastModifiedBy
    @Column(name = "nguoi_thay_doi")
    private String nguoiThayDoi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_danh_muc")
    private DanhMuc idDanhMuc;

    @Column(name = "don_vi")
    private String donVi ;

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


    public DanhMuc getIdDanhMuc() {
        return this.idDanhMuc;
    }

    public void setIdDanhMuc(DanhMuc idDanhMuc) {
        this.idDanhMuc = idDanhMuc;
    }
}
