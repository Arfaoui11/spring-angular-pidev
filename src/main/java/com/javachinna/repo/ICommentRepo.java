package com.javachinna.repo;

import com.javachinna.model.Comment;
import org.springframework.data.repository.CrudRepository;


public interface ICommentRepo extends CrudRepository<Comment, Integer> {


}
