package com.proreact.cine.movie.repositories;

import com.proreact.cine.movie.models.Pelicula;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
@Repository
public interface PeliculaRepository extends R2dbcRepository<Pelicula, Integer> {
    Flux<Pelicula> findPeliculasByTituloContainsIgnoreCaseOrderByTituloAsc(String titulo);
}
