package com.javachinna.service;


import com.javachinna.model.*;
import com.javachinna.QrCode.QRCodeGenerator;
import com.javachinna.repo.*;
import com.google.zxing.WriterException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TreeMap;


@Slf4j
@Service
public class ServiceFormation implements IServiceFormation {

    @Autowired
    private UserRepository iUserRepo;

    @Autowired
    private IFormationRepo iFormationRepo;
    @Autowired
    private IResultRepo iResultRepo;
    @Autowired
    private ISearchRepo iSearchRepo;


    @Autowired
    private SendEmailService emailSenderService;

    private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/static/img/QRCode.png";





    @Override
    public void ajouterFormateur(User formateur) {
        iUserRepo.save(formateur);
    }

    @Override
    public void addFormation(Formation formation) {
        iFormationRepo.save(formation);
    }

    @Override
    public void updateFormation(Formation formation, Integer idFormateur) {
        Formation f = iFormationRepo.findById(idFormateur).orElse(null);

        f.setTitle(formation.getTitle());
        f.setDomain(formation.getDomain());
        f.setStart(formation.getStart());
        f.setEnd(formation.getEnd());
        f.setFrais(formation.getFrais());
        f.setNbrHeures(formation.getNbrHeures());
        f.setNbrMaxParticipant(formation.getNbrMaxParticipant());

        //  formation.setFormateur(formateur);
        iFormationRepo.save(f);
    }

    @Override
    public void deleteFormation(Integer idFormation) {
        Formation f = iFormationRepo.findById(idFormation).orElse(null);
        iFormationRepo.delete(f);
    }


    @Override
    public List<Formation> afficherFormation() {
        List<Formation> f =   (List<Formation>)iFormationRepo.findAll();
            return  f;
    }



    @Override
    public List<User> afficherFormateur() {

       return iUserRepo.getFormateur();
    }

    @Override
    public List<User> afficherApprenant() {
        return iUserRepo.getApprenant();
    }

    @Override
    public User FormateurwithMaxHo() {
        return iUserRepo.FormateurwithMaxHo();
    }


    @Override
    @Scheduled(cron = "0 0/20 * * * *")
  //  @Scheduled(cron = "0 0 9 28 * ?")
    public User getFormateurRemunerationMaxSalaire() {


        int max = 0;

        TreeMap<Integer, String> map = new TreeMap<>();

        User u = new User();

        LocalDate currentdDate1 = LocalDate.now();
        Date date = new Date();

        boolean status = false;


        Calendar calLast = Calendar.getInstance();
        Calendar calFirst = Calendar.getInstance();
        calLast.set(Calendar.DATE, calLast.getActualMaximum(Calendar.DATE));
        calFirst.set(Calendar.DATE, calFirst.getActualMinimum(Calendar.DATE));

        Date lastDayOfMonth = calLast.getTime();
        Date firstDayOfMonth = calFirst.getTime();


        ZoneId defaultZoneId = ZoneId.systemDefault();

        Date dd = Date.from(currentdDate1.minusDays(15).atStartOfDay(defaultZoneId).toInstant());
        Date df = Date.from(currentdDate1.plusDays(15).atStartOfDay(defaultZoneId).toInstant());


        log.info("start : " + firstDayOfMonth);
        log.info("last : " + lastDayOfMonth);

        if (firstDayOfMonth.equals(firstDayOfMonth)) {
            status = true;
        }


      //   if(lastDayOfMonth.equals(lastDayOfMonth))
       //  {
        for (Formation f : this.iFormationRepo.findAll()) {
            if (f.getStart().after(firstDayOfMonth) && f.getEnd().before(lastDayOfMonth)) {

                map.put(this.iFormationRepo.getFormateurRemunerationByDate(f.getFormateur().getId(), firstDayOfMonth, lastDayOfMonth), f.getFormateur().getId().toString());
                if (this.iFormationRepo.getFormateurRemunerationByDate(f.getFormateur().getId(), firstDayOfMonth, lastDayOfMonth) > max) {
                    max = this.iFormationRepo.getFormateurRemunerationByDate(f.getFormateur().getId(), firstDayOfMonth, lastDayOfMonth);
                }
            }
        }

        if (status) {


            for (Formation f : this.iFormationRepo.findAll()) {

                for (User user : iUserRepo.getFormateurByFormation(f.getIdFormation())) {
                    user.setSalary(this.iFormationRepo.getFormateurRemunerationByDate(user.getId(), firstDayOfMonth, lastDayOfMonth));
                    iUserRepo.save(user);
                }
            }
            log.info(" liste" + map);
            log.info(" Max Salaire " + max);

            for (Formation f : this.iFormationRepo.findAll()) {
                if (f.getStart().after(firstDayOfMonth) && f.getEnd().before(lastDayOfMonth)) {
                    if (this.iFormationRepo.getFormateurRemunerationByDate(f.getFormateur().getId(), firstDayOfMonth, lastDayOfMonth) == max) {

                        u = this.iUserRepo.findById(f.getFormateur().getId()).orElse(null);
                    }
                }
            }

            u.setSalary(max + 200 );
            iUserRepo.save(u);
            this.emailSenderService.sendSimpleEmail(u.getEmail(), "we have max houre of travel ", "we have max houre of travel we elevate salary with 200 $  : " + u.getSalary()+ "$  Name " + u.getLastName() + "--" + u.getFirstName() + " . ");
            return u;
        }


     // }
      return null;

    }


