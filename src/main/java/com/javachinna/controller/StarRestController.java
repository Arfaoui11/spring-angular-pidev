package com.javachinna.controller;


import com.javachinna.model.Star;
import com.javachinna.service.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/star")
    public class StarRestController {
        /*

        @Autowired
        StarService starService;
        @PostMapping("/{idStar}")
        public Star save(@RequestBody Star star , @PathVariable("idStar") Long idStar){
            return  starService.save(idStar,star) ;
        }
        @GetMapping("/{idStar}")
        public List<Star>findAllByTopicId (@PathVariable("idStar") Long idStar){
            return  starService.findAllByTopicId(idStar);
        }

         */
    }
