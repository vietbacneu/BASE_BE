package com.example.qlbhbe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;

/**
 * Generated at 2021/11/12 14:16:44
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NhanVienKhenThuongDTO {

    private Long id;

    private LocalDateTime ngay;

    private String mieuta;

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

    private Long khenThuongId;

    private String khenThuongTen;

    private Double khenThuongMucThuong;

    private String khenThuongMieuta;

    public NhanVienKhenThuongDTO() {
    }

    public NhanVienKhenThuongDTO(Long id, LocalDateTime ngay, String mieuta, Long idNhanVien, String  hoNhanVien, String tenNhanVien, String sdtNhanVien, String emailNhanVien, String gioiTinhNhanVien, String diaChiNhanVien, LocalDateTime ngaySinhNhanVien, LocalDateTime ngayBatDauNhanVien, LocalDateTime ngayKetThucNhanVien, Long khenThuongId, String khenThuongTen, Double khenThuongMucThuong, String khenThuongMieuta) {
        this.id = id;
        this.ngay = ngay;
        this.mieuta = mieuta;
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
        this.khenThuongId = khenThuongId;
        this.khenThuongTen = khenThuongTen;
        this.khenThuongMucThuong = khenThuongMucThuong;
        this.khenThuongMieuta = khenThuongMieuta;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getNgay() {
        return this.ngay;
    }

    public void setNgay(LocalDateTime ngay) {
        this.ngay = ngay;
    }

    public String getMieuta() {
        return this.mieuta;
    }

    public void setMieuta(String mieuta) {
        this.mieuta = mieuta;
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

    public Long getKhenThuongId() {
        return this.khenThuongId;
    }

    public void setKhenThuongId(Long khenThuongId) {
        this.khenThuongId = khenThuongId;
    }

    public String getKhenThuongTen() {
        return this.khenThuongTen;
    }

    public void setKhenThuongTen(String khenThuongTen) {
        this.khenThuongTen = khenThuongTen;
    }

    public Double getKhenThuongMucThuong() {
        return this.khenThuongMucThuong;
    }

    public void setKhenThuongMucThuong(Double khenThuongMucThuong) {
        this.khenThuongMucThuong = khenThuongMucThuong;
    }

    public String getKhenThuongMieuta() {
        return this.khenThuongMieuta;
    }

    public void setKhenThuongMieuta(String khenThuongMieuta) {
        this.khenThuongMieuta = khenThuongMieuta;
    }
}
