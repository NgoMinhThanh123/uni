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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "lecturer")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lecturer.findAll", query = "SELECT l FROM Lecturer l"),
    @NamedQuery(name = "Lecturer.findById", query = "SELECT l FROM Lecturer l WHERE l.id = :id"),
    @NamedQuery(name = "Lecturer.findByName", query = "SELECT l FROM Lecturer l WHERE l.name = :name"),
    @NamedQuery(name = "Lecturer.findByBirthday", query = "SELECT l FROM Lecturer l WHERE l.birthday = :birthday"),
    @NamedQuery(name = "Lecturer.findByGender", query = "SELECT l FROM Lecturer l WHERE l.gender = :gender"),
    @NamedQuery(name = "Lecturer.findByPhone", query = "SELECT l FROM Lecturer l WHERE l.phone = :phone"),
    @NamedQuery(name = "Lecturer.findByAddress", query = "SELECT l FROM Lecturer l WHERE l.address = :address"),
    @NamedQuery(name = "Lecturer.findByEmail", query = "SELECT l FROM Lecturer l WHERE l.email = :email")})
public class Lecturer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull(message = "{lecturer.id.notNullMsg}")
    @Size(max = 10, message = "{lecturer.id.lenErrMsg}")
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull(message = "{lecturer.name.notNullMsg}")
    @Size(max = 100, message = "{lecturer.name.lenErrMsg}")
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull(message = "{lecturer.birthday.notNullMsg}")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "birthday")
    @Temporal(TemporalType.DATE)
    private Date birthday;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gender")
    private short gender;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull(message = "{lecturer.phone.lenErrMsg}")
    @Size(max = 10, message = "{lecturer.phone.lenErrMsg}")
    @Column(name = "phone")
    private String phone;
    @Basic(optional = false)
    @Size(min = 1, max = 255, message = "{lecturer.adress.lenErrMsg}")
    @Column(name = "address")
    private String address;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull(message = "{lecturer.email.notNullMsg}")
    @Size(min = 15, max = 50, message = "{lecturer.email.lenErrMsg}")
    @Column(name = "email")
    private String email;
    @JoinColumn(name = "faculty_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @JsonIgnore
    private Faculty facultyId;
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    @JsonIgnore
    private User userId;
    @JoinColumn(name = "classes_id", referencedColumnName = "id")
    @ManyToOne
    private Classes classesId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "lecturerId")
    private Set<LecturerSubject> lecturerSubjectSet;

    public Lecturer() {
    }

    public Lecturer(String id) {
        this.id = id;
    }

    public Lecturer(String id, String name, Date birthday, short gender, String phone, String address, String email) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.email = email;
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

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public short getGender() {
        return gender;
    }

    public void setGender(short gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Faculty getFacultyId() {
        return facultyId;
    }

    public void setFacultyId(Faculty facultyId) {
        this.facultyId = facultyId;
    }

    public User getUserId() {
        return userId;
    }

    public void setUserId(User userId) {
        this.userId = userId;
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
        if (!(object instanceof Lecturer)) {
            return false;
        }
        Lecturer other = (Lecturer) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nmt.model.Lecturer[ id=" + id + " ]";
    }

    public Classes getClassesId() {
        return classesId;
    }

    public void setClassesId(Classes classesId) {
        this.classesId = classesId;
    }

    @XmlTransient
    public Set<LecturerSubject> getLecturerSubjectSet() {
        return lecturerSubjectSet;
    }

    public void setLecturerSubjectSet(Set<LecturerSubject> lecturerSubjectSet) {
        this.lecturerSubjectSet = lecturerSubjectSet;
    }
    
}
