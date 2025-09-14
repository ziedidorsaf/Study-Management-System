package com.enit.controller;

import com.enit.entities.Session;
import com.enit.service.ILocalSession;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SessionServlet", urlPatterns = {"/session"})
public class SessionServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private ILocalSession sessionService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        List<Session> sessions = sessionService.findAllSessions();
        request.setAttribute("sessions", sessions);

        String studentIdParam = request.getParameter("studentId");
        if (studentIdParam != null && !studentIdParam.isEmpty()) {
            List<Session> sessionsByStudent = sessionService.findSessionsByStudentId(studentIdParam);
            if (sessionsByStudent != null && !sessionsByStudent.isEmpty()) {
                request.setAttribute("sessionsByStudent", sessionsByStudent);
            } else {
                request.setAttribute("message", "Aucune session trouvée pour cet étudiant.");
            }
        } else {
            request.setAttribute("message", "Veuillez sélectionner un étudiant.");
        }

        request.getRequestDispatcher("/Session-list.jsp").forward(request, response);
    }
}