    public TreeMap<Integer, User> getFormateurRemunerationMaxSalaireTrie() {

        TreeMap<Integer, User> map = new TreeMap<>();



        LocalDate currentdDate1 =  LocalDate.now();

        ZoneId defaultZoneId = ZoneId.systemDefault();

        Date dd =Date.from(currentdDate1.minusDays(15).atStartOfDay(defaultZoneId).toInstant());
        Date df =Date.from(currentdDate1.plusDays(15).atStartOfDay(defaultZoneId).toInstant());

        for (Formation f: this.iFormationRepo.findAll()) {
            if (f.getStart().after(dd) && f.getEnd().before(df) )
            {
                map.put(this.iFormationRepo.getFormateurRemunerationByDate(f.getFormateur().getId(),dd,df),f.getFormateur());

            }

        }
      //  List<Map.Entry<Integer, User>> singleList = map.entrySet().stream().collect(Collectors.toList());
        return map;
    }

    @Override
    public List<Object> getFormateurRemunerationByDateTrie() {
        LocalDate currentdDate1 =  LocalDate.now();



        ZoneId defaultZoneId = ZoneId.systemDefault();

        Date dd =Date.from(currentdDate1.minusDays(15).atStartOfDay(defaultZoneId).toInstant());
        Date df =Date.from(currentdDate1.plusDays(15).atStartOfDay(defaultZoneId).toInstant());

        return this.iFormationRepo.getFormateurRemunerationByDateTrie(dd,df);
    }

    @Override
   // @Scheduled(cron = "0 0/1 * * * *")
    public void CertifactionStudents() {

        boolean status = false;
        boolean fin;


        try {


            for (Formation f : iFormationRepo.findAll())
            {
                for (User u : iUserRepo.getApprenantByFormation(f.getIdFormation()))
                {
                    if(iResultRepo.getScore(u.getId()) >= 200 && iResultRepo.getScore(u.getId()) <=250 && iResultRepo.getNbrQuiz(u.getId()) == 5 )
                    {
                        fin=false;

                        for (Result r : iResultRepo.getResultByIdUAndAndIdF(u.getId(),f.getIdFormation()))
                        {

                            if (!r.isStatus())
                            {

                                r.setStatus(true);
                                iResultRepo.save(r);
                                status=true;
                            }

                            if (status && !fin)
                            {
                                log.info( " Status  true ");
                                QRCodeGenerator.generateQRCodeImage(f.getDomain().toString(),150,150,QR_CODE_IMAGE_PATH);
                                this.emailSenderService.sendSimpleEmail(u.getEmail()," Congratulations Mr's : "+u.getLastName()+" "+u.getFirstName()+" you have finished your Courses  " ," Certification At : "+ new Date()+"  in Courses of Domain "+f.getDomain()+" "+" And Niveau : " +f.getLevel() +" .");
                                fin=true; /// return /////
                            }


                        }




                    }

                }

            }








        } catch (WriterException | IOException e) {

            e.printStackTrace();
        }



    }

    @Override
    public List<Formation> SearchMultiple(String key) {

        if (key.equals(""))
        {
            return (List<Formation>) iFormationRepo.findAll();
        }else
        {
            return iFormationRepo.rech(key);
        }

    }



    @Override
    public void ajouterApprenant(User apprenant) {
            iUserRepo.save(apprenant);
    }

