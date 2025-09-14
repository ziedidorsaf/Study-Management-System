<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.*, jakarta.servlet.http.*" %>
<%@ page import="com.enit.entities.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><%= (request.getAttribute("session") != null) ? "Modifier" : "Ajouter" %> une Session</title>
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
        .form-container {
            background: rgba(255, 255, 255, 0.1);
            backdrop-filter: blur(10px);
            padding: 2rem;
            border-radius: 15px;
            box-shadow: 0 8px 32px rgba(0, 0, 0, 0.2);
            width: 100%;
            max-width: 600px;
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
        h1 {
            color: #fff;
            text-shadow: 0 4px 6px rgba(0, 0, 0, 0.2);
            font-size: 2.5rem;
            font-weight: 600;
            margin-bottom: 1.5rem;
            text-align: center;
        }

        /* Labels */
        label {
            font-weight: bold;
            margin-top: 15px;
            display: block;
            color: #fff;
            font-size: 1.1em;
        }

        /* Champs de formulaire */
        input[type="text"], input[type="number"], input[type="date"] {
            width: 100%;
            padding: 12px;
            margin-top: 8px;
            margin-bottom: 20px;
            border: 2px solid rgba(255, 255, 255, 0.3);
            border-radius: 8px;
            box-sizing: border-box;
            background: rgba(255, 255, 255, 0.1);
            color: #fff;
            font-size: 1em;
            transition: border-color 0.3s ease, box-shadow 0.3s ease;
        }

        input[type="text"]:focus, input[type="number"]:focus, input[type="date"]:focus {
            border-color: rgba(255, 255, 255, 0.7);
            box-shadow: 0 0 8px rgba(255, 255, 255, 0.5);
            outline: none;
        }

        /* Bouton */
        button {
            background: rgba(255, 255, 255, 0.1);
            color: white;
            padding: 12px 24px;
            border: 2px solid rgba(255, 255, 255, 0.3);
            border-radius: 8px;
            cursor: pointer;
            font-weight: bold;
            font-size: 1.1em;
            transition: background 0.3s ease, transform 0.3s ease;
            width: 100%;
            margin-top: 20px;
        }

        button:hover {
            background: rgba(255, 255, 255, 0.2);
            transform: translateY(-2px);
        }

        /* Lien de retour */
        a {
            display: inline-block;
            margin-top: 20px;
            color: #fff;
            text-decoration: none;
            font-weight: bold;
            font-size: 1.1em;
            transition: color 0.3s ease;
        }

        a:hover {
            color: rgba(255, 255, 255, 0.7);
            text-decoration: underline;
        }

        /* Responsive design pour les petits écrans */
        @media (max-width: 768px) {
            h1 {
                font-size: 2rem;
            }
            .form-container {
                padding: 1.5rem;
            }
        }
    </style>
    <!-- Lien vers Google Fonts pour la police Poppins -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap" rel="stylesheet">
</head>
<body>
    <div class="form-container">
        <h1><%= (request.getAttribute("session") != null) ? "Modifier" : "Ajouter" %> une Session</h1>
        <form action="sessionAdmin" method="post">
            <input type="hidden" name="action" value="<%= (request.getAttribute("session") != null) ? "edit" : "add" %>">
            <%
                Session sessionEntity = (Session) request.getAttribute("session");
                if (sessionEntity != null) {
            %>
                <input type="hidden" name="idStudent" value="<%= sessionEntity.getSessionPK().getIdStudent() %>">
                <input type="hidden" name="idExam" value="<%= sessionEntity.getSessionPK().getIdExam() %>">
            <%
                }
            %>
            <label for="idStudent">ID Étudiant:</label>
            <input type="text" id="idStudent" name="idStudent" value="<%= (sessionEntity != null) ? sessionEntity.getSessionPK().getIdStudent() : "" %>" required><br>
            <label for="idExam">ID Examen:</label>
            <input type="number" id="idExam" name="idExam" value="<%= (sessionEntity != null) ? sessionEntity.getSessionPK().getIdExam() : "" %>" required><br>
            <label for="sessionDate">Date de la session:</label>
            <input type="datetime-local" id="sessionDate" name="sessionDate" value="<%= (sessionEntity != null) ? sessionEntity.getSessionDate() : "" %>" required><br>
            <label for="grade">Note:</label>
            <input type="number" step="0.01" id="grade" name="grade" value="<%= (sessionEntity != null) ? sessionEntity.getGrade() : "" %>" required><br>
            <button type="submit"><%= (sessionEntity != null) ? "Modifier" : "Ajouter" %></button>
        </form>
        <a href="${pageContext.request.contextPath}/admin/sessionAdmin?action=list">Retour à la liste</a>
    </div>
</body>
</html>