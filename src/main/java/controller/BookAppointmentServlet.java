package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AppointmentDAO;
import model.Appointment;

@WebServlet("/api/book-appointment")
public class BookAppointmentServlet extends HttpServlet {

    private AppointmentDAO appointmentDAO;

    @Override
    public void init() throws ServletException {
        appointmentDAO = new AppointmentDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String pName = request.getParameter("patientName");
        String dName = request.getParameter("doctorName");
        String date = request.getParameter("appointmentDate");

        Appointment apt = new Appointment(pName, dName, date);

        boolean isSuccess = appointmentDAO.saveAppointment(apt);

        if (isSuccess) {
            out.print("{\"status\":\"success\", \"message\":\"Appointment booked successfully!\"}");
        } else {
            out.print("{\"status\":\"error\", \"message\":\"Database insertion failed.\"}");
        }

        out.flush();
    }

}
