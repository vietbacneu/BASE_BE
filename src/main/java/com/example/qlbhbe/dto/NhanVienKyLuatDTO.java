package com.example.qlbhbe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;

/**
 * Generated at 2021/11/12 14:16:57
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NhanVienKyLuatDTO {

    private Long id;

    private String mieuta;

    private LocalDateTime ngay;

    private Long idNhanVien;

    private String  hoNhanVien;

    private String tenNhanVien;

    private String sdtNhanVien;

    private String emailNhanVien;

    private String gioiTinhNhanVien;

    private String diaChiNhanVien;

    private LocalDateTime ngaySinhNhanVien;

    private LocalDateTime ngayBatDauNhanVien;

    private LocalDateTime ngayKetThucNhanVien;

    private Long kyLuatId;

    private String kyLuatTenLoi;

    private Double kyLuatMucPhat;

    private String kyLuatMieuTa;

    public NhanVienKyLuatDTO() {
    }

    public NhanVienKyLuatDTO(Long id, String mieuta, LocalDateTime ngay, Long idNhanVien, String  hoNhanVien, String tenNhanVien, String sdtNhanVien, String emailNhanVien, String gioiTinhNhanVien, String diaChiNhanVien, LocalDateTime ngaySinhNhanVien, LocalDateTime ngayBatDauNhanVien, LocalDateTime ngayKetThucNhanVien, Long kyLuatId, String kyLuatTenLoi, Double kyLuatMucPhat, String kyLuatMieuTa) {
        this.id = id;
        this.mieuta = mieuta;
        this.ngay = ngay;
        this.idNhanVien = idNhanVien;
        this.hoNhanVien =  hoNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.sdtNhanVien = sdtNhanVien;
        this.emailNhanVien = emailNhanVien;
        this.gioiTinhNhanVien = gioiTinhNhanVien;
        this.diaChiNhanVien = diaChiNhanVien;
        this.ngaySinhNhanVien = ngaySinhNhanVien;
        this.ngayBatDauNhanVien = ngayBatDauNhanVien;
        this.ngayKetThucNhanVien = ngayKetThucNhanVien;
        this.kyLuatId = kyLuatId;
        this.kyLuatTenLoi = kyLuatTenLoi;
        this.kyLuatMucPhat = kyLuatMucPhat;
        this.kyLuatMieuTa = kyLuatMieuTa;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMieuta() {
        return this.mieuta;
    }

    public void setMieuta(String mieuta) {
        this.mieuta = mieuta;
    }

    public LocalDateTime getNgay() {
        return this.ngay;
    }

    public void setNgay(LocalDateTime ngay) {
        this.ngay = ngay;
    }

    public Long getNhanVienId() {
        return this.idNhanVien;
    }

    public void setNhanVienId(Long idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public String getHoNhanVien() {
        return this.hoNhanVien;
    }

    public void setHoNhanVien(String  hoNhanVien) {
        this.hoNhanVien =  hoNhanVien;
    }

    public String getTenNhanVien() {
        return this.tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public String getSdtNhanVien() {
        return this.sdtNhanVien;
    }

    public void setSdtNhanVien(String sdtNhanVien) {
        this.sdtNhanVien = sdtNhanVien;
    }

    public String getEmailNhanVien() {
        return this.emailNhanVien;
    }

    public void setEmailNhanVien(String emailNhanVien) {
        this.emailNhanVien = emailNhanVien;
    }

    public String getGioiTinhNhanVien() {
        return this.gioiTinhNhanVien;
    }

    public void setGioiTinhNhanVien(String gioiTinhNhanVien) {
        this.gioiTinhNhanVien = gioiTinhNhanVien;
    }

    public String getDiaChiNhanVien() {
        return this.diaChiNhanVien;
    }

    public void setDiaChiNhanVien(String diaChiNhanVien) {
        this.diaChiNhanVien = diaChiNhanVien;
    }

    public LocalDateTime getNgaySinhNhanVien() {
        return this.ngaySinhNhanVien;
    }

    public void setNgaySinhNhanVien(LocalDateTime ngaySinhNhanVien) {
        this.ngaySinhNhanVien = ngaySinhNhanVien;
    }

    public LocalDateTime getNgayBatDauNhanVien() {
        return this.ngayBatDauNhanVien;
    }

    public void setNgayBatDauNhanVien(LocalDateTime ngayBatDauNhanVien) {
        this.ngayBatDauNhanVien = ngayBatDauNhanVien;
    }

    public LocalDateTime getNgayKetThucNhanVien() {
        return this.ngayKetThucNhanVien;
    }

    public void setNgayKetThucNhanVien(LocalDateTime ngayKetThucNhanVien) {
        this.ngayKetThucNhanVien = ngayKetThucNhanVien;
    }

    public Long getKyLuatId() {
        return this.kyLuatId;
    }

    public void setKyLuatId(Long kyLuatId) {
        this.kyLuatId = kyLuatId;
    }

    public String getKyLuatTenLoi() {
        return this.kyLuatTenLoi;
    }

    public void setKyLuatTenLoi(String kyLuatTenLoi) {
        this.kyLuatTenLoi = kyLuatTenLoi;
    }

    public Double getKyLuatMucPhat() {
        return this.kyLuatMucPhat;
    }

    public void setKyLuatMucPhat(Double kyLuatMucPhat) {
        this.kyLuatMucPhat = kyLuatMucPhat;
    }

    public String getKyLuatMieuTa() {
        return this.kyLuatMieuTa;
    }

    public void setKyLuatMieuTa(String kyLuatMieuTa) {
        this.kyLuatMieuTa = kyLuatMieuTa;
    }
}