    @Override
    public void ajouterEtAffecterFormationAFormateur(Formation formation, Long idFormateur) {

        User formateur = iUserRepo.findById(idFormateur).orElse(null);

        LocalDate currentdDate1 =  LocalDate.now();

        ZoneId defaultZoneId = ZoneId.systemDefault();

        Date dd =Date.from(currentdDate1.minusMonths(3).atStartOfDay(defaultZoneId).toInstant());
        Date df =Date.from(currentdDate1.plusMonths(3).atStartOfDay(defaultZoneId).toInstant());

            if (this.iFormationRepo.nbrCoursesParFormateur(idFormateur,dd,df) <2 && formateur.getProfession() == Profession.FORMER.FORMER)
            {
                formation.setLikes(0);
                formation.setDislikes(0);
                formation.setFormateur(formateur);
                iFormationRepo.save(formation);
            }else
            {
                this.emailSenderService.sendSimpleEmail(formateur.getEmail(),"we don't have acces to have two coursus in same semester " ,"we have 2 (MAX formation in this semester) NAME : "+formateur.getLastName() +" "+formateur.getFirstName() +" .");
                log.info("we have 2 (MAX formation in this Semester ");
            }

    }


    public Formation getFile(Integer fileId) throws FileNotFoundException {
        return iFormationRepo.findById(fileId).orElseThrow(() -> new FileNotFoundException("File not found with id " + fileId));
    }


    @Override
   // @Scheduled(cron = "*/30 * * * * *")
    public void affecterApprenantFormationWithMax(Long idApprenant, Integer idFormation) {

        Formation formation = iFormationRepo.findById(idFormation).orElse(null);

        User apprenant = iUserRepo.findById(idApprenant).orElse(null);

        LocalDate currentdDate1 =  LocalDate.now();
        User user = new User();

        ZoneId defaultZoneId = ZoneId.systemDefault();

        Date dd =Date.from(currentdDate1.minusMonths(3).atStartOfDay(defaultZoneId).toInstant());
        Date df =Date.from(currentdDate1.plusMonths(3).atStartOfDay(defaultZoneId).toInstant());

        ///User with gifts Free for Max Score


        if(iResultRepo.getScore(idApprenant)==null)
        {
            if (iFormationRepo.getNbrApprenantByFormationId(idFormation) < formation.getNbrMaxParticipant() && apprenant.getProfession() == Profession.LEARNER) {

                if (iFormationRepo.getNbrFormationByApprenant(idApprenant, formation.getDomain(), dd, df) < 2 ) {

                    log.info("nbr "+iFormationRepo.getNbrFormationByApprenant(idApprenant, formation.getDomain(), dd, df));
                    formation.getApprenant().add(apprenant);
                    iFormationRepo.save(formation);


                } else {
                    this.emailSenderService.sendSimpleEmail(apprenant.getEmail(), "we don't have acces to add two coursus in same domain ", "we have 2 (MAX formation in this domain) NAME : " + apprenant.getLastName() + " " + apprenant.getFirstName() + " .");
                    log.info("this apprenant we have 2 (MAX formation in this domain ");
                }
            } else {
                this.emailSenderService.sendSimpleEmail(apprenant.getEmail(), "Learner list complete  ", " We have in this courses " + formation.getNbrMaxParticipant() + " number of learner Maximum" + apprenant.getLastName() + " - " + apprenant.getFirstName() + "  .");
                log.info(" Learner list complete Max learner " + formation.getNbrMaxParticipant());
            }

        }else
        {
            for(Formation form : iFormationRepo.findAll()) {
                if(iUserRepo.getApprenantWithScoreForGifts(form.getIdFormation()).size()!=0)
                {
                    user = iUserRepo.getApprenantWithScoreForGifts(form.getIdFormation()).get(0);
                    //}


                    if (iFormationRepo.getNbrApprenantByFormationId(idFormation) < formation.getNbrMaxParticipant() && apprenant.getProfession() == Profession.LEARNER) {

                        if (iFormationRepo.getNbrFormationByApprenant(idApprenant, formation.getDomain(), dd, df) < 2 || apprenant.getId().equals(user.getId())) {
                            if (iFormationRepo.getNbrFormationByApprenant(idApprenant, formation.getDomain(), dd, df) < 3) {

                                log.info("nbr "+iFormationRepo.getNbrFormationByApprenant(idApprenant, formation.getDomain(), dd, df));
                                formation.getApprenant().add(apprenant);
                                iFormationRepo.save(formation);
                            } else {
                                log.info("this apprenant we have 3 (MAX formation in this domain ");
                                this.emailSenderService.sendSimpleEmail(apprenant.getEmail(), "we don't have acces to add two coursus in same domain ", "we have 2 (MAX formation in this domain) NAME : " + apprenant.getLastName() + " " + apprenant.getFirstName() + " .");
                            }

                        } else {
                            this.emailSenderService.sendSimpleEmail(apprenant.getEmail(), "we don't have acces to add two coursus in same domain ", "we have 2 (MAX formation in this domain) NAME : " + apprenant.getLastName() + " " + apprenant.getFirstName() + " .");
                            log.info("this apprenant we have 2 (MAX formation in this domain ");
                        }
                    } else {
                        this.emailSenderService.sendSimpleEmail(apprenant.getEmail(), "Learner list complete  ", " We have in this courses " + formation.getNbrMaxParticipant() + " number of learner Maximum" + apprenant.getLastName() + " - " + apprenant.getFirstName() + "  .");
                        log.info(" Learner list complete Max learner " + formation.getNbrMaxParticipant());
                    }
                }


            }
        }




    }
/*
    @EventListener(ApplicationReadyEvent.class)
    public void sendMail() throws MessagingException {
        emailSenderService.sendSimpleEmailWithFils("mahdijr2015@gmail.com","we don't add two coursus in same domain " ,"this apprenant we have 2 (MAX formation in this domain","/Users/macos/Downloads/Examen-SOA-Révision.pdf");
    }
*/


