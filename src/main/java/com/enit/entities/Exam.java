package com.enit.entities;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Exam") // Optionnel : spécifie le nom de la table
public class Exam implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idExam;

    private String name;

    @ManyToOne
    @JoinColumn(name = "idSpeciality", nullable = false)
    private Speciality speciality;

    // Constructeurs
    public Exam() {}

    public Exam(String name, Speciality speciality) {
        this.name = name;
        this.speciality = speciality;
    }

    // Getters et Setters
    public int getIdExam() {
        return idExam;
    }

    public void setIdExam(int idExam) {
        this.idExam = idExam;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    @Override
    public String toString() {
        return "Exam [idExam=" + idExam + ", name=" + name + ", speciality=" + speciality + "]";
    }
}