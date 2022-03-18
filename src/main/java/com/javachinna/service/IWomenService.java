package com.javachinna.service;


import com.javachinna.model.*;

import java.util.Date;
import java.util.List;

public interface IWomenService {
    List<Complaint> RetrieveAllComplaints();
    void  AddComplaintAndAssignToUser(Complaint c, Long idUser);
    void  DeleteComplaint(Long idCom);
    void UpdateComplaint(Complaint c, Long idUser);
    Complaint RetrieveComplaint (Long idCom);


    List<ComplaintResponse> RetrieveAllComplaintsResponses();
    void AddComplaintResponseAndAssignToComplaintAndAdmin(ComplaintResponse response, Long idCom, Long idUser);
    void DeleteComplaintResponse(Long idResp);
    void UpdateComplaintResponse(ComplaintResponse response, Long idCom, Long idUser);


    List<Appointment> RetrieveAllAppointments();
    void AddAppointmentAndAssignDoctorAndUser(Appointment re, Long idDoctor, Long idUser);
    void DeleteAppointment(Long idApp);
    void DeleteAppointmentAfterfinalDate();
     void UpdateAppointment(Appointment re, Long idUser, Long idDoctor);
    Appointment RetrieveAppointment(Long idApp);
    List<Appointment> getAppointmentByClinicalAndSpeciality(Long idClinical , Profession profession);


    Clinical AddClinical(Clinical clinical);
    void AddDoctorAndAssignToClinical(User doctor, Long idClinical);
    User AddUser(User user);
    int GetNbrAppointmentDoctor(Long idDoctor);

    int GetIncomeDoctor(long idDoc, Date startDate, Date endDate);
    List<User> ScoreDoctor();

}
