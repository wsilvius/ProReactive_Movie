package com.proreact.cine.movie.controller;

import com.proreact.cine.movie.models.Cartelera;
import com.proreact.cine.movie.models.Pelicula;
import com.proreact.cine.movie.service.CarteleraService;
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
@RequestMapping("/carteleras")
@AllArgsConstructor
public class CarteleraController {
    CarteleraService carteleraService;

    @PostMapping("/save")
    public Mono<Cartelera> crearCartelera(@RequestBody Cartelera cartelera){
        return carteleraService.save(cartelera);
    }

    @GetMapping("/all")
    public Flux<Cartelera> consultarCartelera(){
        return carteleraService.findAll();
    }

    @GetMapping("/one/{id}")
    public Mono<Cartelera> unaCartelera(@PathVariable Integer id){
        return carteleraService.findById(id);
    }

    @GetMapping("/onet/{horario}")
    public Flux<Cartelera> unaCarteleraByHorario(@PathVariable String horario){
        return carteleraService.findByHorario(horario);
    }

    @GetMapping("/delete/{id}")
    public Mono<String> eliminaCartelera(@PathVariable Integer id){
        return carteleraService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public Mono<Cartelera> actualizaCartelera(@PathVariable Integer id, @RequestBody Cartelera cartelera) {
        return carteleraService.update(id, cartelera);
    }
}
