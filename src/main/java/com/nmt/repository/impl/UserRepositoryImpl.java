/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmt.repository.impl;

import com.nmt.model.User;
import com.nmt.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.NoResultException;
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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author acer
 */
@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository{
    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;
    
    @Autowired
    private BCryptPasswordEncoder passEncoder;

    @Override
    public List<User> getUsers(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<User> q = b.createQuery(User.class);
        Root root = q.from(User.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                predicates.add(b.like(root.get("email"), String.format("%%%s%%", kw)));
            }
            q.where(predicates.toArray(Predicate[]::new));
        }

        q.orderBy(b.asc(root.get("id")));

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
    public boolean addOrUpdateUser(User u) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            if (u.getId() == null) {
                s.save(u);
            } else {
                s.update(u);
            }

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public User getUserById(int id) {
         Session s = this.factory.getObject().getCurrentSession();
         return s.get(User.class, id);
    }

    @Override
    public boolean deleteUser(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            User t = this.getUserById(id);
            s.delete(t);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public User getUserByUsername(String username) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM User WHERE username=:un");
        q.setParameter("un", username);

        try {
            return (User) q.getSingleResult();
        } catch (NoResultException e) {
            return null; // Trả về null nếu không tìm thấy dữ liệu
        }
    }

    @Override
    public User getUByUn(String username) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("FROM User WHERE username=:un");
        q.setParameter("un", username);

        try {
            return (User) q.getSingleResult();
        } catch (NoResultException e) {
            return null; // Trả về null nếu không tìm thấy dữ liệu
        }
    }

    @Override
    public boolean authUser(String username, String password) {
         User  u = this.getUserByUsername(username);
        
        return this.passEncoder.matches(password, u.getPassword());
    }

    @Override
    public User addUser(User user) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(user);
        
        return user;
    }

    @Override
    public boolean isValidSchoolEmail(String email) {
        String schoolDomain = "ou.edu.vn";
        return email.endsWith("@" + schoolDomain);
    }

    @Override
    public User getUserByEmail(String email) {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("From User Where email=:em");
        q.setParameter("em", email);

        try {
            return (User) q.getSingleResult();
        } catch (NoResultException e) {
            return null; // Trả về null nếu không tìm thấy dữ liệu
        }
    }

    @Override
    public int countUsers() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT COUNT(*) FROM User");

        return Integer.parseInt(q.getSingleResult().toString());
    }
}
