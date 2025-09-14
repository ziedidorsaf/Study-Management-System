<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Liste des étudiants</title>
    <style>
        /* Reset des styles par défaut */
        body, h1, h2, table, form, label, input, select, button {
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
        .student-container {
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
        h1, h2 {
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

        /* Tableau */
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 2rem;
            background: rgba(255, 255, 255, 0.1);
            border-radius: 10px;
            overflow: hidden;
        }

        th, td {
            padding: 1rem;
            text-align: left;
            border-bottom: 1px solid rgba(255, 255, 255, 0.2);
        }

        th {
            background: rgba(255, 255, 255, 0.2);
            font-weight: 600;
        }

        tr:hover {
            background: rgba(255, 255, 255, 0.1);
        }

        /* Actions dans le tableau */
        td a {
            color: #3498db;
            text-decoration: none;
            margin-right: 0.5rem;
            transition: color 0.3s ease;
        }

        td a:hover {
            color: #2980b9;
        }

        /* Boutons */
        .button {
            display: inline-block;
            margin-top: 1rem;
            padding: 0.75rem 1.5rem;
            background: #3498db;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            font-size: 1rem;
            font-weight: 500;
            transition: background 0.3s ease;
        }

        .button:hover {
            background: #2980b9;
        }

        /* Responsive design pour les petits écrans */
        @media (max-width: 768px) {
            h1 {
                font-size: 2rem;
            }
            h2 {
                font-size: 1.5rem;
            }
            table {
                font-size: 0.9rem;
            }
            th, td {
                padding: 0.75rem;
            }
        }
    </style>
    <!-- Lien vers Google Fonts pour la police Poppins -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap" rel="stylesheet">
</head>
<body>
    <div class="student-container">
        <h1>Liste des étudiants</h1>

        <!-- Afficher les messages de succès ou d'erreur -->
        <c:if test="${not empty successMessage}">
            <div style="color: green; margin-bottom: 1rem;">${successMessage}</div>
        </c:if>
        <c:if test="${not empty errorMessage}">
            <div style="color: red; margin-bottom: 1rem;">${errorMessage}</div>
        </c:if>

        <!-- Tableau des étudiants -->
        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nom</th>
                    <th>Email</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="student" items="${students}">
                    <tr>
                        <td>${student.idStudent}</td>
                        <td>${student.name}</td>
                        <td>${student.email}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/admin/studentAdmin?action=edit&id=${student.idStudent}">Modifier</a>
                            <a href="${pageContext.request.contextPath}/admin/studentAdmin?action=delete&idStudent=${student.idStudent}" onclick="return confirm('Êtes-vous sûr de vouloir supprimer cet étudiant ?');">Supprimer</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <!-- Bouton pour ajouter un nouvel étudiant -->
        <a href="${pageContext.request.contextPath}/admin/studentAdmin?action=add" class="button">add student</a>

        <!-- Bouton pour revenir au tableau de bord admin -->
        <a href="http://localhost:8083/coursWeb/admin" class="button" style="background: #2ecc71;">back to Dashboard</a>
    </div>
</body>
</html>