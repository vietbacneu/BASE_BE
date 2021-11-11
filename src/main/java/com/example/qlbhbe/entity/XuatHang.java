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
@Table(name = "xuat_hang")
@EntityListeners(AuditingEntityListener.class)
public class XuatHang implements Serializable {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ma_xuat_hang")
    private String maXuatHang;

    @Column(name = "ngay_xuat")
    private LocalDate ngayXuat;

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
    @JoinColumn(name = "id_cua_hang")
    private CuaHang cuaHang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_khach_hang")
    private KhachHang khachHang;

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

    public CuaHang getCuaHang() {
        return cuaHang;
    }

    public void setCuaHang(CuaHang cuaHang) {
        this.cuaHang = cuaHang;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }
}
