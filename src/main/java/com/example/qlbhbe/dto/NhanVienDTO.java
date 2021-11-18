package com.example.qlbhbe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;

/**
 * Generated at 2021/11/12 14:16:00
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NhanVienDTO {

    private Long id;

    private String ho;

    private String ten;

    private String sdt;

    private String email;

    private String gioiTinh;

    private String diaChi;

    private LocalDate ngaySinh;

    private LocalDate ngayBatDau;

    private LocalDate ngayKetThuc;

    private String trinhDo;

    private String quocTich;

    private Long chucVuId;

    private String tenChucVu;

    private String chucVuMieuTa;

    private Double chucVuHeSoLuong;

    private Long phongBanId;

    private String tenPhongBan;

    private String phongBanThongTin;

    private String phongBanMieuTa;

    private Double heSoLuong;

    private Long isCount;

    private String tenLoi;

    private String tenThuong;
    private Double mucPhat;
    private Double mucThuong;
    private String month;
    private String type;
    private String tenThuongPhat;
    private Double valueThuongPhat;
    public NhanVienDTO() {
    }

    public NhanVienDTO(Long id, String ho, String ten, String sdt, String email, String gioiTinh, String diaChi, LocalDate ngaySinh, LocalDate ngayBatDau, LocalDate ngayKetThuc, String trinhDo, String quocTich, Long chucVuId, String chucVuTenChucVu, String chucVuMieuTa, Double chucVuHeSoLuong, Long phongBanId, String phongBanTen, String phongBanThongTin, String phongBanMieuTa) {
        this.id = id;
        this.ho = ho;
        this.ten = ten;
        this.sdt = sdt;
        this.email = email;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.ngaySinh = ngaySinh;
        this.ngayBatDau = ngayBatDau;
        this.ngayKetThuc = ngayKetThuc;
        this.trinhDo = trinhDo;
        this.quocTich = quocTich;
        this.chucVuId = chucVuId;
        this.tenChucVu = chucVuTenChucVu;
        this.chucVuMieuTa = chucVuMieuTa;
        this.chucVuHeSoLuong = chucVuHeSoLuong;
        this.phongBanId = phongBanId;
        this.tenPhongBan = phongBanTen;
        this.phongBanThongTin = phongBanThongTin;
        this.phongBanMieuTa = phongBanMieuTa;
    }

    public String getTenThuongPhat() {
        return tenThuongPhat;
    }

    public void setTenThuongPhat(String tenThuongPhat) {
        this.tenThuongPhat = tenThuongPhat;
    }

    public Double getValueThuongPhat() {
        return valueThuongPhat;
    }

    public void setValueThuongPhat(Double valueThuongPhat) {
        this.valueThuongPhat = valueThuongPhat;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTenLoi() {
        return tenLoi;
    }

    public void setTenLoi(String tenLoi) {
        this.tenLoi = tenLoi;
    }

    public String getTenThuong() {
        return tenThuong;
    }

    public void setTenThuong(String tenThuong) {
        this.tenThuong = tenThuong;
    }

    public Double getMucPhat() {
        return mucPhat;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public void setMucPhat(Double mucPhat) {
        this.mucPhat = mucPhat;
    }

    public Double getMucThuong() {
        return mucThuong;
    }

    public void setMucThuong(Double mucThuong) {
        this.mucThuong = mucThuong;
    }

    public Long getIsCount() {
        return isCount;
    }

    public Double getHeSoLuong() {
        return heSoLuong;
    }

    public void setHeSoLuong(Double heSoLuong) {
        this.heSoLuong = heSoLuong;
    }

    public void setIsCount(Long isCount) {
        this.isCount = isCount;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHo() {
        return this.ho;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public String getTen() {
        return this.ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSdt() {
        return this.sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGioiTinh() {
        return this.gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return this.diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public LocalDate getNgaySinh() {
        return this.ngaySinh;
    }

    public void setNgaySinh(LocalDate ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public LocalDate getNgayBatDau() {
        return this.ngayBatDau;
    }

    public void setNgayBatDau(LocalDate ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public LocalDate getNgayKetThuc() {
        return this.ngayKetThuc;
    }

    public void setNgayKetThuc(LocalDate ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    public String getTrinhDo() {
        return this.trinhDo;
    }

    public void setTrinhDo(String trinhDo) {
        this.trinhDo = trinhDo;
    }

    public String getQuocTich() {
        return this.quocTich;
    }

    public void setQuocTich(String quocTich) {
        this.quocTich = quocTich;
    }

    public Long getChucVuId() {
        return this.chucVuId;
    }

    public void setChucVuId(Long chucVuId) {
        this.chucVuId = chucVuId;
    }

    public String getTenChucVu() {
        return this.tenChucVu;
    }

    public void setTenChucVu(String tenChucVu) {
        this.tenChucVu = tenChucVu;
    }

    public String getChucVuMieuTa() {
        return this.chucVuMieuTa;
    }

    public void setChucVuMieuTa(String chucVuMieuTa) {
        this.chucVuMieuTa = chucVuMieuTa;
    }

    public Double getChucVuHeSoLuong() {
        return this.chucVuHeSoLuong;
    }

    public void setChucVuHeSoLuong(Double chucVuHeSoLuong) {
        this.chucVuHeSoLuong = chucVuHeSoLuong;
    }

    public Long getPhongBanId() {
        return this.phongBanId;
    }

    public void setPhongBanId(Long phongBanId) {
        this.phongBanId = phongBanId;
    }

    public String getTenPhongBan() {
        return this.tenPhongBan;
    }

    public void setTenPhongBan(String tenPhongBan) {
        this.tenPhongBan = tenPhongBan;
    }

    public String getPhongBanThongTin() {
        return this.phongBanThongTin;
    }

    public void setPhongBanThongTin(String phongBanThongTin) {
        this.phongBanThongTin = phongBanThongTin;
    }

    public String getPhongBanMieuTa() {
        return this.phongBanMieuTa;
    }

    public void setPhongBanMieuTa(String phongBanMieuTa) {
        this.phongBanMieuTa = phongBanMieuTa;
    }
}
