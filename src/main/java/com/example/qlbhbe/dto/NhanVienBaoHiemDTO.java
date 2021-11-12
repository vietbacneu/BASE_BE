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

    public NhanVienBaoHiemDTO(Long id, LocalDate ngayDong, String mieuTa, Long idNhanVien, String hoNhanVien, String tenNhanVien, String sdtNhanVien, String emailNhanVien, String gioiTinhNhanVien, String diaChiNhanVien, LocalDateTime ngaySinhNhanVien, LocalDateTime ngayBatDauNhanVien, LocalDateTime ngayKetThucNhanVien, Long idBaoHiem, String maSoBaoHiem, String tenBaoHiem, Integer baoHiemMucDong, String thongTinBaoHiem) {
        this.id = id;
        this.ngayDong = ngayDong;
        this.mieuTa = mieuTa;
        this.idNhanVien = idNhanVien;
        this.hoNhanVien = hoNhanVien;
        this.tenNhanVien = tenNhanVien;
        this.sdtNhanVien = sdtNhanVien;
        this.emailNhanVien = emailNhanVien;
        this.gioiTinhNhanVien = gioiTinhNhanVien;
        this.diaChiNhanVien = diaChiNhanVien;
        this.ngaySinhNhanVien = ngaySinhNhanVien;
        this.ngayBatDauNhanVien = ngayBatDauNhanVien;
        this.ngayKetThucNhanVien = ngayKetThucNhanVien;
        this.idBaoHiem = idBaoHiem;
        this.maSoBaoHiem = maSoBaoHiem;
        this.tenBaoHiem = tenBaoHiem;
        this.baoHiemMucDong = baoHiemMucDong;
        this.thongTinBaoHiem = thongTinBaoHiem;
    }

    public Long getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(Long idNhanVien) {
        this.idNhanVien = idNhanVien;
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

    public Long getIsCount() {
        return isCount;
    }

    public void setIsCount(Long isCount) {
        this.isCount = isCount;
    }

    public Long getId() {
        return this.id;
    }

    public Double getMucDong() {
        return mucDong;
    }

    public void setMucDong(Double mucDong) {
        this.mucDong = mucDong;
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
        return this.idNhanVien;
    }

    public void setNhanVienId(Long idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public String getHoNhanVien() {
        return this.hoNhanVien;
    }

    public void setHoNhanVien(String hoNhanVien) {
        this.hoNhanVien = hoNhanVien;
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

    public Long getidBaoHiem() {
        return this.idBaoHiem;
    }

    public void setidBaoHiem(Long idBaoHiem) {
        this.idBaoHiem = idBaoHiem;
    }

    public String getBaoHiemMaSo() {
        return this.maSoBaoHiem;
    }

    public void setBaoHiemMaSo(String maSoBaoHiem) {
        this.maSoBaoHiem = maSoBaoHiem;
    }

    public String gettenBaoHiem() {
        return this.tenBaoHiem;
    }

    public void settenBaoHiem(String tenBaoHiem) {
        this.tenBaoHiem = tenBaoHiem;
    }

    public Integer getBaoHiemMucDong() {
        return this.baoHiemMucDong;
    }

    public void setBaoHiemMucDong(Integer baoHiemMucDong) {
        this.baoHiemMucDong = baoHiemMucDong;
    }

    public String getThongTinBaoHiem() {
        return this.thongTinBaoHiem;
    }

    public void setThongTinBaoHiem(String thongTinBaoHiem) {
        this.thongTinBaoHiem = thongTinBaoHiem;
    }
}
