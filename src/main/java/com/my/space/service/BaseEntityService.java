//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.my.space.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.my.space.repository.BaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class BaseEntityService<M, ID extends Serializable> {
    public BaseRepository<M, ID> baseRepository;

    public BaseEntityService() {
    }

    public M save(M m) {
        return this.baseRepository.save(m);
    }

    public M update(M m) {
        return this.baseRepository.save(m);
    }

    public void delete(ID id) {
        this.baseRepository.delete(id);
    }

    public void delete(M m) {
        this.baseRepository.delete(m);
    }

    public M findOne(ID id) {
        return this.baseRepository.findOne(id);
    }

    public boolean exists(ID id) {
        return this.baseRepository.exists(id);
    }

    public List<M> findAllByProperty(String property, Object value) {
        return this.baseRepository.findAllByProperty(property, value);
    }

    public M findOneByProperty(String property, Object value) {
        return this.baseRepository.findOneByProperty(property, value);
    }

    public List<M> findAll(String jql, Object... values) {
        return this.baseRepository.findAll(jql, values);
    }

    public List<M> findAll(String jql, Map<String, Object> values) {
        return this.baseRepository.findAll(jql, values);
    }

    public M findOne(String jql, Object... values) {
        return this.baseRepository.findOne(jql, values);
    }

    public M findOne(String jql, Map<String, Object> values) {
        return this.baseRepository.findOne(jql, values);
    }

    public Page<M> findAll(Pageable pageable, String jql, Object... values) {
        return this.baseRepository.findAll(pageable, jql, values);
    }

    public Page<M> findAll(Pageable pageable, String jql, Map<String, Object> values) {
        return this.baseRepository.findAll(pageable, jql, values);
    }

    public void delete(Object... ids) {
        this.baseRepository.delete(ids);
    }

    public void deleteBy(String property, Object value) {
        this.baseRepository.deleteBy(property, value);
    }

    public void update(String jql, Object... value) {
        this.baseRepository.update(jql, value);
    }

    public void updateProperty(String property, Object value, Object... ids) {
        this.baseRepository.update(property, new Object[]{value, ids});
    }

    public BaseRepository<M, ID> getBaseRepository() {
        return this.baseRepository;
    }

    @Autowired
    public void setBaseRepository(BaseRepository<M, ID> baseRepository) {
        this.baseRepository = baseRepository;
    }
}
