package com.example.qlbhbe.service.congnochitiet;

import com.example.qlbhbe.dto.CongNoChiTietDTO;
import com.example.qlbhbe.entity.CongNoChiTiet;
import com.example.qlbhbe.repo.congnochitiet.CongNoChiTietRepo;
import com.example.qlbhbe.service.AbstractService;
import com.example.qlbhbe.util.DataUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
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
public class CongNoChiTietServiceImpl extends AbstractService<CongNoChiTiet, Long> implements CongNoChiTietService {

    private final CongNoChiTietRepo congNoChiTietRepo;

    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    public CongNoChiTietServiceImpl(CongNoChiTietRepo congNoChiTietRepo) {
        super(congNoChiTietRepo);
        this.congNoChiTietRepo = congNoChiTietRepo;
    }

    @Override
    public CongNoChiTiet update(long id) {
        Optional<CongNoChiTiet> opt = congNoChiTietRepo.findById(id);
        if (opt.isPresent()) {
            CongNoChiTiet congNoChiTiet = opt.get();
            congNoChiTiet.setTrangThai("dathanhtoan");
            congNoChiTietRepo.save(congNoChiTiet);
        }
        return null;
    }

    @Override
    public Page<CongNoChiTietDTO> search(CongNoChiTietDTO command, Pageable pageable) throws Exception {
        try {
            StringBuilder queryStr = new StringBuilder();
            StringBuilder count = new StringBuilder();
            StringBuilder from = new StringBuilder();
            Map<String, Object> params = new HashMap<>();
            queryStr.append("select cnd.id,\n" +
                    "       cn.ma_cong_no,\n" +
                    "       case\n" +
                    "           when (cnd.trang_thai = 'chuathanhtoan' or cnd.trang_thai is null) then 'Chưa thanh toán'\n" +
                    "           when cnd.trang_thai = 'dathanhtoan' then 'Đã thanh toán' end           trangThaiName,\n" +
                    "       cnd.so_tien_thanh_toan,\n" +
                    "       cnd.ngay_thanh_toan, cnd.trang_thai\n" +
                    "from cong_no cn,\n" +
                    "     cong_no_chi_tiet cnd\n" +
                    "where cn.id = cnd.id_cong_no   ");


            count.append("");
            if (!DataUtil.isNullOrEmpty(command.getMaCongNo())) {
                from.append(" and lower(cn.ma_cong_no) like :ma ");
                params.put("ma", '%' + command.getMaCongNo().toLowerCase(Locale.ROOT) + '%');
            }
            queryStr.append(from);
            Query query = entityManager.createNativeQuery(queryStr.toString());
            Query countQuery = entityManager.createNativeQuery("select count(*) from (" + queryStr.toString() + ") tmp");
            for (Map.Entry<String, Object> p : params.entrySet()) {
                query.setParameter(p.getKey(), p.getValue());
                countQuery.setParameter(p.getKey(), p.getValue());
            }
            query.setFirstResult((int) pageable.getOffset());
            query.setMaxResults(pageable.getPageSize());
            List<Object[]> objects = query.getResultList();
            Object o = countQuery.getSingleResult();
            List<CongNoChiTietDTO> danhMucDTOS = DataUtil.convertLsObjectsToClass(Arrays.asList("id", "maCongNo", "trangThaiName", "soTienThanhToan",
                            "ngayThanhToan", "trangThai")
                    , objects, CongNoChiTietDTO.class);

            return new PageImpl<>(danhMucDTOS, pageable, Long.parseLong(o.toString()));
        } catch (Exception e) {
            throw e;
        }
    }



}
