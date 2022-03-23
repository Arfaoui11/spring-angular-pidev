package com.javachinna.service;


import com.javachinna.model.ReactComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReactCommentRepo extends JpaRepository<ReactComment, Long> {

}
