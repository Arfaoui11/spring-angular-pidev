package com.javachinna.controller;


import com.javachinna.model.*;
import com.javachinna.QrCode.QRCodeGenerator;
import com.javachinna.service.*;
import com.javachinna.payLoad.Response;
import com.google.zxing.WriterException;
import com.itextpdf.text.DocumentException;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/Courses")
@CrossOrigin()
public class RestControllerForm {

    @Autowired
    private IServiceFormation iServiceFormation;

    @Autowired
    private IServicesQuiz iServicesQuiz;
    @Autowired
    private ISendEmailService iServiceEmail;
    @Autowired
    private SendEmailService emailSenderService;
    @Autowired
    private DatabaseFileService fileStorageService;

    private final PDFGeneratorService pdfGeneratorService;

    BadWordConfig badWordConfig  = new BadWordConfig();

    @Autowired
    private exportPdf export;

    private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/static/img/QRCode.png";

    public RestControllerForm(PDFGeneratorService pdfGeneratorService) {
        this.pdfGeneratorService = pdfGeneratorService;
    }

    @GetMapping("/pdf/generate")
    @ApiOperation(value = " Generate PDF ")
    public void generatePDF(HttpServletResponse response,String p1 ,String p2 ,String qrcode) throws IOException, DocumentException {

     // export.pdfReader();
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=pdf_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        this.pdfGeneratorService.exportFor(response,p1,p2,qrcode);
    }




    @PostMapping("/addFomateur")
    @ApiOperation(value = " ajouter Formateur ")
    public void ajouterFormateur(@RequestBody User formateur,HttpServletResponse response) {


        byte[] image = new byte[0];
        try {

            // Generate and Return Qr Code in Byte Array
            image = QRCodeGenerator.getQRCodeImage(formateur.getEmail(),250,250);

             QRCodeGenerator.generateQRCodeImage(formateur.getEmail(),250,250,QR_CODE_IMAGE_PATH);

            // Generate and Save Qr Code Image in static/image folder

        } catch (WriterException | IOException e) {

            e.printStackTrace();
        }
        // Convert Byte Array into Base64 Encode String
        String qrcode = Base64.getEncoder().encodeToString(image);
       // log.info(qrcode);


      //  generatePDF(response,formateur.getEmail(),formateur.getEmail(),qrcode);
        iServiceEmail.sendSimpleEmail("mahdijr2015@gmail.com"," add Formateur " ," add succesful ... ");
        iServiceFormation.ajouterFormateur(formateur);

    }

    @RequestMapping(value = {"/ajouterFormation"}, method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = " ajouter Formation ")
    public void addFormation(@RequestBody Formation formation){
        iServiceFormation.addFormation(formation);
    }


    @ApiOperation(value = "update Formation")
    @PutMapping("/updateFormation/{id}")
    @ResponseBody
    public void updateFormation(@RequestBody Formation formation,@PathVariable(name = "id") Integer idFormateur){
        iServiceFormation.updateFormation(formation,idFormateur);
    }


    @ApiOperation(value = "Delete Formation")
    @GetMapping("/deleteFormation/{id}")
    @ResponseBody
    public void deleteFormation(@PathVariable(name = "id") Integer idForm){
        iServiceFormation.deleteFormation(idForm);
    }

