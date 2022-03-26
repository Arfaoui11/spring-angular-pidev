package com.javachinna.service;


import com.javachinna.model.*;
import com.javachinna.repo.DatabaseFileRepository;
import com.javachinna.repo.ICandidacyRepository;
import com.javachinna.repo.IPartnerRepository;
import com.javachinna.repo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;


@Service
@Slf4j

public class PartnerServiceImpl implements IPartnerService{

    @Autowired
    IPartnerRepository partnerrepo;
    @Autowired
    UserRepository userrepo;
    @Autowired
    ICandidacyRepository candirepo;
    @Autowired
    DatabaseFileRepository filerepo;



    @Override
    public void addPartner(PartnerInstitution partner) {
        partnerrepo.save(partner);
    }




    @Override
    public void deletePartnerById(Integer Id) {
        log.info("Partner deleted ,ID:"+Id);
        partnerrepo.deleteById(Id);

    }

    @Override
    public PartnerInstitution getPartnerById(Integer IdPartner) {
        PartnerInstitution partnerInstitution=partnerrepo.findById(IdPartner).orElse(null);
        return partnerInstitution;
    }



   /* @Override
    public void updatePartner(PartnerInstitution partnerInstitution) {
        partnerrepo.save(partnerInstitution);
    }*/

    @Override
    public List<PartnerInstitution> getAllPartners() {
        return partnerrepo.getAllPartners();
    }

    @Override
    public int getNumberOfStudentDemandsByUniversity(Integer idUniversity) {
        return partnerrepo.getNumberOfStudentDemandsByUniversity(idUniversity);
    }

    @Override
    public PartnerInstitution getUniversityWithTheHighestDemands() {
      return null;


    }


    /**@Override
    public PartnerInstitution updatePartnerInstitution(PartnerInstitution p) {
        PartnerInstitution existingPartner=partnerrepo.findById(p.getIdPartner()).orElse(null);
        if(p.getName()!=null){
            existingPartner.setName(p.getName());}
        if(p.getCountry()!=null){
            existingPartner.setCountry(p.getCountry());}
        if(p.getGeographicalArea()!=null){
            existingPartner.setGeographicalArea(p.getGeographicalArea());}
        if(p.getLanguage()!=null){
            existingPartner.setLanguage(p.getLanguage());}
        if(p.getEmail()!=null){
            existingPartner.setEmail(p.getEmail());}
        if(p.getPicture()!=null){
            existingPartner.setPicture(p.getPicture());}
        if(p.getVideo()!=null){
            existingPartner.setVideo(p.getVideo());}
        if(String.valueOf(p.getCapacityReception())!=null){
            existingPartner.setCapacityReception(p.getCapacityReception());}
        if(p.getDescription()!=null){
            existingPartner.setDescription(p.getDescription());}
        if(p.getSpecial()!=null){
            existingPartner.setSpecial(p.getSpecial());}
        if(String.valueOf(p.isActive())!=null){
            existingPartner.setActive(p.isActive());}
        log.info("partner updated"+p.toString());
        return partnerrepo.save(p);

    }*/

    @Override
    public List<PartnerInstitution> FindAllpartnersByArea(GeographicalArea Area) {
        return partnerrepo.FindAllpartnersByArea(Area);
    }

    @Override
    public List<PartnerInstitution> FindAllpartnersByAreaAndSpecialty(GeographicalArea Area, specialty s) {
        return partnerrepo.FindAllpartnersByAreaAndSpecialty(Area,s);
    }

    @Override
    public double getUniversityRemunerationByDate(Integer idUniversity, Date dateDebut, Date dateFin) {
        return partnerrepo.getUniversityRemunerationByDate(idUniversity,dateDebut,dateFin);
    }

    @Override
    public void affectStudentToPartnerInstitution(Long idUser, Integer idPartner) {
        PartnerInstitution p = partnerrepo.findById(idPartner).orElse(null);
        User student=userrepo.findById(idUser).orElse(null);
        if(p.getUser().getProfession().compareTo(Profession.STUDENT)==0){
            p.setUser(student);
        }
        partnerrepo.save(p);




        //if(p.getUser().getRole().toString()=="STUDENT") {
           // p.setUser(student);
        //}
        //partnerrepo.save(p);

    }




    @Override
    //@Scheduled(cron = "*/30 * * * * *")
    public int numPartnerBySpecialty(specialty s) {
       return partnerrepo.numPartnerBySpecialty(s);

    }

    @Override
    @Transactional
    public void desaffecterPartnerFromAllCandidacy(Integer idUniversity) {


        PartnerInstitution university=partnerrepo.findById(idUniversity).orElse(null);

        List<CandidacyUniversity> listc= (List<CandidacyUniversity>) candirepo.findAll();
        for(CandidacyUniversity c : listc){
            if(c.getPartnerInstitution()==university){
                c.setPartnerInstitution(null);
                candirepo.save(c);
            }
        }
    }

    @Override
    public List<Object> getUniversitiesRemunerationByDateTrie(Date dateDebut, Date dateFin) {
        return partnerrepo.getUniversitiesRemunerationByDateTrie(dateDebut,dateFin);
    }

    @Override
    public int getNumberOfStudentsByUniversity(Integer idUniversity) {
        return partnerrepo.getNumberOfStudentsByUniversity(idUniversity);
    }

    @Override
    public List<PartnerInstitution> getAllUniversityByTopRating() {
        return partnerrepo.getAllUniversityByTopRating();
    }
    @Override
    public int getRemainingCapacityReception(Integer idUniversity) {
        PartnerInstitution university = partnerrepo.findById(idUniversity).orElse(null);
        assert university != null;
        return (university.getCapacityReception()-candirepo.countAcceptedDemandByUniversity(idUniversity));
    }

    @Override
    public boolean checkAvailableUniversity(Integer IdUniversity) {
        PartnerInstitution university = partnerrepo.findById(IdUniversity).orElse(null);
        if (getRemainingCapacityReception(IdUniversity) != 0 ){
            assert university != null;
            university.setActive(true);
            university.setCapacityReception(getRemainingCapacityReception(IdUniversity));
            partnerrepo.save(university);
            return true;
        }
        else {
            assert university != null;
            university.setActive(false);
            university.setCapacityReception(0);
            partnerrepo.save(university);
            return false;
        }
    }




}
