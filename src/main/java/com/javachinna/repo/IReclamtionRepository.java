package com.javachinna.repo;

import com.javachinna.model.Complaint;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IReclamtionRepository extends CrudRepository<Complaint, Long> {
   // @Query("select count(c) from Complaint c join c.doctor d where d.idUser=:id")
  // int  GetNbrComplaintsTodoctor(@Param("id") Long idDoctor);
}
