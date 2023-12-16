package com.proreact.cine.movie.service;

import com.proreact.cine.movie.models.Pelicula;
import com.proreact.cine.movie.repositories.PeliculaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;


@ExtendWith(MockitoExtension.class)
class PeliculaServiceTest {

    @Mock
    private PeliculaRepository peliculaRepository;

    @InjectMocks
    private PeliculaService peliculaService;

    @BeforeEach
    void Setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        Pelicula pelicula = new Pelicula(99,"test","test","test","test"
                ,"test","test","test","test","test");

        Mockito.when(peliculaRepository.save(pelicula)).thenReturn(Mono.just(pelicula));

        StepVerifier.create(peliculaService.save(pelicula))
                .expectNext(pelicula)
                .verifyComplete();
    }

    @Test
    void testFindAll() {
        Pelicula pelicula = new Pelicula(99,"test","test","test","test"
                ,"test","test","test","test","test");

        Mockito.when(peliculaRepository.findAll()).thenReturn(Flux.just(pelicula));

        StepVerifier.create(peliculaService.findAll())
                .expectNext(pelicula)
                .verifyComplete();
    }

    @Test
    void testFindById() {
        Pelicula pelicula = new Pelicula(1,"test","test","test","test"
                ,"test","test","test","test","test");
        Integer id = 1;

        Mockito.when(peliculaRepository.findById(id)).thenReturn(Mono.just(pelicula));

        StepVerifier.create(peliculaService.findById(id))
                .expectNext(pelicula)
                .verifyComplete();
    }

    @Test
    void testDeleteById() {
        Pelicula pelicula = new Pelicula(1,"test","test","test","test"
                ,"test","test","test","test","test");
        Integer id = 1;

        Mockito.when(peliculaRepository.findById(id)).thenReturn(Mono.just(pelicula));
        Mockito.when(peliculaRepository.deleteById(pelicula.getId_pelicula())).thenReturn(Mono.empty());

        StepVerifier.create(peliculaService.deleteById(id))
                .expectNext("Eliminada")
                .verifyComplete();
    }

    @Test
    void testFindByTitulo() {
        Pelicula pelicula = new Pelicula(99,"test","test","test","test"
                ,"test","test","test","test","test");
        String titulo = "test";

        Mockito.when(peliculaRepository.findPeliculasByTituloContainsIgnoreCaseOrderByTituloAsc(titulo))
                .thenReturn(Flux.just(pelicula));

        StepVerifier.create(peliculaService.findByTitulo(titulo))
                .expectNext(pelicula)
                .verifyComplete();
    }

    @Test
    void testUpdate() {
        Pelicula existingPelicula = new Pelicula(1,"test","test","test","test"
                ,"test","test","test","test","test");
        Pelicula updatedPelicula = new Pelicula(99,"test","test","test","test"
                ,"test","test","test","test","test");
        Integer id = 1;

        Mockito.when(peliculaRepository.findById(id)).thenReturn(Mono.just(existingPelicula));
        Mockito.when(peliculaRepository.save(existingPelicula)).thenReturn(Mono.just(existingPelicula));

        StepVerifier.create(peliculaService.update(id, updatedPelicula))
                .expectNext(existingPelicula)
                .verifyComplete();
    }
}
