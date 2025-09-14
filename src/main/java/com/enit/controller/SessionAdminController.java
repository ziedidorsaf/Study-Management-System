package com.enit.controller;

import com.enit.entities.Session;
import com.enit.entities.SessionPK;
import com.enit.entities.Student;
import com.enit.entities.Exam;
import com.enit.service.ILocalSession;
import com.enit.service.ILocalStudent;
import com.enit.service.ILocalExam;
import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(name = "SessionAdminController", urlPatterns = {"/admin/sessionAdmin"})
public class SessionAdminController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private ILocalSession sessionService;

    @EJB
    private ILocalStudent studentService;

    @EJB
    private ILocalExam examService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                listSessions(request, response);
                break;
            case "add":
                showAddForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteSession(request, response);
                break;
            default:
                listSessions(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("add".equals(action)) {
            addSession(request, response);
        } else if ("edit".equals(action)) {
            updateSession(request, response);
        }
    }

    private void listSessions(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Session> sessions = sessionService.findAllSessions();
        request.setAttribute("sessions", sessions);
        request.getRequestDispatcher("/Session-list.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/sessionForm.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStudent = request.getParameter("idStudent");
        int idExam = Integer.parseInt(request.getParameter("idExam"));
        SessionPK sessionPK = new SessionPK(idStudent, idExam);
        Session session = sessionService.findSessionById(sessionPK);
        request.setAttribute("session", session);
        request.getRequestDispatcher("/sessionForm.jsp").forward(request, response);
    }

    private void addSession(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStudent = request.getParameter("idStudent");
        int idExam = Integer.parseInt(request.getParameter("idExam"));
        String sessionDateStr = request.getParameter("sessionDate");
        float grade = Float.parseFloat(request.getParameter("grade")); // Récupérer la note

        // Vérifier si l'étudiant existe
        Student student = studentService.findStudentById(idStudent);
        if (student == null) {
            request.setAttribute("errorMessage", "L'étudiant avec l'ID " + idStudent + " n'existe pas.");
            request.getRequestDispatcher("/sessionForm.jsp").forward(request, response);
            return;
        }

        // Vérifier si l'examen existe
        Exam exam = examService.findExamById(idExam);
        if (exam == null) {
            request.setAttribute("errorMessage", "L'examen avec l'ID " + idExam + " n'existe pas.");
            request.getRequestDispatcher("/sessionForm.jsp").forward(request, response);
            return;
        }

        // Parser la date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date sessionDate = null;
        try {
            sessionDate = dateFormat.parse(sessionDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Format de date invalide. Utilisez le format 'yyyy-MM-ddTHH:mm'.");
            request.getRequestDispatcher("/sessionForm.jsp").forward(request, response);
            return;
        }

        // Créer la session
        SessionPK sessionPK = new SessionPK(idStudent, idExam);
        Session session = new Session(sessionPK, student, exam, sessionDate, grade);

        // Sauvegarder la session
        try {
            sessionService.createSession(session);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Une erreur s'est produite lors de la création de la session.");
            request.getRequestDispatcher("/sessionForm.jsp").forward(request, response);
            return;
        }

        // Rediriger vers la liste des sessions
        response.sendRedirect("sessionAdmin?action=list");
    }

    private void updateSession(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String idStudent = request.getParameter("idStudent");
        int idExam = Integer.parseInt(request.getParameter("idExam"));
        String sessionDateStr = request.getParameter("sessionDate");
        float grade = Float.parseFloat(request.getParameter("grade")); // Récupérer la note

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        Date sessionDate = null;
        try {
            sessionDate = dateFormat.parse(sessionDateStr);
        } catch (ParseException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Format de date invalide. Utilisez le format 'yyyy-MM-ddTHH:mm'.");
            request.getRequestDispatcher("/sessionForm.jsp").forward(request, response);
            return;
        }

        SessionPK sessionPK = new SessionPK(idStudent, idExam);
        Session session = sessionService.findSessionById(sessionPK);

        if (session == null) {
            request.setAttribute("errorMessage", "Session non trouvée.");
            request.getRequestDispatcher("/sessionForm.jsp").forward(request, response);
            return;
        }

        // Mettre à jour la session
        session.setSessionDate(sessionDate);
        session.setGrade(grade); // Mettre à jour la note
        sessionService.updateSession(session);

        response.sendRedirect("sessionAdmin?action=list");
    }
    
    private void deleteSession(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idStudent = request.getParameter("idStudent");
        int idExam = Integer.parseInt(request.getParameter("idExam"));
        SessionPK sessionPK = new SessionPK(idStudent, idExam);
        sessionService.deleteSession(sessionPK);
        response.sendRedirect("sessionAdmin?action=list");
    }
}