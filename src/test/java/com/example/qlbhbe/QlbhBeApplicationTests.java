package com.example.qlbhbe;

import com.example.qlbhbe.entity.NhanVien;
import com.example.qlbhbe.repo.nhanvien.NhanVienRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@SpringBootTest
class QlbhBeApplicationTests {

    @Autowired
    NhanVienRepo nhanVienRepo;

    @PersistenceContext
    EntityManager entityManager;

    @Test
    @Transactional(rollbackFor = Exception.class)
    public void test() {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setTen("Bắc11");
        nhanVien = nhanVienRepo.save(nhanVien);
        entityManager.flush();
        delete(nhanVien.getId());
    }

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public String delete(Long id) {
        Object o = entityManager.createNativeQuery("select ten from nhan_vien where id = :id").setParameter("id", id).getSingleResult();
        entityManager.createNativeQuery("delete from nhan_vien where id = :id").setParameter("id", id).executeUpdate();
        return "OK";
    }

}
