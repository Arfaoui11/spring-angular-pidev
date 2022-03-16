package com.quizapp.repositories;


import com.quizapp.models.Subscription;
import org.springframework.data.repository.CrudRepository;


public interface SubscriptionRepo extends CrudRepository<Subscription, Integer> {

}
