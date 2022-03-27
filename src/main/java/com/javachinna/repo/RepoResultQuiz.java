package com.javachinna.repo;

import com.javachinna.model.ResultQuiz;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

//import com.javachinna.model.Result;

@Repository
public interface RepoResultQuiz extends CrudRepository<ResultQuiz, Integer> {




   /* @Query(value = "select count(r.id) from ResultQuiz r join r.sUser u join r.quiz q where u.idUser=:idUser and q.idQuiz=:idquiz and r.status=false")
    static Integer findUserQuiz(@Param("idu") Integer idUser, @Param("idq") Integer idQuiz) {
        return value ;
    }


    @Query(value = "select count(r.id) from ResultQuiz r join r.sUser u where u.idUser=:idu")
    Integer getNbrQuiz(@Param("idu") Long idU);

    @Query(value = "select sum(r.totalCorrect) from ResultQuiz r join r.sUser u where u.idUser=:idu group by r.sUser ")
    Integer getScore(@Param("idu") Long idU);*/

}
