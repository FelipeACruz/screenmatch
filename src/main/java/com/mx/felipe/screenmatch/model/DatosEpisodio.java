package com.mx.felipe.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosEpisodio(
        @JsonAlias("Title") String titulo,
        @JsonAlias("Episode") String episodio,
        @JsonAlias("Released") String fechaLanzamiento,
        @JsonAlias("Season") String temporada,
        @JsonAlias("Runtime") String duracion,
        @JsonAlias("imdbRating") String evaluacion) {

}
