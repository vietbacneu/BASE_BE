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

    private Long nhanVienId;

    private String nhanVienHo;

    private String nhanVienTen;

    private String nhanVienSdt;

    private String nhanVienEmail;

    private String nhanVienGioiTinh;

    private String nhanVienDiaChi;

    private LocalDateTime nhanVienNgaySinh;

    private LocalDateTime nhanVienNgayBatDau;

    private LocalDateTime nhanVienNgayKetThuc;

    private Long baoHiemId;

    private String baoHiemMaSo;

    private String baoHiemTen;

    private Integer baoHiemMucDong;

    private String baoHiemThongTin;

    public NhanVienBaoHiemDTO() {
    }

    public NhanVienBaoHiemDTO(Long id, LocalDate ngayDong, String mieuTa, Long nhanVienId, String nhanVienHo, String nhanVienTen, String nhanVienSdt, String nhanVienEmail, String nhanVienGioiTinh, String nhanVienDiaChi, LocalDateTime nhanVienNgaySinh, LocalDateTime nhanVienNgayBatDau, LocalDateTime nhanVienNgayKetThuc, Long baoHiemId, String baoHiemMaSo, String baoHiemTen, Integer baoHiemMucDong, String baoHiemThongTin) {
        this.id = id;
        this.ngayDong = ngayDong;
        this.mieuTa = mieuTa;
        this.nhanVienId = nhanVienId;
        this.nhanVienHo = nhanVienHo;
        this.nhanVienTen = nhanVienTen;
        this.nhanVienSdt = nhanVienSdt;
        this.nhanVienEmail = nhanVienEmail;
        this.nhanVienGioiTinh = nhanVienGioiTinh;
        this.nhanVienDiaChi = nhanVienDiaChi;
        this.nhanVienNgaySinh = nhanVienNgaySinh;
        this.nhanVienNgayBatDau = nhanVienNgayBatDau;
        this.nhanVienNgayKetThuc = nhanVienNgayKetThuc;
        this.baoHiemId = baoHiemId;
        this.baoHiemMaSo = baoHiemMaSo;
        this.baoHiemTen = baoHiemTen;
        this.baoHiemMucDong = baoHiemMucDong;
        this.baoHiemThongTin = baoHiemThongTin;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getNgayDong() {
        return this.ngayDong;
    }

    public void setNgayDong(LocalDate ngayDong) {
        this.ngayDong = ngayDong;
    }

    public String getMieuTa() {
        return this.mieuTa;
    }

    public void setMieuTa(String mieuTa) {
        this.mieuTa = mieuTa;
    }

    public Long getNhanVienId() {
        return this.nhanVienId;
    }

    public void setNhanVienId(Long nhanVienId) {
        this.nhanVienId = nhanVienId;
    }

    public String getNhanVienHo() {
        return this.nhanVienHo;
    }

    public void setNhanVienHo(String nhanVienHo) {
        this.nhanVienHo = nhanVienHo;
    }

    public String getNhanVienTen() {
        return this.nhanVienTen;
    }

    public void setNhanVienTen(String nhanVienTen) {
        this.nhanVienTen = nhanVienTen;
    }

    public String getNhanVienSdt() {
        return this.nhanVienSdt;
    }

    public void setNhanVienSdt(String nhanVienSdt) {
        this.nhanVienSdt = nhanVienSdt;
    }

    public String getNhanVienEmail() {
        return this.nhanVienEmail;
    }

    public void setNhanVienEmail(String nhanVienEmail) {
        this.nhanVienEmail = nhanVienEmail;
    }

    public String getNhanVienGioiTinh() {
        return this.nhanVienGioiTinh;
    }

    public void setNhanVienGioiTinh(String nhanVienGioiTinh) {
        this.nhanVienGioiTinh = nhanVienGioiTinh;
    }

    public String getNhanVienDiaChi() {
        return this.nhanVienDiaChi;
    }

    public void setNhanVienDiaChi(String nhanVienDiaChi) {
        this.nhanVienDiaChi = nhanVienDiaChi;
    }

    public LocalDateTime getNhanVienNgaySinh() {
        return this.nhanVienNgaySinh;
    }

    public void setNhanVienNgaySinh(LocalDateTime nhanVienNgaySinh) {
        this.nhanVienNgaySinh = nhanVienNgaySinh;
    }

    public LocalDateTime getNhanVienNgayBatDau() {
        return this.nhanVienNgayBatDau;
    }

    public void setNhanVienNgayBatDau(LocalDateTime nhanVienNgayBatDau) {
        this.nhanVienNgayBatDau = nhanVienNgayBatDau;
    }

    public LocalDateTime getNhanVienNgayKetThuc() {
        return this.nhanVienNgayKetThuc;
    }

    public void setNhanVienNgayKetThuc(LocalDateTime nhanVienNgayKetThuc) {
        this.nhanVienNgayKetThuc = nhanVienNgayKetThuc;
    }

    public Long getBaoHiemId() {
        return this.baoHiemId;
    }

    public void setBaoHiemId(Long baoHiemId) {
        this.baoHiemId = baoHiemId;
    }

    public String getBaoHiemMaSo() {
        return this.baoHiemMaSo;
    }

    public void setBaoHiemMaSo(String baoHiemMaSo) {
        this.baoHiemMaSo = baoHiemMaSo;
    }

    public String getBaoHiemTen() {
        return this.baoHiemTen;
    }

    public void setBaoHiemTen(String baoHiemTen) {
        this.baoHiemTen = baoHiemTen;
    }

    public Integer getBaoHiemMucDong() {
        return this.baoHiemMucDong;
    }

    public void setBaoHiemMucDong(Integer baoHiemMucDong) {
        this.baoHiemMucDong = baoHiemMucDong;
    }

    public String getBaoHiemThongTin() {
        return this.baoHiemThongTin;
    }

    public void setBaoHiemThongTin(String baoHiemThongTin) {
        this.baoHiemThongTin = baoHiemThongTin;
    }
}
