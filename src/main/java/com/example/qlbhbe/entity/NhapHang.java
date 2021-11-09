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
@Table(name = "nhap_hang")
@EntityListeners(AuditingEntityListener.class)
public class NhapHang implements Serializable {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ma_nhap_hang")
    private String maNhapHang;

    @Column(name = "ngay_nhap")
    private LocalDate ngayNhap;

    @CreatedDate
    @Column(name = "ngay_tao")
    private LocalDate ngayTao;

    @CreatedBy
    @Column(name = "nguoi_tao")
    private String nguoiTao;

    @LastModifiedDate
    @Column(name = "ngay_thay_doi")
    private LocalDate ngayThayDoi;

    @LastModifiedBy
    @Column(name = "nguoi_thay_doi")
    private String nguoiThayDoi;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nha_cung_cap")
    private NhaCungCap nhaCungCap;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cua_hang")
    private CuaHang cuaHang;

    public CuaHang getCuaHang() {
        return cuaHang;
    }

    public void setCuaHang(CuaHang idCuaHang) {
        this.cuaHang = idCuaHang;
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

    public java.time.LocalDate getNgayNhap() {
        return this.ngayNhap;
    }

    public void setNgayNhap(java.time.LocalDate ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public java.time.LocalDate getNgayTao() {
        return this.ngayTao;
    }

    public void setNgayTao(java.time.LocalDate ngayTao) {
        this.ngayTao = ngayTao;
    }

    public java.time.LocalDate getNgayThayDoi() {
        return this.ngayThayDoi;
    }

    public void setNgayThayDoi(java.time.LocalDate ngayThayDoi) {
        this.ngayThayDoi = ngayThayDoi;
    }

    public NhaCungCap getNhaCungCap() {
        return this.nhaCungCap;
    }

    public void setNhaCungCap(NhaCungCap idNhaCungCap) {
        this.nhaCungCap = idNhaCungCap;
    }

    public String getNguoiTao() {
        return nguoiTao;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public String getNguoiThayDoi() {
        return nguoiThayDoi;
    }

    public void setNguoiThayDoi(String nguoiThayDoi) {
        this.nguoiThayDoi = nguoiThayDoi;
    }
}
