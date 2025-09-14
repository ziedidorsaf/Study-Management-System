package com.enit.service;

import java.util.List;

import jakarta.ejb.Local;

import com.enit.entities.Student;

@Local
public interface ILocalStudent {
	boolean authenticate(String username, String password);

    void createStudent(Student student);

    Student findStudentById(String idStudent);

 
    List<Student> findAllStudents();

  
    void updateStudent(Student student);
    
    
    Student findByLogin(String login);

    
    void deleteStudent(String idStudent);

}
