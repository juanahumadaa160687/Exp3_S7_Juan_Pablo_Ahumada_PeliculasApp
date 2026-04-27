package org.cslt.peliculas_app.repositories;

import org.cslt.peliculas_app.models.Pelicula;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PeliculaRepositoryTest {

    @Autowired
    private PeliculaRepository peliculaRepository;

    Pelicula pelicula;

    @BeforeEach
    public void setUp() {
        pelicula = new Pelicula();
        pelicula.setTitulo("Titulo");
        pelicula.setDirector("Director");
        pelicula.setGenero("Genero");
        pelicula.setSinopsis("Sinopsis");
        pelicula.setFecha_estreno(Date.valueOf("2024-01-01"));

        peliculaRepository.save(pelicula);
    }

    @Test
    @DisplayName("Get All Peliculas")
    public void getAllPeliculas() {
        List<Pelicula> peliculas = peliculaRepository.findAll();
        assertNotNull(peliculas);
        assertTrue(!peliculas.isEmpty());
    }

    @Test
    @DisplayName("Get Pelicula By ID")
    public void getPeliculaById() {
        Pelicula movie = peliculaRepository.findById(pelicula.getId()).get();
        assertNotNull(movie);
    }

    @Test
    @DisplayName("Save Pelicula")
    public void savePelicula() {
        Pelicula pelicula2 = new Pelicula();
        pelicula2.setTitulo("Titulo2");
        pelicula2.setDirector("Director2");
        pelicula2.setGenero("Genero2");
        pelicula2.setSinopsis("Sinopsis2");
        pelicula2.setFecha_estreno(Date.valueOf("2024-01-01"));

        peliculaRepository.save(pelicula2);

        assertNotNull(pelicula2);
    }

    @Test
    @DisplayName("Delete Pelicula")
    public void deletePelicula() {
        peliculaRepository.deleteById(pelicula.getId());
        assertNotNull(peliculaRepository.findById(pelicula.getId()));
    }
}
