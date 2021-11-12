package com.example.qlbhbe.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;

/**
 * Generated at 2021/11/12 14:13:53
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ChamCongDTO {

    private Long id;

    private Double soGioLam;

    private LocalDateTime ngayLam;

    private String mieuTa;

    public ChamCongDTO() {
    }

    public ChamCongDTO(Long id, Double soGioLam, LocalDateTime ngayLam, String mieuTa) {
        this.id = id;
        this.soGioLam = soGioLam;
        this.ngayLam = ngayLam;
        this.mieuTa = mieuTa;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getSoGioLam() {
        return this.soGioLam;
    }

    public void setSoGioLam(Double soGioLam) {
        this.soGioLam = soGioLam;
    }

    public LocalDateTime getNgayLam() {
        return this.ngayLam;
    }

    public void setNgayLam(LocalDateTime ngayLam) {
        this.ngayLam = ngayLam;
    }

    public String getMieuTa() {
        return this.mieuTa;
    }

    public void setMieuTa(String mieuTa) {
        this.mieuTa = mieuTa;
    }
}
