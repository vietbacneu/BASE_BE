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
@Table(name = "tro_cap")
@EntityListeners(AuditingEntityListener.class)
public class TroCap implements Serializable {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ten")
    private String ten;

    @Column(name = "muc_tro_cap")
    private Double mucTroCap;

    @Column(name = "mieu_ta")
    private String mieuTa;

    @Column(name = "ma_tro_cap")
    private String maTroCap;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTen() {
        return this.ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Double getMucTroCap() {
        return this.mucTroCap;
    }

    public void setMucTroCap(Double mucTroCap) {
        this.mucTroCap = mucTroCap;
    }

    public String getMieuTa() {
        return this.mieuTa;
    }

    public void setMieuTa(String mieuTa) {
        this.mieuTa = mieuTa;
    }

    public String getMaTroCap() {
        return this.maTroCap;
    }

    public void setMaTroCap(String maTroCap) {
        this.maTroCap = maTroCap;
    }
}
