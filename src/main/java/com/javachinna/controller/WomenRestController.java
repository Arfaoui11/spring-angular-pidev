package com.javachinna.controller;



import com.javachinna.model.*;
import com.javachinna.service.ISendEmailService;
import com.javachinna.service.IWomenService;
import com.javachinna.service.PDFGeneratorService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/heplpspace")
public class WomenRestController {

    @Autowired
    IWomenService womenService;
    @Autowired
    ISendEmailService iSendEmailService;

    private final PDFGeneratorService pdfGeneratorService;

    public WomenRestController(PDFGeneratorService pdfGeneratorService) {
        this.pdfGeneratorService = pdfGeneratorService;
    }
    @GetMapping("/pdf/generate")
    public void generatePDF(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        this.pdfGeneratorService.export(response);
    }

    @ApiOperation(value = "retrieve All Complaints ")
    @GetMapping("/retrieve-All-Complaints")
    @ResponseBody
    public List<Complaint> RetrieveAllComplaints()
    {
      return  womenService.RetrieveAllComplaints();
    }


    @ApiOperation(value = "Add Complaint And Assign To User ")
    @PostMapping("/AddComplaintAndAssignToUser/{idU}")
    @ResponseBody
    public void  AddComplaintAndAssignToUser(@RequestBody Complaint c, @PathVariable("idU") Long idUser)
    {
     //  iSendEmailService.sendSimpleEmail("farouk.hajjej@esprit.tn","your complaint is taken care of!","Complaint Response");
        womenService.AddComplaintAndAssignToUser(c,idUser);
    }


    @ApiOperation(value = "update Complaint By Id ")
    @PutMapping("/updateComplaintById/{idU}")
    @ResponseBody
    public void UpdateComplaint(@RequestBody Complaint c, @PathVariable(name="idU") Long idUser)
    {
        womenService.UpdateComplaint(c,idUser);
    }


    @ApiOperation(value = "delete Complaint By Id ")
    @DeleteMapping("/deleteComplaintById/{idCom}")
    @ResponseBody
    public void DeleteComplaint(@PathVariable("idCom") Long idCom)
    {
        womenService.DeleteComplaint(idCom);

    }


    @ApiOperation(value = "Retrieve Complaint by ID ")
    @GetMapping("/retrieve-Complaint-by-ID/{idCom}")
    @ResponseBody
    public Complaint RetrieveComplaint(@PathVariable("idCom") Long idCom)
    {
        return womenService.RetrieveComplaint(idCom);
    }


    @ApiOperation(value = "Retrieve All Complaint Responses ")
    @GetMapping("/retrieve-All-ComplaintResponses")
    @ResponseBody
    public List<ComplaintResponse> RetrieveAllComplaintsResponses(){
      return   womenService.RetrieveAllComplaintsResponses();
    }


    @RequestMapping(value = {"/AddComplaintResponseAndAssignToComplaintAndAdmin/{idCom}/{idAdmin}"}, method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "Add Complaint Response And Assign To Complaint And Admin ")
    void AddComplaintResponseAndAssignToComplaintAndAdmin(@RequestBody ComplaintResponse response, @PathVariable(name = "idCom") Long idCom, @PathVariable(name = "idAdmin") Long idUser){
        womenService.AddComplaintResponseAndAssignToComplaintAndAdmin(response,idCom,idUser);
    }


    @RequestMapping(value = {"/UpdateComplaintResponse/{idCom}/{idAdmin}"}, method = RequestMethod.PUT)
    @ResponseBody
    @ApiOperation(value = "Update Complaint Response  ")
    public void UpdateComplaintResponse(@RequestBody ComplaintResponse response, @PathVariable(name = "idCom") Long idCom, @PathVariable(name="idU") Long idUser){
       womenService.UpdateComplaintResponse(response, idCom, idUser);
    }
    @ApiOperation(value = "Delete Complaint Response By Id ")
    @DeleteMapping("/deleteComplaintResponsetById/{idResp}")
    @ResponseBody
    public void DeleteComplaintResponse(@PathVariable(name = "idResp") Long idResp){
        womenService.DeleteComplaintResponse(idResp);

    }



