//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.my.space.repository;

import java.io.Serializable;
import java.util.*;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.provider.PersistenceProvider;
import org.springframework.data.jpa.repository.query.QueryUtils;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID>
        implements BaseRepository<T, ID> {
    private final EntityManager em;
    private final JpaEntityInformation<T, ?> entityInformation;
    private final PersistenceProvider provider;
    private Class<T> entityClass;
    private String entityName;
    private String idName;

    public BaseRepositoryImpl(JpaEntityInformation<T, ?> entityInformation,
            EntityManager entityManager) {
        super(entityInformation, entityManager);
        Assert.notNull(entityInformation);
        Assert.notNull(entityManager);
        this.entityInformation = entityInformation;
        this.em = entityManager;
        this.provider = PersistenceProvider.fromEntityManager(entityManager);
        this.entityClass = this.entityInformation.getJavaType();
        this.entityName = this.entityInformation.getEntityName();
        this.idName = (String) this.entityInformation.getIdAttributeNames().iterator().next();
    }

    public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
        this(JpaEntityInformationSupport.getEntityInformation(domainClass, em), em);
    }

    public T findOneByProperty(String property, Object value) {
        return this.findOne("select e from " + this.entityName + " e where e." + property + " = ?1",
                new Object[] {value});
    }

    public List<T> findAllByProperty(String property, Object value) {
        return this.findAll("select e from " + this.entityName + " e where e." + property + " = ?1",
                new Object[] {value});
    }

    public List<T> findAll(String jql, Object... values) {
        TypedQuery query = this.em.createQuery(jql, this.entityClass);
        query = this.applyParameters(query, values);
        return query.getResultList();
    }

    public List<T> findAll(String jql, Map<String, Object> values) {
        TypedQuery query = this.em.createQuery(jql, this.entityClass);
        query = this.applyParameters(query, values);
        return query.getResultList();
    }


    public T findOne(String jql, Object... values) {
        List results = this.findAll(jql, values);
        return CollectionUtils.isEmpty(results) ? null : (T) results.get(0);
    }

    public T findOne(String jql, Map<String, Object> values) {
        List results = this.findAll(jql, values);
        return CollectionUtils.isEmpty(results) ? null : (T) results.get(0);
    }

    public Page<T> findAll(Pageable pageable, String jql, Object... values) {
        TypedQuery query = this.em.createQuery(jql, this.entityClass);
        query = this.applyParameters(query, values);
        return (Page) (pageable == null ? new PageImpl(query.getResultList())
                : this.readPage(query, pageable, jql, values));
    }

    public Page<T> findAll(Pageable pageable, String jql, Map<String, Object> values) {
        TypedQuery query = this.em.createQuery(jql, this.entityClass);
        query = this.applyParameters(query, values);
        return (Page) (pageable == null ? new PageImpl(query.getResultList())
                : this.readPage(query, pageable, jql, values));
    }

    @Transactional
    public void delete(Object... ids) {
        if (ids != null && ids.length > 0) {
            ArrayList models = new ArrayList();
            Object[] var3 = ids;
            int var4 = ids.length;

            for (int var5 = 0; var5 < var4; ++var5) {
                Object id = var3[var5];
                Object model = null;

                try {
                    model = this.entityClass.newInstance();
                } catch (Exception var10) {
                    throw new RuntimeException("batch delete " + this.entityClass + " error",
                            var10);
                }

                try {
                    ReflectionUtils.setField(this.entityClass.getField(this.idName), model, id);
                } catch (Exception var9) {
                    throw new RuntimeException(
                            "batch delete " + this.entityClass + " error, can not set id", var9);
                }

                models.add(model);
            }

            super.deleteInBatch(models);
        }

    }

    @Transactional
    public void deleteBy(String property, Object value) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaDelete delete = cb.createCriteriaDelete(this.entityClass);
        Root e = delete.from(this.entityClass);
        delete.where(cb.equal(e.get(property), value));
        Query query = this.em.createQuery(delete);
        query.executeUpdate();
    }

    protected Page<T> readPage(TypedQuery<T> query, Pageable pageable, String jql,
                               Object... values) {
        query.setFirstResult(pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
        Long total = this._executeCountQuery(this.getCountQuery(jql, values));
        List content = total.longValue() > (long) pageable.getOffset() ? query.getResultList()
                : Collections.emptyList();
        return new PageImpl(content, pageable, total.longValue());
    }

    protected Page<T> readPage(TypedQuery<T> query, Pageable pageable, String jql,
                               Map<String, Object> values) {
        query.setFirstResult(pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());
        Long total = this._executeCountQuery(this.getCountQuery(jql, values));
        List content = total.longValue() > (long) pageable.getOffset() ? query.getResultList()
                : Collections.emptyList();
        return new PageImpl(content, pageable, total.longValue());
    }

    public void update(String jql, Object... values) {
        Query query = this.em.createQuery(jql);
        if (values != null) {
            for (int i = 0; values != null && i < values.length; ++i) {
                query.setParameter(i + 1, values[i]);
            }
        }

        query.executeUpdate();
    }

    public void updateProperty(String property, Object value, Object... ids) {
        CriteriaBuilder cb = this.em.getCriteriaBuilder();
        CriteriaUpdate update = cb.createCriteriaUpdate(this.entityClass);
        Root e = update.from(this.entityClass);
        update.set(property, value);
        update.where(e.get(this.idName).in((Object[]) ids));
        Query query = this.em.createQuery(update);
        query.executeUpdate();
    }

    private TypedQuery<T> applyParameters(TypedQuery<T> q, Object[] values) {
        if (values != null && values.length != 0) {
            for (int i = 0; i < values.length; ++i) {
                q.setParameter(i + 1, values[i]);
            }

            return q;
        } else {
            return q;
        }
    }

    private TypedQuery<T> applyParameters(TypedQuery<T> query, Map<String, Object> values) {
        Iterator var3 = values.entrySet().iterator();

        while (var3.hasNext()) {
            Entry e = (Entry) var3.next();
            query.setParameter((String) e.getKey(), e.getValue());
        }

        return query;
    }

    private TypedQuery<Long> getCountQuery(String q, Object... values) {
        String countQueryString = QueryUtils.createCountQueryFor(q);
        TypedQuery query = this.em.createQuery(countQueryString, Long.class);

        for (int i = 0; values != null && i < values.length; ++i) {
            query.setParameter(i + 1, values[i]);
        }

        return query;
    }

    private TypedQuery<Long> getCountQuery(String q, Map<String, Object> values) {
        String countQueryString = QueryUtils.createCountQueryFor(q);
        TypedQuery query = this.em.createQuery(countQueryString, Long.class);
        Iterator var5 = values.entrySet().iterator();

        while (var5.hasNext()) {
            Entry e = (Entry) var5.next();
            query.setParameter((String) e.getKey(), e.getValue());
        }

        return query;
    }

    private Long _executeCountQuery(TypedQuery<Long> query) {
        List totals = query.getResultList();
        Long total = Long.valueOf(0L);

        Long element;
        for (Iterator var4 = totals.iterator(); var4.hasNext(); total =
                Long.valueOf(total.longValue() + (element == null ? 0L : element.longValue()))) {
            element = (Long) var4.next();
        }

        return total;
    }
}
