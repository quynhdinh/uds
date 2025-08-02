package com.quynhdv.uds.util;

import java.util.List;
import org.json.JSONArray;
import com.quynhdv.uds.dto.AppointmentDTO;

public class JSONConverterUtil {
    public static String convertAppointmentListToJSON(List<AppointmentDTO> appointments) {
        JSONArray jsonArray = new JSONArray();
        appointments.forEach(appointment -> {
            jsonArray.put(appointment.toJSON());
        });
        return jsonArray.toString(2);
    }
}