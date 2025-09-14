package com.enit.controller;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

import com.enit.entities.Admin;
import com.enit.service.LoginAdminService;

@WebServlet("/loginAdmin")
public class LoginAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 @EJB
	    private LoginAdminService loginService;
	   @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        // Rediriger vers la page de connexion (login.jsp)
	        req.getRequestDispatcher("/adminLogin.jsp").forward(req, resp);
	    }
	  @Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        String username = req.getParameter("username");
	        String password = req.getParameter("password");

	        Admin admin = loginService.authenticate(username, password);

	        if (admin != null) {
	            // Authentification réussie
	            req.getSession().setAttribute("admin", admin); // Stocker l'admin en session
	            resp.sendRedirect(req.getContextPath() + "/admin"); // Rediriger vers /admin
	        } else {
	            // Authentification échouée
	            req.setAttribute("error", "Login ou mot de passe incorrect");
	            req.getRequestDispatcher("adminLogin.jsp").forward(req, resp);
	        }
	    }
	
	}

