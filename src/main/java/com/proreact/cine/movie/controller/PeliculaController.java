package com.proreact.cine.movie.controller;

import com.proreact.cine.movie.models.Pelicula;
import com.proreact.cine.movie.service.PeliculaService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/peliculas")
@AllArgsConstructor
public class PeliculaController {
    PeliculaService peliculaService;

    @PostMapping("/save")
    public Mono<Pelicula> crearPelicula(@RequestBody Pelicula pelicula){
        return peliculaService.save(pelicula);
    }

    @GetMapping("/all")
    public Flux<Pelicula> consultarPeliculas(){
        return peliculaService.findAll();
    }

    @GetMapping("/one/{id}")
    public Mono<Pelicula> unaPelicula(@PathVariable Integer id){
        return peliculaService.findById(id);
    }

    @GetMapping("/onet/{titulo}")
    public Flux<Pelicula> unaPeliculaByTitulo(@PathVariable String titulo){
        return peliculaService.findByTitulo(titulo);
    }

    @GetMapping("/delete/{id}")
    public Mono<String> eliminaPelicula(@PathVariable Integer id){
        return peliculaService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public Mono<Pelicula> actualizaPelicula(@PathVariable Integer id, @RequestBody Pelicula pelicula) {
        return peliculaService.update(id, pelicula);
    }

}
