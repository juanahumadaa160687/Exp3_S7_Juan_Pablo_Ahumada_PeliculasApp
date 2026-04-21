package org.cslt.peliculas_app.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import java.sql.Date;

@Entity
@Table(name = "PELICULA")

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor

public class Pelicula extends RepresentationModel<Pelicula> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String titulo;
    String director;
    String sinopsis;
    String genero;
    Date fecha_estreno;

}
