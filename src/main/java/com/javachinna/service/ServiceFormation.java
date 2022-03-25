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
import org.springframework.transaction.annotation.Transactional;

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
    private ICommentsRepo iCommentsRepo;
    @Autowired
    private ILikesRepo iLikesRepo;
    @Autowired
    private IDislikesRepo iDislikesRepo;

    @Autowired
    private exportPdf export;




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
  //  @Scheduled(cron = "0 0/20 * * * *")
    @Scheduled(cron = "0 0 9 28 * ?")
    public User getFormateurRemunerationMaxSalaire() throws MessagingException {


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

        Date dd = Date.from(currentdDate1.plusMonths(1).atStartOfDay(defaultZoneId).toInstant());
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
            this.emailSenderService.sendSimpleEmailWithFils(u.getEmail(), "we have max houre of travel ", "we have max houre of travel we elevate salary with 200 $  : " + u.getSalary()+ "$  Name " + u.getLastName() + "--" + u.getFirstName() + " . ","/Users/macos/IdeaProjects/springPidev/src/main/resources/static/mybadges/goldbadge.png");

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
    @Scheduled(cron = "0 0/1 * * * *")
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
                        log.info( " Status"+iResultRepo.getNbrQuiz(u.getId()));
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

                              export.pdfReader(f,u);

                                QRCodeGenerator.generateQRCodeImage(f.getDomain().toString(),150,150,QR_CODE_IMAGE_PATH);
                                this.emailSenderService.sendSimpleEmailWithFils(u.getEmail()," Congratulations Mr's : "+u.getLastName()+" "+u.getFirstName()+" you have finished your Courses  " ," Certification At : "+ new Date()+"  in Courses of Domain "+f.getDomain()+" "+" And Niveau : " +f.getLevel() +" .","/Users/macos/IdeaProjects/springPidev/src/main/resources/static/Certif/C"+u.getId()+".pdf");
                                fin=true; /// return /////
                            }


                        }




                    }

                }

            }

        } catch (WriterException | IOException | MessagingException e) {

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
                formation.setRating(0.0);
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

    if(apprenant.getState() == State.DISCIPLINED)
    {
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

    }else
    {
        this.emailSenderService.sendSimpleEmail(apprenant.getEmail(), "You are PUNISHED  ", " we don't have access to add new courses you are (PUNISHED/EXCLUDED)" + apprenant.getLastName() + " - " + apprenant.getFirstName() + "  .");
        log.info(" no add  " + apprenant.getState());
    }

    }


   // @Scheduled(cron = "0 0/1 * * * *")
    public void ListComplete()
    {
        for(Formation f : iFormationRepo.findAll())
        {
            if (iFormationRepo.getNbrApprenantByFormationId(f.getIdFormation()) < f.getNbrMaxParticipant())
            {
                this.emailSenderService.sendSimpleEmail("mahdi.arfaoui1@esprit.tn", "Learner list no complete   ", " we have in this courses "+iFormationRepo.getNbrApprenantByFormationId(f.getIdFormation()) +" learner in this formation "+f.getTitle() +" " + f.getDomain());
                log.info(" we have access to add this courses " + f.getNbrMaxParticipant());
            }else {
                this.emailSenderService.sendSimpleEmail("mahdi.arfaoui1@esprit.tn", "Learner list complete Max    ", " we have in this courses "+iFormationRepo.getNbrApprenantByFormationId(f.getIdFormation()) +" learner  in this formation "+f.getTitle() +" " + f.getDomain());
                log.info(" Learner list complete Max learner " + f.getNbrMaxParticipant());
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

    ///////////       Comments ///////////////
    @Override
    public void likeComments(Integer idC ){
        PostComments post = iCommentsRepo.findById(idC).orElse(null);
      //  User user = iUserRepo.findById(idU).orElse(null);
        Likes likes = new Likes();


        if(post.getLikes().size() == 0)
        {
            likes.setPostComments(post);
          //  likes.setUser(user);
            likes.setNbrLikes(1);
            likes.setCreateAt(new Date());
            iLikesRepo.save(likes);
        }
        else{
            Likes l = iLikesRepo.findById(post.getLikes().stream().findFirst().get().getId()).orElse(null);
                l.setNbrLikes(l.getNbrLikes()+1);
                l.setCreateAt(new Date());
                iLikesRepo.save(l);
        }

    }



    @Override
    public void dislikeComments(Integer idC ) {
        PostComments post = iCommentsRepo.findById(idC).orElse(null);
      //  User user = iUserRepo.findById(idU).orElse(null);
        Dislikes dislikes = new Dislikes();

        if(post.getDislikes().size() == 0)
        {

            dislikes.setPostComments(post);
          //  dislikes.setUser(user);
            dislikes.setNbrDislikes(1);
            dislikes.setCreateAt(new Date());
            iDislikesRepo.save(dislikes);
        }
        else{
            Dislikes d = iDislikesRepo.findById(post.getDislikes().stream().findFirst().get().getId()).orElse(null);
            d.setNbrDislikes(d.getNbrDislikes()+1);
            d.setCreateAt(new Date());
            iDislikesRepo.save(d);

        }

    }


    //////////////////// Courses //////////////////////

    @Override
    public void FormationWithRate(Integer idF, Double rate) {
        Formation formation = iFormationRepo.findById(idF).orElse(null);

        if(formation.getRating()==0)
        {
            formation.setRating(rate);
        }else {
            formation.setRating(((formation.getRating()+rate)/2.0));
        }
       
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
    @Override
    public void addComments(PostComments postComments,Integer idF,Long idUser) {

        Formation formation = iFormationRepo.findById(idF).orElse(null);
        User user = iUserRepo.findById(idUser).orElse(null);
        postComments.setUserC(user);
        postComments.setFormation(formation);
        iCommentsRepo.save(postComments);
    }

    @Override
    public void deleteComments(Integer idC) {
        log.info("In methode deleteComment");
        log.warn("Are you sure you want to delete Comment");
        iCommentsRepo.deleteById(idC);
        log.error("exeption");
    }

    @Override
    public PostComments upDateComment(PostComments postComments,Integer idF,Long idUser) {


        User user =  iUserRepo.findById(idUser).orElse(null);
        Formation f = iFormationRepo.findById(idF).orElse(null);


        postComments.setFormation(f);
        postComments.setUserC(user);



        return iCommentsRepo.save(postComments);
    }

    @Override
    public List<PostComments> getAllComments() {
        return (List<PostComments>) iCommentsRepo.findAll();
    }

    @Override
    @Scheduled(cron = "0 0/2 * * * *")
    public void LeanerStatus() {


        for(User user: iUserRepo.findAll())
        {
            User u = iUserRepo.findById(user.getId()).orElse(null);

            if (u != iUserRepo.findById(1L).orElse(null) )
            {
                if(iUserRepo.nbrCommentsBadByUser(user.getId())==1 && u.getState()!=State.WARNED)
                {
                    u.setState(State.WARNED);
                    iUserRepo.save(u);
                }else if(iUserRepo.nbrCommentsBadByUser(user.getId())==2 && u.getState()!=State.PUNISHED) {
                    u.setState(State.PUNISHED);
                    iUserRepo.save(u);
                }else if(iUserRepo.nbrCommentsBadByUser(user.getId())==3 && u.getState()!=State.EXCLUDED) {
                    u.setState(State.EXCLUDED);
                    iUserRepo.save(u);
                }
            }


        }

    }


    @Override
    @Scheduled(cron = "0 0/1 * * * *")
    public void decisionUserPUNISHED() {


        Date date = new Date();

        for (User user : iUserRepo.findAll())
        {
            if (user.getState()==State.PUNISHED)
            {
            for (PostComments p : iUserRepo.CommentsByUser(user.getId()))
            {
                Date period20J = new Date(p.getCreateAt().getTime() + (1000 * 60 * 60 * 48));
                if(date.after(period20J) && iUserRepo.nbrCommentsBadByUser(user.getId()) >=1)
                {
                    user.setState(State.DISCIPLINED);
                    iUserRepo.save(user);
                    iCommentsRepo.deleteById(p.getIdComn());
                }
            }
            }

        }

    }





}
