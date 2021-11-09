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

import org.apache.tomcat.jni.Local;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "nhap_hang_chi_tiet")
@EntityListeners(AuditingEntityListener.class)
public class NhapHangChiTiet implements Serializable {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "so_luong")
    private Double soLuong;

    @Column(name = "gia")
    private Double gia;

    @Column(name = "mieu_ta")
    private String mieuTa;

    @Column(name = "ngay_het_han")
    private LocalDate ngayHetHan;

    @Column(name = "ngay_san_xuat")
    private LocalDate ngaySanXuat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nhap_hang")
    private NhapHang idNhapHang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_san_pham")
    private SanPham idSanPham;

    public LocalDate getNgayHetHan() {
        return ngayHetHan;
    }

    public void setNgayHetHan(LocalDate ngayHetHan) {
        this.ngayHetHan = ngayHetHan;
    }

    public LocalDate getNgaySanXuat() {
        return ngaySanXuat;
    }

    public void setNgaySanXuat(LocalDate ngaySanXuat) {
        this.ngaySanXuat = ngaySanXuat;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getSoLuong() {
        return this.soLuong;
    }

    public void setSoLuong(Double soLuong) {
        this.soLuong = soLuong;
    }

    public Double getGia() {
        return this.gia;
    }

    public void setGia(Double gia) {
        this.gia = gia;
    }

    public String getMieuTa() {
        return this.mieuTa;
    }

    public void setMieuTa(String mieuTa) {
        this.mieuTa = mieuTa;
    }

    public NhapHang getIdNhapHang() {
        return this.idNhapHang;
    }

    public void setIdNhapHang(NhapHang idNhapHang) {
        this.idNhapHang = idNhapHang;
    }

    public SanPham getIdSanPham() {
        return this.idSanPham;
    }

    public void setIdSanPham(SanPham idSanPham) {
        this.idSanPham = idSanPham;
    }
}
