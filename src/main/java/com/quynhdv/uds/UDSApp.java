package com.quynhdv.uds;

import java.util.List;
import com.quynhdv.uds.service.AppointmentService;
import com.quynhdv.uds.dto.AppointmentDTO;
import com.quynhdv.uds.util.JSONConverterUtil;

public class UDSApp {
    public static void main(String[] args) {

        System.out.println("Hello! Welcome to the UDS Dental Appointment Management!");
        System.out.println("__________________________________________________________________________");

        AppointmentService appointmentService = new AppointmentService();

        int quarter = 1;
        int year = 2025;

        System.out.println("Finding appointments for Quarter " + quarter + " of year " + year + ":");
        System.out.println("__________________________________________________________________________");

        try {
            List<AppointmentDTO> quarterlyAppointments = appointmentService.getAppointmentsByQuarter(quarter, year);

            if (quarterlyAppointments.isEmpty()) {
                System.out.println("No appointments found for Quarter " + quarter + " of " + year);
            } else {
                String appointmentsJson = JSONConverterUtil.convertAppointmentListToJSON(quarterlyAppointments);
                System.out.println(
                        "Appointments for Quarter " + quarter + " of " + year + " (sorted by date descending):");
                System.out.println(appointmentsJson);
            }
        } catch (Exception e) {
            System.err.println("Error retrieving appointments: " + e.getMessage());
        }

        System.out.println("\nExiting the application...Goodbye!");
    }
}