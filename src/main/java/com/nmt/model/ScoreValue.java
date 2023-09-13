/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nmt.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author acer
 */
@Entity
@Table(name = "score_value")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ScoreValue.findAll", query = "SELECT s FROM ScoreValue s"),
    @NamedQuery(name = "ScoreValue.findById", query = "SELECT s FROM ScoreValue s WHERE s.id = :id"),
    @NamedQuery(name = "ScoreValue.findByValue", query = "SELECT s FROM ScoreValue s WHERE s.value = :value")})
public class ScoreValue implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "value")
    private double value;
    @JoinColumn(name = "score_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Score scoreId;
    @JoinColumn(name = "score_column_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private ScoreColumn scoreColumnId;

    public ScoreValue() {
    }

    public ScoreValue(Integer id) {
        this.id = id;
    }

    public ScoreValue(Integer id, double value) {
        this.id = id;
        this.value = value;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Score getScoreId() {
        return scoreId;
    }

    public void setScoreId(Score scoreId) {
        this.scoreId = scoreId;
    }

    public ScoreColumn getScoreColumnId() {
        return scoreColumnId;
    }

    public void setScoreColumnId(ScoreColumn scoreColumnId) {
        this.scoreColumnId = scoreColumnId;
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
        if (!(object instanceof ScoreValue)) {
            return false;
        }
        ScoreValue other = (ScoreValue) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.nmt.model.ScoreValue[ id=" + id + " ]";
    }
    
}
