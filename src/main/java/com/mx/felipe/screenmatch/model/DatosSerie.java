package com.mx.felipe.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record DatosSerie(@JsonAlias("Title") String titulo,
                         @JsonAlias("Released") String fechaLanzamiento,
                         @JsonAlias("totalSeasons") Integer totalDeTemporadas,
                         @JsonAlias("imdbRating") Integer evalucaion) {


}
