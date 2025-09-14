package com.enit.service;
import com.enit.entities.Exam ;

import java.util.Date;
import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;

import com.enit.entities.Session;
import com.enit.entities.SessionPK;
import com.enit.service.ILocalSession;

@Stateless
public class LocalSessionImpl implements ILocalSession {

    @PersistenceContext(unitName = "UP_COURS") // Remplacez par le nom de votre unité de persistance
    private EntityManager entityManager;

    @Override
    public void createSession(Session session) {
        if (session.getExam() != null) {
            // Reattach the Exam entity to the current session
            Exam managedExam = entityManager.merge(session.getExam());
            session.setExam(managedExam);
        }
        entityManager.persist(session);
    }

    @Override
    public Session findSessionById(SessionPK sessionPK) {
        return entityManager.find(Session.class, sessionPK); // Recherche une session par sa clé primaire
    }

    @Override
    public List<Session> findAllSessions() {
        // Crée une requête JPQL pour récupérer toutes les sessions
        TypedQuery<Session> query = entityManager.createQuery("SELECT s FROM Session s", Session.class);
        return query.getResultList(); // Retourne la liste des sessions
    }

    @Override
    public void updateSession(Session session) {
        entityManager.merge(session); // Met à jour la session dans la base de données
    }

    @Override
    public void deleteSession(SessionPK sessionPK) {
        Session session = entityManager.find(Session.class, sessionPK); // Recherche la session par sa clé primaire
        if (session != null) {
            entityManager.remove(session); // Supprime la session si elle existe
        }
    }

    @Override
    public List<Session> findSessionsByStudentId(String idStudent) {
        // Crée une requête JPQL pour récupérer les sessions par ID étudiant
        TypedQuery<Session> query = entityManager.createQuery(
            "SELECT s FROM Session s WHERE s.sessionPK.idStudent = :idStudent", Session.class);
        query.setParameter("idStudent", idStudent);
        return query.getResultList(); // Retourne la liste des sessions pour l'étudiant donné
    }

    @Override
    public List<Session> findSessionsByExamId(int idExam) {
        // Crée une requête JPQL pour récupérer les sessions par ID examen
        TypedQuery<Session> query = entityManager.createQuery(
            "SELECT s FROM Session s WHERE s.sessionPK.idExam = :idExam", Session.class);
        query.setParameter("idExam", idExam);
        return query.getResultList(); // Retourne la liste des sessions pour l'examen donné
    }

    @Override
    public List<Session> findSessionsByDate(Date sessionDate) {
        // Crée une requête JPQL pour récupérer les sessions par date
        TypedQuery<Session> query = entityManager.createQuery(
            "SELECT s FROM Session s WHERE s.sessionDate = :sessionDate", Session.class);
        query.setParameter("sessionDate", sessionDate);
        return query.getResultList(); // Retourne la liste des sessions pour la date donnée
    }
    
    
   
    
    @Override
    public List<Session> findSessionsByGrade(float grade) {
        // Crée une requête JPQL pour récupérer les sessions par note
        TypedQuery<Session> query = entityManager.createQuery(
            "SELECT s FROM Session s WHERE s.grade = :grade", Session.class);
        query.setParameter("grade", grade);
        return query.getResultList(); // Retourne la liste des sessions pour la note donnée
    }

    @Override
    public void updateSessionGrade(SessionPK sessionPK, float grade) {
        // Recherche la session par sa clé primaire
        Session session = entityManager.find(Session.class, sessionPK);
        if (session != null) {
            // Met à jour la note de la session
            session.setGrade(grade);
            entityManager.merge(session); // Sauvegarde les modifications
        }
    }

}