package com.enit.service;

import java.util.List;

import jakarta.ejb.Local;

import com.enit.entities.Exam;

@Local
public interface ILocalExam {
	   void createExam(Exam exam);

	    
	    Exam findExamById(int idExam);

	   
	    List<Exam> findAllExams();

	    void updateExam(Exam exam);

	    void deleteExam(int idExam);
	    
	    List<Exam> findExamsBySpecialityId(int specialityId);
	

}
