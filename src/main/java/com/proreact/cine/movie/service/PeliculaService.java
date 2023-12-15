package com.proreact.cine.movie.service;

import com.proreact.cine.movie.models.Pelicula;
import com.proreact.cine.movie.repositories.PeliculaRepository;
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
public class PeliculaService {
    private final Logger LOGGER = LoggerFactory.getLogger(PeliculaService.class);
    private final PeliculaRepository peliculaRepository;

    public Mono<Pelicula> save(Pelicula pelicula){
        return peliculaRepository.save(pelicula)
                .doOnNext(encontrada -> LOGGER.info("Pelicula Grabada"))
                .onErrorResume(throwable -> {
                    LOGGER.error("Error grabando la Película", throwable);
                    return Mono.empty();
                });
    }

    public Flux<Pelicula> findAll(){
        return peliculaRepository.findAll()
                .doOnNext(encontrada -> LOGGER.info("Todas las Peliculas"))
                .onErrorResume(throwable -> {
                    LOGGER.error("Error consultando las Películas", throwable);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Películas no encontradas").getMostSpecificCause()));
    }

    public Mono<Pelicula> findById(Integer id_pelicula){
        return peliculaRepository.findById(id_pelicula)
                .doOnNext(encontrada -> LOGGER.info("Película id: "+id_pelicula+" encontrada, título: "
                        +encontrada.getTitulo()))
                .onErrorResume(throwable -> {
                    LOGGER.error("Error consulta de Película con el id: "+id_pelicula, throwable);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Película con Id: "+id_pelicula+" no encontrada").getMostSpecificCause()));
        //TODO el getMostSpecificCause nos presenta el texto de la causa del error y no el stacktrace completo
    }

    public Mono<String> deleteById(Integer id_pelicula){
        return this.findById(id_pelicula)
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                                "Película con Id: "+id_pelicula+" no encontrada").getMostSpecificCause()))
                .flatMap(encontrada -> peliculaRepository.deleteById(encontrada.getId_pelicula()))
                .doOnNext(encontrada -> LOGGER.info("Película id: "+id_pelicula+" eliminada"))
                .onErrorResume(throwable -> {
                    LOGGER.error("Error eliminación de Película con el id: "+id_pelicula, throwable);
                    return Mono.empty();
                })
                .flatMap(eliminada -> Mono.just("Eliminada"));
    }

    public Flux<Pelicula> findByTitulo(String titulo){
        return peliculaRepository.findPeliculasByTituloContainsIgnoreCaseOrderByTituloAsc(titulo)
                .doOnNext(encontrada -> LOGGER.info("Película título: "+titulo+" encontrada"))
                .onErrorResume(throwable -> {
                    LOGGER.error("Error consulta de Película con título: "+titulo, throwable);
                    return Mono.empty();
                })
                .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Película título: "+titulo+" no encontrada").getMostSpecificCause()));
    }

    public Mono<Pelicula> update(Integer id, Pelicula pelicula) {
        return this.findById(id)
                .flatMap(existingPelicula -> {
                    existingPelicula.setTitulo(pelicula.getTitulo());
                    existingPelicula.setOriginal(pelicula.getOriginal());
                    existingPelicula.setDescripcion(pelicula.getDescripcion());
                    existingPelicula.setReparto(pelicula.getReparto());
                    existingPelicula.setDirector(pelicula.getDirector());
                    existingPelicula.setDuracion(pelicula.getDuracion());
                    existingPelicula.setEstreno(pelicula.getEstreno());
                    existingPelicula.setClasificacion(pelicula.getClasificacion());
                    existingPelicula.setGenero(pelicula.getGenero());
                    return this.save(existingPelicula);
                })
                .onErrorResume(throwable -> {
                    LOGGER.error("Error actualizando película con Id: "+id, throwable);
                    return Mono.empty();
                });
    }

}
