package org.cslt.peliculas_app.controllers;

import org.cslt.peliculas_app.models.Pelicula;

import org.cslt.peliculas_app.services.PeliculaServiceImpl;
import org.hamcrest.Matchers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.*;

import java.sql.Date;

import java.util.Arrays;

import static org.mockito.Mockito.when;


@WebMvcTest(controllers = PeliculaController.class)
public class PeliculaControllerTest {

    Pelicula movie;

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PeliculaServiceImpl peliculaServiceMock;

    @BeforeEach
    public void setup() {
        movie = new Pelicula();
        movie.setId(1L);
        movie.setTitulo("Titulo");
        movie.setGenero("Genero");
        movie.setSinopsis("Sinopsis");
        movie.setFecha_estreno(Date.valueOf("2024-01-01"));
        movie.setDirector("Director");

    }

    @Test
    @DisplayName("Get All Peliculas")
    public void getAllPeliculas() throws Exception {
        when(peliculaServiceMock.getAllPeliculas()).thenReturn(Arrays.asList(movie));
        mockMvc.perform(MockMvcRequestBuilders.get("/peliculas"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].titulo", Matchers.is("Titulo")));
    }

    @Test
    @DisplayName("Get Pelicula by ID")
    public void getPeliculaById() throws Exception {
        when(peliculaServiceMock.getPeliculaById(1L)).thenReturn(movie);
        mockMvc.perform(MockMvcRequestBuilders.get("/peliculas/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.titulo", Matchers.is("Titulo")));
    }

    @Test
    @DisplayName("Create Pelicula")
    public void createPelicula() throws Exception {
        when(peliculaServiceMock.createPelicula(movie)).thenReturn(movie);
        mockMvc.perform(MockMvcRequestBuilders.post("/peliculas/new")
                .contentType("application/json")
                .content("{\"titulo\":\"Titulo\",\"director\":\"Director\",\"sinopsis\":\"Sinopsis\",\"genero\":\"Genero\",\"fecha_estreno\":\"2024-01-01\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Update Pelicula")
    public void updatePelicula() throws Exception {
        when(peliculaServiceMock.updatePelicula(eq(movie.getId()), any(Pelicula.class))).thenReturn(movie);
        mockMvc.perform(MockMvcRequestBuilders.put("/peliculas/update/{id}", movie.getId())
                .contentType("application/json")
                .content("{\"titulo\":\"Titulo\",\"director\":\"Director\",\"sinopsis\":\"Sinopsis\",\"genero\":\"Genero\",\"fecha_estreno\":\"2024-01-01\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Delete Pelicula")
    public void deletePelicula() throws Exception {
        doNothing().when(peliculaServiceMock).deletePelicula(1L);
        mockMvc.perform(MockMvcRequestBuilders.delete("/peliculas/delete/{id}", 1L))
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(peliculaServiceMock, times(1)).deletePelicula(1L);
    }
}
