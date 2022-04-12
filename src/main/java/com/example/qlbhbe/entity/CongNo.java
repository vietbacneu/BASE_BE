package com.example.qlbhbe.entity;

import java.io.Serializable;
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
@Table(name = "cong_no")
@EntityListeners(AuditingEntityListener.class)
public class CongNo implements Serializable {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ma_cong_no")
    private String maCongNo;

    @Column(name = "loai_hop_dong")
    private String loaiHopDong;

    @Column(name = "id_hop_dong")
    private Integer idHopDong;

    @Column(name = "trang_thai_thanh_toan")
    private String trangThaiThanhToan;

    @Column(name = "so_tien")
    private Double soTien;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_nhan_vien")
    private NhanVien nhanVien;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMaCongNo() {
        return this.maCongNo;
    }

    public void setMaCongNo(String maCongNo) {
        this.maCongNo = maCongNo;
    }

    public String getLoaiHopDong() {
        return this.loaiHopDong;
    }

    public void setLoaiHopDong(String loadHopDong) {
        this.loaiHopDong = loadHopDong;
    }

    public Integer getIdHopDong() {
        return this.idHopDong;
    }

    public void setIdHopDong(Integer idHopDong) {
        this.idHopDong = idHopDong;
    }

    public String getTrangThaiThanhToan() {
        return this.trangThaiThanhToan;
    }

    public void setTrangThaiThanhToan(String trangThaiThanhToan) {
        this.trangThaiThanhToan = trangThaiThanhToan;
    }

    public Double getSoTien() {
        return this.soTien;
    }

    public void setSoTien(Double soTien) {
        this.soTien = soTien;
    }

    public NhanVien getNhanVien() {
        return this.nhanVien;
    }

    public void setNhanVien(NhanVien idNhanVien) {
        this.nhanVien = idNhanVien;
    }
}