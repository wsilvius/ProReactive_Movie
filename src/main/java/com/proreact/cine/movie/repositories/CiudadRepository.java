package com.proreact.cine.movie.repositories;

import com.proreact.cine.movie.models.Ciudad;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface CiudadRepository extends R2dbcRepository<Ciudad, Integer> {
    Flux<Ciudad> findCiudadByNombreContainsIgnoreCaseOrderByNombreAsc (String nombre);
}
