package org.cslt.peliculas_app.controllers;

import org.cslt.peliculas_app.models.Pelicula;
import org.cslt.peliculas_app.services.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/peliculas")
public class PeliculaController {

    @Autowired
    PeliculaService peliculaService;

    @GetMapping
    public CollectionModel<EntityModel<Pelicula>> obtenerPeliculas() {

        List<Pelicula> peliculas = peliculaService.getAllPeliculas();

        List<EntityModel<Pelicula>> peliculaResources = peliculas.stream()
                .map(pelicula -> EntityModel.of(pelicula,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPeliculaById(pelicula.getId())).withSelfRel()
                ))
                .collect(Collectors.toList());

        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).obtenerPeliculas());
        CollectionModel<EntityModel<Pelicula>> resources = CollectionModel.of(peliculaResources, linkTo.withRel("peliculas"));

        return resources;
    }

    @GetMapping("/{id}")
    public EntityModel<Pelicula> getPeliculaById(@PathVariable Long id) {

        Pelicula pelicula = peliculaService.getPeliculaById(id);

        return EntityModel.of(pelicula,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPeliculaById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).obtenerPeliculas()).withRel("peliculas")
        );

    }

    @PostMapping("/new")
    public EntityModel<Pelicula> createPelicula(@RequestBody Pelicula pelicula) {

        Pelicula savedPelicula = peliculaService.createPelicula(pelicula);

        return EntityModel.of(savedPelicula,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPeliculaById(savedPelicula.getId())).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).obtenerPeliculas()).withRel("peliculas")
        );

    }

    @PutMapping("/update/{id}")
    public EntityModel<Pelicula> updatePelicula(@PathVariable Long id, @RequestBody Pelicula pelicula) {

        Pelicula updatedPelicula = peliculaService.updatePelicula(id, pelicula);

        return EntityModel.of(updatedPelicula,
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getPeliculaById(id)).withSelfRel(),
                WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).obtenerPeliculas()).withRel("peliculas")
        );

    }

    @DeleteMapping("/delete/{id}")
    public void deletePelicula(@PathVariable Long id) {
        peliculaService.deletePelicula(id);
        System.out.println("Pelicula con ID " + id + " eliminada.");
    }

}
