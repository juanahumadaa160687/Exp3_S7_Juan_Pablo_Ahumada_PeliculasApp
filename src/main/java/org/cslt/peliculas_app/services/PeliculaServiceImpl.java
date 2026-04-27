package org.cslt.peliculas_app.services;

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
        if(peliculaRepository.existsById(id)) {
            pelicula.setId(id);
            return peliculaRepository.save(pelicula);
        }
        return null;
    }

    @Override
    public void deletePelicula(Long id) {
        peliculaRepository.deleteById(id);
    }

}
