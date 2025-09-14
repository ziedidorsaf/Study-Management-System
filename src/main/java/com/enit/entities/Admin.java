package com.enit.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "Admin") // Optionnel : spécifie le nom de la table
public class Admin implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private int idAdmin;
    private String username;
    private String password;

    // Constructeurs
    public Admin() {}

    public Admin(int idAdmin, String username, String password) {
        this.idAdmin = idAdmin;
        this.username = username;
        this.password = password;
    }

    // Getters et Setters
    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin [idAdmin=" + idAdmin + ", username=" + username + ", password=" + password + "]";
    }
}