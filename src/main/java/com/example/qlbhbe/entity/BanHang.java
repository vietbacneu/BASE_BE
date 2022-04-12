package com.example.qlbhbe.entity;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

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

    @Column(name = "ngay_xuat")
    private LocalDate ngayXuat;

    @Column(name = "nguoi_tao")
    private String nguoiTao;

    @Column(name = "ngay_tao")
    private LocalDate ngayTao;

    @Column(name = "nguoi_thay_doi")
    private String nguoiThayDoi;

    @Column(name = "ngay_thay_doi")
    private LocalDate ngayThayDoi;

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

    public java.time.LocalDate getNgayXuat() {
        return this.ngayXuat;
    }

    public void setNgayXuat(java.time.LocalDate ngayXuat) {
        this.ngayXuat = ngayXuat;
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
