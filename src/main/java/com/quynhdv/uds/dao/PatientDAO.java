package com.quynhdv.uds.dao;

import java.time.LocalDate;
import com.quynhdv.uds.model.Patient;

public class PatientDAO {
    private static PatientDAO instance;

    private Patient[] patients = null;

    // Private constructor to prevent instantiation
    private PatientDAO() {
    }

    // Public method to provide access to the single instance
    public static synchronized PatientDAO getInstance() {
        if (instance == null) {
            // Lazy initialization of the singleton instance
            // This ensures that the instance is created only when it is needed.
            instance = new PatientDAO();
        }
        return instance;
    }

    private void loadData() {
        this.patients = new Patient[] {
                new Patient(1, "John", "Smith", "(641) 001-1234", LocalDate.of(1987, 1, 19)),
                new Patient(2, "Anna", "Smith", "(319) 716-1987", LocalDate.of(2001, 7, 26)),
                new Patient(3, "Carlos", "Jimenez", "(319) 098-7711", LocalDate.of(1969, 11, 5)),
                new Patient(4, "Albert", "Einstein", "(641) 119-6142", LocalDate.of(1955, 12, 28))
        };
    }

    /**
     * Fetches the Patient data from the data source.
     * If the array is null or empty, loads the data first.
     * 
     * @return patients the array of Patients
     */
    public Patient[] getPatients() {
        if (patients == null || patients.length == 0) {
            loadData();
        }
        return patients;
    }
}