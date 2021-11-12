package com.example.qlbhbe.entity;

import java.io.Serializable;
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

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "nhan_vien_khen_thuong")
@EntityListeners(AuditingEntityListener.class)
public class NhanVienKhenThuong implements Serializable {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ngay")
    private LocalDateTime ngay;

    @Column(name = "mieuta")
    private String mieuta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nhan_vien")
    private NhanVien nhanVien;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_khen_thuong")
    private KhenThuong khenThuong;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public java.time.LocalDateTime getNgay() {
        return this.ngay;
    }

    public void setNgay(java.time.LocalDateTime ngay) {
        this.ngay = ngay;
    }

    public String getMieuta() {
        return this.mieuta;
    }

    public void setMieuta(String mieuta) {
        this.mieuta = mieuta;
    }

    public NhanVien getNhanVien() {
        return this.nhanVien;
    }

    public void setNhanVien(NhanVien idNhanVien) {
        this.nhanVien = idNhanVien;
    }

    public KhenThuong getKhenThuong() {
        return this.khenThuong;
    }

    public void setKhenThuong(KhenThuong idKhenThuong) {
        this.khenThuong = idKhenThuong;
    }
}
