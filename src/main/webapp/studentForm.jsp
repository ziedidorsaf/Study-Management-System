<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jakarta.servlet.*, jakarta.servlet.http.*" %>
<%@ page import="com.enit.entities.*" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title><%= (request.getAttribute("student") != null) ? "Modifier" : "Ajouter" %> un Étudiant</title>
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
        input[type="text"], input[type="password"] {
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

        input[type="text"]:focus, input[type="password"]:focus {
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
        <h1><%= (request.getAttribute("student") != null) ? "Modifier" : "Ajouter" %> un Étudiant</h1>
        <form action="studentAdmin" method="post">
            <input type="hidden" name="action" value="<%= (request.getAttribute("student") != null) ? "edit" : "add" %>">
            <%
                Student student = (Student) request.getAttribute("student");
                if (student != null) {
            %>
                <input type="hidden" name="idStudent" value="<%= student.getIdStudent() %>">
            <%
                }
            %>
            <!-- Add the ID Student field -->
            <label for="idStudent">ID Étudiant:</label>
            <input type="text" id="idStudent" name="idStudent" 
                   value="<%= (student != null) ? student.getIdStudent() : "" %>" 
                   <%= (student != null) ? "readonly" : "required" %>><br>
            
            <label for="name">Nom:</label>
            <input type="text" id="name" name="name" value="<%= (student != null) ? student.getName() : "" %>" required><br>
            <label for="email">Email:</label>
            <input type="text" id="email" name="email" value="<%= (student != null) ? student.getEmail() : "" %>" required><br>
            <label for="password">Mot de passe:</label>
            <input type="password" id="password" name="password" value="<%= (student != null) ? student.getPassword() : "" %>" required><br>
            <button type="submit"><%= (student != null) ? "Modifier" : "Ajouter" %></button>
        </form>
        <a href="${pageContext.request.contextPath}/admin/studentAdmin?action=list">Retour à la liste</a>
    </div>
</body>
</html>