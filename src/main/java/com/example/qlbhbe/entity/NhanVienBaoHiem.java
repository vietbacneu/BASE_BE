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

    @Column(name = "muc_dong")
    private Double mucDong;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nhan_vien")
    private NhanVien nhanVien;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_bao_hiem")
    private BaoHiem baoHiem;

    public Double getMucDong() {
        return mucDong;
    }

    public void setMucDong(Double mucDong) {
        this.mucDong = mucDong;
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
