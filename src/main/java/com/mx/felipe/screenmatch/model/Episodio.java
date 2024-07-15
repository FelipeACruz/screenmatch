package com.mx.felipe.screenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;

import java.time.DateTimeException;
import java.time.LocalDate;

public class Episodio {

    private Integer temporada;
    private Integer numeroEpisodio;
    private String titulo;
    private LocalDate fechaLanzamiento;
    private Double evaluacion;

    public Integer getTemporada() {
        return temporada;
    }

    public Episodio(Integer temporada, DatosEpisodio datosEpisodio) {
        this.temporada = temporada;
        this.numeroEpisodio = Integer.parseInt(datosEpisodio.episodio());
        this.titulo = datosEpisodio.titulo();
        try {
            this.fechaLanzamiento = LocalDate.parse(datosEpisodio.fechaLanzamiento());
        } catch (DateTimeException e) {
            this.fechaLanzamiento = null;
        }
        try {
            this.evaluacion = Double.valueOf(datosEpisodio.evaluacion());
        } catch (NumberFormatException e) {
            this.evaluacion = 0.0;
        }

    }

    public void setTemporada(Integer temporada) {
        this.temporada = temporada;
    }

    public Integer getNumeroEpisodio() {
        return numeroEpisodio;
    }

    public void setNumeroEpisodio(Integer numeroEpisodio) {
        this.numeroEpisodio = numeroEpisodio;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public LocalDate getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(LocalDate fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public Double getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Double evaluacion) {
        this.evaluacion = evaluacion;
    }

    @Override
    public String toString() {
        return "temporada=" + temporada +
                ", numeroEpisodio=" + numeroEpisodio +
                ", titulo='" + titulo + '\'' +
                ", fechaLanzamiento=" + fechaLanzamiento +
                ", evaluacion=" + evaluacion +
                '}';
    }
}
