package com.example.qlbhbe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;

/**
 * Generated at 2021/11/12 14:15:03
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChamCongDetailsDTO {

    private Long id;

    private Double soGioLam;

    private LocalDateTime ngayLam;

    private String mieuTa;

    private Long idNhanVienId;

    private String idNhanVienHo;

    private String idNhanVienTen;

    private String idNhanVienSdt;

    private String idNhanVienEmail;

    private String idNhanVienGioiTinh;

    private String idNhanVienDiaChi;

    private LocalDateTime idNhanVienNgaySinh;

    private LocalDateTime idNhanVienNgayBatDau;

    private LocalDateTime idNhanVienNgayKetThuc;

    private Long idPhongBanId;

    private String idPhongBanTen;

    private String idPhongBanThongTin;

    private String idPhongBanMieuTa;

    public ChamCongDetailsDTO() {
    }

    public ChamCongDetailsDTO(Long id, Double soGioLam, LocalDateTime ngayLam, String mieuTa, Long idNhanVienId, String idNhanVienHo, String idNhanVienTen, String idNhanVienSdt, String idNhanVienEmail, String idNhanVienGioiTinh, String idNhanVienDiaChi, LocalDateTime idNhanVienNgaySinh, LocalDateTime idNhanVienNgayBatDau, LocalDateTime idNhanVienNgayKetThuc, Long idPhongBanId, String idPhongBanTen, String idPhongBanThongTin, String idPhongBanMieuTa) {
        this.id = id;
        this.soGioLam = soGioLam;
        this.ngayLam = ngayLam;
        this.mieuTa = mieuTa;
        this.idNhanVienId = idNhanVienId;
        this.idNhanVienHo = idNhanVienHo;
        this.idNhanVienTen = idNhanVienTen;
        this.idNhanVienSdt = idNhanVienSdt;
        this.idNhanVienEmail = idNhanVienEmail;
        this.idNhanVienGioiTinh = idNhanVienGioiTinh;
        this.idNhanVienDiaChi = idNhanVienDiaChi;
        this.idNhanVienNgaySinh = idNhanVienNgaySinh;
        this.idNhanVienNgayBatDau = idNhanVienNgayBatDau;
        this.idNhanVienNgayKetThuc = idNhanVienNgayKetThuc;
        this.idPhongBanId = idPhongBanId;
        this.idPhongBanTen = idPhongBanTen;
        this.idPhongBanThongTin = idPhongBanThongTin;
        this.idPhongBanMieuTa = idPhongBanMieuTa;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getSoGioLam() {
        return this.soGioLam;
    }

    public void setSoGioLam(Double soGioLam) {
        this.soGioLam = soGioLam;
    }

    public LocalDateTime getNgayLam() {
        return this.ngayLam;
    }

    public void setNgayLam(LocalDateTime ngayLam) {
        this.ngayLam = ngayLam;
    }

    public String getMieuTa() {
        return this.mieuTa;
    }

    public void setMieuTa(String mieuTa) {
        this.mieuTa = mieuTa;
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

    public LocalDateTime getIdNhanVienNgaySinh() {
        return this.idNhanVienNgaySinh;
    }

    public void setIdNhanVienNgaySinh(LocalDateTime idNhanVienNgaySinh) {
        this.idNhanVienNgaySinh = idNhanVienNgaySinh;
    }

    public LocalDateTime getIdNhanVienNgayBatDau() {
        return this.idNhanVienNgayBatDau;
    }

    public void setIdNhanVienNgayBatDau(LocalDateTime idNhanVienNgayBatDau) {
        this.idNhanVienNgayBatDau = idNhanVienNgayBatDau;
    }

    public LocalDateTime getIdNhanVienNgayKetThuc() {
        return this.idNhanVienNgayKetThuc;
    }

    public void setIdNhanVienNgayKetThuc(LocalDateTime idNhanVienNgayKetThuc) {
        this.idNhanVienNgayKetThuc = idNhanVienNgayKetThuc;
    }

    public Long getIdPhongBanId() {
        return this.idPhongBanId;
    }

    public void setIdPhongBanId(Long idPhongBanId) {
        this.idPhongBanId = idPhongBanId;
    }

    public String getIdPhongBanTen() {
        return this.idPhongBanTen;
    }

    public void setIdPhongBanTen(String idPhongBanTen) {
        this.idPhongBanTen = idPhongBanTen;
    }

    public String getIdPhongBanThongTin() {
        return this.idPhongBanThongTin;
    }

    public void setIdPhongBanThongTin(String idPhongBanThongTin) {
        this.idPhongBanThongTin = idPhongBanThongTin;
    }

    public String getIdPhongBanMieuTa() {
        return this.idPhongBanMieuTa;
    }

    public void setIdPhongBanMieuTa(String idPhongBanMieuTa) {
        this.idPhongBanMieuTa = idPhongBanMieuTa;
    }
}
