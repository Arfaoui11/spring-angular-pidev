package com.javachinna.service;

import com.javachinna.model.Formation;
import com.javachinna.model.Subscription;
import com.javachinna.model.Surprise;
import com.javachinna.model.User;
import com.javachinna.repo.SubscriptionRepo;
import com.javachinna.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


@Service
public class SubscriptionService implements ISubscriptionService {

    @Autowired
    SubscriptionRepo subscRepo;
    @Autowired
    UserRepository userRepo;
    @Autowired
    SendEmailService emailService;


    @Override
    public Subscription addSubscription(Subscription subsc) {
        return subscRepo.save(subsc);
    }

    @Override
    public void updateSubscription(Subscription subsc) {
        subscRepo.save(subsc);
    }

    @Override
    public Subscription retrieveSubscription(Integer id) {
        Subscription sub = subscRepo.findById(id).orElse(null);
        return sub;
    }

    @Override
    public List<Subscription> retrieveAllSubscriptions() {
        List<Subscription> subscriptions = new ArrayList<>();
        subscRepo.findAll().forEach(subscription -> {
            subscriptions.add(subscription);
        });
        return subscriptions;
    }

    @Override
    public void deleteSubscription(Integer id) {
        subscRepo.deleteById(id);

    }


    @Override
    public void assignSubscriptionToUser(Integer id_subs, Long id_user) {
        User u = userRepo.findById(id_user).orElse(null);
        Subscription sub = subscRepo.findById(id_subs).orElse(null);
        u.getSubscs().add(sub);
        userRepo.save(u);

    }

    @Override
    public int getNbreSubsByUser(Long idUser) {
        return subscRepo.GetNbrSubscriptionByUser(idUser);
    }


    @Override
    public Integer getNumberOfUserInThisSubscription(Integer idSubscription) {
        return subscRepo.getNberOfUserInThisSubscription(idSubscription);

    }

    @Override
    public void Surprise(Long idUser) {
        User user = userRepo.findById(idUser).orElse(null);
        int nbre = subscRepo.GetNbrSubscriptionByUser(idUser);
        if (nbre % 3 == 0) {

            subscRepo.GetNbrSubscriptionByUser(idUser);

            Random random = new Random();
            for (int i = 0; i < 3; i++) {

                int rand = random.nextInt(i);
            }

            emailService.sendSimpleEmail(user.getEmail(), "you win Surprise with us", "Surprise");

        }


    }

//    @Override
//    public List<Object> getUserRemunerationByDateTrie() {
//        LocalDate currentdDate1 =  LocalDate.now();
//
//        ZoneId defaultZoneId = ZoneId.systemDefault();
//
//        Date Start_D =Date.from(currentdDate1.minusDays(30).atStartOfDay(defaultZoneId).toInstant());
//        Date Final_D =Date.from(currentdDate1.plusDays(30).atStartOfDay(defaultZoneId).toInstant());
//
//        return this.subscRepo(Start_D,Final_D);
//    }

//}
//
    @Override
    public List<Subscription> SearchMultiple(String key) {

        if (key.equals("")) {
            return (List<Subscription>) subscRepo.findAll();
        } else {
         //   return subscRepo.reaserch(key);
        }
        return null;
    }
}
