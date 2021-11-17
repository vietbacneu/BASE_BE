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
import java.time.LocalDateTime;

@Entity
@Table(name = "ky_luat")
@EntityListeners(AuditingEntityListener.class)
public class KyLuat implements Serializable {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String maKyLuat;

    @Column(name = "ten_loi")
    private String tenLoi;

    @Column(name = "muc_phat")
    private Double mucPhat;

    @Column(name = "mieu_ta")
    private String mieuTa;

    public String getMaKyLuat() {
        return maKyLuat;
    }

    public void setMaKyLuat(String maKyLuat) {
        this.maKyLuat = maKyLuat;
    }

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTenLoi() {
        return this.tenLoi;
    }

    public void setTenLoi(String tenLoi) {
        this.tenLoi = tenLoi;
    }

    public Double getMucPhat() {
        return this.mucPhat;
    }

    public void setMucPhat(Double mucPhat) {
        this.mucPhat = mucPhat;
    }

    public String getMieuTa() {
        return this.mieuTa;
    }

    public void setMieuTa(String mieuTa) {
        this.mieuTa = mieuTa;
    }
}
