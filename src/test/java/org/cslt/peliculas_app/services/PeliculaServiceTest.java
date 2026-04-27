package org.cslt.peliculas_app.services;

import org.cslt.peliculas_app.models.Pelicula;
import org.cslt.peliculas_app.repositories.PeliculaRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

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
    @DisplayName("Test Get All Peliculas")
    public void testGetAllPeliculas() {
        List<Pelicula> mockPeliculas = Arrays.asList(pelicula);
        when(peliculaRepository.findAll()).thenReturn(mockPeliculas);

        assertEquals(mockPeliculas, peliculaServiceMock.getAllPeliculas());
    }

    @Test
    @DisplayName("Test Get Pelicula By Id")
    public void testGetPeliculaById() {
        when(peliculaRepository.findById(1L)).thenReturn(Optional.of(pelicula));

        assertEquals(pelicula, peliculaServiceMock.getPeliculaById(1L));
    }

    @Test
    @DisplayName("Test Create Pelicula")
    public void testCreatePelicula() {
        when(peliculaRepository.save(any(Pelicula.class))).thenReturn(pelicula);

        assertEquals(pelicula, peliculaServiceMock.createPelicula(pelicula));
    }

    @Test
    @DisplayName("Test Update Pelicula")
    public void testUpdatePelicula() {
        when(peliculaRepository.existsById(1L)).thenReturn(true);
        when(peliculaRepository.save(pelicula)).thenReturn(pelicula);

        Pelicula mockPelicula = peliculaServiceMock.updatePelicula(1L, pelicula);

        assertEquals(1L, mockPelicula.getId());
    }

    @Test
    @DisplayName("Test Update Pelicula Not Found")
    public void testUpdatePeliculaNotFound() {
        when(peliculaRepository.existsById(1L)).thenReturn(false);
        assertNull(peliculaServiceMock.updatePelicula(1L, pelicula));
        verify(peliculaRepository, never()).save(any(Pelicula.class));
    }

    @Test
    @DisplayName("Test Delete Pelicula")
    public void testDeletePelicula() {
        when(peliculaRepository.existsById(1L)).thenReturn(true);
        peliculaServiceMock.deletePelicula(1L);
        verify(peliculaRepository).deleteById(1L);
    }

}
