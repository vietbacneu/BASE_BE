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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nhan_vien")
    private NhanVien idNhanVien;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tro_cap")
    private TroCap idTroCap;

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

    public NhanVien getIdNhanVien() {
        return this.idNhanVien;
    }

    public void setIdNhanVien(NhanVien idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public TroCap getIdTroCap() {
        return this.idTroCap;
    }

    public void setIdTroCap(TroCap idTroCap) {
        this.idTroCap = idTroCap;
    }
}
