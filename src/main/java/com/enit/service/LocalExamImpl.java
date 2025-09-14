package com.enit.service;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import com.enit.entities.Exam;

@Stateless
public class LocalExamImpl implements ILocalExam {

    @PersistenceContext(unitName = "UP_COURS") 
    private EntityManager entityManager;

    @Override
    public void createExam(Exam exam) {
        entityManager.persist(exam); // Persiste l'examen dans la base de données
    }

    @Override
    public Exam findExamById(int idExam) {
        return entityManager.find(Exam.class, idExam); // Recherche un examen par son ID
    }

    @Override
    public List<Exam> findAllExams() {
        // Crée une requête JPQL pour récupérer tous les examens
        TypedQuery<Exam> query = entityManager.createQuery("SELECT e FROM Exam e", Exam.class);
        return query.getResultList(); // Retourne la liste des examens
    }

    @Override
    public void updateExam(Exam exam) {
        entityManager.merge(exam); // Met à jour l'examen dans la base de données
    }

    @Override
    public void deleteExam(int idExam) {
        Exam exam = entityManager.find(Exam.class, idExam); // Recherche l'examen par son ID
        if (exam != null) {
            entityManager.remove(exam); // Supprime l'examen s'il existe
        }
    }
    
    
    
    
    @Override
    public List<Exam> findExamsBySpecialityId(int specialityId) {
        // Crée une requête JPQL pour récupérer les examens par ID de spécialité
        TypedQuery<Exam> query = entityManager.createQuery(
            "SELECT e FROM Exam e WHERE e.speciality.idSpeciality = :specialityId", Exam.class);
        query.setParameter("specialityId", specialityId);
        return query.getResultList();
    }
}