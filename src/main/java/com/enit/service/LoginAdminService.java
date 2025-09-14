package com.enit.service;

import com.enit.entities.Admin;
import jakarta.ejb.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

@Singleton
public class LoginAdminService {

    @PersistenceContext(unitName = "UP_COURS")
    private EntityManager entityManager;

    public Admin authenticate(String username, String password) {
        // Requête JPQL mise à jour pour utiliser "username" au lieu de "login"
        TypedQuery<Admin> query = entityManager.createQuery(
            "SELECT a FROM Admin a WHERE a.username = :username AND a.password = :password", Admin.class);
        query.setParameter("username", username); // Utilisation de "username"
        query.setParameter("password", password);

        try {
            return query.getSingleResult(); // Retourne l'admin si trouvé
        } catch (Exception e) {
            return null; // Retourne null si aucun admin n'est trouvé
        }
    }
}