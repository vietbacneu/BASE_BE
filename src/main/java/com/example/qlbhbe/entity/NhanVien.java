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
@Table(name = "nhan_vien")
@EntityListeners(AuditingEntityListener.class)
public class NhanVien implements Serializable {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ho")
    private String ho;

    @Column(name = "ten")
    private String ten;

    @Column(name = "sdt")
    private String sdt;

    @Column(name = "email")
    private String email;

    @Column(name = "gioi_tinh")
    private String gioiTinh;

    @Column(name = "dia_chi")
    private String diaChi;

    @Column(name = "ngay_sinh")
    private LocalDate ngaySinh;

    @Column(name = "ngay_bat_dau")
    private LocalDate ngayBatDau;

    @Column(name = "trinh_do")
    private String trinhDo;

    @Column(name = "quoc_tich")
    private String quocTich;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_chuc_vu")
    private ChucVu chucVu;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_phong_ban")
    private PhongBan phongBan;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getHo() {
        return this.ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getTen() {
        return this.ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSdt() {
        return this.sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGioiTinh() {
        return this.gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return this.diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public java.time.LocalDate getNgaySinh() {
        return this.ngaySinh;
    }

    public void setNgaySinh(java.time.LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public java.time.LocalDate getNgayBatDau() {
        return this.ngayBatDau;
    }

    public void setNgayBatDau(java.time.LocalDate ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }


    public String getTrinhDo() {
        return this.trinhDo;
    }

    public void setTrinhDo(String trinhDo) {
        this.trinhDo = trinhDo;
    }

    public String getQuocTich() {
        return this.quocTich;
    }

    public void setQuocTich(String quocTich) {
        this.quocTich = quocTich;
    }

    public ChucVu getChucVu() {
        return this.chucVu;
    }

    public void setChucVu(ChucVu idChucVu) {
        this.chucVu = idChucVu;
    }

    public PhongBan getPhongBan() {
        return this.phongBan;
    }

    public void setPhongBan(PhongBan idPhongBan) {
        this.phongBan = idPhongBan;
    }
}
