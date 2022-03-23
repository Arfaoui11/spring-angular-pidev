package com.javachinna.repo;

import com.javachinna.model.Comment;
import com.javachinna.model.Topic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface ITopicRepo extends CrudRepository<Topic, Long> {


    @Query("select u.comments from Topic u where u=:topic")
    List<Comment> getAllCommentsByTopic(@Param("topic") Topic topic);

    @Query("select count(c.idComment) from Topic t join t.comments c where t.idTopic=:idTopic")
    Integer getNbrCommentTopic(@Param("idTopic") Long idTopic);

}
