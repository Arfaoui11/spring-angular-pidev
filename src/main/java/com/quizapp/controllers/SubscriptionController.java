package com.quizapp.controllers;


import com.quizapp.models.Subscription;
import com.quizapp.services.ISubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/subscription")
public class SubscriptionController {
	@Autowired
    ISubscriptionService subscriptionService;
	//@Autowired
    //IUserService userService;
    
    
    //http://localhost:8089/api/subscription/addSubscription
    @PostMapping("/addSubscription")
    @ResponseBody
    public Subscription addSubscription (@RequestBody Subscription subscription){
        return subscriptionService.addSubscription(subscription);
    }
    
  //http://localhost:8089/api/subscription/updateSubscription/{id_subscription}
    @PutMapping("/updateSubscription/{id_subscription}")
    @ResponseBody
    public void updateSubscription (@RequestBody Subscription s){
       subscriptionService.updateSubscription(s);
    }

    
  //http://localhost:8089/api/subscription/retrieveSubscription/{id_subscription}
    @GetMapping("/retrieveSubscription/{id_subscription}")
    @ResponseBody
    public Subscription retrieveSubscription(@PathVariable("id_subscription") Integer id_subscription){
         return subscriptionService.retrieveSubscription(id_subscription);
     }
    
    //http://localhost:8089/api/subscription/retrieveAllSubscriptions
    @GetMapping("/retrieveAllSubscriptions")
    @ResponseBody
    public List<Subscription> getSubscriptions(){
        List<Subscription> listSubscription= subscriptionService.retrieveAllSubscriptions();

        return listSubscription;
    }
  //http://localhost:8089/api/subscription/deleteSubscription/{id_subscription}
    @DeleteMapping("/deleteUser/{id_user}")
    @ResponseBody
    public void deleteSubscription(@PathVariable("id_subscription") Integer id_subscription){
    	subscriptionService.deleteSubscription(id_subscription);
    }
    
    
  //http://localhost:8089/api/subscription/assignSubscriptionToUser/{id_subs}/{id_user}
   // @GetMapping("/assignSubscriptionToUser/{id_subs}/{id_user}")
    //@ResponseBody
    //public void assignSubscriptionToUser(@PathVariable("id_subs") Integer id_subs, @PathVariable("id_user") Integer id_user )  {
    //	subscriptionService.assignSubscriptionToUser(id_subs, id_user);

    	
    //}

}



