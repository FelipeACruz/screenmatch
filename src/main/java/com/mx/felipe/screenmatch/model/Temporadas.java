package com.mx.felipe.screenmatch.model;

import com.mx.felipe.screenmatch.service.ConsumoAPI;
import com.mx.felipe.screenmatch.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;

public class Temporadas {

    private DatosSerie  datosSerie;
    private ConsumoAPI consumoAPI;
    private ConvierteDatos conversor;
    private String API_KEY;


    public Temporadas(DatosSerie datosSerie, ConsumoAPI consumoAPI, ConvierteDatos convierteDatos, String API_KEY) {
        this.datosSerie = datosSerie;
        this.consumoAPI = consumoAPI;
        this.conversor = convierteDatos;
        this.API_KEY = API_KEY;

    }

    public List<DatosTemporadas> obtenerDatosTemporada(String URL_BASE, String serie) {

        List<DatosTemporadas> temporadas = new ArrayList<>();

        for (int i = 1; i <= Integer.parseInt(this.datosSerie.totalDeTemporadas()); i++) {
            var jsonTemporada =  this.consumoAPI.getData(URL_BASE + serie.replace(" ", "+") + "&Season=" + i + API_KEY);
            var datosTemporada = conversor.obtenerDatos(jsonTemporada, DatosTemporadas.class);
            temporadas.add(datosTemporada);
        }

        return temporadas;


    }


}
