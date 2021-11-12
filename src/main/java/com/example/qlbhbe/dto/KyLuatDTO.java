package com.example.qlbhbe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Generated at 2021/11/12 14:15:49
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class KyLuatDTO {

    private Long id;

    private String tenLoi;

    private Double mucPhat;

    private String mieuTa;

    public KyLuatDTO() {
    }

    public KyLuatDTO(Long id, String tenLoi, Double mucPhat, String mieuTa) {
        this.id = id;
        this.tenLoi = tenLoi;
        this.mucPhat = mucPhat;
        this.mieuTa = mieuTa;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTenLoi() {
        return this.tenLoi;
    }

    public void setTenLoi(String tenLoi) {
        this.tenLoi = tenLoi;
    }

    public Double getMucPhat() {
        return this.mucPhat;
    }

    public void setMucPhat(Double mucPhat) {
        this.mucPhat = mucPhat;
    }

    public String getMieuTa() {
        return this.mieuTa;
    }

    public void setMieuTa(String mieuTa) {
        this.mieuTa = mieuTa;
    }
}
