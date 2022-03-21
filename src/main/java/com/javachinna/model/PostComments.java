package com.javachinna.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table( name = "PostComments")
public class PostComments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idComn")
    private Integer idComn;

    private String message ;
    @Temporal(TemporalType.DATE)
    private Date createAt;
    private Integer likes;
    private Integer dislikes;

    @ManyToOne
    @JsonIgnore
    private User userC;

    @ManyToOne
    @JsonIgnore
    private Formation formation;


}
