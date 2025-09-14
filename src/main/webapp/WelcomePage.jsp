<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Welcome</title>
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
            overflow: hidden;
        }

        /* Conteneur principal */
        .welcome-container {
            background: rgba(255, 255, 255, 0.1);
            backdrop-filter: blur(10px);
            padding: 2.5rem;
            border-radius: 15px;
            box-shadow: 0 8px 32px rgba(0, 0, 0, 0.2);
            width: 100%;
            max-width: 600px;
            border: 1px solid rgba(255, 255, 255, 0.2);
            text-align: center;
            animation: fadeIn 1.5s ease-in-out;
            position: relative;
            overflow: hidden;
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

        /* Effet de particules animées */
        .particles {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            z-index: -1;
        }

        .particle {
            position: absolute;
            width: 10px;
            height: 10px;
            background: rgba(255, 255, 255, 0.3);
            border-radius: 50%;
            animation: float 5s infinite ease-in-out;
        }

        @keyframes float {
            0%, 100% {
                transform: translateY(0) translateX(0);
            }
            50% {
                transform: translateY(-20px) translateX(20px);
            }
        }

        /* Titre */
        h1 {
            font-size: 2.5rem;
            font-weight: 600;
            margin-bottom: 1.5rem;
            color: #fff;
            text-shadow: 0 4px 6px rgba(0, 0, 0, 0.2);
            animation: slideIn 1s ease-in-out;
        }

        @keyframes slideIn {
            from {
                opacity: 0;
                transform: translateX(-50px);
            }
            to {
                opacity: 1;
                transform: translateX(0);
            }
        }

        /* Description */
        p {
            font-size: 1.1rem;
            color: #e0e0e0;
            margin-bottom: 2rem;
            animation: slideIn 1.2s ease-in-out;
        }

        /* Bouton de connexion admin */
        .admin-login-button {
            display: inline-block;
            padding: 1rem 2rem;
            background: rgba(255, 255, 255, 0.2);
            color: #fff;
            text-decoration: none;
            border-radius: 10px;
            font-size: 1.1rem;
            font-weight: 500;
            transition: background 0.3s ease, transform 0.2s ease, box-shadow 0.3s ease;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
        }

        .admin-login-button:hover {
            background: rgba(255, 255, 255, 0.3);
            transform: translateY(-3px);
            box-shadow: 0 6px 12px rgba(0, 0, 0, 0.2);
        }

        .admin-login-button:active {
            transform: translateY(0);
        }

        /* Responsive design pour les petits écrans */
        @media (max-width: 480px) {
            h1 {
                font-size: 2rem;
            }
            p {
                font-size: 1rem;
            }
            .admin-login-button {
                padding: 0.8rem 1.5rem;
                font-size: 1rem;
            }
        }
    </style>
    <!-- Lien vers Google Fonts pour la police Poppins -->
    <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600&display=swap" rel="stylesheet">
</head>
<body>
    <div class="welcome-container">
        <!-- Effet de particules animées -->
        <div class="particles">
            <div class="particle" style="top: 10%; left: 20%; animation-delay: 0s;"></div>
            <div class="particle" style="top: 30%; left: 70%; animation-delay: 1s;"></div>
            <div class="particle" style="top: 60%; left: 40%; animation-delay: 2s;"></div>
            <div class="particle" style="top: 80%; left: 10%; animation-delay: 3s;"></div>
        </div>

        <h1>Study Management System</h1>
        <p>Welcome to the Admin Portal</p>
        <div>
            <a href="adminLogin.jsp" class="admin-login-button">Admin Login</a>
        </div>
    </div>
</body>
</html>