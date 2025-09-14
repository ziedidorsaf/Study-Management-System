package com.enit.entities;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Speciality") // Optionnel : spécifie le nom de la table
public class Speciality implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSpeciality;

    private String nameS;

    // Constructeurs
    public Speciality() {}

    public Speciality(String name) {
        this.nameS = name;
    }

    // Getters et Setters
    public int getIdSpeciality() {
        return idSpeciality;
    }

    public void setIdSpeciality(int idSpeciality) {
        this.idSpeciality = idSpeciality;
    }

    public String getNameS() {
        return nameS;
    }

    public void setNameS(String name) {
        this.nameS = name;
    }

    @Override
    public String toString() {
        return "Speciality [idSpeciality=" + idSpeciality + ", name=" + nameS + "]";
    }
}