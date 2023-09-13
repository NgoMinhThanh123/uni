package com.nmt.repository.impl;

import com.nmt.model.Lecturer;

import java.util.List;
import java.util.Map;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.nmt.repository.LecturerRepository;
import java.util.ArrayList;
import javax.persistence.NoResultException;
import javax.persistence.criteria.Predicate;
import org.hibernate.HibernateException;
import org.springframework.core.env.Environment;

/**
 *
 * @author admin
 */
@Repository
@Transactional
public class LecturerRepositoryImpl implements LecturerRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;

    @Override
    public List<Lecturer> getLecturers(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Lecturer> q = b.createQuery(Lecturer.class);
        Root root = q.from(Lecturer.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                predicates.add(b.like(root.get("name"), String.format("%%%s%%", kw)));
            }
            q.where(predicates.toArray(Predicate[]::new));
        }

        q.orderBy(b.desc(root.get("id")));

        Query query = s.createQuery(q);

        if (params != null) {
            String page = params.get("page");
            if (page != null) {
                int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
                query.setFirstResult((Integer.parseInt(page) - 1) * pageSize);
                query.setMaxResults(pageSize);
            }
        }

        return query.getResultList();

    }

    @Override
    public int countLecturers() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT COUNT(*) FROM Lecturer");

        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public boolean addLeturer(Lecturer l) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(l);

        return true;
    }

    @Override
    public boolean updateLeturer(Lecturer l) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.update(l);

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Lecturer getLecturerById(String id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Lecturer.class, id);
    }

    @Override
    public boolean deleteLecturer(String id) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            Lecturer l = this.getLecturerById(id);
            s.delete(l);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Lecturer getLecturerByUsername(String username) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            String hql = "FROM Lecturer s WHERE s.userId.username = :username";
            Lecturer lecturer = s.createQuery(hql, Lecturer.class)
                    .setParameter("username", username)
                    .uniqueResult();
            return lecturer;
        } catch (NoResultException e) {
            return null;
        }
    }

}
