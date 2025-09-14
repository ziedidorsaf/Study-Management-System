package com.enit.controller;

import com.enit.entities.Exam;
import com.enit.entities.Speciality;
import com.enit.service.ILocalExam;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ExamAdminController", urlPatterns = {"/admin/examAdmin"})
public class ExamAdminController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private ILocalExam examService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                listExams(request, response);
                break;
            case "add":
                showAddForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteExam(request, response);
                break;
            default:
                listExams(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            addExam(request, response);
        } else if ("edit".equals(action)) {
            updateExam(request, response);
        }
    }

    private void listExams(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Exam> exams = examService.findAllExams();
        request.setAttribute("exams", exams); // Ajoute la liste des examens à la requête
        request.getRequestDispatcher("/Exam-list.jsp").forward(request, response); // Envoie la requête à la JSP
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/examForm.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Exam exam = examService.findExamById(id);
        request.setAttribute("exam", exam);
        request.getRequestDispatcher("/examForm.jsp").forward(request, response);
    }

    private void addExam(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        int idSpeciality = Integer.parseInt(request.getParameter("idSpeciality"));

        // Créez un objet Speciality avec l'ID récupéré (vous devrez peut-être récupérer l'objet Speciality complet depuis la base de données)
        Speciality speciality = new Speciality();
        speciality.setIdSpeciality(idSpeciality);

        Exam exam = new Exam();
        exam.setName(name);
        exam.setSpeciality(speciality);

        examService.createExam(exam);
        response.sendRedirect("examAdmin?action=list");
    }

    private void updateExam(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int idSpeciality = Integer.parseInt(request.getParameter("idSpeciality"));

        // Créez un objet Speciality avec l'ID récupéré (vous devrez peut-être récupérer l'objet Speciality complet depuis la base de données)
        Speciality speciality = new Speciality();
        speciality.setIdSpeciality(idSpeciality);

        Exam exam = examService.findExamById(id);
        exam.setName(name);
        exam.setSpeciality(speciality);

        examService.updateExam(exam);
        response.sendRedirect("examAdmin?action=list");
    }

    private void deleteExam(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Exam exam = examService.findExamById(id);
        examService.deleteExam(id);
        response.sendRedirect("examAdmin?action=list");
    }
}