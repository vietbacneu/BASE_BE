package com.example.qlbhbe.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "khen_thuong")
@EntityListeners(AuditingEntityListener.class)
public class KhenThuong implements Serializable {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String maKhenThuong;

    @Column(name = "ten")
    private String ten;

    @Column(name = "muc_thuong")
    private Double mucThuong;

    @Column(name = "mieu_ta")
    private String mieuTa;

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

    public Double getMucThuong() {
        return this.mucThuong;
    }

    public void setMucThuong(Double mucThuong) {
        this.mucThuong = mucThuong;
    }

    public String getMieuTa() {
        return this.mieuTa;
    }

    public void setMieuTa(String mieuta) {
        this.mieuTa = mieuta;
    }

    public String getMaKhenThuong() {
        return maKhenThuong;
    }

    public void setMaKhenThuong(String maKhenThuong) {
        this.maKhenThuong = maKhenThuong;
    }
}
