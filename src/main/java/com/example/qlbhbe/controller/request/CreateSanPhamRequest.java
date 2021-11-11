package com.example.qlbhbe.controller.request;

import com.example.qlbhbe.entity.DanhMuc;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;


@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateSanPhamRequest {
    private long id;

    private String maSanPham;

    private String tenSanPham;

    private Double giaBanNiemYet;

    private Double giaNhapNiemYet;

    private Double soLuong;

    private String mieuTa;

    private LocalDate ngayTao = LocalDate.now();

    private String nguoiTao = "admin";

    private LocalDate ngayThayDoi = LocalDate.now();

    private String nguoiThayDoi = "admin";

    private DanhMuc idDanhMuc;

    private String donVi ;
}
