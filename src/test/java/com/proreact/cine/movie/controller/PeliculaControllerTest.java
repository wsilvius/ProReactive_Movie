package com.proreact.cine.movie.controller;

import com.proreact.cine.movie.models.Pelicula;
import com.proreact.cine.movie.repositories.PeliculaRepository;
import com.proreact.cine.movie.service.PeliculaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PeliculaControllerTest {

    @Mock
    private PeliculaService peliculaService;
    @Mock
    private PeliculaRepository peliculaRepository;

    @InjectMocks
    private PeliculaController peliculaController;

    //private final WebTestClient webTestClient = WebTestClient.bindToController(peliculaController).build();
    private WebTestClient webTestClient;

    @BeforeEach
    void Setup(){
        MockitoAnnotations.openMocks(this);
        //peliculaController = new PeliculaController(peliculaService);
        webTestClient = WebTestClient.bindToController(peliculaController).build();
        //peliculaService = new PeliculaService(peliculaRepository);
    }

    @Test
    void testCrearPelicula() {
        //peliculaController = new PeliculaController(peliculaService);
        //webTestClient = WebTestClient.bindToController(peliculaController).build();

        Pelicula pelicula = new Pelicula(99,"test","test","test","test"
                ,"test","test","test","test","test");
        when(peliculaService.save(any(Pelicula.class))).thenReturn(Mono.just(pelicula));

        webTestClient.post().uri("/peliculas/save")
                .bodyValue(pelicula)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Pelicula.class);

        verify(peliculaService, times(1)).save(any(Pelicula.class));
    }

    @Test
    void testConsultarPeliculas() {
        Pelicula pelicula1 = new Pelicula(99,"test","test","test","test"
                ,"test","test","test","test","test");
        Pelicula pelicula2 = new Pelicula(99,"test","test","test","test"
                ,"test","test","test","test","test");
        when(peliculaService.findAll()).thenReturn(Flux.just(pelicula1, pelicula2));

        webTestClient.get().uri("/peliculas/all")
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Pelicula.class);

        verify(peliculaService, times(1)).findAll();
    }

    @Test
    void testUnaPelicula() {
        int id = 1;
        Pelicula pelicula = new Pelicula(1,"test","test","test","test"
                ,"test","test","test","test","test");
        when(peliculaService.findById(id)).thenReturn(Mono.just(pelicula));

        webTestClient.get().uri("/peliculas/one/{id}", id)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Pelicula.class);

        verify(peliculaService, times(1)).findById(id);
    }

    @Test
    void testUnaPeliculaByTitulo() {
        String titulo = "Titanic";
        Pelicula pelicula1 = new Pelicula(99,"Titanic","test","test","test"
                ,"test","test","test","test","test");
        Pelicula pelicula2 = new Pelicula(99,"Titanic","test","test","test"
                ,"test","test","test","test","test");
        when(peliculaService.findByTitulo(titulo)).thenReturn(Flux.just(pelicula1, pelicula2));

        webTestClient.get().uri("/peliculas/onet/{titulo}", titulo)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(Pelicula.class);

        verify(peliculaService, times(1)).findByTitulo(titulo);
    }

    @Test
    void testEliminaPelicula() {
        int id = 1;
        when(peliculaService.deleteById(id)).thenReturn(Mono.just("Eliminada"));

        webTestClient.get().uri("/peliculas/delete/{id}", id)
                .exchange()
                .expectStatus().isOk()
                .expectBody(String.class)
                .isEqualTo("Eliminada");
        verify(peliculaService, times(1)).deleteById(id);
    }

    @Test
    void testActualizaPelicula() {
        int id = 1;
        Pelicula pelicula = new Pelicula(1,"test","test","test","test"
                ,"test","test","test","test","test");
        when(peliculaService.update(eq(id), any(Pelicula.class))).thenReturn(Mono.just(pelicula));

        webTestClient.put().uri("/peliculas/update/{id}", id)
                .bodyValue(pelicula)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Pelicula.class);

        verify(peliculaService, times(1)).update(eq(id), any(Pelicula.class));
    }
}