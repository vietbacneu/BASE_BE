package com.example.qlbhbe.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;


public abstract class AbstractService<E extends Serializable, I extends Serializable> implements BaseService<E, I> {
    private JpaRepository<E, I> repo;

    public AbstractService(JpaRepository<E, I> repo) {
        this.repo = repo;
    }

    //@Override
    //public Page<LI> search(SP params, Pageable pageable) {
    //    return ((BaseSearchRepo<LI, SP>) repo).search(params, pageable);
    //}

    //@Override
    //public Optional<DT> findDetailsDTOById(I id) {
    //    return ((BaseDetailsRepo<DT, I>) repo).findDetailsDTOById(id);
    //}

    @Override
    public boolean existsById(I id) {
        return repo.existsById(id);
    }

    @Override
    public void save(E entity) {
        repo.save(entity);
    }

    @Override
    public void delete(E entity) {
        repo.delete(entity);
    }

    @Override
    public void deleteById(I id) {
        repo.deleteById(id);
    }

    @Override
    public Optional<E> findById(I id) {
        return repo.findById(id);
    }

    @Override
    public List<E> findAll() {
        return repo.findAll();
    }

    @Override
    public Page<E> findAll(Pageable pageable) {
        return repo.findAll(pageable);
    }

    @Override
    public E getOne(I id) {
        return repo.getOne(id);
    }
}
