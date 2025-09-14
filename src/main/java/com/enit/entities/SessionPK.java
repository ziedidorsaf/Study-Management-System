package com.enit.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class SessionPK implements Serializable {
    private static final long serialVersionUID = 1L;

    @Column(name = "idStudent")
    private String idStudent;

    @Column(name = "idExam")
    private int idExam;

    // Constructeurs
    public SessionPK() {}

    public SessionPK(String idStudent, int idExam) {
        this.idStudent = idStudent;
        this.idExam = idExam;
    }

    // Getters et Setters
    public String getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }

    public int getIdExam() {
        return idExam;
    }

    public void setIdExam(int idExam) {
        this.idExam = idExam;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((idStudent == null) ? 0 : idStudent.hashCode());
        result = prime * result + idExam;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        SessionPK other = (SessionPK) obj;
        return idExam == other.idExam && idStudent.equals(other.idStudent);
    }

    @Override
    public String toString() {
        return "SessionPK [idStudent=" + idStudent + ", idExam=" + idExam + "]";
    }
}