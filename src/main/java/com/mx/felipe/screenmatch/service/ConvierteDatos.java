package com.mx.felipe.screenmatch.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mx.felipe.screenmatch.model.DatosSerie;

public class ConvierteDatos implements IConvirteDatos {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T obtenerDatos(String json, Class<T> clase) {
        return null;
    }
}
