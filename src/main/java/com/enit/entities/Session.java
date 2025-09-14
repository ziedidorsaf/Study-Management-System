package com.enit.entities;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Session") // Optionnel : spécifie le nom de la table
public class Session implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private SessionPK sessionPK;

    @ManyToOne
    @MapsId("idStudent")
    @JoinColumn(name = "idStudent", nullable = false)
    private Student student;

    @ManyToOne
    @MapsId("idExam")
    @JoinColumn(name = "idExam", nullable = false)
    private Exam exam;

    @Temporal(TemporalType.TIMESTAMP)
    private Date sessionDate;
    
    private float grade;

    // Constructeurs
    public Session() {}

    public Session(SessionPK sessionPK, Student student, Exam exam, Date sessionDate , float grade) {
        this.sessionPK = sessionPK;
        this.student = student;
        this.exam = exam;
        this.sessionDate = sessionDate;
        this.grade = grade;
    }

    // Getters et Setters
    public SessionPK getSessionPK() {
        return sessionPK;
    }

    public void setSessionPK(SessionPK sessionPK) {
        this.sessionPK = sessionPK;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }

    public Date getSessionDate() {
        return sessionDate;
    }

    public void setSessionDate(Date sessionDate) {
        this.sessionDate = sessionDate;
    }
    
    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Session [sessionPK=" + sessionPK + ", sessionDate=" + sessionDate +", grade=" + grade + "]";
    }
}