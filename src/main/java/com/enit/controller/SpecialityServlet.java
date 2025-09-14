package com.enit.controller;

import com.enit.entities.Speciality;
import com.enit.service.ILocalSpeciality;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "SpecialityServlet", urlPatterns = {"/speciality"})
public class SpecialityServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private ILocalSpeciality specialityService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Définir l'encodage de la réponse
        response.setContentType("text/html;charset=UTF-8");

        // Récupérer toutes les spécialités pour les afficher
        List<Speciality> specialities = specialityService.findAllSpecialities();
        request.setAttribute("specialities", specialities);

        // Récupérer l'ID de la spécialité sélectionnée (si applicable)
        String specialityIdParam = request.getParameter("specialityId");
        if (specialityIdParam != null && !specialityIdParam.isEmpty()) {
            try {
                int specialityId = Integer.parseInt(specialityIdParam);

                // Récupérer la spécialité correspondante
                Speciality speciality = specialityService.findSpecialityById(specialityId);

                if (speciality != null) {
                    request.setAttribute("speciality", speciality); // Utilisez "speciality" au lieu de "selectedSpeciality"
                } else {
                    request.setAttribute("message", "Aucune spécialité trouvée avec cet ID.");
                }
            } catch (NumberFormatException e) {
                request.setAttribute("message", "L'ID de la spécialité est invalide.");
            }
        } else {
            request.setAttribute("message", "Veuillez sélectionner une spécialité.");
        }

        // Rediriger vers la page JSP
        request.getRequestDispatcher("/Speciality-list.jsp").forward(request, response);
    }
}