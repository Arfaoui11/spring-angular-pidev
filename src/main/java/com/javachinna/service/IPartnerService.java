package com.javachinna.service;

import com.javachinna.model.GeographicalArea;
import com.javachinna.model.PartnerInstitution;
import com.javachinna.model.specialty;

import java.util.Date;
import java.util.List;

public interface IPartnerService {
    public void addPartner (PartnerInstitution partner);
    public void deletePartnerById(Integer Id);
    public PartnerInstitution getPartnerById(Integer IdPartner);
    //public void updatePartner(PartnerInstitution partnerInstitution);
    public List<PartnerInstitution> getAllPartners();
     public int getNumberOfStudentDemandsByUniversity( Integer idUniversity);
    PartnerInstitution getUniversityWithTheHighestDemands();
    //public PartnerInstitution updatePartnerInstitution(PartnerInstitution p);
    public List<PartnerInstitution> FindAllpartnersByArea(GeographicalArea Area);
    public List<PartnerInstitution> FindAllpartnersByAreaAndSpecialty(GeographicalArea Area,specialty s);
    public double getUniversityRemunerationByDate(Integer idUniversity,  Date dateDebut,  Date dateFin);
    void affectStudentToPartnerInstitution(Long idUser,Integer idPartner);
    int numPartnerBySpecialty(specialty s);
    public void desaffecterPartnerFromAllCandidacy(Integer idUniversity);
    public List<Object> getUniversitiesRemunerationByDateTrie( Date dateDebut,  Date dateFin);
    public int getNumberOfStudentsByUniversity( Integer idUniversity);
    public List<PartnerInstitution> getAllUniversityByTopRating();
    //Page<PartnerInstitution> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
    public int getRemainingCapacityReception(Integer idUniversity);
    public boolean checkAvailableUniversity(Integer IdUniversity);








}
