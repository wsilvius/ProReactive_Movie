package com.proreact.cine.movie.controller;

import com.proreact.cine.movie.models.Sala;
import com.proreact.cine.movie.service.SalaService;
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
@RequestMapping("/salas")
@AllArgsConstructor
public class SalaController {
    SalaService salaService;

    @PostMapping("/save")
    public Mono<Sala> crearSala(@RequestBody Sala sala){
        return salaService.save(sala);
    }

    @GetMapping("/all")
    public Flux<Sala> consultarSalaa(){
        return salaService.findAll();
    }

    @GetMapping("/one/{id}")
    public Mono<Sala> unaSala(@PathVariable Integer id){
        return salaService.findById(id);
    }

    @GetMapping("/onet/{nombre}")
    public Flux<Sala> unaSalaByNombre(@PathVariable String nombre){
        return salaService.findByHorario(nombre);
    }

    @GetMapping("/delete/{id}")
    public Mono<String> eliminaSala(@PathVariable Integer id){
        return salaService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public Mono<Sala> actualizaSala(@PathVariable Integer id, @RequestBody Sala sala) {
        return salaService.update(id, sala);
    }
}
