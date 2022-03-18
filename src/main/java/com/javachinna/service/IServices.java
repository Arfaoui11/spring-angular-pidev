package com.javachinna.service;

import com.example.demo.Entity.Candidacy;
import com.example.demo.Entity.Offres;
import com.example.demo.Entity.Profession;
import com.example.demo.Entity.User;

import java.util.Date;
import java.util.List;

public interface IServices {


    void updateOffer (Offres offer,Integer idUser );
    void  updateOffer(Offres offer);
    void deleteOffer(Integer idOffer);
    List<Offres> GetOffer();

    void add(Candidacy candidacy,Integer idO,Integer idU);
    void  updateCandidacy (Candidacy candidacy);
    void  deleteCandidacy(Integer idCandidacy);
    List<Candidacy> GetCandidacy();
    void addUser (User user);

    List<Offres> OffresParDateCreation(Date date1,Date date2);
    List<Candidacy> offerByProfession( Profession profession);
    Integer  getNumberOfUserInThisCandidacy( Integer idCandidacy);
    Offres getOffresHighRecommended();
    List<Offres> listAll (String keyword);
    List<Candidacy> findAll(String keyword);
}
