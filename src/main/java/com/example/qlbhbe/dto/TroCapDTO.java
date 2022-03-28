package com.example.qlbhbe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Generated at 2022/03/28 15:06:40
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TroCapDTO {

    private Long id;

    private String ten;

    private Double mucTroCap;

    private String mieuTa;

    private String maTroCap;

    private Long isCount;

    public Long getIsCount() {
        return isCount;
    }

    public void setIsCount(Long isCount) {
        this.isCount = isCount;
    }

    public TroCapDTO() {
    }

    public TroCapDTO(Long id, String ten, Double mucTroCap, String mieuTa, String maTroCap) {
        this.id = id;
        this.ten = ten;
        this.mucTroCap = mucTroCap;
        this.mieuTa = mieuTa;
        this.maTroCap = maTroCap;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTen() {
        return this.ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Double getMucTroCap() {
        return this.mucTroCap;
    }

    public void setMucTroCap(Double mucTroCap) {
        this.mucTroCap = mucTroCap;
    }

    public String getMieuTa() {
        return this.mieuTa;
    }

    public void setMieuTa(String mieuTa) {
        this.mieuTa = mieuTa;
    }

    public String getMaTroCap() {
        return this.maTroCap;
    }

    public void setMaTroCap(String maTroCap) {
        this.maTroCap = maTroCap;
    }
}
