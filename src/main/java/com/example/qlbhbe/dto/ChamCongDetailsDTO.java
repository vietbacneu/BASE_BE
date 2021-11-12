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

    private String hoNhanVien;

    private String idTenNhanVien;

    private String idSdtNhanVien;

    private String idEmailNhanVien;

    private String idGioiTinhNhanVien;

    private String idDiaChiNhanVien;

    private LocalDateTime idNgaySinhNhanVien;

    private LocalDateTime idNgayBatDauNhanVien;

    private LocalDateTime idNgayKetThucNhanVien;

    private Long idPhongBanId;

    private String idPhongBanTen;

    private String idPhongBanThongTin;

    private String idPhongBanMieuTa;

    public ChamCongDetailsDTO() {
    }

    public ChamCongDetailsDTO(Long id, Double soGioLam, LocalDateTime ngayLam, String mieuTa, Long idNhanVienId, String hoNhanVien, String idTenNhanVien, String idSdtNhanVien, String idEmailNhanVien, String idGioiTinhNhanVien, String idDiaChiNhanVien, LocalDateTime idNgaySinhNhanVien, LocalDateTime idNgayBatDauNhanVien, LocalDateTime idNgayKetThucNhanVien, Long idPhongBanId, String idPhongBanTen, String idPhongBanThongTin, String idPhongBanMieuTa) {
        this.id = id;
        this.soGioLam = soGioLam;
        this.ngayLam = ngayLam;
        this.mieuTa = mieuTa;
        this.idNhanVienId = idNhanVienId;
        this.hoNhanVien = hoNhanVien;
        this.idTenNhanVien = idTenNhanVien;
        this.idSdtNhanVien = idSdtNhanVien;
        this.idEmailNhanVien = idEmailNhanVien;
        this.idGioiTinhNhanVien = idGioiTinhNhanVien;
        this.idDiaChiNhanVien = idDiaChiNhanVien;
        this.idNgaySinhNhanVien = idNgaySinhNhanVien;
        this.idNgayBatDauNhanVien = idNgayBatDauNhanVien;
        this.idNgayKetThucNhanVien = idNgayKetThucNhanVien;
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

    public String getHoNhanVien() {
        return this.hoNhanVien;
    }

    public void setHoNhanVien(String hoNhanVien) {
        this.hoNhanVien = hoNhanVien;
    }

    public String getIdTenNhanVien() {
        return this.idTenNhanVien;
    }

    public void setIdTenNhanVien(String idTenNhanVien) {
        this.idTenNhanVien = idTenNhanVien;
    }

    public String getIdSdtNhanVien() {
        return this.idSdtNhanVien;
    }

    public void setIdSdtNhanVien(String idSdtNhanVien) {
        this.idSdtNhanVien = idSdtNhanVien;
    }

    public String getIdEmailNhanVien() {
        return this.idEmailNhanVien;
    }

    public void setIdEmailNhanVien(String idEmailNhanVien) {
        this.idEmailNhanVien = idEmailNhanVien;
    }

    public String getIdGioiTinhNhanVien() {
        return this.idGioiTinhNhanVien;
    }

    public void setIdGioiTinhNhanVien(String idGioiTinhNhanVien) {
        this.idGioiTinhNhanVien = idGioiTinhNhanVien;
    }

    public String getIdDiaChiNhanVien() {
        return this.idDiaChiNhanVien;
    }

    public void setIdDiaChiNhanVien(String idDiaChiNhanVien) {
        this.idDiaChiNhanVien = idDiaChiNhanVien;
    }

    public LocalDateTime getIdNgaySinhNhanVien() {
        return this.idNgaySinhNhanVien;
    }

    public void setIdNgaySinhNhanVien(LocalDateTime idNgaySinhNhanVien) {
        this.idNgaySinhNhanVien = idNgaySinhNhanVien;
    }

    public LocalDateTime getIdNgayBatDauNhanVien() {
        return this.idNgayBatDauNhanVien;
    }

    public void setIdNgayBatDauNhanVien(LocalDateTime idNgayBatDauNhanVien) {
        this.idNgayBatDauNhanVien = idNgayBatDauNhanVien;
    }

    public LocalDateTime getIdNgayKetThucNhanVien() {
        return this.idNgayKetThucNhanVien;
    }

    public void setIdNgayKetThucNhanVien(LocalDateTime idNgayKetThucNhanVien) {
        this.idNgayKetThucNhanVien = idNgayKetThucNhanVien;
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
