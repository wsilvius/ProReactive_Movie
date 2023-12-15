package com.proreact.cine.movie.repositories;

import com.proreact.cine.movie.models.Sala;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface SalaRepository extends R2dbcRepository<Sala, Integer> {
    Flux<Sala> findSalasByNombreContainsIgnoreCaseOrderByNombreAsc (String nombre);
}
