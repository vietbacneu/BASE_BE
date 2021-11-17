package com.example.qlbhbe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Generated at 2021/11/12 14:16:26
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NhanVienBaoHiemDTO {

    private Long id;

    private LocalDate ngayDong;

    private String mieuTa;

    private Long idNhanVien;

    private String hoNhanVien;

    private String tenNhanVien;

    private String tenChucVu;

    private String tenPhongBan;

    private String sdtNhanVien;

    private String emailNhanVien;

    private String gioiTinhNhanVien;

    private String diaChiNhanVien;

    private LocalDateTime ngaySinhNhanVien;

    private LocalDateTime ngayBatDauNhanVien;

    private LocalDateTime ngayKetThucNhanVien;

    private Long idBaoHiem;

    private String maSoBaoHiem;

    private String tenBaoHiem;

    private Integer baoHiemMucDong;

    private String thongTinBaoHiem;

    private Double mucDong;

    private Long isCount;

    public NhanVienBaoHiemDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getNgayDong() {
        return ngayDong;
    }

    public void setNgayDong(LocalDate ngayDong) {
        this.ngayDong = ngayDong;
    }

    public String getMieuTa() {
        return mieuTa;
    }

    public void setMieuTa(String mieuTa) {
        this.mieuTa = mieuTa;
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

    public String getSdtNhanVien() {
        return sdtNhanVien;
    }

    public void setSdtNhanVien(String sdtNhanVien) {
        this.sdtNhanVien = sdtNhanVien;
    }

    public String getEmailNhanVien() {
        return emailNhanVien;
    }

    public void setEmailNhanVien(String emailNhanVien) {
        this.emailNhanVien = emailNhanVien;
    }

    public String getGioiTinhNhanVien() {
        return gioiTinhNhanVien;
    }

    public void setGioiTinhNhanVien(String gioiTinhNhanVien) {
        this.gioiTinhNhanVien = gioiTinhNhanVien;
    }

    public String getDiaChiNhanVien() {
        return diaChiNhanVien;
    }

    public void setDiaChiNhanVien(String diaChiNhanVien) {
        this.diaChiNhanVien = diaChiNhanVien;
    }

    public LocalDateTime getNgaySinhNhanVien() {
        return ngaySinhNhanVien;
    }

    public void setNgaySinhNhanVien(LocalDateTime ngaySinhNhanVien) {
        this.ngaySinhNhanVien = ngaySinhNhanVien;
    }

    public LocalDateTime getNgayBatDauNhanVien() {
        return ngayBatDauNhanVien;
    }

    public void setNgayBatDauNhanVien(LocalDateTime ngayBatDauNhanVien) {
        this.ngayBatDauNhanVien = ngayBatDauNhanVien;
    }

    public LocalDateTime getNgayKetThucNhanVien() {
        return ngayKetThucNhanVien;
    }

    public void setNgayKetThucNhanVien(LocalDateTime ngayKetThucNhanVien) {
        this.ngayKetThucNhanVien = ngayKetThucNhanVien;
    }

    public Long getIdBaoHiem() {
        return idBaoHiem;
    }

    public void setIdBaoHiem(Long idBaoHiem) {
        this.idBaoHiem = idBaoHiem;
    }

    public String getMaSoBaoHiem() {
        return maSoBaoHiem;
    }

    public void setMaSoBaoHiem(String maSoBaoHiem) {
        this.maSoBaoHiem = maSoBaoHiem;
    }

    public String getTenBaoHiem() {
        return tenBaoHiem;
    }

    public void setTenBaoHiem(String tenBaoHiem) {
        this.tenBaoHiem = tenBaoHiem;
    }

    public Integer getBaoHiemMucDong() {
        return baoHiemMucDong;
    }

    public void setBaoHiemMucDong(Integer baoHiemMucDong) {
        this.baoHiemMucDong = baoHiemMucDong;
    }

    public String getThongTinBaoHiem() {
        return thongTinBaoHiem;
    }

    public void setThongTinBaoHiem(String thongTinBaoHiem) {
        this.thongTinBaoHiem = thongTinBaoHiem;
    }

    public Double getMucDong() {
        return mucDong;
    }

    public void setMucDong(Double mucDong) {
        this.mucDong = mucDong;
    }

    public Long getIsCount() {
        return isCount;
    }

    public void setIsCount(Long isCount) {
        this.isCount = isCount;
    }
}
