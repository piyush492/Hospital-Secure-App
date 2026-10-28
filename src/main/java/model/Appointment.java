package model;

public class Appointment {

    private String patientName;
    private String doctorName;
    private String appointmentdate;

    public Appointment(String patientName, String doctorName, String appointmentdate) {
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.appointmentdate = appointmentdate;

    }

    public String getPatientName() {
        return this.patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDoctorName() {
        return this.doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getAppointmentDate() {
        return this.appointmentdate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentdate = appointmentDate;
    }

}
