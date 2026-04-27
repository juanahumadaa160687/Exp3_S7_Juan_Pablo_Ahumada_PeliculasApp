package org.cslt.peliculas_app.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PeliculaModelTest {

    @Test
    @DisplayName("Test Getters And Setters")
    public void testGettersAndSetters() {
        Pelicula pelicula = new Pelicula();
        pelicula.setId(1L);
        pelicula.setTitulo("Titulo");
        pelicula.setDirector("Director");
        pelicula.setGenero("Genero");
        pelicula.setSinopsis("Sinopsis");
        pelicula.setFecha_estreno(Date.valueOf("2024-01-01"));

        assertEquals(1L, pelicula.getId());
        assertEquals("Titulo", pelicula.getTitulo());
    }
}
