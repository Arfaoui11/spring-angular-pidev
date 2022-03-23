package com.javachinna.repo;


import com.javachinna.model.Star;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStarRepo extends JpaRepository<Star, Long > {
    /*
    List<Star> findAllByTopicId(Long idStar);

     */
}
