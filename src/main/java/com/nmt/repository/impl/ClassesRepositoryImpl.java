/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmt.repository.impl;

import com.nmt.model.Classes;
import com.nmt.model.Faculty;
import com.nmt.repository.ClassesRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author acer
 */
@Repository
@Transactional
public class ClassesRepositoryImpl implements ClassesRepository{
    @Autowired
    private LocalSessionFactoryBean factory;
     @Autowired
    private Environment env;

    @Override
    public List<Classes> getClasses(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Classes> q = b.createQuery(Classes.class);
        Root root = q.from(Classes.class);
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
    public boolean addClass(Classes c) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            if (c.getId() != null) {
                s.save(c);
            }
            System.out.println(s);

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateClass(Classes c) {
          Session s = this.factory.getObject().getCurrentSession();
        try {
            if (c.getId() != null) {
                s.save(c);
            }

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Classes getClassById(String id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Classes.class, id);
    }

    @Override
    public boolean deleteClass(String id) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            Classes c = this.getClassById(id);
            s.delete(c);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public int countClasses() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT COUNT(*) FROM Classes");

        return Integer.parseInt(q.getSingleResult().toString());
    }
}
