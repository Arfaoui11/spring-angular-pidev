package com.javachinna.repo;

import com.javachinna.model.PostComments;
import org.springframework.data.repository.CrudRepository;

public interface ICommentsRepo extends CrudRepository<PostComments,Integer> {

}
