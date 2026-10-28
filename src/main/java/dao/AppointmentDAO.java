package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import model.Appointment;
import util.DBConnection;

public class AppointmentDAO {

    public boolean saveAppointment(Appointment appointment) {
        String query = "INSERT INTO appointments (patient_name, doctor_name, appointment_date) VALUES (?,?,?)";

        boolean IsSaved = false;

        try (Connection con = DBConnection.getConnection(); PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, appointment.getPatientName());
            ps.setString(2, appointment.getDoctorName());
            ps.setString(3, appointment.getAppointmentDate());
            try {
                java.sql.Date sqlDate = java.sql.Date.valueOf(appointment.getAppointmentDate());
                ps.setDate(3, sqlDate);

            } catch (Exception e) {
                ps.setString(3, appointment.getAppointmentDate());
            }
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                IsSaved = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return IsSaved;

    }

}
