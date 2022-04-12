package com.example.qlbhbe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.time.LocalDate;
import java.util.List;

/**
 * Generated at 2021/10/14 17:18:02
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class XuatHangDTO {

    private Long id;

    private LocalDate ngayTao = LocalDate.now();

    private String nguoiTao = "admin";

    private LocalDate ngayThayDoi = LocalDate.now();

    private String nguoiThayDoi = "admin";

    private String maXuatHang;

    private LocalDate ngayXuat;

    private Long idKhachHang;

    private Long idCuaHang;

    private String tenCuaHang;

    private String tenKhachHang;

    private String startDate;

    private String endDate;

    private Long idPhuongThuc;

    private String tenPhuongThuc;

    private Double totalDT;

    List<XuatHangChiTietDTO> xuatHangChiTietDTOList;

    public XuatHangDTO() {
    }

    public XuatHangDTO(Long id, String nguoiTao, LocalDate ngayTao, String nguoiThayDoi, LocalDate ngayThayDoi, String maXuatHang, LocalDate ngayXuat) {
        this.id = id;
        this.nguoiTao = nguoiTao;
        this.ngayTao = ngayTao;
        this.nguoiThayDoi = nguoiThayDoi;
        this.ngayThayDoi = ngayThayDoi;
        this.maXuatHang = maXuatHang;
        this.ngayXuat = ngayXuat;
    }



    public Double getTotalDT() {
        return totalDT;
    }

    public void setTotalDT(Double totalDT) {
        this.totalDT = totalDT;
    }

    public String getTenCuaHang() {
        return tenCuaHang;
    }

    public void setTenCuaHang(String tenCuaHang) {
        this.tenCuaHang = tenCuaHang;
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

    public Long getIdCuaHang() {
        return idCuaHang;
    }

    public void setIdCuaHang(Long idCuaHang) {
        this.idCuaHang = idCuaHang;
    }

    public Long getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(Long idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNguoiTao() {
        return this.nguoiTao;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public LocalDate getNgayTao() {
        return this.ngayTao;
    }

    public void setNgayTao(LocalDate ngayTao) {
        this.ngayTao = ngayTao;
    }

    public String getNguoiThayDoi() {
        return this.nguoiThayDoi;
    }

    public void setNguoiThayDoi(String nguoiThayDoi) {
        this.nguoiThayDoi = nguoiThayDoi;
    }

    public LocalDate getNgayThayDoi() {
        return this.ngayThayDoi;
    }

    public void setNgayThayDoi(LocalDate ngayThayDoi) {
        this.ngayThayDoi = ngayThayDoi;
    }

    public String getMaXuatHang() {
        return this.maXuatHang;
    }

    public void setMaXuatHang(String maXuatHang) {
        this.maXuatHang = maXuatHang;
    }

    public LocalDate getNgayXuat() {
        return this.ngayXuat;
    }

    public void setNgayXuat(LocalDate ngayXuat) {
        this.ngayXuat = ngayXuat;
    }

    public List<XuatHangChiTietDTO> getXuatHangChiTietDTOList() {
        return xuatHangChiTietDTOList;
    }

    public void setXuatHangChiTietDTOList(List<XuatHangChiTietDTO> xuatHangChiTietDTOList) {
        this.xuatHangChiTietDTOList = xuatHangChiTietDTOList;
    }
}
