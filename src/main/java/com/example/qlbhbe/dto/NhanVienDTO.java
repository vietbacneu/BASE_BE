package com.example.qlbhbe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;

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

    private LocalDateTime ngaySinh;

    private LocalDateTime ngayBatDau;

    private LocalDateTime ngayKetThuc;

    private String trinhDo;

    private String quocTich;

    private Long chucVuId;

    private String chucVuTenChucVu;

    private String chucVuMieuTa;

    private Double chucVuHeSoLuong;

    private Long phongBanId;

    private String phongBanTen;

    private String phongBanThongTin;

    private String phongBanMieuTa;

    private Long isCount;

    public NhanVienDTO() {
    }

    public NhanVienDTO(Long id, String ho, String ten, String sdt, String email, String gioiTinh, String diaChi, LocalDateTime ngaySinh, LocalDateTime ngayBatDau, LocalDateTime ngayKetThuc, String trinhDo, String quocTich, Long chucVuId, String chucVuTenChucVu, String chucVuMieuTa, Double chucVuHeSoLuong, Long phongBanId, String phongBanTen, String phongBanThongTin, String phongBanMieuTa) {
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
        this.chucVuTenChucVu = chucVuTenChucVu;
        this.chucVuMieuTa = chucVuMieuTa;
        this.chucVuHeSoLuong = chucVuHeSoLuong;
        this.phongBanId = phongBanId;
        this.phongBanTen = phongBanTen;
        this.phongBanThongTin = phongBanThongTin;
        this.phongBanMieuTa = phongBanMieuTa;
    }

    public Long getIsCount() {
        return isCount;
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

    public LocalDateTime getNgaySinh() {
        return this.ngaySinh;
    }

    public void setNgaySinh(LocalDateTime ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public LocalDateTime getNgayBatDau() {
        return this.ngayBatDau;
    }

    public void setNgayBatDau(LocalDateTime ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    public LocalDateTime getNgayKetThuc() {
        return this.ngayKetThuc;
    }

    public void setNgayKetThuc(LocalDateTime ngayKetThuc) {
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

    public String getChucVuTenChucVu() {
        return this.chucVuTenChucVu;
    }

    public void setChucVuTenChucVu(String chucVuTenChucVu) {
        this.chucVuTenChucVu = chucVuTenChucVu;
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

    public String getPhongBanTen() {
        return this.phongBanTen;
    }

    public void setPhongBanTen(String phongBanTen) {
        this.phongBanTen = phongBanTen;
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
