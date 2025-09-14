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

@WebServlet(name = "StudentServlet", urlPatterns = {"/student"})
public class StudentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private ILocalStudent studentService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set response encoding
        response.setContentType("text/html;charset=UTF-8");

        // Retrieve all students to display
        List<Student> students = studentService.findAllStudents();
        request.setAttribute("students", students);

        // Retrieve the student ID if provided
        String studentId = request.getParameter("studentId");
        if (studentId != null && !studentId.isEmpty()) {
            Student student = studentService.findStudentById(studentId);
            if (student != null) {
                request.setAttribute("student", student);
            } else {
                request.setAttribute("message", "Aucun étudiant trouvé avec cet ID.");
            }
        }

        // Forward to the JSP
        request.getRequestDispatcher("/Student-list.jsp").forward(request, response);
    }
}