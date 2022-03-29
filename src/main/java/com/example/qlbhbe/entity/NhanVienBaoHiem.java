package com.example.qlbhbe.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
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
@Table(name = "nhan_vien_bao_hiem")
@EntityListeners(AuditingEntityListener.class)
public class NhanVienBaoHiem implements Serializable {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ngay_dong")
    private LocalDate ngayDong;

    @Column(name = "mieu_ta")
    private String mieuTa;

    @Column(name = "tu_ngay")
    private Date tuNgay;

    @Column(name = "den_ngay")
    private Date denNgay;

    @Column(name = "he_so")
    private Double heSo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nhan_vien")
    private NhanVien nhanVien;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_bao_hiem")
    private BaoHiem baoHiem;

    public Double getHeSo() {
        return heSo;
    }

    public void setHeSo(Double soTien) {
        this.heSo = soTien;
    }

    public Date getTuNgay() {
        return tuNgay;
    }

    public void setTuNgay(Date tuNgay) {
        this.tuNgay = tuNgay;
    }

    public Date getDenNgay() {
        return denNgay;
    }

    public void setDenNgay(Date denNgay) {
        this.denNgay = denNgay;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public java.time.LocalDate getNgayDong() {
        return this.ngayDong;
    }

    public void setNgayDong(java.time.LocalDate ngayDong) {
        this.ngayDong = ngayDong;
    }

    public String getMieuTa() {
        return this.mieuTa;
    }

    public void setMieuTa(String mieuTa) {
        this.mieuTa = mieuTa;
    }

    public NhanVien getNhanVien() {
        return this.nhanVien;
    }

    public void setNhanVien(NhanVien idNhanvien) {
        this.nhanVien = idNhanvien;
    }

    public BaoHiem getBaoHiem() {
        return this.baoHiem;
    }

    public void setBaoHiem(BaoHiem idBaoHiem) {
        this.baoHiem = idBaoHiem;
    }
}
