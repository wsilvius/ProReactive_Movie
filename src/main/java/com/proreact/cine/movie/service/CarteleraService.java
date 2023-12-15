package com.proreact.cine.movie.service;

import com.proreact.cine.movie.models.Cartelera;
import com.proreact.cine.movie.models.Pelicula;
import com.proreact.cine.movie.repositories.CarteleraRepository;
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
public class CarteleraService {
    private final Logger LOGGER = LoggerFactory.getLogger(CarteleraService.class);
    private final CarteleraRepository carteleraRepository;

    public Mono<Cartelera> save(Cartelera cartelera){
        return carteleraRepository.save(cartelera)
                .doOnNext(encontrada -> LOGGER.info("Cartelera Grabada"))
                .onErrorResume(throwable -> {
                    LOGGER.error("Error grabando la Cartelera", throwable);
                    return Mono.empty();
                });
    }

    public Flux<Cartelera> findAll(){
        return carteleraRepository.findAll()
                .doOnNext(encontrada -> LOGGER.info("Todas las Carteleras"))
                .onErrorResume(throwable -> {
                    LOGGER.error("Error consultando las Carteleras", throwable);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Carteleras no encontradas").getMostSpecificCause()));
    }

    public Mono<Cartelera> findById(Integer id){
        return carteleraRepository.findById(id)
                .doOnNext(encontrada -> LOGGER.info("Cartelera id: "+id+" encontrada, horario: "
                        +encontrada.getHorario()))
                .onErrorResume(throwable -> {
                    LOGGER.error("Error consulta de Cartelera con el id: "+id, throwable);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cartelera con Id: "+id+" no encontrada").getMostSpecificCause()));
    }

    public Mono<String> deleteById(Integer id){
        return this.findById(id)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cartelera con Id: "+id+" no encontrada").getMostSpecificCause()))
                .flatMap(encontrada -> carteleraRepository.deleteById(encontrada.getId()))
                .doOnNext(encontrada -> LOGGER.info("Cartelera id: "+id+" eliminada"))
                .onErrorResume(throwable -> {
                    LOGGER.error("Error eliminación de Cartelera con el id: "+id, throwable);
                    return Mono.empty();
                })
                .flatMap(eliminada -> Mono.just("Eliminada"));
    }

    public Flux<Cartelera> findByHorario(String horario){
        return carteleraRepository.findCartelerasByHorarioContainsIgnoreCaseOrderByHorarioAsc(horario)
                .doOnNext(encontrada -> LOGGER.info("Cartelera horario: "+horario+" encontrada"))
                .onErrorResume(throwable -> {
                    LOGGER.error("Error consulta de Cartelera con horario: "+horario, throwable);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Cartelera horario: "+horario+" no encontrada").getMostSpecificCause()));
    }

    public Mono<Cartelera> update(Integer id, Cartelera cartelera) {
        return this.findById(id)
                .flatMap(existing -> {
                    existing.setHorario(cartelera.getHorario());
                    existing.setSala(cartelera.getSala());
                    existing.setPelicula(cartelera.getPelicula());
                    return this.save(existing);
                })
                .onErrorResume(throwable -> {
                    LOGGER.error("Error actualizando Cartelera con Id: "+id, throwable);
                    return Mono.empty();
                });
    }


}
