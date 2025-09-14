package com.enit.entities;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Student") // Optionnel : spécifie le nom de la table
public class Student implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String idStudent;

    private String name;

    private String email;

    private String password;
    // Constructeurs
    public Student() {}

    public Student(String idStudent, String name, String email, String password) {
        this.idStudent = idStudent;
        this.name = name;
        this.email = email;
        this.password = password;
    }

    // Getters et Setters
    public String getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(String idStudent) {
        this.idStudent = idStudent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Student [idStudent=" + idStudent + ", name=" + name + ", email=" + email + ", password=" + password + "]";
    }
}