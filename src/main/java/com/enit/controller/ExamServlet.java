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

@WebServlet(name = "ExamServlet", urlPatterns = {"/exam"})
public class ExamServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private ILocalExam examService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Définir l'encodage de la réponse
        response.setContentType("text/html;charset=UTF-8");

        // Récupérer tous les examens pour les afficher
        List<Exam> exams = examService.findAllExams();
        request.setAttribute("exams", exams);

        // Récupérer l'ID de la spécialité sélectionnée
        String specialityIdParam = request.getParameter("specialityId");
        if (specialityIdParam != null && !specialityIdParam.isEmpty()) {
            try {
                int specialityId = Integer.parseInt(specialityIdParam);

                // Récupérer les examens associés à la spécialité
                List<Exam> examsBySpeciality = examService.findExamsBySpecialityId(specialityId);

                if (examsBySpeciality != null && !examsBySpeciality.isEmpty()) {
                    request.setAttribute("examsBySpeciality", examsBySpeciality);
                } else {
                    request.setAttribute("message", "Aucun examen trouvé pour la spécialité sélectionnée.");
                }
            } catch (NumberFormatException e) {
                request.setAttribute("message", "L'ID de la spécialité est invalide.");
            }
        } else {
            request.setAttribute("message", "Veuillez sélectionner une spécialité.");
        }

        // Rediriger vers la page JSP
        request.getRequestDispatcher("/Exam-list.jsp").forward(request, response);
    }
}