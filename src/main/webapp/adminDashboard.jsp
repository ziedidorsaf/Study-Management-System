<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.*, jakarta.servlet.http.*" %>
<%@ page import="com.enit.entities.Admin" %>

<!DOCTYPE html>
<%
    // Vérifier si l'admin est connecté
    Admin admin = (Admin) session.getAttribute("admin");
    if (admin == null) {
        response.sendRedirect(request.getContextPath() + "/adminLogin.jsp");
        return;
    }
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Tableau de Bord Admin</title>
    <style>
        /* Reset des styles par défaut */
        body, h1, h2, h3, p, ul, li, a, form, input, button {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Poppins', sans-serif;
        }

        /* Style général de la page */
        body {
            background: linear-gradient(135deg, #667eea, #764ba2);
            color: #fff;
            padding: 2rem;
            display: flex;
            flex-direction: column;
            align-items: center;
            min-height: 100vh;
        }

        /* Conteneur principal */
        .dashboard-container {
            background: rgba(255, 255, 255, 0.1);
            backdrop-filter: blur(10px);
            padding: 2rem;
            border-radius: 15px;
            box-shadow: 0 8px 32px rgba(0, 0, 0, 0.2);
            width: 100%;
            max-width: 1200px;
            border: 1px solid rgba(255, 255, 255, 0.2);
            animation: fadeIn 1.5s ease-in-out;
        }

        /* Animation d'apparition */
        @keyframes fadeIn {
            from {
                opacity: 0;
                transform: translateY(-20px);
            }
            to {
                opacity: 1;
                transform: translateY(0);
            }
        }

        /* Titres */
        h1, h2, h3 {
            color: #fff;
            text-shadow: 0 4px 6px rgba(0, 0, 0, 0.2);
        }

        h1 {
            font-size: 2.5rem;
            font-weight: 600;
            margin-bottom: 1.5rem;
        }

        h2 {
            font-size: 1.75rem;
            font-weight: 500;
            margin-bottom: 1rem;
        }

        h3 {
            font-size: 1.25rem;
            font-weight: 500;
            margin-bottom: 0.5rem;
        }

        /* Liens rapides */
        .quick-links {
            display: flex;
            gap: 1rem;
            margin-bottom: 2rem;
        }

        .link-card {
            background: rgba(255, 255, 255, 0.1);
            padding: 1rem;
            border-radius: 10px;
            text-align: center;
            flex: 1;
            color: #fff;
            text-decoration: none;
            transition: background 0.3s ease;
        }

        .link-card:hover {
            background: rgba(255, 255, 255, 0.2);
        }

        /* Bouton de déconnexion */
        .logout-button {
            display: inline-block;
            margin-top: 1rem;
            padding: 0.75rem 1.5rem;
            background: rgba(255, 255, 255, 0.1);
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            transition: background 0.3s ease;
        }

        .logout-button:hover {
            background: rgba(255, 255, 255, 0.2);
        }

        /* Responsive design pour les petits écrans */
        @media (max-width: 768px) {
            h1 {
                font-size: 2rem;
            }
            h2 {
                font-size: 1.5rem;
            }
            .quick-links {
                flex-direction: column;
            }
        }
    </style>
    <!-- Lien vers Google Fonts pour la police Poppins -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap" rel="stylesheet">
</head>
<body>
    <div class="dashboard-container">
        <h1>Admin Dashboard</h1>

        <!-- Liens rapides -->
        <div class="quick-links">
            <a href="${pageContext.request.contextPath}/admin/studentAdmin" class="link-card">Manage Students</a>
            <a href="${pageContext.request.contextPath}/admin/specialityAdmin" class="link-card">Manage Specialities</a>
            <a href="${pageContext.request.contextPath}/admin/sessionAdmin" class="link-card">Manage Sessions</a>
            <a href="${pageContext.request.contextPath}/admin/examAdmin" class="link-card">Manage Exams</a>
        </div>

        <!-- Bouton de déconnexion -->
        <a href="${pageContext.request.contextPath}/logout.jsp" class="logout-button">Logout</a>
    </div>
</body>
</html>