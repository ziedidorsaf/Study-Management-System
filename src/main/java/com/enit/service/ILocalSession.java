package com.enit.service;

import java.util.Date;
import java.util.List;

import jakarta.ejb.Local;

import com.enit.entities.Session;
import com.enit.entities.SessionPK;

@Local
public interface ILocalSession {

	  void createSession(Session session);

	    
	    Session findSessionById(SessionPK sessionPK);

	    
	    List<Session> findAllSessions();

	   
	    void updateSession(Session session);

	    
	    void deleteSession(SessionPK sessionPK);

	    
	    List<Session> findSessionsByStudentId(String idStudent);

	    
	    List<Session> findSessionsByExamId(int idExam);

	    
	    List<Session> findSessionsByDate(Date sessionDate);
	    
	    
	 // Ajouter une méthode pour rechercher des sessions par note
	    List<Session> findSessionsByGrade(float grade);

	    // Ajouter une méthode pour mettre à jour la note d'une session
	    void updateSessionGrade(SessionPK sessionPK, float grade);
}
