<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Logout</title>
    <style>
        /* Reset des styles par défaut */
        body, h1, p, a {
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
        .logout-container {
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

        /* Message */
        p {
            font-size: 1rem;
            margin-bottom: 1.5rem;
            color: #e0e0e0;
        }

        /* Lien de redirection */
        a {
            display: inline-block;
            padding: 0.75rem 1.5rem;
            background: #3498db;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            font-size: 1rem;
            font-weight: 500;
            transition: background 0.3s ease;
        }

        a:hover {
            background: #2980b9;
        }

        /* Responsive design pour les petits écrans */
        @media (max-width: 480px) {
            .logout-container {
                padding: 1.5rem;
            }
            h1 {
                font-size: 1.75rem;
            }
            a {
                padding: 0.5rem 1rem;
                font-size: 0.9rem;
            }
        }
    </style>
    <!-- Lien vers Google Fonts pour la police Poppins -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap" rel="stylesheet">
</head>
<body>
    <div class="logout-container">
        <h1>Logout Successful</h1>
        <p>You have been successfully logged out. Redirecting to the login page...</p>
        <a href="adminLogin.jsp">Go to Login</a>
    </div>

    <!-- Script pour rediriger automatiquement après 3 secondes -->
    <script>
        setTimeout(function() {
            window.location.href = "WelcomePage.jsp";
        }, 3000); // Redirection après 3 secondes
    </script>

    <!-- Invalidation de la session -->
    <% session.invalidate(); %>
</body>
</html>