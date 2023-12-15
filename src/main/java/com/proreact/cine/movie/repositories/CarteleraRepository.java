package com.proreact.cine.movie.repositories;

import com.proreact.cine.movie.models.Cartelera;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface CarteleraRepository extends R2dbcRepository<Cartelera, Integer> {
    Flux<Cartelera> findCartelerasByHorarioContainsIgnoreCaseOrderByHorarioAsc (String horario);
}
