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
@Table(name = "hop_dong_ban_hang")
@EntityListeners(AuditingEntityListener.class)
public class XuatHang implements Serializable {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ma_hop_dong_ban_hang")
    private String maXuatHang;

    @Column(name = "duong_dan")
    private String duongDan;

    @Column(name = "hop_dong_dinh_kem")
    private String hopDongDinhKem;

    @Column(name = "ngay_xuat")
    private LocalDate ngayXuat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_khach_hang")
    private KhachHang khachHang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nhan_vien")
    private NhanVien nhanVien;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_thanh_toan")
    private PhuongThucThanhToan thanhToan;

    public String getDuongDan() {
        return duongDan;
    }

    public void setDuongDan(String duongDan) {
        this.duongDan = duongDan;
    }

    public String getHopDongDinhKem() {
        return hopDongDinhKem;
    }

    public void setHopDongDinhKem(String hopDongDinhKem) {
        this.hopDongDinhKem = hopDongDinhKem;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public PhuongThucThanhToan getThanhToan() {
        return thanhToan;
    }

    public void setThanhToan(PhuongThucThanhToan thanhToan) {
        this.thanhToan = thanhToan;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMaXuatHang() {
        return this.maXuatHang;
    }

    public void setMaXuatHang(String maXuatHang) {
        this.maXuatHang = maXuatHang;
    }

    public java.time.LocalDate getNgayXuat() {
        return this.ngayXuat;
    }

    public void setNgayXuat(java.time.LocalDate ngayNhap) {
        this.ngayXuat = ngayNhap;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }
}
