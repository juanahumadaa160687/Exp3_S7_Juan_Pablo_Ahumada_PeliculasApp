package org.cslt.peliculas_app.services;

import org.cslt.peliculas_app.models.Pelicula;

import java.util.List;

public interface PeliculaService {

    List<Pelicula> getAllPeliculas();
    Pelicula getPeliculaById(Long id);
    Pelicula createPelicula(Pelicula pelicula);
    Pelicula updatePelicula(Long id, Pelicula pelicula);
    void deletePelicula(Long id);

}
