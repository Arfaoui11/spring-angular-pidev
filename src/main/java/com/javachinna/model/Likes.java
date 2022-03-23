package com.javachinna.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer nbrLikes;
    @Temporal(TemporalType.DATE)
    private Date createAt;


    @ManyToOne
    @JsonIgnore
    private PostComments postComments;

   /* @ManyToOne
    @JsonIgnore
    private User user;


    */



}
