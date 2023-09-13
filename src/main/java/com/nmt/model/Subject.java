/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author acer
 */
@Entity
@Table(name = "subject")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Subject.findAll", query = "SELECT s FROM Subject s"),
    @NamedQuery(name = "Subject.findById", query = "SELECT s FROM Subject s WHERE s.id = :id"),
    @NamedQuery(name = "Subject.findByName", query = "SELECT s FROM Subject s WHERE s.name = :name"),
    @NamedQuery(name = "Subject.findByCredit", query = "SELECT s FROM Subject s WHERE s.credit = :credit")})
public class Subject implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull(message = "{subject.id.notNullMsg}")
    @Size(min = 1, max = 12, message = "{subject.id.lenErrMsg}")
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull(message = "{subject.name.notNullMsg}")
    @Size(min = 1, max = 45, message = "{subject.name.lenErrMsg}")
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull(message = "{subject.credit.notNullMsg}")
    @Column(name = "credit")
    private int credit;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subjectId")
    @JsonIgnore
    private Set<SubjectSemester> subjectSemesterSet;
    @JoinColumn(name = "faculty_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Faculty facultyId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subjectId")
    @JsonIgnore
    private Set<LecturerSubject> lecturerSubjectSet;
    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subjectId")
    private Set<StudentSubject> studentSubjectSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subjectId")
    @JsonIgnore
    private Set<ClassesSubject> classesSubjectSet;

    public Subject() {
    }

    public Subject(String id) {
        this.id = id;
    }

    public Subject(String id, String name, int credit) {
        this.id = id;
        this.name = name;
        this.credit = credit;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    @XmlTransient
    public Set<SubjectSemester> getSubjectSemesterSet() {
        return subjectSemesterSet;
    }

    public void setSubjectSemesterSet(Set<SubjectSemester> subjectSemesterSet) {
        this.subjectSemesterSet = subjectSemesterSet;
    }

    public Faculty getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Faculty facultyId) {
        this.facultyId = facultyId;
    }
    @XmlTransient
    public Set<LecturerSubject> getLecturerSubjectSet() {
        return lecturerSubjectSet;
    }

    public void setLecturerSubjectSet(Set<LecturerSubject> lecturerSubjectSet) {
        this.lecturerSubjectSet = lecturerSubjectSet;
    }

    @XmlTransient
    public Set<StudentSubject> getStudentSubjectSet() {
        return studentSubjectSet;
    }

    public void setStudentSubjectSet(Set<StudentSubject> studentSubjectSet) {
        this.studentSubjectSet = studentSubjectSet;
    }

    @XmlTransient
    public Set<ClassesSubject> getClassesSubjectSet() {
        return classesSubjectSet;
    }

    public void setClassesSubjectSet(Set<ClassesSubject> classesSubjectSet) {
        this.classesSubjectSet = classesSubjectSet;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subject)) {
            return false;
        }
        Subject other = (Subject) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nmt.model.Subject[ id=" + id + " ]";
    }
    
}
