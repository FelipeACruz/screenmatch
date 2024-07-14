package com.mx.felipe.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosSerie(@JsonAlias("Title") String titulo,
                         @JsonAlias("Released") String fechaLanzamiento,
                         @JsonAlias("totalSeasons") String totalDeTemporadas,
                         @JsonAlias("imdbRating") String evaluacion) {


}
