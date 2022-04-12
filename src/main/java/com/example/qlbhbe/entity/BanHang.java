package com.example.qlbhbe.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "ban_hang")
@EntityListeners(AuditingEntityListener.class)
public class BanHang implements Serializable {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ma_ban_hang")
    private String maBanHang;

    @Column(name = "ngay_ban")
    private LocalDate ngayBan;

    @Column(name = "nguoi_tao")
    private String nguoiTao = "admin";

    @Column(name = "ngay_tao")
    private LocalDate ngayTao = LocalDate.now();

    @Column(name = "nguoi_thay_doi")
    private String nguoiThayDoi = "admin";

    @Column(name = "ngay_thay_doi")
    private LocalDate ngayThayDoi = LocalDate.now();

    @Column(name = "trang_thai")
    private String trangThai;

    private String ghiChu;

    public String getTrangThai() {
        return trangThai;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_khach_hang")
    private KhachHang khachHang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_thanh_toan")
    private PhuongThucThanhToan thanhToan;

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public PhuongThucThanhToan getThanhToan() {
        return thanhToan;
    }

    public void setThanhToan(PhuongThucThanhToan thanhToan) {
        this.thanhToan = thanhToan;
    }

    public KhachHang getKhachHang() {
        return khachHang;
    }

    public void setKhachHang(KhachHang khachHang) {
        this.khachHang = khachHang;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMaBanHang() {
        return this.maBanHang;
    }

    public void setMaBanHang(String maBanHang) {
        this.maBanHang = maBanHang;
    }

    public java.time.LocalDate getNgayBan() {
        return this.ngayBan;
    }

    public void setNgayBan(java.time.LocalDate ngayXuat) {
        this.ngayBan = ngayXuat;
    }

    public String getNguoiTao() {
        return this.nguoiTao;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public java.time.LocalDate getNgayTao() {
        return this.ngayTao;
    }

    public void setNgayTao(java.time.LocalDate ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getNguoiThayDoi() {
        return this.nguoiThayDoi;
    }

    public void setNguoiThayDoi(String nguoiThayDoi) {
        this.nguoiThayDoi = nguoiThayDoi;
    }

    public java.time.LocalDate getNgayThayDoi() {
        return this.ngayThayDoi;
    }

    public void setNgayThayDoi(java.time.LocalDate ngayThayDoi) {
        this.ngayThayDoi = ngayThayDoi;
    }
}
