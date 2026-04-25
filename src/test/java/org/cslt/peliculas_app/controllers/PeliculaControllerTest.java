package org.cslt.peliculas_app.controllers;

import org.cslt.peliculas_app.models.Pelicula;

import org.cslt.peliculas_app.services.PeliculaServiceImpl;
import org.hamcrest.Matchers;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.sql.Date;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;


@WebMvcTest(controllers = PeliculaController.class)
public class PeliculaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PeliculaServiceImpl peliculaServiceMock;

    @Test
    @DisplayName("Test New Pelicula")
    public void createPelicula() throws Exception {

        Pelicula pelicula = new Pelicula();

        pelicula.setId(1L);
        pelicula.setTitulo("Titulo");
        pelicula.setDirector("Director");
        pelicula.setGenero("Genero");
        pelicula.setSinopsis("Sinopsis");
        pelicula.setFecha_estreno(Date.valueOf("2024-01-01"));

        when(peliculaServiceMock.createPelicula(pelicula)).thenReturn(pelicula);

        mockMvc.perform(MockMvcRequestBuilders.post("/peliculas/new")
                .contentType("application/json")
                .content("{\"titulo\":\"Titulo\",\"director\":\"Director\",\"genero\":\"Genero\",\"sinopsis\":\"Sinopsis\",\"fecha_estreno\":\"2024-01-01\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Test Get All")
    public void obtenerTodasLasPeliculas() throws Exception {
        Pelicula pelicula = new Pelicula();
        pelicula.setId(1L);
        pelicula.setTitulo("Titulo");
        pelicula.setDirector("Director");
        pelicula.setGenero("Genero");
        pelicula.setSinopsis("Sinopsis");
        pelicula.setFecha_estreno(Date.valueOf("2024-01-01"));

        Pelicula pelicula2 = new Pelicula();
        pelicula2.setId(2L);
        pelicula2.setTitulo("Titulo2");
        pelicula2.setDirector("Director2");
        pelicula2.setGenero("Genero2");
        pelicula2.setSinopsis("Sinopsis2");
        pelicula2.setFecha_estreno(Date.valueOf("2024-02-01"));

        List<Pelicula> peliculas = Arrays.asList(pelicula, pelicula2);

        when(peliculaServiceMock.getAllPeliculas()).thenReturn(peliculas);

        mockMvc.perform(MockMvcRequestBuilders.get("/peliculas")
                .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.aMapWithSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].titulo", Matchers.is("Titulo")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].id", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[1].titulo", Matchers.is("Titulo2")));
    }

    @Test
    @DisplayName("Test Get Pelicula By Id")
    public void obtenerPeliculaById() throws Exception {
        Pelicula pelicula = new Pelicula();
        pelicula.setId(1L);
        pelicula.setTitulo("Titulo");
        pelicula.setDirector("Director");
        pelicula.setGenero("Genero");
        pelicula.setSinopsis("Sinopsis");
        pelicula.setFecha_estreno(Date.valueOf("2024-01-01"));

        Pelicula pelicula2 = new Pelicula();
        pelicula2.setId(2L);
        pelicula2.setTitulo("Titulo2");
        pelicula2.setDirector("Director2");
        pelicula2.setGenero("Genero2");
        pelicula2.setSinopsis("Sinopsis2");
        pelicula2.setFecha_estreno(Date.valueOf("2024-02-01"));

        Pelicula pelicula3 = new Pelicula();
        pelicula3.setId(3L);
        pelicula3.setTitulo("Titulo3");
        pelicula3.setDirector("Director3");
        pelicula3.setGenero("Genero3");
        pelicula3.setSinopsis("Sinopsis3");
        pelicula3.setFecha_estreno(Date.valueOf("2024-02-01"));

        when(peliculaServiceMock.getPeliculaById(1L)).thenReturn(pelicula);
        when(peliculaServiceMock.getPeliculaById(2L)).thenReturn(pelicula2);
        when(peliculaServiceMock.getPeliculaById(3L)).thenReturn(pelicula3);

        mockMvc.perform(MockMvcRequestBuilders.get("/peliculas/1")
                .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.titulo", Matchers.is("Titulo")));

        mockMvc.perform(MockMvcRequestBuilders.get("/peliculas/2")
                .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.titulo", Matchers.is("Titulo2")));

        mockMvc.perform(MockMvcRequestBuilders.get("/peliculas/3")
                .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(3)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.titulo", Matchers.is("Titulo3")));

    }


    @Test
    @DisplayName("Test Delete Pelicula")
    public void deletePelicula() throws Exception {
        Pelicula pelicula = new Pelicula();
        pelicula.setId(1L);
        pelicula.setTitulo("Titulo");
        pelicula.setDirector("Director");
        pelicula.setGenero("Genero");
        pelicula.setSinopsis("Sinopsis");
        pelicula.setFecha_estreno(Date.valueOf("2024-01-01"));

        when(peliculaServiceMock.getPeliculaById(1L)).thenReturn(pelicula);

        mockMvc.perform(MockMvcRequestBuilders.delete("/peliculas/delete/1")
                .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Test Update Pelicula")
    public void updatePelicula() throws Exception {
        Pelicula pelicula = new Pelicula();
        pelicula.setId(1L);
        pelicula.setTitulo("Titulo");
        pelicula.setDirector("Director");
        pelicula.setGenero("Genero");
        pelicula.setSinopsis("Sinopsis");
        pelicula.setFecha_estreno(Date.valueOf("2024-01-01"));

        Pelicula updatedPelicula = new Pelicula();
        updatedPelicula.setId(1L);
        updatedPelicula.setTitulo("Titulo2");
        updatedPelicula.setDirector("Director2");
        updatedPelicula.setGenero("Genero2");
        updatedPelicula.setSinopsis("Sinopsis2");
        updatedPelicula.setFecha_estreno(Date.valueOf("2024-02-01"));

        when(peliculaServiceMock.updatePelicula(1L, updatedPelicula)).thenReturn(updatedPelicula);

        mockMvc.perform(MockMvcRequestBuilders.put("/peliculas/update/1")
                .contentType("application/json")
                .content("{\"titulo\":\"Titulo2\",\"director\":\"Director2\",\"genero\":\"Genero2\",\"sinopsis\":\"Sinopsis2\",\"fecha_estreno\":\"2024-02-01\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.titulo", Matchers.is("Titulo2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.director", Matchers.is("Director2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.genero", Matchers.is("Genero2")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sinopsis", Matchers.is("Sinopsis2")));
    }


}
