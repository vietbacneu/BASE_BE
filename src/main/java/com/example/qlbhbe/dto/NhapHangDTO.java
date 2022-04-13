package com.example.qlbhbe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.util.List;

/**
 * Generated at 2021/10/14 17:17:46
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class NhapHangDTO {

    private Long id;

    private LocalDate ngayTao = LocalDate.now();

    private String nguoiTao = "admin";

    private LocalDate ngayThayDoi = LocalDate.now();

    private String nguoiThayDoi = "admin";

    private String maNhapHang;

    private LocalDate ngayNhap;

    private Long idNhaCungCap;

    private String tenNhaCungCap;

    private LocalDate startDate;

    private LocalDate endDate;

    private String tenCuaHang;

    private Long idCuaHang;

    private Long idPhuongThuc;

    private String tenPhuongThuc;

    private Double totalDT;

    private Long isCount;

    List<NhapHangChiTietDTO> nhapHangChiTietDTOList;

    public Long getIsCount() {
        return isCount;
    }

    public void setIsCount(Long isCount) {
        this.isCount = isCount;
    }

    public Double getTotalDT() {
        return totalDT;
    }

    public void setTotalDT(Double totalDT) {
        this.totalDT = totalDT;
    }

    public Long getIdPhuongThuc() {
        return idPhuongThuc;
    }

    public void setIdPhuongThuc(Long idPhuongThuc) {
        this.idPhuongThuc = idPhuongThuc;
    }

    public String getTenPhuongThuc() {
        return tenPhuongThuc;
    }

    public void setTenPhuongThuc(String tenPhuongThuc) {
        this.tenPhuongThuc = tenPhuongThuc;
    }

    public NhapHangDTO() {
    }

    public NhapHangDTO(Long id, LocalDate ngayTao, String nguoiTao, LocalDate ngayThayDoi, String nguoiThayDoi, String maNhapHang, LocalDate ngayNhap, String tenNhaCungCap) {
        this.id = id;
        this.ngayTao = ngayTao;
        this.nguoiTao = nguoiTao;
        this.ngayThayDoi = ngayThayDoi;
        this.nguoiThayDoi = nguoiThayDoi;
        this.maNhapHang = maNhapHang;
        this.ngayNhap = ngayNhap;
        this.tenNhaCungCap = tenNhaCungCap;
    }

    public String getTenCuaHang() {
        return tenCuaHang;
    }

    public void setTenCuaHang(String tenCuaHang) {
        this.tenCuaHang = tenCuaHang;
    }

    public Long getIdCuaHang() {
        return idCuaHang;
    }

    public void setIdCuaHang(Long idCuaHang) {
        this.idCuaHang = idCuaHang;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Long getIdNhaCungCap() {
        return idNhaCungCap;
    }

    public void setIdNhaCungCap(Long idNhaCungCap) {
        this.idNhaCungCap = idNhaCungCap;
    }

    public String getTenNhaCungCap() {
        return tenNhaCungCap;
    }

    public void setTenNhaCungCap(String tenNhaCungCap) {
        this.tenNhaCungCap = tenNhaCungCap;
    }

    public List<NhapHangChiTietDTO> getNhapHangChiTietDTOList() {
        return nhapHangChiTietDTOList;
    }

    public void setNhapHangChiTietDTOList(List<NhapHangChiTietDTO> nhapHangChiTietDTOList) {
        this.nhapHangChiTietDTOList = nhapHangChiTietDTOList;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getNgayTao() {
        return this.ngayTao;
    }

    public void setNgayTao(LocalDate ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getNguoiTao() {
        return this.nguoiTao;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public LocalDate getNgayThayDoi() {
        return this.ngayThayDoi;
    }

    public void setNgayThayDoi(LocalDate ngayThayDoi) {
        this.ngayThayDoi = ngayThayDoi;
    }

    public String getNguoiThayDoi() {
        return this.nguoiThayDoi;
    }

    public void setNguoiThayDoi(String nguoiThayDoi) {
        this.nguoiThayDoi = nguoiThayDoi;
    }

    public String getMaNhapHang() {
        return this.maNhapHang;
    }

    public void setMaNhapHang(String maNhapHang) {
        this.maNhapHang = maNhapHang;
    }

    public LocalDate getNgayNhap() {
        return this.ngayNhap;
    }

    public void setNgayNhap(LocalDate ngayNhap) {
        this.ngayNhap = ngayNhap;
    }
}
