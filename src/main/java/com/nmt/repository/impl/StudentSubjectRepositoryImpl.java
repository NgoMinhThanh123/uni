/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmt.repository.impl;

import com.nmt.model.*;
import com.nmt.repository.StudentRepository;
import com.nmt.repository.StudentSubjectRepository;
import com.nmt.repository.SubjectRepository;
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
public class StudentSubjectRepositoryImpl implements StudentSubjectRepository {

    @Autowired
    private Environment env;
    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private StudentSubjectRepository studentSubjectRepository;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<StudentSubject> getStudentSubjects(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<StudentSubject> q = b.createQuery(StudentSubject.class);
        Root root = q.from(StudentSubject.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                predicates.add(b.like(root.get("id"), String.format("%%%s%%", kw)));
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
    public StudentSubject getStudentSubjectById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(StudentSubject.class, id);
    }

    @Override
    public boolean addOrUpdateStudentSubject(StudentSubject st) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            if (st.getId() == null) {
                s.save(st);
            } else {
                s.update(st);
            }

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean deleteStudentSubject(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            StudentSubject t = this.getStudentSubjectById(id);
            s.delete(t);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public StudentSubject getStudentSubjectByStudentAndSubjectId(String studentId, String subjectId) {
        Session s = this.factory.getObject().getCurrentSession();
        StudentSubject studentSubject = new StudentSubject();
        try {

            Query q = s.createNativeQuery("SELECT DISTINCT student_subject.* " +
                            "FROM student_subject " +
                            "WHERE student_subject.student_id = :studentId AND student_subject.subject_id = :subjectId")
                    .addEntity("student_subject", StudentSubject.class);
            q.setParameter("studentId", studentId);
            q.setParameter("subjectId", subjectId);

            studentSubject = (StudentSubject) q.getSingleResult();


        }catch (Exception e){
            e.printStackTrace();
        }

        return studentSubject;
    }

    @Override
    public int countStudentSubject() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT COUNT(*) FROM StudentSubject");

        return Integer.parseInt(q.getSingleResult().toString());
    }

}