    @GetMapping("/exportPDF")
    @ResponseBody
    public ResponseEntity<InputStreamResource> exportPDF() throws IOException {
        ByteArrayInputStream bais = export.FormationPDFReport(iServiceFormation.afficherFormation());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition","Inline ;filename=formation.pdf");
        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bais));
    }

    @ApiOperation(value = "Retrieve All Formation")
    @GetMapping("/retrieveFormation")
    @ResponseBody
    public List<Formation> afficherFormation(){

        return iServiceFormation.afficherFormation();
    }




    @ApiOperation(value = "Retrieve All Formateur")
    @GetMapping("/retrieveFormateur")
    @ResponseBody
    public List<User> afficherFormateur(){
        return iServiceFormation.afficherFormateur();
    }

    @ApiOperation(value = "Retrieve All Apprenant")
    @GetMapping("/retrieveApprenant")
    @ResponseBody
    public List<User> afficherApprenant()
    {
        return iServiceFormation.afficherApprenant();
    }


    @RequestMapping(value = {"/ajouterApprenant"}, method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = " ajouter Apprenant ")
    public void ajouterApprenant(@RequestBody User apprenant)
    {
        iServiceFormation.ajouterApprenant(apprenant);
    }


    @RequestMapping(value = {"/ajouterEtAffecterFormationAFormateur/{id}"}, method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "ajouter Et Affecter Formation A Formateur  ")
    public void ajouterEtAffecterFormationAFormateur(@RequestBody Formation formation,@PathVariable(name = "id") Long idFormateur)
    {
        iServiceFormation.ajouterEtAffecterFormationAFormateur(formation, idFormateur);
    }


    @RequestMapping(value = {"/affecterApprenantFormation/{idA}/{idF}"}, method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "ajouter Et Affecter Formation A Formateur  ")
    public void affecterApprenantFormation(@PathVariable(name = "idA") Long idApprenant,@PathVariable(name = "idF") Integer idFormation)
    {
        iServiceFormation.affecterApprenantFormation(idApprenant, idFormation);
    }



    @PostMapping("/uploadFile/{idF}")
    @ResponseBody
    public Response uploadFile(@RequestParam("file") MultipartFile file, @PathVariable(name = "idF") Integer idFormation) {
        DatabaseFile fileName = fileStorageService.storeFile(file,idFormation);

        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/downloadFile/")
                .path(fileName.getFileName())
                .toUriString();

        return new Response(fileName.getFileName(), fileDownloadUri,
                file.getContentType(), file.getSize());
    }

    @PostMapping("/uploadMultipleFiles/{idF}")
    public List<Response> uploadMultipleFiles(@RequestParam("files") MultipartFile[] files,@PathVariable(name = "idF") Integer idFormation) {
        return Arrays.asList(files)
                .stream()
                .map(file -> uploadFile(file,idFormation))
                .collect(Collectors.toList());
    }


    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) throws FileNotFoundException {
        // Load file as Resource
        DatabaseFile databaseFile = fileStorageService.getFile(fileName);

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(databaseFile.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + databaseFile.getFileName() + "\"")
                .body(new ByteArrayResource(databaseFile.getData()));
    }




    @RequestMapping(value = {"/affecterApprenantFormationWithMax/{idA}/{idF}"}, method = RequestMethod.POST)
    @ResponseBody
    @ApiOperation(value = "ajouter Et Affecter Formation A Formateur with Max  ")
    public void affecterApprenantFormationWithMax(@PathVariable(name = "idA") Long idApprenant,@PathVariable(name = "idF") Integer idFormation)
    {
        iServiceFormation. affecterApprenantFormationWithMax(idApprenant, idFormation);
    }



    @ApiOperation(value = "Récupérer Revenu Brut Formateur Remuneration By Date")
    @GetMapping("/getFormateurRemunerationByDate/{idFormateur}/{startDate}/{endDate}")
    @ResponseBody
    public Integer getFormateurRemunerationByDate(@PathVariable(name = "idFormateur") Long idFormateur, @PathVariable(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateDebut, @PathVariable(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateFin)
    {
        return iServiceFormation.getFormateurRemunerationByDate(idFormateur, dateDebut, dateFin);
    }


    @ApiOperation(value = "Récupérer Revenu Brut Formatation")
    @GetMapping("/getRevenueByFormation/{idF}")
    @ResponseBody
    public Integer getRevenueByFormation(@PathVariable(name = "idF") Integer idFormation)
    {
        return iServiceFormation.getRevenueByFormation(idFormation);
    }


    @ApiOperation(value = " get Formateur Remuneration Max Salaire ")
    @GetMapping("/getFormateurRemunerationMaxSalaire")
    @ResponseBody
    public User getFormateurRemunerationMaxSalaire() throws MessagingException {
      return this.iServiceFormation.getFormateurRemunerationMaxSalaire();
    }

    @ApiOperation(value = " get Formateur Remuneration Max Salaire trier ")
    @GetMapping("/getFormateurRemunerationMaxSalaireTrie")
    @ResponseBody
    public TreeMap<Integer, User> getFormateurRemunerationMaxSalaireTrie()
    {
        return this.iServiceFormation.getFormateurRemunerationMaxSalaireTrie();
    }



    @ApiOperation(value = " get Formateur Max Salaire trier ")
    @GetMapping("/getFormateurMaxSalaireTrie")
    @ResponseBody
    public List<Object> getFormateurRemunerationByDateTrie()
    {
        return iServiceFormation.getFormateurRemunerationByDateTrie();
    }


    @ApiOperation(value = "Récupérer Nbr Apprenant By Formation")
    @GetMapping("/getNbrApprenantByFormation/{t}")
    @ResponseBody
    public Integer getNbrApprenantByFormation(@PathVariable(name = "t") String title)
    {
        return iServiceFormation.getNbrApprenantByFormation(title);
    }

    @ApiOperation(value = "Récupérer Nbr Formation By Apprenant ")
    @GetMapping("/getNbrFormationByApprenant/{id}/{domain}/{startDate}/{endDate}")
    @ResponseBody
    public Integer getNbrFormationByApprenant(@PathVariable(name = "id") Long idApp, @PathVariable(name = "domain") Domain domain, @PathVariable(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateDebut, @PathVariable(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date dateFin){
        return iServiceFormation.getNbrFormationByApprenant(idApp,domain, dateDebut, dateFin);
    }



    @ApiOperation(value = "Récupérer Nbr Apprenantt By Formation")
    @GetMapping("/NbrApprenantByFormation")
    public List<Object[]> getNbrApprenantByFormation()
    {
        return iServiceFormation.getNbrApprenantByFormation();
    }

    @ApiOperation(value = "Récupérer Liste Apprenant By Formation")
    @GetMapping("/ApprenantByFormation/{idF}")
    @ResponseBody
    public List<User> getApprenantByFormation(@PathVariable(name = "idF") Integer idF){
        return iServiceFormation.getApprenantByFormation(idF);
    }

    @ApiOperation(value = "Formateur with Max tarif")
    @GetMapping("/FormateurwithMaxHo")
    public User FormateurwithMaxHo()
    {
        return iServiceFormation.FormateurwithMaxHo();
    }



    @PostMapping("/addQuiz/{idF}")
    @ApiOperation(value = " add Quiz ")
    public void addQuiz(@RequestBody QuizCourses quiz, @PathVariable(name = "idF") Integer idF)
    {
        iServicesQuiz.addQuiz(quiz,idF);
    }

    @PostMapping("/addQuestionAndAsigntoQuiz/{idQuiz}")
    @ApiOperation(value = " add Question And Asign To Quiz ")
    public void addQuestionAndAsigntoQuiz(@RequestBody QuestionCourses question, @PathVariable(name = "idQuiz")  Integer idQuiz)
    {
        iServicesQuiz.addQuestionAndAsigntoQuiz(question, idQuiz);
    }




    @ApiOperation(value = "get Quiz Question")
    @GetMapping("/getQuizQuestion/{id}")
    public List<QuestionCourses> getQuizQuestion(@PathVariable("id") Integer idQ)
    {
        return iServicesQuiz.getQuizQuestion(idQ);
    }



    @ApiOperation(value = "get Quiz Question M2")
    @GetMapping("/getQuizQuestionM")
    public List<QuestionCourses> getQuestions()
    {
        return iServicesQuiz.getQuestions();
    }


    @PostMapping("/SaveScore/{idU}/{idQ}")
    @ApiOperation(value = " Save Score Quiz ")
    public Integer saveScore(@RequestBody Result result,@PathVariable(name = "idU") Long idUser,@PathVariable(name = "idQ") Integer idQuiz)
    {
       return   this.iServicesQuiz.saveScore(result,idUser,idQuiz);
    }

/*
    @ApiOperation(value = " Apprenent With Max Score In Formation ")
    @GetMapping("/ApprenentwithMaxScoreInFormation/{idF}")
    @ResponseBody
    public User ApprenentwithMaxScoreInFormation(@PathVariable(name = "idF") Integer id)
    {
        return this.iServicesQuiz.ApprenentwithMaxScoreInFormation(id);
    }


 */
    @ApiOperation(value = " Max Score In Formation")
    @GetMapping("/MaxScoreInFormation")
    @ResponseBody
    public Integer MaxScoreInFormation()
    {
        return this.iServicesQuiz.MaxScoreInFormation();
    }


/*
    @PostMapping("/addLikes/{idF}")
    @ApiOperation(value = " add Likes ")
    void likeFormation(@PathVariable(name = "idF") Integer idF){
        iServiceFormation.likeFormation(idF);
    }


    @PostMapping("/addDisLikes/{idF}")
    @ApiOperation(value = " add DisLikes ")
    void dislikeFormation(@PathVariable(name = "idF") Integer idF)
    {
        iServiceFormation.dislikeFormation(idF);
    }


 */

    @PostMapping("/likeFormationWithRate/{idF}/{nbr}")
    @ApiOperation(value = " add Rate ")
    public void likeFormationWithRate(@PathVariable(name = "idF") Integer idF,@PathVariable(name = "nbr") Integer rate)
    {
     //   iServiceFormation.dislikeFormationWIthRate(idF, rate);
    }

    @PutMapping("/FormationWIthRate/{idF}/{nbr}")
    @ApiOperation(value = " add Rating ")
    public void FormationWIthRate(@PathVariable(name = "idF") Integer idF,@PathVariable(name = "nbr") Double rate)
    {
        iServiceFormation.FormationWithRate(idF, rate);
    }

    @PostMapping("/addComments/{idF}/{idU}")
    @ApiOperation(value = " ajouter Comments ")
    @ResponseBody
    public void addComments(@RequestBody PostComments postComments,@PathVariable(name = "idF") Integer idF,@PathVariable(name = "idU") Long idUser)
    {
        PostComments p = new PostComments();
        p.setMessage(badWordConfig.filterText(postComments.getMessage()));
        p.setLikes(postComments.getLikes());
        p.setDislikes(postComments.getDislikes());
        p.setCreateAt(postComments.getCreateAt());
        iServiceFormation.addComments(p,idF,idUser);

    }

    @ApiOperation(value = "Delete Comments")
    @GetMapping("/deleteComments/{id}")
    @ResponseBody
    public void deleteComments(@PathVariable(name = "id") Integer idC)
    {
        iServiceFormation.deleteComments(idC);
    }



    @ApiOperation(value = " get Apprenant With Score Quiz ")
    @GetMapping("/getApprenantWithScoreQuiz/{idF}")
    @ResponseBody
    List<Object> getApprenantWithScoreQuiz(@PathVariable("idF") Integer id)
    {
        return this.iServicesQuiz.getApprenantWithScoreQuiz(id);
    }

    @ApiOperation(value = " get Apprenant With Score  ")
    @GetMapping("/ApprenentwithMaxScore/{idF}")
    @ResponseBody
    public Object ApprenentwithMaxScore(@PathVariable("idF") Integer id)
    {
        return iServicesQuiz.ApprenentwithMaxScore(id);
    }

    @ApiOperation(value = " get Apprenant With Score in All Quiz formation ")
    @GetMapping("/ApprenentwithMaxScoreQuiz/{idF}")
    @ResponseBody
    public User ApprenentwithMaxScoreQuiz(@PathVariable("idF") Integer id)
    {
        return iServicesQuiz.ApprenentwithMaxScoreQuiz(id);
    }


    @ApiOperation(value = " Search Multiple  ")
    @GetMapping("/SearchMultiple/{keyword}")
    @ResponseBody
    public List<Formation> SearchMultiple(@PathVariable("keyword") String key)
    {
        return iServiceFormation.SearchMultiple(key);
    }

    @ApiOperation(value = " get Quiz By Formation ")
    @GetMapping("/getQuizByFormation/{id}")
    @ResponseBody
    public List<QuizCourses> getQuizByFormation(@PathVariable("id") Integer idF)
    {
        return this.iServicesQuiz.getQuizByFormation(idF);
    }

    @ApiOperation(value = "Delete Quiz")
    @GetMapping("/DeleteQuiz/{id}")
    @ResponseBody
    public void DeleteQuiz(@PathVariable("id") Integer idQ)
    {
        this.iServicesQuiz.DeleteQuiz(idQ);
    }


    @ApiOperation(value = " Search Historique ")
    @PostMapping("/SearchHistorique/{keyword}")
    @ResponseBody
    public void SearchHistorique(@PathVariable("keyword") String keyword)
    {
        iServiceFormation.SearchHistorique(keyword);
    }


    @ApiOperation(value = " get Top Score  ")
    @GetMapping("/getTopScore")
    @ResponseBody
    public List<Result> getTopScore()
    {
     return iServicesQuiz.getTopScore();
    }

    @ApiOperation(value = " get Score  ")
    @GetMapping("/getScore/{id}")
    @ResponseBody
    public Integer getScore(@PathVariable("id") Long idU)
    {
        return iServicesQuiz.getScore(idU);
    }

}