    @ApiOperation(value = "Retrieve All Appointment ")
    @GetMapping("/retrieve-All-Appointment")
    @ResponseBody
    public List<Appointment> RetrieveAllAppointments(){
       // pdfGeneratorService.export();
        return womenService.RetrieveAllAppointments();
    }


    @ApiOperation(value = "get Appointment By Clinical And Speciality")
    @GetMapping("/getAppointmentByClinicalAndSpeciality/{idClinical}/{speciality}")
    @ResponseBody
    public List<Appointment> getAppointmentByClinicalAndSpeciality(@PathVariable(name = "idClinical") Long idClinical, @PathVariable(name = "speciality") Profession profession) {
      return womenService.getAppointmentByClinicalAndSpeciality(idClinical,profession);
    }

    @ApiOperation(value = "Retrieve Appointment by ID ")
    @GetMapping("/retrieve-Appointment-by-ID/{id}")
    @ResponseBody
    public Appointment RetrieveAppointment(@PathVariable("id") Long idApp){
        return womenService.RetrieveAppointment(idApp);
    }


    @RequestMapping(value = {"/addRdvAndAssignMedAndPatient/{idD}/{idU}"}, method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "add Rdv And Assign Doctor And Patient ")
    public void AddAppointmentAndAssignDoctorAndUser(@RequestBody Appointment re, @PathVariable(name = "idD") Long idDoctor, @PathVariable(name = "idU") Long idUser){
        womenService.AddAppointmentAndAssignDoctorAndUser(re,idDoctor,idUser);
    }


    @ApiOperation(value = "Update Apppointment By Id ")
    @PutMapping("/updateApppointmentById/{idU}/{idDoc}")
    @ResponseBody
    public void UpdateAppointment(@RequestBody Appointment re, @PathVariable("idU") Long idUser, @PathVariable("idDoc") Long idDoctor){
       womenService.UpdateAppointment(re,idUser,idDoctor);
    }

    @ApiOperation(value = "Delete Appointment By Id ")
    @DeleteMapping("/deleteAppointmentById/{idApp}")
    @ResponseBody
    public void DeleteAppointment(@PathVariable("idApp") Long idApp){
        womenService.DeleteAppointment(idApp);
    }


    @ApiOperation(value = "Add Clinical ")
    @RequestMapping(value = {"/addClinical"}, method = RequestMethod.POST)
    @ResponseBody
    public Clinical AddClinical(@RequestBody Clinical clinical){
        return  womenService.AddClinical(clinical);
    }


    @RequestMapping(value = {"/AddDoctorAndAssignToClinical/{idCl}"}, method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "add Doctor And Assign To Clinical ")
    public void AddDoctorAndAssignToClinical(@RequestBody User doctor, @PathVariable(name ="idCl") Long idClinical){
        womenService.AddDoctorAndAssignToClinical(doctor,idClinical);
    }


    @RequestMapping(value = {"/addUser"}, method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "Add User ")
    public User AddUser(@RequestBody User user){
        return womenService.AddUser(user);
    }


    @ApiOperation(value = "get Nbr Appointment By Doctor")
    @GetMapping("/getNbrAppointmentDoctor/{idD}")
    @ResponseBody
    public int GetNbrAppointmentDoctor(@PathVariable(name = "idD") Long idDoctor){
        return womenService.GetNbrAppointmentDoctor(idDoctor);
    }


    @ApiOperation(value = "get Revenu Doctor")
    @GetMapping("/getRevenuMedecin/{idDoc}/{startDate}/{endDate}")
    @ResponseBody
    public int GetIncomeDoctor(@PathVariable(name = "idDoc") long idDoc, @PathVariable(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate, @PathVariable(name="endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate){
        return womenService.GetIncomeDoctor(idDoc,startDate,endDate);
    }
    @ApiOperation(value = "get Score Doctor")
    @GetMapping("/getScoreDoctor")
    @ResponseBody
    public List<User> ScoreDoctor(){
        return womenService.ScoreDoctor();
    }


}