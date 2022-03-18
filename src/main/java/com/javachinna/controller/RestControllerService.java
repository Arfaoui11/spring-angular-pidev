package com.javachinna.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

public class RestControllerService {
    @RestController
    public class RestControllerService {

        @Autowired
        private IServices iServices;





        @RequestMapping(value = {"/ajouterEtAffecterOffresACandidacy/{id}"}, method = RequestMethod.POST)
        @ResponseBody
        @ApiOperation(value = "ajouter Et Affecter Offres A Candidacy  ")
        public void updateOffer(@RequestBody Offres offer, @PathVariable(name = "id") Integer idU ) {
            iServices.updateOffer(offer,idU);

        }
        @PutMapping(value = {"/updateOffres}"})
        @ResponseBody
        @ApiOperation(value = " update offres  ")
        public void updateOffer(@RequestBody Offres offer) {
            iServices.updateOffer(offer);
        }


        @DeleteMapping("/deleteOffer")
        @ResponseBody
        @ApiOperation(value = "Update Offer ")
        public void deleteOffer(Integer id) {
            iServices.deleteOffer(id);
        }


        @GetMapping("/GetOffer")
        @ResponseBody
        @ApiOperation(value = "Get Offer ")
        public List<Offres> GetOffer() {
            return iServices.GetOffer();
        }


        @RequestMapping(value = {"/addCandidacy/{idOff}/{idUser}"}, method = RequestMethod.POST)
        @ResponseBody
        @ApiOperation(value = "ajouter Et Affecter Candidacy  ")
        public void addCandidacy(@RequestBody Candidacy candidacy,@PathVariable(name = "idOff") Integer idO,@PathVariable(name = "idUser") Integer idU)
        {
            iServices.add(candidacy, idO, idU);
        }
        @DeleteMapping("/deleteCandidacy")
        @ResponseBody
        @ApiOperation(value = "Update Candidacy ")
        public void deleteCandidacy(Integer id) {
            iServices.deleteCandidacy(id);
        }

        @PutMapping(value = {"/updateCandidacy}"})
        @ResponseBody
        @ApiOperation(value = " update candidacy  ")
        public void updateCandidacy(@RequestBody Candidacy candidacy) {
            iServices.updateCandidacy(candidacy);
        }


        @GetMapping("/GetCandidacy")
        @ResponseBody
        @ApiOperation(value = "Get Candidacy ")
        public List<Candidacy> GetCandidacy() {
            return iServices.GetCandidacy();
        }

        @RequestMapping(value = {"/addUser"}, method = RequestMethod.POST)
        @ResponseBody
        @ApiOperation(value = "ajouter User  ")
        public void addCUser(@RequestBody User user){
            iServices.addUser(user);
        }


        @GetMapping(value="/getOffresByDateCreation/{d1}/{d2}")
        @ResponseBody
        public List<Offres> OffresParDateCreation(@PathVariable("d1") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) Date date1, @PathVariable("d2") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) Date date2) {

            return iServices.OffresParDateCreation(date1, date2);
        }
        @GetMapping(value ="/getOfferByProfession/{pro}")
        @ResponseBody
        public List<Candidacy> offerByProfession(@PathVariable("pro") Profession profession){
            return  iServices.offerByProfession(profession);
        }
        @GetMapping(value  ="/getNumberOfUserInThisCandidacy/{id}")
        @ResponseBody
        public Integer  getNumberOfUserInThisCandidacy(@PathVariable("id") Integer idCandidacy){
            return iServices.getNumberOfUserInThisCandidacy(idCandidacy);
        }
        @GetMapping(value  ="/getOffresHighRecommended")
        @ResponseBody
        public Offres getOffresHighRecommended(){
            return  iServices.getOffresHighRecommended();

        }
        @GetMapping("/SearchOffer/{keyword}")
        @ResponseBody
        @ApiOperation(value = "search offer ")
        public List<Offres> SearchOffer( @PathVariable("keyword") String keyword) {
            List<Offres> listOffer = iServices.listAll(keyword);
            return  listOffer;
        }
        @GetMapping("/SearchCandidacy/{keyword}")
        @ResponseBody
        @ApiOperation(value = "search offer ")
        public List<Candidacy> SearchCandidacy( @PathVariable("keyword") String keyword) {
            List<Candidacy> listCandidacy = iServices.findAll(keyword);
            return  listCandidacy;
        }

    }

}
