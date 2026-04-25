package org.cslt.peliculas_app.services;

import org.cslt.peliculas_app.models.Pelicula;
import org.cslt.peliculas_app.repositories.PeliculaRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PeliculaServiceTest {

    @Mock
    private PeliculaRepository peliculaRepository;

    @InjectMocks
    private PeliculaServiceImpl peliculaServiceMock;

    private Pelicula pelicula;

    @BeforeEach
    public void setup() {
        pelicula = new Pelicula();
        pelicula.setId(1L);
        pelicula.setTitulo("Pelicula");
        pelicula.setDirector("Director");
        pelicula.setGenero("Genero");
        pelicula.setSinopsis("Sinopsis");
        pelicula.setFecha_estreno(Date.valueOf("2024-01-01"));
    }


    @Test
    @DisplayName("Test Create Pelicula")
    public void savePelicula() {
        when(peliculaRepository.save(any())).thenReturn(pelicula);

        Pelicula savedPelicula = peliculaServiceMock.createPelicula(pelicula);

        assertNotNull(savedPelicula);
        assertEquals(pelicula.getTitulo(), savedPelicula.getTitulo());
        assertEquals(pelicula.getDirector(), savedPelicula.getDirector());
    }

    @Test
    @DisplayName("Test Get All Peliculas")
    public void getAllPeliculas() {

        List<Pelicula> peliculasList = new ArrayList<>();

        when(peliculaRepository.findAll()).thenReturn(peliculasList);

        assertEquals(peliculasList, peliculaServiceMock.getAllPeliculas());

    }

    @Test
    @DisplayName("Test Get Pelicula By Id")
    public void getPeliculaById() {

        when(peliculaRepository.findById(any())).thenReturn(Optional.of(pelicula));

        Pelicula resultado = peliculaServiceMock.getPeliculaById(pelicula.getId());

        assertNotNull(resultado);
        assertEquals(pelicula,  peliculaServiceMock.getPeliculaById(pelicula.getId()));

    }

    @Test
    @DisplayName("Test Update Pelicula")
    public void updatePelicula() {

        when(peliculaRepository.existsById(1L)).thenReturn(true);
        when(peliculaRepository.save(pelicula)).thenReturn(pelicula);
        Pelicula peli =  peliculaServiceMock.updatePelicula(1L, pelicula);
        assertEquals(1L, pelicula.getId());
        assertEquals(pelicula, peli);
        verify(peliculaRepository).save(pelicula);

    }

    @Test
    @DisplayName("Test Delete Pelicula")
    public void deletePelicula() {
        peliculaServiceMock.deletePelicula(1L);
        verify(peliculaRepository).deleteById(1L);
    }

}
