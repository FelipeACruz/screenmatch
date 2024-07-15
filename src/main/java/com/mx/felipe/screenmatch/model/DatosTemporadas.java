package com.mx.felipe.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosTemporadas(
        @JsonAlias("Title") String titulo,
        @JsonAlias("Season") String temporada,
        @JsonAlias("totalSeasons") String totalTemporadas,
        @JsonAlias("Episodes") List<DatosEpisodio> episodios) {


}
