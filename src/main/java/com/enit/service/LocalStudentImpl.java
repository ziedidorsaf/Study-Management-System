package com.enit.service;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import com.enit.entities.Student;
import com.enit.service.ILocalStudent;

@Stateless
public class LocalStudentImpl implements ILocalStudent {

    @PersistenceContext(unitName = "UP_COURS") // Remplacez par le nom de votre unité de persistance
    private EntityManager entityManager;

    @Override
    public boolean authenticate(String username, String password) {
        // Crée une requête JPQL pour vérifier les informations d'authentification
        TypedQuery<Student> query = entityManager.createQuery(
            "SELECT s FROM Student s WHERE s.username = :username AND s.password = :password", Student.class);
        query.setParameter("username", username);
        query.setParameter("password", password);

        try {
            Student student = query.getSingleResult(); // Tente de récupérer un étudiant
            return student != null; // Retourne true si un étudiant est trouvé
        } catch (Exception e) {
            return false; // Retourne false si aucune correspondance n'est trouvée
        }
    }

    @Override
    public void createStudent(Student student) {
        entityManager.persist(student); // Persiste l'étudiant dans la base de données
    }

    @Override
    public Student findStudentById(String idStudent) {
        return entityManager.find(Student.class, idStudent); // Recherche un étudiant par son ID
    }

    @Override
    public List<Student> findAllStudents() {
        // Crée une requête JPQL pour récupérer tous les étudiants
        TypedQuery<Student> query = entityManager.createQuery("SELECT s FROM Student s", Student.class);
        return query.getResultList(); // Retourne la liste des étudiants
    }

    @Override
    public void updateStudent(Student student) {
        entityManager.merge(student); // Met à jour l'étudiant dans la base de données
    }

    @Override
    public Student findByLogin(String login) {
        // Crée une requête JPQL pour rechercher un étudiant par son login
        TypedQuery<Student> query = entityManager.createQuery(
            "SELECT s FROM Student s WHERE s.username = :login", Student.class);
        query.setParameter("login", login);

        try {
            return query.getSingleResult(); // Retourne l'étudiant trouvé
        } catch (Exception e) {
            return null; // Retourne null si aucun étudiant n'est trouvé
        }
    }

    @Override
    public void deleteStudent(String idStudent) {
        if (idStudent == null || idStudent.isEmpty()) {
            throw new IllegalArgumentException("ID étudiant invalide.");
        }

        Student student = entityManager.find(Student.class, idStudent);
        if (student == null) {
            throw new IllegalArgumentException("Étudiant non trouvé avec l'ID: " + idStudent);
        }

        entityManager.remove(student);
    }
}