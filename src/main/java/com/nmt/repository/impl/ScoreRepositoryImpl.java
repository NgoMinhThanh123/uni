/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmt.repository.impl;

import com.nmt.model.Score;
import com.nmt.model.ScoreColumn;
import com.nmt.model.ScoreValue;
import com.nmt.repository.ScoreRepository;
import dto.ScoreDto;
import dto.ScoreListDto;
import dto.StudentDto;
import dto.StudentScoreDTO;
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
public class ScoreRepositoryImpl implements ScoreRepository {

    @Autowired
    private LocalSessionFactoryBean factory;
    @Autowired
    private Environment env;

    @Override
    public List<Score> getScores(Map<String, String> params) {
        Session s = this.factory.getObject().getCurrentSession();
        CriteriaBuilder b = s.getCriteriaBuilder();
        CriteriaQuery<Score> q = b.createQuery(Score.class);
        Root root = q.from(Score.class);
        q.select(root);

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
    public boolean addOrUpdateScore(Score u) {
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
    public Score getScoreById(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        return s.get(Score.class, id);
    }

    @Override
    public boolean deleteScore(int id) {
        Session s = this.factory.getObject().getCurrentSession();
        try {
            Score t = this.getScoreById(id);
            s.delete(t);
            return true;
        } catch (HibernateException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public int countScores() {
        Session s = this.factory.getObject().getCurrentSession();
        Query q = s.createQuery("SELECT COUNT(*) FROM Score");

        return Integer.parseInt(q.getSingleResult().toString());
    }

    @Override
    public List<StudentScoreDTO> getStudentScores(String lecturerId, String semesterId, String subjectId) {
        Session s = this.factory.getObject().getCurrentSession();
        List<Object[]> objects = new ArrayList<>();
        List<StudentScoreDTO> studentScoreDTOs = new ArrayList<>();

        try {
            String sql = "SELECT student.id, student.name AS student_name, semester.name AS semester_name, semester.school_year, subject.name AS subject_name, score_value.value, score_column.name AS score_column_name\n"
                    + "FROM score\n"
                    + "join student_subject on student_subject.id = score.student_subject_id\n"
                    + "join student on student_subject.student_id = student.id\n"
                    + "join subject on student_subject.subject_id = subject.id\n"
                    + "join semester on score.semester_id = semester.id\n"
                    + "join lecturer_subject on lecturer_subject.subject_id = subject.id\n"
                    + "join lecturer on lecturer_subject.lecturer_id = lecturer.id\n"
                    + "join score_value on score_value.score_id = score.id\n"
                    + "join score_column on score_value.score_column_id = score_column.id\n"
                    + "where lecturer.id = :lecturerId and semester.id = :semesterId and subject.id = :subjectId";
            Query query = s.createNativeQuery(sql);
            query.setParameter("lecturerId", lecturerId);
            query.setParameter("semesterId", semesterId);
            query.setParameter("subjectId", subjectId);

            objects = query.getResultList();
            for (int i = 0; i < objects.size(); i++) {
                StudentScoreDTO studentScoreDTO = new StudentScoreDTO();
                studentScoreDTO.setStudentId(objects.get(i)[0].toString());
                studentScoreDTO.setStudentName(objects.get(i)[1].toString());
                studentScoreDTO.setSemesterName(objects.get(i)[2].toString());
                studentScoreDTO.setSchoolYear(objects.get(i)[3].toString());
                studentScoreDTO.setSubjectName(objects.get(i)[4].toString());
                studentScoreDTO.setScoreValue(Double.parseDouble(objects.get(i)[5].toString()));
                studentScoreDTO.setScoreColumnName(objects.get(i)[6].toString());
                studentScoreDTOs.add(studentScoreDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentScoreDTOs;
    }

    @Override
    public List<StudentScoreDTO> getListScoresExport(String subjectId, String semesterId) {
        Session s = this.factory.getObject().getCurrentSession();
        List<Object[]> objects = new ArrayList<>();
        List<StudentScoreDTO> studentScoreDTOs = new ArrayList<>();

        try {
            String sql = "SELECT student.id, student.name AS student_name, semester.name AS semester_name, semester.school_year, subject.name AS subject_name, score_value.value, score_column.name AS score_column_name\n"
                    + "FROM score\n"
                    + "join student_subject on score.student_subject_id = student_subject.id\n"
                    + "join subject on student_subject.subject_id = subject.id\n"
                    + "join semester on score.semester_id = semester.id\n"
                    + "join student on student_subject.student_id = student.id\n"
                    + "join lecturer_subject on lecturer_subject.subject_id = subject.id\n"
                    + "join lecturer on lecturer_subject.lecturer_id = lecturer.id\n"
                    + "join score_value on score_value.score_id = score.id\n"
                    + "join score_column on score_value.score_column_id = score_column.id\n"
                    + "where semester.id = :semesterId and subject.id = :subjectId";
            Query query = s.createNativeQuery(sql);
            query.setParameter("semesterId", semesterId);
            query.setParameter("subjectId", subjectId);

            objects = query.getResultList();
            for (int i = 0; i < objects.size(); i++) {
                StudentScoreDTO studentScoreDTO = new StudentScoreDTO();
                studentScoreDTO.setStudentId(objects.get(i)[0].toString());
                studentScoreDTO.setStudentName(objects.get(i)[1].toString());
                studentScoreDTO.setSemesterName(objects.get(i)[2].toString());
                studentScoreDTO.setSchoolYear(objects.get(i)[3].toString());
                studentScoreDTO.setSubjectName(objects.get(i)[4].toString());
                studentScoreDTO.setScoreValue(Double.parseDouble(objects.get(i)[5].toString()));
                studentScoreDTO.setScoreColumnName(objects.get(i)[6].toString());
                studentScoreDTOs.add(studentScoreDTO);
            }
            System.out.println(studentScoreDTOs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentScoreDTOs;
    }

    @Override
    public List<ScoreDto> getScoreByStudentId(String studentId, String subjectId, String semesterId) {
        Session s = this.factory.getObject().getCurrentSession();
        List<Object[]> objects = new ArrayList<>();
        List<ScoreDto> scoreDTOs = new ArrayList<>();

        try {
            String sql = "SELECT score_column.name AS column_name, score_value.value as score\n"
                    + "FROM score_column\n"
                    + "left join score_value on score_value.score_column_id = score_column.id\n"
                    + "left join score on score_value.score_id = score.id\n"
                    + "join student_subject on score.student_subject_id = student_subject.id\n"
                    + "join subject on student_subject.subject_id = subject.id\n"
                    + "join semester on score.semester_id = semester.id\n"
                    + "join student on student_subject.student_id = student.id\n"
                    + "join lecturer_subject on lecturer_subject.subject_id = subject.id\n"
                    + "join lecturer on lecturer_subject.lecturer_id = lecturer.id\n"
                    + "where student.id = :studentId and subject.id = :subjectId and semester.id = :semesterId \n"
                    + "group by score_column.name, score_value.value";
            Query query = s.createNativeQuery(sql);
            query.setParameter("studentId", studentId);
            query.setParameter("subjectId", subjectId);
            query.setParameter("semesterId", semesterId);

            objects = query.getResultList();
            for (int i = 0; i < objects.size(); i++) {
                ScoreDto scoreDto = new ScoreDto();
                scoreDto.setScoreColumnName(objects.get(i)[0].toString());
                scoreDto.setScoreValue(Double.parseDouble(objects.get(i)[1].toString()));

                scoreDTOs.add(scoreDto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return scoreDTOs;
    }

    @Override
    public Score addScore(Score score) {
        Session s = this.factory.getObject().getCurrentSession();
        s.save(score);

        return score;
    }

}
