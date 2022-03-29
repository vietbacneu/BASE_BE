package com.example.qlbhbe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Generated at 2021/11/12 14:13:49
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaoHiemDTO {

    private Long id;

    private String maBaoHiem;

    private String maSo;

    private Double heSo;

    private String ten;

    private Integer mucDong;

    private String thongTin;

    private Long isCount;

    public BaoHiemDTO() {
    }

    public BaoHiemDTO(Long id, String maSo, String ten, Integer mucDong, String thongTin) {
        this.id = id;
        this.maSo = maSo;
        this.ten = ten;
        this.mucDong = mucDong;
        this.thongTin = thongTin;
    }

    public Double getHeSo() {
        return heSo;
    }

    public void setHeSo(Double heSo) {
        this.heSo = heSo;
    }

    public String getMaBaoHiem() {
        return maBaoHiem;
    }

    public void setMaBaoHiem(String maBaoHiem) {
        this.maBaoHiem = maBaoHiem;
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

    public String getMaSo() {
        return this.maSo;
    }

    public void setMaSo(String maSo) {
        this.maSo = maSo;
    }

    public String getTen() {
        return this.ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public Integer getMucDong() {
        return this.mucDong;
    }

    public void setMucDong(Integer mucDong) {
        this.mucDong = mucDong;
    }

    public String getThongTin() {
        return this.thongTin;
    }

    public void setThongTin(String thongTin) {
        this.thongTin = thongTin;
    }
}
