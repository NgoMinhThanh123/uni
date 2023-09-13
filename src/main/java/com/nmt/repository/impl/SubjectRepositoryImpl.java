package com.nmt.repository.impl;

import com.nmt.model.Faculty;
import com.nmt.model.Subject;
import com.nmt.repository.FacultyRepository;
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
public class SubjectRepositoryImpl implements SubjectRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private FacultyRepository facRepo;
    @Autowired
    private Environment env;

    @Override
    public List<Subject> getSubjects(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Subject> q = b.createQuery(Subject.class);
        Root root = q.from(Subject.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                predicates.add(b.like(root.get("name"), String.format("%%%s%%", kw)));
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
    public boolean addSubject(Subject c) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(c);

        return true;
    }

    @Override
    public boolean updateSubject(Subject c) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.update(c);

            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Subject getSubjectById(String id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Subject.class, id);
    }

    @Override
    public boolean deleteSubject(String id) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            Subject t = this.getSubjectById(id);
            s.delete(t);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public int countSubjects() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT COUNT(*) FROM Subject");

        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public List<Subject> getSubjectByLecturerId(String lecturerId) {
        Session s = this.factory.getObject().getCurrentSession();
        List<Subject> subjects = new ArrayList<>();
        try {
            String sql = "SELECT subject.id, subject.name, subject.credit, subject.faculty_id\n"
                    + "FROM subject join lecturer_subject on subject.id = lecturer_subject.subject_id\n"
                    + "join lecturer on lecturer.id = lecturer_subject.lecturer_id\n"
                    + "where lecturer.id = :lecturerId";
            Query query = s.createNativeQuery(sql);
            query.setParameter("lecturerId", lecturerId);

            subjects = query.getResultList();
            System.out.println(subjects);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return subjects;
    }

    @Override
    public List<Subject> getSubjectByStudentId(String studentId) {
        Session s = this.factory.getObject().getCurrentSession();
        List<Subject> subjects = new ArrayList<>();
        try {
            String sql = "SELECT subject.name\n"
                    + "FROM subject join student_subject on subject.id = student_subject.subject_id\n"
                    + "join student on student.id = student_subject.student_id\n"
                    + "where student.id = :studentId";
            Query query = s.createNativeQuery(sql);
            query.setParameter("studentId", studentId);

            subjects = query.getResultList();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return subjects;
    }

    @Override
    public List<Subject> getSubjectByStudentAndSemesterId(String studentId, String semesterId) {
        Session s = this.factory.getObject().getCurrentSession();

        List<Subject> subjects = new ArrayList<>();
        try {
            String sql = "SELECT distinct subject.id, subject.name, subject.credit, subject.faculty_id\n"
                    + "FROM subject join student_subject on student_subject.subject_id = subject.id\n"
                    + "join student on student.id = student_subject.student_id\n"
                    + "join score on score.student_subject_id = student_subject.id\n"
                    + "join semester on score.semester_id = semester.id\n"
                    + "where student.id = :studentId and semester.id = :semesterId";
            Query query = s.createNativeQuery(sql);
            query.setParameter("studentId", studentId);
            query.setParameter("semesterId", semesterId);

            List<Object[]> objects = query.getResultList();
            for (int i = 0; i < objects.size(); i++) {
                Subject subject = new Subject();
                Faculty faculty = this.facRepo.getFacultyById(objects.get(i)[3].toString());
                subject.setId(objects.get(i)[0].toString());
                subject.setName(objects.get(i)[1].toString());
                subject.setCredit(Integer.parseInt(objects.get(i)[2].toString()));
                subject.setFacultyId(faculty);
                subjects.add(subject);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return subjects;
    }

}
