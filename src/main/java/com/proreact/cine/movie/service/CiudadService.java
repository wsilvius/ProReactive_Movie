package com.proreact.cine.movie.service;

import com.proreact.cine.movie.models.Ciudad;
import com.proreact.cine.movie.repositories.CiudadRepository;
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
public class CiudadService {
    private final Logger LOGGER = LoggerFactory.getLogger(Ciudad.class);
    private final CiudadRepository ciudadRepository;

    public Mono<Ciudad> save(Ciudad ciudad){
        return ciudadRepository.save(ciudad)
                .doOnNext(encontrada -> LOGGER.info("Ciudad Grabada"))
                .onErrorResume(throwable -> {
                    LOGGER.error("Error grabando la Ciudad", throwable);
                    return Mono.empty();
                });
    }

    public Flux<Ciudad> findAll(){
        return ciudadRepository.findAll()
                .doOnNext(encontrada -> LOGGER.info("Todas las Ciudad"))
                .onErrorResume(throwable -> {
                    LOGGER.error("Error consultando las Ciudad", throwable);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Ciudad no encontradas").getMostSpecificCause()));
    }

    public Mono<Ciudad> findById(Integer id){
        return ciudadRepository.findById(id)
                .doOnNext(encontrada -> LOGGER.info("Ciudad id: "+id+" encontrada, Ciudad: "
                        +encontrada.getNombre()))
                .onErrorResume(throwable -> {
                    LOGGER.error("Error consulta de Ciudad con el id: "+id, throwable);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Ciudad con Id: "+id+" no encontrada").getMostSpecificCause()));
    }

    public Mono<String> deleteById(Integer id){
        return this.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Ciudad con Id: "+id+" no encontrada").getMostSpecificCause()))
                .flatMap(encontrada -> ciudadRepository.deleteById(encontrada.getId()))
                .doOnNext(encontrada -> LOGGER.info("Ciudad id: "+id+" eliminada"))
                .onErrorResume(throwable -> {
                    LOGGER.error("Error eliminación de Ciudad con el id: "+id, throwable);
                    return Mono.empty();
                })
                .flatMap(eliminada -> Mono.just("Eliminada"));
    }

    public Flux<Ciudad> findByNombre(String nombre){
        return ciudadRepository.findCiudadByNombreContainsIgnoreCaseOrderByNombreAsc(nombre)
                .doOnNext(encontrada -> LOGGER.info("Ciudad: "+nombre+" encontrada"))
                .onErrorResume(throwable -> {
                    LOGGER.error("Error consulta de Ciudad: "+nombre, throwable);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Ciudad: "+nombre+" no encontrada").getMostSpecificCause()));
    }

    public Mono<Ciudad> update(Integer id, Ciudad ciudad) {
        return this.findById(id)
                .flatMap(existing -> {
                    existing.setNombre(ciudad.getNombre());
                    return this.save(existing);
                })
                .onErrorResume(throwable -> {
                    LOGGER.error("Error actualizando Ciudad con Id: "+id, throwable);
                    return Mono.empty();
                });
    }
}
