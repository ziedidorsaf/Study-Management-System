package com.enit.controller;

import com.enit.entities.Student;
import com.enit.service.ILocalStudent;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "StudentAdminController", urlPatterns = {"/admin/studentAdmin"})
public class StudentAdminController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private ILocalStudent studentService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                listStudents(request, response);
                break;
            case "add":
                showAddForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteStudent(request, response);
                break;
            default:
                listStudents(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            addStudent(request, response);
        } else if ("edit".equals(action)) {
            updateStudent(request, response);
        }
    }

    private void listStudents(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Student> students = studentService.findAllStudents();
        request.setAttribute("students", students); // Add the list of students to the request
        request.getRequestDispatcher("/Student-list.jsp").forward(request, response); // Forward to the JSP
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/studentForm.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        Student student = studentService.findStudentById(id);
        request.setAttribute("student", student);
        request.getRequestDispatcher("/studentForm.jsp").forward(request, response);
    }

    private void addStudent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStudent = request.getParameter("idStudent");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        
     // Check if the student ID already exists
        Student existingStudent = studentService.findStudentById(idStudent);
        if (existingStudent != null) {
            request.setAttribute("errorMessage", "Un étudiant avec cet ID existe déjà.");
            request.getRequestDispatcher("/studentForm.jsp").forward(request, response);
            return;
        }

        Student student = new Student(idStudent, name, email, password);
        studentService.createStudent(student);
        response.sendRedirect("studentAdmin?action=list");
    }

    private void updateStudent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStudent = request.getParameter("idStudent");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Student student = studentService.findStudentById(idStudent);
        student.setName(name);
        student.setEmail(email);
        student.setPassword(password);

        studentService.updateStudent(student);
        response.sendRedirect("studentAdmin?action=list");
    }

    private void deleteStudent(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStudent = request.getParameter("idStudent"); // Use idStudent instead of id

        // Validate input
        if (idStudent == null || idStudent.isEmpty()) {
            request.setAttribute("errorMessage", "ID étudiant invalide.");
            listStudents(request, response);
            return;
        }

        // Delete the student
        try {
            studentService.deleteStudent(idStudent);
        } catch (Exception e) {
            // Handle error: Student not found or other issues
            request.setAttribute("errorMessage", "Erreur lors de la suppression de l'étudiant: " + e.getMessage());
            listStudents(request, response);
            return;
        }

        response.sendRedirect("studentAdmin?action=list");
    }
}