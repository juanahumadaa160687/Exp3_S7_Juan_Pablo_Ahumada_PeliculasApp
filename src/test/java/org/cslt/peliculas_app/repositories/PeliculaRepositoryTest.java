package org.cslt.peliculas_app.repositories;


import org.cslt.peliculas_app.models.Pelicula;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.sql.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PeliculaRepositoryTest {

    @Autowired
    private PeliculaRepository peliculaRepository;

    private Pelicula pelicula1;
    private Pelicula pelicula2;

    @BeforeEach
    public void setup() {
        pelicula1 = new Pelicula();
        pelicula1.setTitulo("Pelicula 1");
        pelicula1.setGenero("Genero1");
        pelicula1.setSinopsis("Sinopsis1");
        pelicula1.setDirector("Director1");
        pelicula1.setFecha_estreno(Date.valueOf("2024-01-01"));
        peliculaRepository.save(pelicula1);

        pelicula2 = new Pelicula();
        pelicula2.setTitulo("Pelicula 2");
        pelicula2.setGenero("Genero2");
        pelicula2.setSinopsis("Sinopsis2");
        pelicula2.setDirector("Director2");
        pelicula2.setFecha_estreno(Date.valueOf("2024-01-01"));
        peliculaRepository.save(pelicula2);

    }

    @AfterEach
    public void tearDown() {
        peliculaRepository.deleteAll();
    }


    @Test
    @DisplayName("Test Create Pelicula")
    public void createPelicula() {

        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo("Titulo");
        pelicula.setDirector("Director");
        pelicula.setGenero("Genero");
        pelicula.setSinopsis("Sinopsis");
        pelicula.setFecha_estreno(Date.valueOf("2024-01-01"));

        Pelicula savedPelicula = peliculaRepository.save(pelicula);

        assertNotNull(savedPelicula.getId());
        assertEquals(pelicula.getTitulo(), savedPelicula.getTitulo());
        assertEquals(pelicula.getDirector(), savedPelicula.getDirector());
        assertEquals(pelicula.getGenero(), savedPelicula.getGenero());
    }

    @Test
    @DisplayName("Test Get All Peliculas")
    public void getAllPeliculas() {
        List<Pelicula> peliculas = peliculaRepository.findAll();

        assertNotNull(peliculas);
        assertEquals(2, peliculas.size());
        assertEquals(pelicula1.getTitulo(), peliculas.get(0).getTitulo());
    }

    @Test
    @DisplayName("Test Get Pelicula By Id")
    public void getPeliculaById() {
        Pelicula pelicula = peliculaRepository.findById(pelicula1.getId()).orElse(null);

        assertNotNull(pelicula);
        assertEquals(pelicula1.getTitulo(), pelicula.getTitulo());
        assertEquals(pelicula1.getDirector(), pelicula.getDirector());
    }

    @Test
    @DisplayName("Test Update Pelicula")
    public void updatePelicula() {

        Pelicula existingPelicula = peliculaRepository.findById(pelicula1.getId()).orElse(null);

        Pelicula updatedPelicula = new Pelicula();
        updatedPelicula.setTitulo("Updated Titulo");
        updatedPelicula.setDirector("Updated Director");
        updatedPelicula.setGenero("Updated Genero");
        updatedPelicula.setSinopsis("Updated Sinopsis");
        updatedPelicula.setFecha_estreno(Date.valueOf("2024-02-01"));

        Pelicula savedPelicula = peliculaRepository.save(updatedPelicula);

        assertNotNull(savedPelicula.getId());
        assertEquals(updatedPelicula.getTitulo(), savedPelicula.getTitulo());
        assertEquals(updatedPelicula.getDirector(), savedPelicula.getDirector());
    }

    @Test
    @DisplayName("Test Delete Pelicula")
    public void deletePelicula() {
        peliculaRepository.deleteById(pelicula1.getId());

        assertFalse(peliculaRepository.findById(pelicula1.getId()).isPresent());
    }
}
