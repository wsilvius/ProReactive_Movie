package com.proreact.cine.movie.controller;

import com.proreact.cine.movie.models.Ciudad;
import com.proreact.cine.movie.service.CiudadService;
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
@RequestMapping("/ciudades")
@AllArgsConstructor
public class CiudadController {
    CiudadService ciudadService;
    @PostMapping("/save")
    public Mono<Ciudad> crearCiudad(@RequestBody Ciudad ciudad){
        return ciudadService.save(ciudad);
    }

    @GetMapping("/all")
    public Flux<Ciudad> consultarCiudad(){
        return ciudadService.findAll();
    }

    @GetMapping("/one/{id}")
    public Mono<Ciudad> unaCiudad(@PathVariable Integer id){
        return ciudadService.findById(id);
    }

    @GetMapping("/onet/{nombre}")
    public Flux<Ciudad> unaCiudadByNombre(@PathVariable String nombre){
        return ciudadService.findByNombre(nombre);
    }

    @GetMapping("/delete/{id}")
    public Mono<String> eliminaCiudad(@PathVariable Integer id){
        return ciudadService.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public Mono<Ciudad> actualizaCiudad(@PathVariable Integer id, @RequestBody Ciudad ciudad) {
        return ciudadService.update(id, ciudad);
    }

}
