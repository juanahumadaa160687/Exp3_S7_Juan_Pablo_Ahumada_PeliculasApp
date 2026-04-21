package org.cslt.peliculas_app.services;

import org.cslt.peliculas_app.models.Pelicula;
import org.cslt.peliculas_app.repositories.PeliculaRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PeliculaServiceTest {

    @InjectMocks
    private PeliculaServiceImpl peliculaServiceMock;

    @Mock
    private PeliculaRepository peliculaRepository;

    @Test
    @DisplayName("Test Create Pelicula")
    public void savePelicula() {
        Pelicula pelicula = new Pelicula();
        pelicula.setTitulo("Pelicula");
        pelicula.setDirector("Director");
        pelicula.setGenero("Genero");
        pelicula.setSinopsis("Sinopsis");
        pelicula.setFecha_estreno(Date.valueOf("2024-01-01"));

        when(peliculaRepository.save(any())).thenReturn(pelicula);

        Pelicula resultado = peliculaServiceMock.createPelicula(pelicula);

        assertEquals(pelicula.getTitulo(), resultado.getTitulo());
        assertEquals(pelicula.getDirector(), resultado.getDirector());
        assertEquals(pelicula.getGenero(), resultado.getGenero());
        assertEquals(pelicula.getSinopsis(), resultado.getSinopsis());

    }

    @Test
    @DisplayName("Test Get All Peliculas")
    public void getAllPeliculas() {

        List<Pelicula> peliculasList = new ArrayList<>();

        Pelicula pelicula1 = new Pelicula();
        pelicula1.setId(1L);
        pelicula1.setTitulo("Pelicula");
        pelicula1.setDirector("Director");
        pelicula1.setGenero("Genero");
        pelicula1.setSinopsis("Sinopsis");
        pelicula1.setFecha_estreno(Date.valueOf("2024-01-01"));

        Pelicula pelicula2 = new Pelicula();
        pelicula2.setId(2L);
        pelicula2.setTitulo("Pelicula 2");
        pelicula2.setDirector("Director 2");
        pelicula2.setGenero("Genero 2");
        pelicula2.setSinopsis("Sinopsis 2");
        pelicula2.setFecha_estreno(Date.valueOf("2024-01-01"));

        peliculasList.add(pelicula1);
        peliculasList.add(pelicula2);

        when(peliculaRepository.findAll()).thenReturn(peliculasList);

        List<Pelicula> peliculas = peliculaServiceMock.getAllPeliculas();

        assertEquals(peliculasList.size(), peliculas.size());
        assertNotNull(peliculas);
        assertEquals(peliculasList.get(0).getTitulo(), peliculas.get(0).getTitulo());
    }

    @Test
    @DisplayName("Test Get Pelicula By Id")
    public void getPeliculaById() {

        Pelicula pelicula1 = new Pelicula();
        pelicula1.setId(1L);
        pelicula1.setTitulo("Pelicula");
        pelicula1.setDirector("Director");
        pelicula1.setGenero("Genero");
        pelicula1.setSinopsis("Sinopsis");
        pelicula1.setFecha_estreno(Date.valueOf("2024-01-01"));

        when(peliculaRepository.findById(any())).thenReturn(Optional.of(pelicula1));

        Pelicula pelicula = peliculaServiceMock.getPeliculaById(any());

        assertEquals(pelicula1.getTitulo(), pelicula.getTitulo());
        assertEquals(pelicula1.getDirector(), pelicula.getDirector());
    }

    @Test
    @DisplayName("Test Update Pelicula")
    public void updatePelicula() {

        Pelicula pelicula1 = new Pelicula();
        pelicula1.setId(1L);
        pelicula1.setTitulo("Pelicula");
        pelicula1.setDirector("Director");
        pelicula1.setGenero("Genero");
        pelicula1.setSinopsis("Sinopsis");
        pelicula1.setFecha_estreno(Date.valueOf("2024-01-01"));

        Pelicula updatedPeli = new Pelicula();
        updatedPeli.setTitulo("Pelicula Actualizada");
        updatedPeli.setDirector("Director Actualizada");
        updatedPeli.setGenero("Genero Actualizada");
        updatedPeli.setSinopsis("Sinopsis Actualizada");
        updatedPeli.setFecha_estreno(Date.valueOf("2024-01-01"));

        when(peliculaRepository.findById(any())).thenReturn(Optional.of(pelicula1));
        when(peliculaRepository.save(any())).thenReturn(updatedPeli);

        Pelicula resultado = peliculaServiceMock.updatePelicula(pelicula1.getId(), updatedPeli);

        assertEquals(updatedPeli.getTitulo(), resultado.getTitulo());
        assertEquals(updatedPeli.getDirector(), resultado.getDirector());
    }

    @Test
    @DisplayName("Test Delete Pelicula")
    public void deletePelicula() {
        Pelicula pelicula1 = new Pelicula();
        pelicula1.setId(1L);
        pelicula1.setTitulo("Pelicula");
        pelicula1.setDirector("Director");
        pelicula1.setGenero("Genero");
        pelicula1.setSinopsis("Sinopsis");
        pelicula1.setFecha_estreno(Date.valueOf("2024-01-01"));

        peliculaServiceMock.deletePelicula(pelicula1.getId());

        assertFalse(peliculaRepository.findById(pelicula1.getId()).isPresent());
    }

}
