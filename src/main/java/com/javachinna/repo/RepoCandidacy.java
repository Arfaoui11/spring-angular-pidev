package com.javachinna.repo;

import com.example.demo.Entity.Candidacy;
import com.example.demo.Entity.Profession;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RepoCandidacy extends CrudRepository<Candidacy,Integer> {

    @Query(value="select d from  Candidacy d join d.offers o  where o.profession=:pro")
     public  void add(Candidacy candidacy,Integer idO,Integer idU);

    @Query("SELECT c  FROM Candidacy c WHERE CONCAT(c.idCandidacy , c.Status) LIKE %?1%")
    public List<Candidacy> findAll(String keyword);

    List<Candidacy> offerByProfession(Profession profession);
}
