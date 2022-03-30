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
@Table(name = "nhan_vien_ky_luat")
@EntityListeners(AuditingEntityListener.class)
public class NhanVienKyLuat implements Serializable {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "mieu_ta")
    private String mieuTa;

    @Column(name = "ngay")
    private LocalDate ngay;

    @Column(name = "muc_phat")
    private Double mucPhat;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nhan_vien")
    private NhanVien nhanVien;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ky_luat")
    private KyLuat kyLuat;

    public Double getMucPhat() {
        return mucPhat;
    }

    public void setMucPhat(Double mucPhat) {
        this.mucPhat = mucPhat;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMieuTa() {
        return this.mieuTa;
    }

    public void setMieuTa(String mieuta) {
        this.mieuTa = mieuta;
    }

    public java.time.LocalDate getNgay() {
        return this.ngay;
    }

    public void setNgay(java.time.LocalDate ngay) {
        this.ngay = ngay;
    }

    public NhanVien getNhanVien() {
        return this.nhanVien;
    }

    public void setNhanVien(NhanVien idNhanVien) {
        this.nhanVien = idNhanVien;
    }

    public KyLuat getKyLuat() {
        return this.kyLuat;
    }

    public void setKyLuat(KyLuat idKyLuat) {
        this.kyLuat = idKyLuat;
    }
}
