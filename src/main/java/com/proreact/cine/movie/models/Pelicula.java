package com.proreact.cine.movie.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@AllArgsConstructor
@Getter
@Setter
public class Pelicula {
    @Id
    private Integer id_pelicula;
    private String titulo;
    private String original;
    private String descripcion;
    private String reparto;
    private String director;
    private String duracion;
    private String estreno;
    private String clasificacion; // (+7,+12,+15,+21,adultos,todos)
    private String genero; // (aventura, accion, comedia, fiction, scfi, belico,
                           // documental, suspenso, horror, terror, concierto,
                           // evento, drama, crimen, historico, thriller, animada)
}