    ///////////////  Affectation 3adiya  ////////////////////
    @Override
    public void affecterApprenantFormation(Long idApprenant, Integer idFormation) {
        User apprenant = iUserRepo.findById(idApprenant).orElse(null);
        Formation formation = iFormationRepo.findById(idFormation).orElse(null);

        formation.getApprenant().add(apprenant);
        iFormationRepo.save(formation);
    }



    @Override
    public Integer nbrCoursesParFormateur(Long idF,Date dateDebut, Date dateFin) {
        return this.iFormationRepo.nbrCoursesParFormateur(idF, dateDebut, dateFin);
    }

    @Override
    public Integer getNbrApprenantByFormation(String title) {
        return  iFormationRepo.getNbrApprenantByFormation(title);
    }


    @Override
   // @Scheduled(cron = "*/30 * * * * *")
    public void getNbrApprenantByFormationn() {

        log.info("La formation : Spring contient : " +iFormationRepo.getNbrApprenantByFormation("Spring") + " Apprenant ");
        log.info("La formation : Devops contient : " +iFormationRepo.getNbrApprenantByFormation("DevOps") + " Apprenant ");

    }

    @Override
    public Integer getNbrFormationByApprenant(Long idApp , Domain domain, Date dateDebut, Date dateFin) {
        return iFormationRepo.getNbrFormationByApprenant(idApp,domain, dateDebut, dateFin);
    }

    @Override
    public List<Object[]> getNbrApprenantByFormation() {

        return iUserRepo.getNbrApprenantByFormation();
    }

    @Override
    public List<User> getApprenantByFormation(Integer idF) {
        return iUserRepo.getApprenantByFormation(idF);
    }

    @Override
    public Integer getFormateurRemunerationByDate(Long idFormateur, Date dateDebut, Date dateFin) {

        return iFormationRepo.getFormateurRemunerationByDate(idFormateur, dateDebut, dateFin);

    }


    @Override
    public Integer getRevenueByFormation(Integer idFormation) {
        Formation f = iFormationRepo.findById(idFormation).orElse(null);

        Integer revenue =  (f.getFrais()*iUserRepo.getRevenueByFormation(idFormation).size());
        return  revenue;
    }

    @Override
    public void likeFormation(Integer idF) {
        Formation formation = iFormationRepo.findById(idF).orElse(null);

        formation.setLikes(formation.getLikes()+1);
        iFormationRepo.save(formation);


    }

    @Override
    public void dislikeFormation(Integer idF) {
        Formation formation = iFormationRepo.findById(idF).orElse(null);

        formation.setDislikes(formation.getDislikes()+1);
        iFormationRepo.save(formation);

    }




    //////////////// Search historique ////////////////

    @Override
    public void SearchHistorique(String keyword) {

        if(keyword!=null)
        {
            String s = iSearchRepo.keyWord(keyword);
            Search search = new Search();

            search.setKeyword(s);
            search.setAtDate(new Date());

            iSearchRepo.save(search);
        }


    }




}