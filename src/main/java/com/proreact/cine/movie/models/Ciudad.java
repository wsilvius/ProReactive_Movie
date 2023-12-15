package com.proreact.cine.movie.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@Getter
@Setter
public class Ciudad {
    @Id
    private Integer id;
    private String nombre;
}
