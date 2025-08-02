package com.quynhdv.uds.dto;

import java.time.LocalDateTime;
import com.quynhdv.uds.model.Patient;
import org.json.JSONObject;

public class AppointmentDTO {
    private int id;
    private LocalDateTime date;
    private Patient patient;
    
    public AppointmentDTO(int id, LocalDateTime date, Patient patient) {
        this.id = id;
        this.date = date;
        this.patient = patient;
    }
    
    public int getId() {
        return id;
    }
    
    public LocalDateTime getDate() {
        return date;
    }
    
    public Patient getPatient() {
        return patient;
    }
    
    public JSONObject toJSON() {
        JSONObject json = new JSONObject();
        json.put("id", id);
        json.put("date", date.toString());
        
        JSONObject patientJson = new JSONObject();
        patientJson.put("id", patient.getId());
        patientJson.put("firstName", patient.getFirstName());
        patientJson.put("lastName", patient.getLastName());
        patientJson.put("phoneNumber", patient.getPhoneNumber());
        patientJson.put("dateOfBirth", patient.getDateOfBirth().toString());
        
        json.put("patient", patientJson);
        
        return json;
    }
}
