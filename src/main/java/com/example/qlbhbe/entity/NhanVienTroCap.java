package com.example.qlbhbe.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "nhan_vien_tro_cap")
@EntityListeners(AuditingEntityListener.class)
public class NhanVienTroCap implements Serializable {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "tu_ngay")
    private LocalDate tuNgay;

    @Column(name = "den_ngay")
    private LocalDate denNgay;

    @Column(name = "mieu_ta")
    private String mieuTa;


    @Column(name = "muc_Tro_Cap")
    private Double mucTroCap;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nhan_vien")
    private NhanVien nhanVien;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tro_cap")
    private TroCap idTroCap;

    public Double getMucTroCap() {
        return mucTroCap;
    }

    public void setMucTroCap(Double mucTroCap) {
        this.mucTroCap = mucTroCap;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public java.time.LocalDate getTuNgay() {
        return this.tuNgay;
    }

    public void setTuNgay(java.time.LocalDate tuNgay) {
        this.tuNgay = tuNgay;
    }

    public java.time.LocalDate getDenNgay() {
        return this.denNgay;
    }

    public void setDenNgay(java.time.LocalDate denNgay) {
        this.denNgay = denNgay;
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

    public TroCap getIdTroCap() {
        return this.idTroCap;
    }

    public void setIdTroCap(TroCap idTroCap) {
        this.idTroCap = idTroCap;
    }
}
