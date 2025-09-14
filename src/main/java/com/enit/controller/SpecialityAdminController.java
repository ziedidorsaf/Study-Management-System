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

@WebServlet(name = "SpecialityAdminController", urlPatterns = {"/admin/specialityAdmin"})

public class SpecialityAdminController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private ILocalSpeciality specialityService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                listSpecialities(request, response);
                break;
            case "add":
                showAddForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteSpeciality(request, response);
                break;
            default:
                listSpecialities(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            addSpeciality(request, response);
        } else if ("edit".equals(action)) {
            updateSpeciality(request, response);
        }
    }

    private void listSpecialities(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Speciality> specialities = specialityService.findAllSpecialities();
        request.setAttribute("specialities", specialities); // Ajoute la liste des spécialités à la requête
        request.getRequestDispatcher("/Speciality-list.jsp").forward(request, response); // Envoie la requête à la JSP
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/specialityForm.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Speciality speciality = specialityService.findSpecialityById(id);
        request.setAttribute("speciality", speciality);
        request.getRequestDispatcher("/specialityForm.jsp").forward(request, response);
    }

    private void addSpeciality(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nameS = request.getParameter("nameS"); // Use "nameS" instead of "name"
        if (nameS == null || nameS.isEmpty()) {
            // Handle error: Name is required
            request.setAttribute("errorMessage", "Le nom de la spécialité est requis.");
            request.getRequestDispatcher("/specialityForm.jsp").forward(request, response);
            return;
        }

        Speciality speciality = new Speciality(nameS);
        specialityService.createSpeciality(speciality);
        response.sendRedirect("specialityAdmin?action=list");
    }

    private void updateSpeciality(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nameS = request.getParameter("nameS"); // Use "nameS" instead of "name"

        Speciality speciality = specialityService.findSpecialityById(id);
        speciality.setNameS(nameS);

        specialityService.updateSpeciality(speciality);
        response.sendRedirect("specialityAdmin?action=list");
    }

    private void deleteSpeciality(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        specialityService.deleteSpeciality(id);
        response.sendRedirect("specialityAdmin?action=list");
    }
}