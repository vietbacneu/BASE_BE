package com.example.qlbhbe.repo.nhanvientrocap;

import com.example.qlbhbe.dto.NhanVienTroCapDTO;
import com.example.qlbhbe.entity.NhanVienTroCap;
import com.example.qlbhbe.entity.NhanVienTroCap_;
import com.example.qlbhbe.repo.AbstractDetailsRepo;
import javax.persistence.EntityManager;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.example.qlbhbe.entity.NhanVien_;
import com.example.qlbhbe.entity.TroCap_;

@Repository
public class NhanVienTroCapDTORepoImpl extends AbstractDetailsRepo<NhanVienTroCapDTO, NhanVienTroCap, Long> implements NhanVienTroCapDTORepo {

    @Autowired
    public NhanVienTroCapDTORepoImpl(EntityManager em) {
        super(em, NhanVienTroCapDTO.class, NhanVienTroCap.class);
    }

    @Override
    public Selection[] getDetailsSelections(Root<NhanVienTroCap> root) {
        return new Selection[] {
            root.get(NhanVienTroCap_.ID),
            root.get(NhanVienTroCap_.MIEU_TA),
            root.get(NhanVienTroCap_.TU_NGAY),
            root.get(NhanVienTroCap_.DEN_NGAY),
            root.join(NhanVienTroCap_.ID_NHAN_VIEN, JoinType.LEFT).get(NhanVien_.ID),
            root.join(NhanVienTroCap_.ID_NHAN_VIEN, JoinType.LEFT).get(NhanVien_.HO),
            root.join(NhanVienTroCap_.ID_NHAN_VIEN, JoinType.LEFT).get(NhanVien_.TEN),
            root.join(NhanVienTroCap_.ID_NHAN_VIEN, JoinType.LEFT).get(NhanVien_.SDT),
            root.join(NhanVienTroCap_.ID_NHAN_VIEN, JoinType.LEFT).get(NhanVien_.EMAIL),
            root.join(NhanVienTroCap_.ID_NHAN_VIEN, JoinType.LEFT).get(NhanVien_.GIOI_TINH),
            root.join(NhanVienTroCap_.ID_NHAN_VIEN, JoinType.LEFT).get(NhanVien_.DIA_CHI),
            root.join(NhanVienTroCap_.ID_NHAN_VIEN, JoinType.LEFT).get(NhanVien_.NGAY_SINH),
            root.join(NhanVienTroCap_.ID_NHAN_VIEN, JoinType.LEFT).get(NhanVien_.NGAY_BAT_DAU),
            root.join(NhanVienTroCap_.ID_NHAN_VIEN, JoinType.LEFT).get(NhanVien_.TRINH_DO),
            root.join(NhanVienTroCap_.ID_TRO_CAP, JoinType.LEFT).get(TroCap_.ID),
            root.join(NhanVienTroCap_.ID_TRO_CAP, JoinType.LEFT).get(TroCap_.TEN),
            root.join(NhanVienTroCap_.ID_TRO_CAP, JoinType.LEFT).get(TroCap_.MUC_TRO_CAP),
            root.join(NhanVienTroCap_.ID_TRO_CAP, JoinType.LEFT).get(TroCap_.MIEU_TA),
            root.join(NhanVienTroCap_.ID_TRO_CAP, JoinType.LEFT).get(TroCap_.MA_TRO_CAP)
        };
    }
}
