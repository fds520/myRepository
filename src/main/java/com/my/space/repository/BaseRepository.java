package com.my.space.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Created by fds on 2017/1/22.
 */
// 这样Spring Data Jpa在启动时就不会去实例化BaseRepository这个接口
@NoRepositoryBean
public interface BaseRepository<M, ID extends Serializable> extends JpaRepository<M, ID> {
    List<M> findAllByProperty(String var1, Object var2);

    M findOneByProperty(String var1, Object var2);

    List<M> findAll(String var1, Object... var2);

    List<M> findAll(String var1, Map<String, Object> var2);

    M findOne(String var1, Object... var2);

    M findOne(String var1, Map<String, Object> var2);

    Page<M> findAll(Pageable var1, String var2, Object... var3);

    Page<M> findAll(Pageable var1, String var2, Map<String, Object> var3);

    void delete(Object... var1);

    void deleteBy(String var1, Object var2);

    void update(String var1, Object... var2);

    void updateProperty(String var1, Object var2, Object... var3);
}
