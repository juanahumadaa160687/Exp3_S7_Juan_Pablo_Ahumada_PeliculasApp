package org.cslt.peliculas_app.services;

import lombok.Setter;
import org.cslt.peliculas_app.models.Pelicula;
import org.cslt.peliculas_app.repositories.PeliculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PeliculaServiceImpl implements PeliculaService {

    @Autowired
    private PeliculaRepository peliculaRepository;

    @Override
    public List<Pelicula> getAllPeliculas() {
        return peliculaRepository.findAll();
    }

    @Override
    public Pelicula getPeliculaById(Long id) {
        return peliculaRepository.findById(id).orElse(null);
    }

    @Override
    public Pelicula createPelicula(Pelicula pelicula) {
        return peliculaRepository.save(pelicula);
    }

    @Override
    public Pelicula updatePelicula(Long id, Pelicula pelicula) {
        Pelicula existingPelicula = peliculaRepository.findById(id).orElse(null);
        if (existingPelicula != null) {
            existingPelicula.setTitulo(pelicula.getTitulo());
            existingPelicula.setDirector(pelicula.getDirector());
            existingPelicula.setSinopsis(pelicula.getSinopsis());
            existingPelicula.setGenero(pelicula.getGenero());
            existingPelicula.setFecha_estreno(pelicula.getFecha_estreno());
            return peliculaRepository.save(existingPelicula);
        }
        return null;
    }

    @Override
    public void deletePelicula(Long id) {
        peliculaRepository.deleteById(id);
    }

}
