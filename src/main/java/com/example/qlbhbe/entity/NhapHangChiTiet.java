package com.example.qlbhbe.entity;

import java.io.Serializable;
import java.time.LocalDate;
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

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "hop_dong_nhap_hang_chi_tiet")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nhap_hang")
    private NhapHang nhapHang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_hang_hoa")
    private SanPham sanPham;

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

    public NhapHang getNhapHang() {
        return this.nhapHang;
    }

    public void setNhapHang(NhapHang idNhapHang) {
        this.nhapHang = idNhapHang;
    }

    public SanPham getSanPham() {
        return this.sanPham;
    }

    public void setSanPham(SanPham idSanPham) {
        this.sanPham = idSanPham;
    }
}
