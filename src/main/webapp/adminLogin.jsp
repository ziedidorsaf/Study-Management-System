<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Login</title>
    <style>
        /* Reset des styles par défaut */
        body, h1, form, label, input, button, a {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
            font-family: 'Poppins', sans-serif;
        }

        /* Style général de la page */
        body {
            background: linear-gradient(135deg, #667eea, #764ba2);
            color: #fff;
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: 100vh;
        }

        /* Conteneur principal */
        .login-container {
            background: rgba(255, 255, 255, 0.1);
            backdrop-filter: blur(10px);
            padding: 2.5rem;
            border-radius: 15px;
            box-shadow: 0 8px 32px rgba(0, 0, 0, 0.2);
            width: 100%;
            max-width: 400px;
            border: 1px solid rgba(255, 255, 255, 0.2);
            text-align: center;
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

        /* Titre */
        h1 {
            font-size: 2rem;
            font-weight: 600;
            margin-bottom: 1.5rem;
            color: #fff;
            text-shadow: 0 4px 6px rgba(0, 0, 0, 0.2);
        }

        /* Formulaire */
        form {
            display: flex;
            flex-direction: column;
        }

        label {
            font-size: 1rem;
            font-weight: 500;
            margin-bottom: 0.5rem;
            color: #e0e0e0;
        }

        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 0.75rem;
            margin-bottom: 1rem;
            border: 1px solid rgba(255, 255, 255, 0.3);
            border-radius: 5px;
            background: rgba(255, 255, 255, 0.1);
            color: #fff;
            font-size: 1rem;
        }

        input[type="text"]:focus, input[type="password"]:focus {
            outline: none;
            border-color: #3498db;
        }

        button {
            background: #3498db;
            color: #fff;
            padding: 0.75rem;
            border: none;
            border-radius: 5px;
            font-size: 1rem;
            font-weight: 500;
            cursor: pointer;
            transition: background 0.3s ease;
        }

        button:hover {
            background: #2980b9;
        }

        /* Bouton de retour */
        .back-button {
            margin-top: 1.5rem;
        }

        .back-button a {
            display: inline-block;
            padding: 0.75rem 1.5rem;
            background: rgba(255, 255, 255, 0.2);
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            font-size: 1rem;
            font-weight: 500;
            transition: background 0.3s ease;
        }

        .back-button a:hover {
            background: rgba(255, 255, 255, 0.3);
        }

        /* Responsive design pour les petits écrans */
        @media (max-width: 480px) {
            .login-container {
                padding: 1.5rem;
            }
            h1 {
                font-size: 1.75rem;
            }
            input[type="text"], input[type="password"] {
                padding: 0.5rem;
                font-size: 0.9rem;
            }
            button, .back-button a {
                padding: 0.5rem 1rem;
                font-size: 0.9rem;
            }
        }
    </style>
    <!-- Lien vers Google Fonts pour la police Poppins -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap" rel="stylesheet">
</head>
<body>
    <div class="login-container">
        <h1>Admin Login</h1>
        <form action="${pageContext.request.contextPath}/loginAdmin" method="post">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
            <button type="submit">Login</button>
            <p class="error-message">${error}</p>
        </form>

        <!-- Bouton de retour -->
        <div class="back-button">
            <a href="WelcomePage.jsp">Back to Welcome Page</a>
        </div>
    </div>
</body>
</html>