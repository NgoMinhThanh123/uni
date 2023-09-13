package com.nmt.repository.impl;

import com.nmt.model.Semester;
import com.nmt.repository.SemesterRepository;
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
public class SemesterRepositoryImpl implements SemesterRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;

    @Override
    public List<Semester> getSemesters(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Semester> q = b.createQuery(Semester.class);
        Root root = q.from(Semester.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                predicates.add(b.like(root.get("schoolYear"), String.format("%%%s%%", kw)));
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
    public Semester getSemesterById(String id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Semester.class, id);
    }

    @Override
    public boolean deleteSemester(String id) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            Semester m = this.getSemesterById(id);
            s.delete(m);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean addSemester(Semester m) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(m);

        return true;
    }

    @Override
    public boolean updateSemester(Semester m) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            if (m.getId() != null) {
                s.update(m);
            }

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public int countSemesters() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT COUNT(*) FROM Semester");

        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public List<Semester> getSemesterByLecturerId(String lecturerId) {
        Session s = this.factory.getObject().getCurrentSession();
        List<Semester> semesters = new ArrayList<>();
        try {
            String sql = "SELECT DISTINCT semester.id, semester.name, semester.school_year\n" +
                    "FROM semester\n" +
                    "join score on score.semester_id = semester.id\n" +
                    "join student_subject on score.student_subject_id = student_subject.id\n" +
                    "join subject on student_subject.subject_id = subject.id\n" +
                    "join lecturer_subject on lecturer_subject.subject_id = subject.id\n"
                    + "join lecturer on lecturer_subject.lecturer_id = lecturer.id\n"
                    + "where lecturer.id = :lecturerId";
            Query query = s.createNativeQuery(sql);
            query.setParameter("lecturerId", lecturerId);
            semesters = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return semesters;
    }

    @Override
    public List<Semester> getSemesterByStudentId(String studentId) {
        Session s = this.factory.getObject().getCurrentSession();
        List<Semester> semesters = new ArrayList<>();
        try {
            String sql = "SELECT DISTINCT semester.id, semester.name, semester.school_year\n" +
                    "FROM semester\n" +
                    "join score on score.semester_id = semester.id\n" +
                    "join student_subject on score.student_subject_id = student_subject.id\n" +
                    "join subject on student_subject.subject_id = subject.id\n" +
                    "join student on student_subject.student_id = student.id\n"
                    + "where student.id = :studentId";
            Query query = s.createNativeQuery(sql);
            query.setParameter("studentId", studentId);
            semesters = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return semesters;
    }

}
