package com.nmt.repository.impl;

import com.nmt.model.Classes;
import com.nmt.model.Faculty;
import com.nmt.model.Lecturer;
import com.nmt.model.Major;
import com.nmt.model.Semester;
import com.nmt.model.Student;
import com.nmt.model.Subject;
import com.nmt.model.User;
import com.nmt.repository.ClassesRepository;
import com.nmt.repository.FacultyRepository;
import com.nmt.repository.LecturerRepository;
import com.nmt.repository.MajorRepository;
import com.nmt.repository.ScoreRepository;
import com.nmt.repository.SemesterRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.nmt.repository.StudentRepository;
import com.nmt.repository.SubjectRepository;
import com.nmt.repository.UserRepository;
import java.util.Date;
import javax.persistence.NoResultException;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.hibernate.HibernateException;

/**
 *
 * @author admin
 */
@Repository
@PropertySource("classpath:configs.properties")
@Transactional
public class StudentRepositoryImpl implements StudentRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private ClassesRepository classesRepo;
    @Autowired
    private FacultyRepository falRepo;
    @Autowired
    private MajorRepository majorRepo;
    @Autowired
    private LecturerRepository lecturerRepo;
    @Autowired
    private SubjectRepository subjectRepo;
    @Autowired
    private SemesterRepository semesterRepo;

    @Override
    public List<Student> getStudents(Map<String, String> params) {

        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Student> q = b.createQuery(Student.class);
        Root root = q.from(Student.class);
        q.select(root);

        if (params != null) {
            List<Predicate> predicates = new ArrayList<>();

            String kw = params.get("kw");
            if (kw != null && !kw.isEmpty()) {
                predicates.add(b.like(root.get("name"), String.format("%%%s%%", kw)));
            }

            String mssv = params.get("mssv");
            if (kw != null && !kw.isEmpty()) {
                predicates.add(b.like(root.get("id"), String.format("%%%s%%", mssv)));
            }

            q.where(predicates.toArray(Predicate[]::new));
        }

        q.orderBy(b.desc(root.get("id")));

        Query query = s.createQuery(q);

        if (params != null) {
            String page = params.get("page");
            int pageSize = Integer.parseInt(this.env.getProperty("PAGE_SIZE"));
            if (page != null) {
                query.setFirstResult((Integer.parseInt(page) - 1) * pageSize);
                query.setMaxResults(pageSize);
            }
        }

        return query.getResultList();
    }

    @Override
    public boolean addStudent(Student c) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            s.save(c);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateStudent(Student c) {
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
    public Student getStudentById(String id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Student.class, id);
    }

    @Override
    public boolean deleteStudent(String id) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            Student t = this.getStudentById(id);
            s.delete(t);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public Student getStudentByUsername(String username) {
        Session s = this.factory.getObject().getCurrentSession();
        Student student = null;

        try {
            String sql = "SELECT student.* " +
                    "FROM student " +
                    "JOIN user ON student.user_id = user.id " +
                    "WHERE user.username = :username";

            Query query = s.createNativeQuery(sql, Student.class);
            query.setParameter("username", username);

            student = (Student) query.getSingleResult();
        } catch (NoResultException e) {
            // Xử lý khi không tìm thấy kết quả nếu cần
        } catch (Exception e) {
            e.printStackTrace();
        }

        return student;
//        Session s = this.factory.getObject().getCurrentSession();
//        try {
//            String hql = "FROM Student s WHERE s.userId.username = :username";
//            Student student = s.createQuery(hql, Student.class)
//                    .setParameter("username", username)
//                    .uniqueResult();
//            return student;
//        } catch (NoResultException e) {
//            return null;
//        }
    }

    @Override
    public List<Student> getListStudent(String lecturerId, String subjectId, String semesterId) {
        Session s = this.factory.getObject().getCurrentSession();
        List<Student> students = new ArrayList<>();
        List<Object[]> objects = new ArrayList<>();

        try {
            String sql = "SELECT distinct student.* \n"
                    + "FROM score\n"
                    + "join student_subject on score.student_subject_id = student_subject.id\n"
                    + "JOIN subject ON student_subject.subject_id = subject.id\n"
                    + "JOIN semester ON score.semester_id = semester.id\n"
                    + "JOIN student ON student_subject.student_id = student.id\n"
                    + "JOIN lecturer_subject ON lecturer_subject.subject_id = subject.id\n"
                    + "JOIN lecturer ON lecturer_subject.lecturer_id = lecturer.id\n"
                    + "LEFT JOIN score_value ON score_value.score_id = score.id\n"
                    + "LEFT JOIN score_column ON score_value.score_column_id = score_column.id\n"
                    + "WHERE lecturer.id = :lecturerId AND subject.id = :subjectId AND semester.id = :semesterId";

            Query query = s.createNativeQuery(sql);
            query.setParameter("lecturerId", lecturerId);
            query.setParameter("subjectId", subjectId);
            query.setParameter("semesterId", semesterId);

            objects = query.getResultList();
            for (int i = 0; i < objects.size(); i++) {
                Student student = new Student();
                User u = this.userRepo.getUserById(Integer.parseInt(objects.get(i)[6].toString()));
                Classes c = this.classesRepo.getClassById(objects.get(i)[7].toString());
                Faculty f = this.falRepo.getFacultyById(objects.get(i)[8].toString());
                Major m = this.majorRepo.getMajorById(objects.get(i)[9].toString());
                student.setId(objects.get(i)[0].toString());
                student.setName(objects.get(i)[1].toString());
                student.setBirthday((Date) objects.get(i)[2]);
                student.setGender(Short.parseShort(objects.get(i)[3].toString()));
                student.setPhone(objects.get(i)[4].toString());
                student.setAddress(objects.get(i)[5].toString());
                student.setUserId(u);
                student.setClassesId(c);
                student.setFacultyId(f);
                student.setMajorId(m);

                students.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return students;
    }

    @Override
    public int countStudents() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT COUNT(*) FROM Student");

        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public List<String> getAllMailOfStudent(String lecturerId, String subjectId, String semesterId) {
        Session s = this.factory.getObject().getCurrentSession();
        Lecturer lecturer = this.lecturerRepo.getLecturerById(lecturerId);
        Subject subject = this.subjectRepo.getSubjectById(subjectId);
        Semester semester = this.semesterRepo.getSemesterById(semesterId);

        Query q = s.createNativeQuery("SELECT user.email \n"
                + "FROM student \n"
                + "JOIN user ON student.user_id = user.id\n"
                + "join student_subject on student_subject.student_id = student.id\n"
                + "join subject on student_subject.subject_id = subject.id\n"
                + "join subject_semester on subject_semester.subject_id = subject.id\n"
                + "join semester on subject_semester.semester_id = semester.id\n"
                + "join lecturer_subject on lecturer_subject.subject_id = subject.id\n"
                + "join lecturer on lecturer_subject.lecturer_id = lecturer.id\n"
                + "where lecturer.id = :lecturerId and subject.id = :subjectId and semester.id = :semesterId");
        q.setParameter("lecturerId", lecturerId);
        q.setParameter("subjectId", subjectId);
        q.setParameter("semesterId", semesterId);
        return q.getResultList();
    }

    @Override
    public List<Student> getStudentByHomeroomTeacher(String lecturerId) {
        Session s = this.factory.getObject().getCurrentSession();
        List<Student> students = new ArrayList<>();
        List<Object[]> objects = new ArrayList<>();

        try {
            String sql = "SELECT student.* \n"
                    + "FROM student\n"
                    + "join classes on student.classes_id = classes.id\n"
                    + "join lecturer on classes.id = lecturer.classes_id\n"
                    + "where lecturer.id = :lecturerId";

            Query query = s.createNativeQuery(sql);
            query.setParameter("lecturerId", lecturerId);

            objects = query.getResultList();
            for (int i = 0; i < objects.size(); i++) {
                Student student = new Student();
                User u = this.userRepo.getUserById(Integer.parseInt(objects.get(i)[6].toString()));
                Classes c = this.classesRepo.getClassById(objects.get(i)[7].toString());
                Faculty f = this.falRepo.getFacultyById(objects.get(i)[8].toString());
                Major m = this.majorRepo.getMajorById(objects.get(i)[9].toString());
                student.setId(objects.get(i)[0].toString());
                student.setName(objects.get(i)[1].toString());
                student.setBirthday((Date) objects.get(i)[2]);
                student.setGender(Short.parseShort(objects.get(i)[3].toString()));
                student.setPhone(objects.get(i)[4].toString());
                student.setAddress(objects.get(i)[5].toString());
                student.setUserId(u);
                student.setClassesId(c);
                student.setFacultyId(f);
                student.setMajorId(m);

                students.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return students;
    }

}
