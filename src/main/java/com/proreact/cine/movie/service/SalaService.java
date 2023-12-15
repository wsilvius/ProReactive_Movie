package com.proreact.cine.movie.service;

import com.proreact.cine.movie.models.Sala;
import com.proreact.cine.movie.repositories.SalaRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class SalaService {
    private final Logger LOGGER = LoggerFactory.getLogger(SalaService.class);
    private final SalaRepository salaRepository;

    public Mono<Sala> save(Sala sala){
        return salaRepository.save(sala)
                .doOnNext(encontrada -> LOGGER.info("Sala Grabada"))
                .onErrorResume(throwable -> {
                    LOGGER.error("Error grabando la Sala", throwable);
                    return Mono.empty();
                });
    }

    public Flux<Sala> findAll(){
        return salaRepository.findAll()
                .doOnNext(encontrada -> LOGGER.info("Todas las Salas"))
                .onErrorResume(throwable -> {
                    LOGGER.error("Error consultando las Salas", throwable);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Salas no encontradas").getMostSpecificCause()));
    }

    public Mono<Sala> findById(Integer id){
        return salaRepository.findById(id)
                .doOnNext(encontrada -> LOGGER.info("Salas id: "+id+" encontrada, sala: "
                        +encontrada.getNombre()))
                .onErrorResume(throwable -> {
                    LOGGER.error("Error consulta de Sala con el id: "+id, throwable);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Sala con Id: "+id+" no encontrada").getMostSpecificCause()));
    }

    public Mono<String> deleteById(Integer id){
        return this.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Sala con Id: "+id+" no encontrada").getMostSpecificCause()))
                .flatMap(encontrada -> salaRepository.deleteById(encontrada.getId()))
                .doOnNext(encontrada -> LOGGER.info("Sala id: "+id+" eliminada"))
                .onErrorResume(throwable -> {
                    LOGGER.error("Error eliminación de Sala con el id: "+id, throwable);
                    return Mono.empty();
                })
                .flatMap(eliminada -> Mono.just("Eliminada"));
    }

    public Flux<Sala> findByHorario(String nombre){
        return salaRepository.findSalasByNombreContainsIgnoreCaseOrderByNombreAsc(nombre)
                .doOnNext(encontrada -> LOGGER.info("Sala: "+nombre+" encontrada"))
                .onErrorResume(throwable -> {
                    LOGGER.error("Error consulta de Sala: "+nombre, throwable);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Sala: "+nombre+" no encontrada").getMostSpecificCause()));
    }

    public Mono<Sala> update(Integer id, Sala sala) {
        return this.findById(id)
                .flatMap(existing -> {
                    existing.setNombre(sala.getNombre());
                    existing.setCiudad(sala.getCiudad());
                    existing.setFormato(sala.getFormato());
                    existing.setNivel(sala.getNivel());
                    existing.setConfiteria(sala.getConfiteria());
                    return this.save(existing);
                })
                .onErrorResume(throwable -> {
                    LOGGER.error("Error actualizando Sala con Id: "+id, throwable);
                    return Mono.empty();
                });
    }

}
