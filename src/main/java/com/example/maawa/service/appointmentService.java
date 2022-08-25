package com.example.maawa.service;

import com.example.maawa.dto.EditAppointmentForm;
import com.example.maawa.exception.apiException;
import com.example.maawa.model.*;
import com.example.maawa.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class appointmentService {

    private final appointmentRepository appointmentRepository;

    private final myUserRepository userRepository;
    private final serviceRepository serviceRepository;
    private final timeSlotRepository timeSlotRepository;


    public List<Appointment> getAppointments() {
        return appointmentRepository.findAll();
    }


    public void addAppointment(Appointment appointment , MyUser user) {
        MyUser clinic=userRepository.findMyUserById(appointment.getClinicId());
        com.example.maawa.model.Service service=serviceRepository.findServiceById(appointment.getServiceId());
        timeSlot timeSlot=timeSlotRepository.findTimeSlotById(appointment.getTimeSlotId());

        if (clinic==null) {
            throw new apiException("Wrong clinicId") ;
        } else if (service==null) {
            throw new apiException("Wrong serviceId");
        } else if (timeSlot==null) {
            throw new apiException("Wrong time slotId");
        }
        if (service.getClinicId()!=clinic.getId()){
            throw new apiException("Wrong serviceId");
        }
        if (timeSlot.getIsAvailable()){
            timeSlot.setIsAvailable(false);
        }else {
            throw new apiException("This time is not available!");
        }
        appointment.setUserId(user.getId());
        appointment.setTotalPrice(service.getPrice());
        LocalDateTime time = LocalDateTime.now();
        appointment.setDateReceived(time);

        appointmentRepository.save(appointment);
    }

    public void deleteAppointment(Integer id , MyUser user) {
        Appointment appointment=appointmentRepository.findAppointmentById(id);
        if(appointment==null){
            throw new apiException("Wrong Appointment ID!");
        }
        if (appointment.getUserId()!=user.getId()){
            throw new apiException("Sorry , You do not have the authority to cancel this appointment");
        }else if (appointment.getStatus().equals("complete")){
            throw new apiException("Sorry , You can not cancel this appointment because its completed!");
        }
        timeSlot timeSlot=timeSlotRepository.findTimeSlotById(appointment.getTimeSlotId());
        timeSlot.setIsAvailable(true);
        appointmentRepository.delete(appointment);
    }

    public void updateAppointment(Integer id, EditAppointmentForm appointment , MyUser user) {
        Appointment appointment1=appointmentRepository.findAppointmentById(id);
        timeSlot timeSlot=timeSlotRepository.findTimeSlotById(appointment.getTimeSlotId());
        com.example.maawa.model.Service service=serviceRepository.findServiceById(appointment.getServiceId());

        if(appointment1==null){
            throw new apiException("Wrong appointment ID!");
        }else if (appointment1.getUserId()!=user.getId()){
            throw new apiException("Sorry , You do not have the authority to update this appointment");
        }else if (appointment1.getStatus().equals("complete")){
            throw new apiException("Sorry , You can not update this appointment because its completed!");
        }

        if (service == null) {
            throw new apiException("Wrong serviceId!");
        }
        appointment1.setServiceId(appointment.getServiceId());
        appointment1.setTotalPrice(service.getPrice());

        if (!timeSlot.getIsAvailable()){
            throw new apiException("This time is not available!");
        }
        appointment1.setTimeSlotId(appointment.getTimeSlotId());
        timeSlot.setIsAvailable(false);
        timeSlotRepository.save(timeSlot);

        appointmentRepository.save(appointment1);
    }

    public void changeStatus(String status,Integer id, MyUser user){
        Appointment appointment=appointmentRepository.findAppointmentById(id);
        if (appointment==null){
            throw new apiException("Wrong appointmentId!");
        }
        if (appointment.getClinicId()!=user.getId()){
            throw new apiException("Sorry , You do not have the authority to update this appointment");
        }
        if(!status.equals("inProgress")&&!status.equals("complete")){
            throw new apiException("Status can be inProgress or complete only!");
        }
        appointment.setStatus(status);
        appointmentRepository.save(appointment);
    }

    public List<Appointment> getMyAppointment(MyUser user){
            return appointmentRepository.findAllByUserId(user.getId());
    }

}
