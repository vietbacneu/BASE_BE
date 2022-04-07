package com.example.qlbhbe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * Generated at 2022/04/03 21:28:32
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CongNoDTO {

    private Long id;

    private String maCongNo;

    private String loaiHopDong;

    private Long idHopDong;

    private Long idNhanVien;

    private String trangThaiThanhToan;

    private Double soTien;

    private Long isCount;

    private String trangThaiName;

    private String loaiHopDongName;

    private String tenNhanVien;

    private String maHopDong;

    private List<CongNoChiTietDTO> congNoChiTietDTOS;

    private Double soTienConLai;

    private Double soTienDaThanhToan;

    public CongNoDTO() {
    }

    public String getTenNhanVien() {
        return tenNhanVien;
    }

    public void setTenNhanVien(String tenNhanVien) {
        this.tenNhanVien = tenNhanVien;
    }

    public Double getSoTienConLai() {
        return soTienConLai;
    }

    public void setSoTienConLai(Double soTienConLai) {
        this.soTienConLai = soTienConLai;
    }

    public Double getSoTienDaThanhToan() {
        return soTienDaThanhToan;
    }

    public void setSoTienDaThanhToan(Double soTienDaThanhToan) {
        this.soTienDaThanhToan = soTienDaThanhToan;
    }

    public String getTrangThaiName() {
        return trangThaiName;
    }

    public void setTrangThaiName(String trangThaiName) {
        this.trangThaiName = trangThaiName;
    }

    public String getLoaiHopDongName() {
        return loaiHopDongName;
    }

    public void setLoaiHopDongName(String loaiHopDongName) {
        this.loaiHopDongName = loaiHopDongName;
    }

    public String getMaHopDong() {
        return maHopDong;
    }

    public void setMaHopDong(String maHopDong) {
        this.maHopDong = maHopDong;
    }

    public List<CongNoChiTietDTO> getCongNoChiTietDTOS() {
        return congNoChiTietDTOS;
    }

    public void setCongNoChiTietDTOS(List<CongNoChiTietDTO> congNoChiTietDTOS) {
        this.congNoChiTietDTOS = congNoChiTietDTOS;
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

    public String getMaCongNo() {
        return this.maCongNo;
    }

    public void setMaCongNo(String maCongNo) {
        this.maCongNo = maCongNo;
    }

    public String getLoaiHopDong() {
        return this.loaiHopDong;
    }

    public void setLoaiHopDong(String loaiHopDong) {
        this.loaiHopDong = loaiHopDong;
    }

    public Long getIdHopDong() {
        return idHopDong;
    }

    public void setIdHopDong(Long idHopDong) {
        this.idHopDong = idHopDong;
    }

    public Long getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(Long idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public String getTrangThaiThanhToan() {
        return this.trangThaiThanhToan;
    }

    public void setTrangThaiThanhToan(String trangThaiThanhToan) {
        this.trangThaiThanhToan = trangThaiThanhToan;
    }

    public Double getSoTien() {
        return this.soTien;
    }

    public void setSoTien(Double soTien) {
        this.soTien = soTien;
    }
}
