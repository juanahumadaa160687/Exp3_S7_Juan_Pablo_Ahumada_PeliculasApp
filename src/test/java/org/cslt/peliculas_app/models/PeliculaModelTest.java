package org.cslt.peliculas_app.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class PeliculaModelTest {

    @Test
    @DisplayName("Test Setters Getters")
    public void testSetters() {
        Pelicula pelicula = new Pelicula();

        pelicula.setId(1L);
        pelicula.setTitulo("Titulo");
        pelicula.setDirector("Director");
        pelicula.setGenero("Genero");
        pelicula.setSinopsis("Sinopsis");
        pelicula.setFecha_estreno(Date.valueOf("2024-01-01"));

        when(pelicula.getId()).thenReturn(1L);
        when(pelicula.getTitulo()).thenReturn("Titulo");
        when(pelicula.getDirector()).thenReturn("Director");
        when(pelicula.getGenero()).thenReturn("Genero");
        when(pelicula.getSinopsis()).thenReturn("Sinopsis");
        when(pelicula.getFecha_estreno()).thenReturn(Date.valueOf("2024-01-01"));

        assertEquals(1L, pelicula.getId());
        assertEquals("Titulo", pelicula.getTitulo());
        assertEquals("Director", pelicula.getDirector());
        assertEquals("Genero", pelicula.getGenero());
        assertEquals("Sinopsis", pelicula.getSinopsis());
        assertEquals("2024-01-01", pelicula.getFecha_estreno().toString());
    }

}
