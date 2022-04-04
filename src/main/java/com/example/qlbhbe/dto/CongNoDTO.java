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

    private String loadHopDong;

    private Integer idHopDong;

    private String trangThaiThanhToan;

    private Double soTien;

    private List<CongNoChiTietDTO> congNoChiTietDTOS;


    public CongNoDTO() {
    }

    public CongNoDTO(Long id, String maCongNo, String loadHopDong, Integer idHopDong, String trangThaiThanhToan, Double soTien) {
        this.id = id;
        this.maCongNo = maCongNo;
        this.loadHopDong = loadHopDong;
        this.idHopDong = idHopDong;
        this.trangThaiThanhToan = trangThaiThanhToan;
        this.soTien = soTien;
    }

    public List<CongNoChiTietDTO> getCongNoChiTietDTOS() {
        return congNoChiTietDTOS;
    }

    public void setCongNoChiTietDTOS(List<CongNoChiTietDTO> congNoChiTietDTOS) {
        this.congNoChiTietDTOS = congNoChiTietDTOS;
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

    public String getLoadHopDong() {
        return this.loadHopDong;
    }

    public void setLoadHopDong(String loadHopDong) {
        this.loadHopDong = loadHopDong;
    }

    public Integer getIdHopDong() {
        return this.idHopDong;
    }

    public void setIdHopDong(Integer idHopDong) {
        this.idHopDong = idHopDong;
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
