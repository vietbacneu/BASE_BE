package com.example.qlbhbe.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "cong_no_chi_tiet")
@EntityListeners(AuditingEntityListener.class)
public class CongNoChiTiet implements Serializable {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "so_tien_thanh_toan")
    private Double soTienThanhToan;

    @Column(name = "ngay_thanh_toan")
    private LocalDate ngayThanhToan;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cong_no")
    private CongNo idCongNo;

    public long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getSoTienThanhToan() {
        return soTienThanhToan;
    }

    public void setSoTienThanhToan(Double soTienThanhToan) {
        this.soTienThanhToan = soTienThanhToan;
    }

    public LocalDate getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(LocalDate ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public CongNo getIdCongNo() {
        return idCongNo;
    }

    public void setIdCongNo(CongNo idCongNo) {
        this.idCongNo = idCongNo;
    }
}
