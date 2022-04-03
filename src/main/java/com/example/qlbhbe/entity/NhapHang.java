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
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "hop_dong_nhap_hang")
@EntityListeners(AuditingEntityListener.class)
public class NhapHang implements Serializable {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ma_nhap_hang")
    private String maNhapHang;

    @Column(name = "hop_dong_dinh_kem")
    private String hopDongDinhKem;

    @Column(name = "duong_dan")
    private String duongDan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nha_cung_cap")
    private NhaCungCap nhaCungCap;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_thanh_toan")
    private PhuongThucThanhToan thanhToan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nhan_vien")
    private NhanVien nhanVien;

    @Column(name = "ngay_nhap")
    private LocalDate ngayNhap;

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public LocalDate getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(LocalDate ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

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

    public String getMaNhapHang() {
        return this.maNhapHang;
    }

    public void setMaNhapHang(String maNhapHang) {
        this.maNhapHang = maNhapHang;
    }


    public NhaCungCap getNhaCungCap() {
        return this.nhaCungCap;
    }

    public void setNhaCungCap(NhaCungCap idNhaCungCap) {
        this.nhaCungCap = idNhaCungCap;
    }

}
