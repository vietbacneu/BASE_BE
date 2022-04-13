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
@Table(name = "thuc_don")
@EntityListeners(AuditingEntityListener.class)
public class ThucDon implements Serializable {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ma_thuc_don")
    private String maThucDon;

    @Column(name = "ten_thuc_don")
    private String tenThucDon;

    @Column(name = "gia")
    private Double gia;

    @Column(name = "ngay_tao")
    private LocalDate ngayTao;

    @Column(name = "nguoi_tao")
    private String nguoiTao;

    @Column(name = "ngay_thay_doi")
    private LocalDate ngayThayDoi;

    @Column(name = "nguoi_thay_doi")
    private String nguoiThayDoi;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMaThucDon() {
        return this.maThucDon;
    }

    public void setMaThucDon(String maThucDon) {
        this.maThucDon = maThucDon;
    }

    public String getTenThucDon() {
        return this.tenThucDon;
    }

    public void setTenThucDon(String tenThucDon) {
        this.tenThucDon = tenThucDon;
    }

    public Double getGia() {
        return gia;
    }

    public void setGia(Double gia) {
        this.gia = gia;
    }

    public java.time.LocalDate getNgayTao() {
        return this.ngayTao;
    }

    public void setNgayTao(java.time.LocalDate ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getNguoiTao() {
        return this.nguoiTao;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public java.time.LocalDate getNgayThayDoi() {
        return this.ngayThayDoi;
    }

    public void setNgayThayDoi(java.time.LocalDate ngayThayDoi) {
        this.ngayThayDoi = ngayThayDoi;
    }

    public String getNguoiThayDoi() {
        return this.nguoiThayDoi;
    }

    public void setNguoiThayDoi(String nguoiThayDoi) {
        this.nguoiThayDoi = nguoiThayDoi;
    }
}