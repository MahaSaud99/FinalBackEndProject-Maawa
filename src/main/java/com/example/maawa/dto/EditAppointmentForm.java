package com.example.maawa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor @Data
public class EditAppointmentForm {
    Integer serviceId;
    Integer timeSlotId;
}
