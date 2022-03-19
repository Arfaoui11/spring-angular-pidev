package com.javachinna.repo;

import com.javachinna.model.Subscription;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SubscriptionRepo extends CrudRepository<Subscription, Integer> {

    @Query("select count(subs) from Subscription subs join subs.users u where u.id=:id")
    int GetNbrSubscriptionByUser(@Param("id") Long idUser);

}

