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
@Table(name = "cham_cong")
@EntityListeners(AuditingEntityListener.class)
public class ChamCong implements Serializable {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "so_gio_lam")
    private Double soGioLam;

    @Column(name = "ngay_lam")
    private LocalDate ngayLam;

    @Column(name = "mieu_ta")
    private String mieuTa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nhan_vien")
    private NhanVien nhanVien;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getSoGioLam() {
        return this.soGioLam;
    }

    public void setSoGioLam(Double soGioLam) {
        this.soGioLam = soGioLam;
    }

    public java.time.LocalDate getNgayLam() {
        return this.ngayLam;
    }

    public void setNgayLam(java.time.LocalDate ngayLam) {
        this.ngayLam = ngayLam;
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

    public void setNhanVien(NhanVien idNhanVien) {
        this.nhanVien = idNhanVien;
    }

}
