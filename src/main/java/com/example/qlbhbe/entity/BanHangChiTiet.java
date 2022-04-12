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
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "ban_hang_chi_tiet")
@EntityListeners(AuditingEntityListener.class)
public class BanHangChiTiet implements Serializable {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "so_luong")
    private Double soLuong;

    @Column(name = "gia")
    private Double gia;

    @Column(name = "mieu_ta")
    private String mieuTa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ban_hang")
    private BanHang idBanHang;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_thuc_don")
    private ThucDon idThucDon;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getSoLuong() {
        return this.soLuong;
    }

    public void setSoLuong(Double soLuong) {
        this.soLuong = soLuong;
    }

    public Double getGia() {
        return this.gia;
    }

    public void setGia(Double gia) {
        this.gia = gia;
    }

    public String getMieuTa() {
        return this.mieuTa;
    }

    public void setMieuTa(String mieuTa) {
        this.mieuTa = mieuTa;
    }

    public BanHang getIdBanHang() {
        return this.idBanHang;
    }

    public void setIdBanHang(BanHang idBanHang) {
        this.idBanHang = idBanHang;
    }

    public ThucDon getIdThucDon() {
        return this.idThucDon;
    }

    public void setIdThucDon(ThucDon idThucDon) {
        this.idThucDon = idThucDon;
    }
}
