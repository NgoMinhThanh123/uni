/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmt.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author acer
 */
@Entity
@Table(name = "semester")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Semester.findAll", query = "SELECT s FROM Semester s"),
    @NamedQuery(name = "Semester.findById", query = "SELECT s FROM Semester s WHERE s.id = :id"),
    @NamedQuery(name = "Semester.findBySchoolYear", query = "SELECT s FROM Semester s WHERE s.schoolYear = :schoolYear"),
    @NamedQuery(name = "Semester.findByFromDate", query = "SELECT s FROM Semester s WHERE s.fromDate = :fromDate"),
    @NamedQuery(name = "Semester.findByToDate", query = "SELECT s FROM Semester s WHERE s.toDate = :toDate")})
public class Semester implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotBlank
    @NotNull()
    @Size(min = 1, max = 5)
    @Column(name = "id")
    private String id;
    
    @Basic(optional = false)
    @NotNull(message = "{semester.name.notNullMsg}")
    @Size(min = 1, max = 10, message = "{semester.name.lenErrMsg}")
    @Column(name = "name")
    private String name;
    
    @Basic(optional = false)
    @NotNull(message = "{semester.schoolYear.notNullMsg}")
    @Column(name = "school_year")
    private int schoolYear;
    @Basic(optional = false)
    @NotNull(message = "{semester.fromDate.notNullMsg}")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "from_date")
    @Temporal(TemporalType.DATE)
    private Date fromDate;
    @Basic(optional = false)
    @NotNull(message = "{semester.toDate.notNullMsg}")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "to_date")
    @Temporal(TemporalType.DATE)
    private Date toDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "semesterId")
    private Set<SubjectSemester> subjectSemesterSet;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "semesterId")
    private Set<Score> scoreSet;

    public Semester() {
    }

    public Semester(String id) {
        this.id = id;
    }

    public Semester(String id, String name, int schoolYear, Date fromDate, Date toDate) {
        this.id = id;
        this.name = name;
        this.schoolYear = schoolYear;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSchoolYear() {
        return schoolYear;
    }

    public void setSchoolYear(int schoolYear) {
        this.schoolYear = schoolYear;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    @XmlTransient
    public Set<SubjectSemester> getSubjectSemesterSet() {
        return subjectSemesterSet;
    }

    public void setSubjectSemesterSet(Set<SubjectSemester> subjectSemesterSet) {
        this.subjectSemesterSet = subjectSemesterSet;
    }

    @XmlTransient
    public Set<Score> getScoreSet() {
        return scoreSet;
    }

    public void setScoreSet(Set<Score> scoreSet) {
        this.scoreSet = scoreSet;
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
        if (!(object instanceof Semester)) {
            return false;
        }
        Semester other = (Semester) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nmt.model.Semester[ id=" + id + " ]";
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    
}
