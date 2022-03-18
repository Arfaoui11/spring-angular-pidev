package com.javachinna.service;

import com.example.demo.Entity.Candidacy;
import com.example.demo.Entity.Offres;
import com.example.demo.Entity.Profession;
import com.example.demo.Entity.User;
import com.example.demo.Repository.RepoCandidacy;
import com.example.demo.Repository.RepoOffers;
import com.example.demo.Repository.RepoUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class ServicesOffre implements IServices{

    @Autowired
    private RepoCandidacy repoCandidacy;
    @Autowired
    private RepoOffers repoOff;
    @Autowired
    private RepoUser repoUser;

    @Override
    public void updateOffer(Offres offer,Integer idUser) {
        User user = repoUser.findById(idUser).orElse(null);
        offer.setUsers(user);
        repoOff.save(offer);

    }


    @Override
    public void  updateOffer(Offres offer){
        repoOff.save(offer);
    }

    @Override
    public void deleteOffer (Integer idOffer){
    Offres offres1 = repoOff.findById(idOffer).orElse(null);
        repoOff.delete(offres1);
    }

   @Override
    public List<Offres> GetOffer(){
    List<Offres> offres =(List<Offres>) repoOff.findAll();
    return  offres ;
   }
    @Override
    public void add(Candidacy candidacy, Integer idO, Integer idU) {

        User user = repoUser.findById(idU).orElse(null);
        Offres offres = repoOff.findById(idO).orElse(null);

        candidacy.setOffers(offres);
        candidacy.setUsersW(user);

        repoCandidacy.save(candidacy);
    }

    @Override
    public void updateCandidacy (Candidacy candidacy){
        repoCandidacy.save(candidacy);
    }

    @Override
    public void deleteCandidacy (Integer idCandidacy){
        Candidacy candidacy1 = repoCandidacy.findById(idCandidacy).orElse(null);
        repoCandidacy.delete(candidacy1);
    }

    @Override
    public List<Candidacy> GetCandidacy() {
        List<Candidacy> candidacy = (List<Candidacy>) repoCandidacy.findAll();
        return  candidacy;
    }

    @Override
    public void addUser(User user) {
        repoUser.save(user);

    }


    @Override
    public List<Offres> OffresParDateCreation(Date date1, Date date2) {

        return repoOff.OffresParDateCreation(date1, date2);
    }
    @Override
    public  List<Candidacy> offerByProfession(Profession profession){
        return repoCandidacy.offerByProfession(profession);
    }

    @Override
    public Integer getNumberOfUserInThisCandidacy(Integer idCandidacy) {
        return  repoOff.getNumberOfUserInThisCandidacy(idCandidacy);

    }

    @Override
    public Offres getOffresHighRecommended() {
         Integer Max=0;
         Offres offres = new Offres();
        for (Offres o:repoOff.findAll() )
        {
            if (repoOff.getNumberOfUserInThisCandidacy(o.getIdOffer())>Max) {
                Max = repoOff.getNumberOfUserInThisCandidacy(o.getIdOffer());
            }

        }
        for(Offres o:repoOff.findAll() ) {

            if (repoOff.getNumberOfUserInThisCandidacy(o.getIdOffer()) == Max) {
                offres = repoOff.findById(o.getIdOffer()).orElse(null);
            }
        }
        return offres;
    }

    @Override
    public List<Offres> listAll(String keyword) {
        if (keyword != null) {
            return  repoOff.findAll(keyword);
        }
        return (List<Offres>) repoOff.findAll();
    }

    @Override
    public List<Candidacy> findAll(String keyword) {
        if (keyword != null){
            return  repoCandidacy.findAll(keyword);
        }
        return (List<Candidacy>) repoCandidacy.findAll();
    }


}
