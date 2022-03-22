package com.javachinna.repo;

import com.javachinna.model.Certificat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICertificatRepo extends JpaRepository<Certificat,Long> {
}
