package com.example.qlbhbe.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.util.Date;

/**
 * Generated at 2022/03/28 15:06:58
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NhanVienTroCapDTO {

    private Long id;

    private String mieuTa;

    private LocalDate tuNgay;

    private LocalDate denNgay;

    private Long idNhanVien;

    private String hoNhanVien;

    private String tenNhanVien;

    private String idNhanVienSdt;

    private String idNhanVienEmail;

    private String idNhanVienGioiTinh;

    private String idNhanVienDiaChi;

    private Date idNhanVienNgaySinh;

    private Date idNhanVienNgayBatDau;

    private String idNhanVienTrinhDo;

    private Long troCap;

    private String tenTroCap;

    private Double mucTroCap;

    private String idTroCapMieuTa;

    private String idTroCapMaTroCap;

    private String tenChucVu;

    private String tenPhongBan;

    public NhanVienTroCapDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMieuTa() {
        return mieuTa;
    }

    public void setMieuTa(String mieuTa) {
        this.mieuTa = mieuTa;
    }
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    public LocalDate getTuNgay() {
        return tuNgay;
    }

    public void setTuNgay(LocalDate tuNgay) {
        this.tuNgay = tuNgay;
    }
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    public LocalDate getDenNgay() {
        return denNgay;
    }

    public void setDenNgay(LocalDate denNgay) {
        this.denNgay = denNgay;
    }

    public Long getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(Long idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public String getHoNhanVien() {
        return hoNhanVien;
    }

    public void setHoNhanVien(String hoNhanVien) {
        this.hoNhanVien = hoNhanVien;
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getIdNhanVienSdt() {
        return idNhanVienSdt;
    }

    public void setIdNhanVienSdt(String idNhanVienSdt) {
        this.idNhanVienSdt = idNhanVienSdt;
    }

    public String getIdNhanVienEmail() {
        return idNhanVienEmail;
    }

    public void setIdNhanVienEmail(String idNhanVienEmail) {
        this.idNhanVienEmail = idNhanVienEmail;
    }

    public String getIdNhanVienGioiTinh() {
        return idNhanVienGioiTinh;
    }

    public void setIdNhanVienGioiTinh(String idNhanVienGioiTinh) {
        this.idNhanVienGioiTinh = idNhanVienGioiTinh;
    }

    public String getIdNhanVienDiaChi() {
        return idNhanVienDiaChi;
    }

    public void setIdNhanVienDiaChi(String idNhanVienDiaChi) {
        this.idNhanVienDiaChi = idNhanVienDiaChi;
    }

    public Date getIdNhanVienNgaySinh() {
        return idNhanVienNgaySinh;
    }

    public void setIdNhanVienNgaySinh(Date idNhanVienNgaySinh) {
        this.idNhanVienNgaySinh = idNhanVienNgaySinh;
    }

    public Date getIdNhanVienNgayBatDau() {
        return idNhanVienNgayBatDau;
    }

    public void setIdNhanVienNgayBatDau(Date idNhanVienNgayBatDau) {
        this.idNhanVienNgayBatDau = idNhanVienNgayBatDau;
    }

    public String getIdNhanVienTrinhDo() {
        return idNhanVienTrinhDo;
    }

    public void setIdNhanVienTrinhDo(String idNhanVienTrinhDo) {
        this.idNhanVienTrinhDo = idNhanVienTrinhDo;
    }

    public Long getTroCap() {
        return troCap;
    }

    public void setTroCap(Long troCap) {
        this.troCap = troCap;
    }

    public String getTenTroCap() {
        return tenTroCap;
    }

    public void setTenTroCap(String tenTroCap) {
        this.tenTroCap = tenTroCap;
    }

    public Double getMucTroCap() {
        return mucTroCap;
    }

    public void setMucTroCap(Double mucTroCap) {
        this.mucTroCap = mucTroCap;
    }

    public String getIdTroCapMieuTa() {
        return idTroCapMieuTa;
    }

    public void setIdTroCapMieuTa(String idTroCapMieuTa) {
        this.idTroCapMieuTa = idTroCapMieuTa;
    }

    public String getIdTroCapMaTroCap() {
        return idTroCapMaTroCap;
    }

    public void setIdTroCapMaTroCap(String idTroCapMaTroCap) {
        this.idTroCapMaTroCap = idTroCapMaTroCap;
    }

    public String getTenChucVu() {
        return tenChucVu;
    }

    public void setTenChucVu(String tenChucVu) {
        this.tenChucVu = tenChucVu;
    }

    public String getTenPhongBan() {
        return tenPhongBan;
    }

    public void setTenPhongBan(String tenPhongBan) {
        this.tenPhongBan = tenPhongBan;
    }
}
