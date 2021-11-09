package com.example.qlbhbe.service.nhacungcap;

import com.example.qlbhbe.controller.request.UpdateNhaCungCapRequest;
import com.example.qlbhbe.dto.NhaCungCapDTO;
import com.example.qlbhbe.entity.NhaCungCap;
import com.example.qlbhbe.mapper.NhaCungCapMapper;
import com.example.qlbhbe.repo.nhacungcap.NhaCungCapRepo;
import com.example.qlbhbe.service.AbstractService;
import com.example.qlbhbe.util.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.*;


@Service
@Transactional
public class NhaCungCapServiceImpl extends AbstractService<NhaCungCap, Long> implements NhaCungCapService {

    private final NhaCungCapRepo nhaCungCapRepo;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    public NhaCungCapServiceImpl(NhaCungCapRepo nhaCungCapRepo) {
        super(nhaCungCapRepo);
        this.nhaCungCapRepo = nhaCungCapRepo;
    }

    @Override
    public NhaCungCap update(long id, NhaCungCapDTO command) {
        Optional<NhaCungCap> opt = nhaCungCapRepo.findById(id);
        if (opt.isPresent()) {
            NhaCungCap nhaCungCap = opt.get();
            return NhaCungCapMapper.INSTANCE.update(command, nhaCungCap);
        }
        return null;
    }

    @Override
    public Page<NhaCungCapDTO> search(NhaCungCapDTO command, Pageable pageable) throws Exception {
        try {
            StringBuilder queryStr = new StringBuilder();
            StringBuilder count = new StringBuilder();
            StringBuilder from = new StringBuilder();
            Map<String, Object> params = new HashMap<>();
            queryStr.append("select id    , " +
                    "    ma_nha_cung_cap  , " +
                    "    ten_nha_cung_cap , " +
                    "    ma_so_thue     , " +
                    "    dia_chi       , " +
                    "    sdt            , " +
                    "    thong_tin        , " +
                    "    nguoi_tao      , " +
                    "    ngay_tao      , " +
                    "    ngay_thay_doi    , " +
                    "    nguoi_thay_doi     ");
            count.append("select count(*) ");
            from.append(" from nha_cung_cap where 1 = 1 ");
            if (!DataUtil.isNullOrEmpty(command.getTenNhaCungCap())) {
                from.append(" and lower(ten_nha_cung_cap) like :ten ");
                params.put("ten", '%' + command.getTenNhaCungCap().toLowerCase(Locale.ROOT) +'%');
            }
            if (!DataUtil.isNullOrEmpty(command.getMaNhaCungCap())) {
                from.append(" and lower(ma_nha_cung_cap) like :ma ");
                params.put("ma", '%' + command.getMaNhaCungCap().toLowerCase(Locale.ROOT)+'%');
            }
            if (!DataUtil.isNullOrEmpty(command.getSdt())) {
                from.append(" and lower(sdt) like :sdt ");
                params.put("sdt", '%' + command.getSdt().toLowerCase(Locale.ROOT)+'%');
            }
            if (!DataUtil.isNullOrEmpty(command.getDiaChi())) {
                from.append(" and lower(dia_chi) like :diachi ");
                params.put("diachi", '%' + command.getDiaChi().toLowerCase(Locale.ROOT)+'%');
            }
            if (!DataUtil.isNullOrEmpty(command.getMaSoThue())) {
                from.append(" and lower(ma_so_thue) like :thue ");
                params.put("thue", '%' + command.getDiaChi().toLowerCase(Locale.ROOT)+'%');
            }
            queryStr.append(from);
            count.append(from);
            Query query = entityManager.createNativeQuery(queryStr.toString());
            Query countQuery = entityManager.createNativeQuery(count.toString());
            for (Map.Entry<String, Object> p : params.entrySet()) {
                query.setParameter(p.getKey(), p.getValue());
                countQuery.setParameter(p.getKey(), p.getValue());
            }
            query.setFirstResult((int) pageable.getOffset());
            query.setMaxResults(pageable.getPageSize());
            List<Object[]> objects = query.getResultList();
            Object o = countQuery.getSingleResult();
            List<NhaCungCapDTO> danhMucDTOS = DataUtil.convertLsObjectsToClass(Arrays.asList("id", "maNhaCungCap", "tenNhaCungCap", "maSoThue",
                            "diaChi", "sdt", "thongTin", "nguoiTao", "ngayTao", "ngayThayDoi", "nguoiThayDoi")
                    , objects, NhaCungCapDTO.class);

            return new PageImpl<>(danhMucDTOS, pageable, Long.parseLong(o.toString()));
        } catch (Exception e) {
            throw e;
        }
    }
}
