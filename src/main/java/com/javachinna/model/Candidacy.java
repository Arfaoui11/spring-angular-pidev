package com.javachinna.model;


import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table( name = "Candidacy")
///s
public class Candidacy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idCandidacy;
    private String Status ;
    @Temporal(TemporalType.DATE)
    private Date dateOfCandidacy;
    private String cv;

    @ManyToOne
    @JsonIgnore
    private User usersW;

    @ManyToOne
    @JsonIgnore
    private Offres offers;

}
