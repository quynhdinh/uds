package com.quynhdv.uds.service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.quynhdv.uds.dao.AppointmentDAO;
import com.quynhdv.uds.dao.PatientDAO;
import com.quynhdv.uds.model.Appointment;
import com.quynhdv.uds.model.Patient;
import com.quynhdv.uds.dto.AppointmentDTO;

public class AppointmentService {
    private AppointmentDAO appointmentDAO;
    private PatientDAO patientDAO;

    public AppointmentService() {
        this.appointmentDAO = AppointmentDAO.getInstance();
        this.patientDAO = PatientDAO.getInstance();
    }

    /**
     * Fetches the appointments data from the DAO.
     * @return appointments the array of Appointments
     */
    public Appointment[] getAppointments() {
        return appointmentDAO.getAppointments();
    }

    /**
     * Finds a patient by their ID
     * @param patientId the ID of the patient
     * @return the Patient object or null if not found
     */
    private Patient findPatientById(int patientId) {
        Patient[] patients = patientDAO.getPatients();
        return Arrays.stream(patients)
                .filter(p -> p.getId() == patientId)
                .findFirst()
                .orElse(null);
    }

    /**
     * Gets appointments for a specific quarter and year, sorted by date in descending order
     * @param quarter the quarter (1-4)
     * @param year the year
     * @return list of AppointmentDTO objects with associated patient data
     */
    public List<AppointmentDTO> getAppointmentsByQuarter(int quarter, int year) {
        if (quarter < 1 || quarter > 4) {
            throw new IllegalArgumentException("Quarter must be between 1 and 4");
        }

        // Define the start and end months for each quarter
        int startMonth, endMonth;
        switch (quarter) {
            case 1: startMonth = 1; endMonth = 3; break;
            case 2: startMonth = 4; endMonth = 6; break;
            case 3: startMonth = 7; endMonth = 9; break;
            case 4: startMonth = 10; endMonth = 12; break;
            default: throw new IllegalArgumentException("Invalid quarter");
        }

        LocalDateTime quarterStart = LocalDateTime.of(year, startMonth, 1, 0, 0);
        LocalDateTime quarterEnd = LocalDateTime.of(year, endMonth, 1, 0, 0).plusMonths(1).minusSeconds(1);

        Appointment[] appointments = appointmentDAO.getAppointments();
        
        return Arrays.stream(appointments)
                .filter(appointment -> {
                    LocalDateTime appointmentDate = appointment.getDate();
                    return appointmentDate.isAfter(quarterStart.minusSeconds(1)) && 
                           appointmentDate.isBefore(quarterEnd.plusSeconds(1));
                })
                .map(appointment -> {
                    Patient patient = findPatientById(appointment.getPatientId());
                    return new AppointmentDTO(appointment.getId(), appointment.getDate(), patient);
                })
                .filter(dto -> dto.getPatient() != null) // Only include appointments with valid patients
                .sorted(Comparator.comparing(AppointmentDTO::getDate).reversed()) // Sort by date descending
                .collect(Collectors.toList());
    }
}
