package com.quynhdv.uds.dao;

import java.time.LocalDateTime;

import com.quynhdv.uds.model.Appointment;

public class AppointmentDAO {
    
    private static AppointmentDAO instance;

    private Appointment[] appointments = null;

    // Private constructor to prevent instantiation
    private AppointmentDAO() {
    }

    // Public method to provide access to the single instance
    public static synchronized AppointmentDAO getInstance() {
        if (instance == null) {
            // Lazy initialization of the singleton instance
            // This ensures that the instance is created only when it is needed.
            instance = new AppointmentDAO();
        }
        return instance;
    }

    private void loadData() {
        this.appointments = new Appointment[] {
            new Appointment(1, LocalDateTime.of(2025,2,28,10,5), 1),
            new Appointment(2, LocalDateTime.of(2024,12,31,13,45), 2),
            new Appointment(3, LocalDateTime.of(2025,5,4,14,0), 3),
            new Appointment(4, LocalDateTime.of(2025,3,16,11,15), 4)
        };
    }

    /**
     * Fetches the Appointments data from the data source.
     * If the array is null or empty, loads the data first.
     * @return appointments the array of Appointments
     */
    public Appointment[] getAppointments() {
        if (appointments == null || appointments.length == 0) {
            loadData();
        }
        return appointments;
    }

}