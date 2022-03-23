package com.javachinna.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

    @NoArgsConstructor
    @FieldDefaults(level = AccessLevel.PRIVATE)
    @AllArgsConstructor
    @Getter
    @Setter
    @ToString
    @Entity
    public class Star {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long idStar ;
        @Max(5)
        @Min(0)
        int note ;

        @ManyToOne()
        @JsonIgnore
        Topic topic ;

}
