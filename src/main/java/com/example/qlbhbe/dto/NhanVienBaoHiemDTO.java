package com.example.qlbhbe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.time.LocalDate;

/**
 * Generated at 2021/11/12 14:16:26
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NhanVienBaoHiemDTO {

    private Long id;

    private LocalDate ngayDong;

    private LocalDate tuNgay;

    private LocalDate denNgay;

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

    private LocalDate ngaySinhNhanVien;

    private LocalDate ngayBatDauNhanVien;

    private LocalDate ngayKetThucNhanVien;

    private Long idBaoHiem;

    private String maSoBaoHiem;

    private String tenBaoHiem;

    private Integer baoHiemMucDong;

    private String thongTinBaoHiem;

    private Double mucDong;

    private Double heSo;

    private Long isCount;

    private String month;

    public NhanVienBaoHiemDTO() {
    }

    public Double getHeSo() {
        return heSo;
    }

    public LocalDate getTuNgay() {
        return tuNgay;
    }

    public void setTuNgay(LocalDate tuNgay) {
        this.tuNgay = tuNgay;
    }

    public LocalDate getDenNgay() {
        return denNgay;
    }

    public void setDenNgay(LocalDate denNgay) {
        this.denNgay = denNgay;
    }

    public void setHeSo(Double heSo) {
        this.heSo = heSo;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
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

    public LocalDate getNgaySinhNhanVien() {
        return ngaySinhNhanVien;
    }

    public void setNgaySinhNhanVien(LocalDate ngaySinhNhanVien) {
        this.ngaySinhNhanVien = ngaySinhNhanVien;
    }

    public LocalDate getNgayBatDauNhanVien() {
        return ngayBatDauNhanVien;
    }

    public void setNgayBatDauNhanVien(LocalDate ngayBatDauNhanVien) {
        this.ngayBatDauNhanVien = ngayBatDauNhanVien;
    }

    public LocalDate getNgayKetThucNhanVien() {
        return ngayKetThucNhanVien;
    }

    public void setNgayKetThucNhanVien(LocalDate ngayKetThucNhanVien) {
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
