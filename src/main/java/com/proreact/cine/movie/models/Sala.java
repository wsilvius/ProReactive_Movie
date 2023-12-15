package com.proreact.cine.movie.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@Getter
@Setter
public class Sala {
    @Id
    private Integer id;
    private String nombre;
    private String ciudad;
    private String formato;// (2d, 3d, imax)
    private String nivel; //(estandar, vip, plus, ultra)
    private String confiteria; // (si/no)
}
