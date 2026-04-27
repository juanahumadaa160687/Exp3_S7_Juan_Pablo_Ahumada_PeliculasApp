package org.cslt.peliculas_app.controllers;

import org.cslt.peliculas_app.models.Pelicula;
import org.cslt.peliculas_app.services.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {

    @Autowired
    PeliculaService peliculaService;

    @GetMapping
    public List<Pelicula> obtenerPeliculas() {
        return peliculaService.getAllPeliculas();
    }

    @GetMapping("/{id}")
    public Pelicula getPeliculaById(@PathVariable Long id) {
        return peliculaService.getPeliculaById(id);
    }

    @PostMapping("/new")
    public Pelicula createPelicula(@RequestBody Pelicula pelicula) {
        return peliculaService.createPelicula(pelicula);
    }

    @PutMapping("/update/{id}")
    public Pelicula updatePelicula(@PathVariable Long id, @RequestBody Pelicula pelicula) {
        return peliculaService.updatePelicula(id, pelicula);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePelicula(@PathVariable Long id) {
        peliculaService.deletePelicula(id);
        System.out.println("Pelicula con ID " + id + " eliminada.");
    }

}
