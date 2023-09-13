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
@Table(name = "classes")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Classes.findAll", query = "SELECT c FROM Classes c"),
    @NamedQuery(name = "Classes.findById", query = "SELECT c FROM Classes c WHERE c.id = :id")})
public class Classes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull(message = "{classes.id.notNullMsg}")
    @Size(min = 1, max = 8, message = "{classes.id.lenErrMsg}")
    @Column(name = "id")
    private String id;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classesId")
    @JsonIgnore
    private Set<Student> studentSet;
    @JoinColumn(name = "faculty_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Faculty facultyId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classId")
    @JsonIgnore
    private Set<ClassesSubject> classesSubjectSet;
    @JsonIgnore
    @OneToMany(mappedBy = "classesId")
    private Set<Lecturer> lecturerSet;

    public Classes() {
    }

    public Classes(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlTransient
    public Set<Student> getStudentSet() {
        return studentSet;
    }

    public void setStudentSet(Set<Student> studentSet) {
        this.studentSet = studentSet;
    }

    public Faculty getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Faculty facultyId) {
        this.facultyId = facultyId;
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
        if (!(object instanceof Classes)) {
            return false;
        }
        Classes other = (Classes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nmt.model.Classes[ id=" + id + " ]";
    }

    @XmlTransient
    public Set<Lecturer> getLecturerSet() {
        return lecturerSet;
    }

    public void setLecturerSet(Set<Lecturer> lecturerSet) {
        this.lecturerSet = lecturerSet;
    }
    
}
