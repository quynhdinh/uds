package com.quynhdv.uds.model;

import java.time.LocalDateTime;


public class Appointment {
    private int id;
    private LocalDateTime date;
    private int patientId;
    public Appointment(int id, LocalDateTime date, int patientId) {
        this.id = id;
        this.date = date;
        this.patientId = patientId;
    }
    public int getId() {
        return id;
    }
    public LocalDateTime getDate() {
        return date;
    }
    public int getPatientId() {
        return patientId;
    }  
}
