package com.example.qlbhbe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Generated at 2022/03/28 15:06:58
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NhanVienTroCapDTO {

    private Long id;

    private String mieuTa;

    private LocalDateTime tuNgay;

    private LocalDateTime denNgay;

    private Long idNhanVienId;

    private String idNhanVienHo;

    private String idNhanVienTen;

    private String idNhanVienSdt;

    private String idNhanVienEmail;

    private String idNhanVienGioiTinh;

    private String idNhanVienDiaChi;

    private LocalDate idNhanVienNgaySinh;

    private LocalDate idNhanVienNgayBatDau;

    private String idNhanVienTrinhDo;

    private Long idTroCapId;

    private String idTroCapTen;

    private Double idTroCapMucTroCap;

    private String idTroCapMieuTa;

    private String idTroCapMaTroCap;

    public NhanVienTroCapDTO() {
    }

    public NhanVienTroCapDTO(Long id, String mieuTa, LocalDateTime tuNgay, LocalDateTime denNgay, Long idNhanVienId, String idNhanVienHo, String idNhanVienTen, String idNhanVienSdt, String idNhanVienEmail, String idNhanVienGioiTinh, String idNhanVienDiaChi, LocalDate idNhanVienNgaySinh, LocalDate idNhanVienNgayBatDau, String idNhanVienTrinhDo, Long idTroCapId, String idTroCapTen, Double idTroCapMucTroCap, String idTroCapMieuTa, String idTroCapMaTroCap) {
        this.id = id;
        this.mieuTa = mieuTa;
        this.tuNgay = tuNgay;
        this.denNgay = denNgay;
        this.idNhanVienId = idNhanVienId;
        this.idNhanVienHo = idNhanVienHo;
        this.idNhanVienTen = idNhanVienTen;
        this.idNhanVienSdt = idNhanVienSdt;
        this.idNhanVienEmail = idNhanVienEmail;
        this.idNhanVienGioiTinh = idNhanVienGioiTinh;
        this.idNhanVienDiaChi = idNhanVienDiaChi;
        this.idNhanVienNgaySinh = idNhanVienNgaySinh;
        this.idNhanVienNgayBatDau = idNhanVienNgayBatDau;
        this.idNhanVienTrinhDo = idNhanVienTrinhDo;
        this.idTroCapId = idTroCapId;
        this.idTroCapTen = idTroCapTen;
        this.idTroCapMucTroCap = idTroCapMucTroCap;
        this.idTroCapMieuTa = idTroCapMieuTa;
        this.idTroCapMaTroCap = idTroCapMaTroCap;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMieuTa() {
        return this.mieuTa;
    }

    public void setMieuTa(String mieuTa) {
        this.mieuTa = mieuTa;
    }

    public LocalDateTime getTuNgay() {
        return this.tuNgay;
    }

    public void setTuNgay(LocalDateTime tuNgay) {
        this.tuNgay = tuNgay;
    }

    public LocalDateTime getDenNgay() {
        return this.denNgay;
    }

    public void setDenNgay(LocalDateTime denNgay) {
        this.denNgay = denNgay;
    }

    public Long getIdNhanVienId() {
        return this.idNhanVienId;
    }

    public void setIdNhanVienId(Long idNhanVienId) {
        this.idNhanVienId = idNhanVienId;
    }

    public String getIdNhanVienHo() {
        return this.idNhanVienHo;
    }

    public void setIdNhanVienHo(String idNhanVienHo) {
        this.idNhanVienHo = idNhanVienHo;
    }

    public String getIdNhanVienTen() {
        return this.idNhanVienTen;
    }

    public void setIdNhanVienTen(String idNhanVienTen) {
        this.idNhanVienTen = idNhanVienTen;
    }

    public String getIdNhanVienSdt() {
        return this.idNhanVienSdt;
    }

    public void setIdNhanVienSdt(String idNhanVienSdt) {
        this.idNhanVienSdt = idNhanVienSdt;
    }

    public String getIdNhanVienEmail() {
        return this.idNhanVienEmail;
    }

    public void setIdNhanVienEmail(String idNhanVienEmail) {
        this.idNhanVienEmail = idNhanVienEmail;
    }

    public String getIdNhanVienGioiTinh() {
        return this.idNhanVienGioiTinh;
    }

    public void setIdNhanVienGioiTinh(String idNhanVienGioiTinh) {
        this.idNhanVienGioiTinh = idNhanVienGioiTinh;
    }

    public String getIdNhanVienDiaChi() {
        return this.idNhanVienDiaChi;
    }

    public void setIdNhanVienDiaChi(String idNhanVienDiaChi) {
        this.idNhanVienDiaChi = idNhanVienDiaChi;
    }

    public LocalDate getIdNhanVienNgaySinh() {
        return this.idNhanVienNgaySinh;
    }

    public void setIdNhanVienNgaySinh(LocalDate idNhanVienNgaySinh) {
        this.idNhanVienNgaySinh = idNhanVienNgaySinh;
    }

    public LocalDate getIdNhanVienNgayBatDau() {
        return this.idNhanVienNgayBatDau;
    }

    public void setIdNhanVienNgayBatDau(LocalDate idNhanVienNgayBatDau) {
        this.idNhanVienNgayBatDau = idNhanVienNgayBatDau;
    }

    public String getIdNhanVienTrinhDo() {
        return this.idNhanVienTrinhDo;
    }

    public void setIdNhanVienTrinhDo(String idNhanVienTrinhDo) {
        this.idNhanVienTrinhDo = idNhanVienTrinhDo;
    }

    public Long getIdTroCapId() {
        return this.idTroCapId;
    }

    public void setIdTroCapId(Long idTroCapId) {
        this.idTroCapId = idTroCapId;
    }

    public String getIdTroCapTen() {
        return this.idTroCapTen;
    }

    public void setIdTroCapTen(String idTroCapTen) {
        this.idTroCapTen = idTroCapTen;
    }

    public Double getIdTroCapMucTroCap() {
        return this.idTroCapMucTroCap;
    }

    public void setIdTroCapMucTroCap(Double idTroCapMucTroCap) {
        this.idTroCapMucTroCap = idTroCapMucTroCap;
    }

    public String getIdTroCapMieuTa() {
        return this.idTroCapMieuTa;
    }

    public void setIdTroCapMieuTa(String idTroCapMieuTa) {
        this.idTroCapMieuTa = idTroCapMieuTa;
    }

    public String getIdTroCapMaTroCap() {
        return this.idTroCapMaTroCap;
    }

    public void setIdTroCapMaTroCap(String idTroCapMaTroCap) {
        this.idTroCapMaTroCap = idTroCapMaTroCap;
    }
}
