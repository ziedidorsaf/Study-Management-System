package com.enit.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminController", urlPatterns = {"/admin/*"}) // Ajoutez "/*" pour gérer les sous-URLs
public class AdminController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getPathInfo(); // Récupère le pathInfo (par exemple, "/students")

        if (action == null || action.equals("/")) {
            // Afficher le tableau de bord
            request.getRequestDispatcher("/adminDashboard.jsp").forward(request, response);
        } else {
            switch (action) {
                case "/students":
                    // Rediriger vers le contrôleur des étudiants
                    request.getRequestDispatcher("/admin/studentAdmin").forward(request, response);
                    break;
                case "/exams":
                    // Rediriger vers le contrôleur des examens
                    request.getRequestDispatcher("/admin/examAdmin").forward(request, response);
                    break;
                case "/specialities":
                    // Rediriger vers le contrôleur des spécialités
                    request.getRequestDispatcher("/admin/specialityAdmin").forward(request, response);
                    break;
                case "/sessions":
                    // Rediriger vers le contrôleur des sessions
                    request.getRequestDispatcher("/admin/sessionAdmin").forward(request, response);
                    break;
                default:
                    // Gérer les URLs inconnues
                    response.sendError(HttpServletResponse.SC_NOT_FOUND, "Page non trouvée");
                    break;
            }
        }
    }
}