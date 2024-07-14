package com.mx.felipe.screenmatch;

import com.mx.felipe.screenmatch.model.DatosEpisodio;
import com.mx.felipe.screenmatch.model.DatosSerie;
import com.mx.felipe.screenmatch.service.ConsumoAPI;
import com.mx.felipe.screenmatch.service.ConvierteDatos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {


		var consumoApi = new ConsumoAPI();
		var json =  consumoApi.getData("http://www.omdbapi.com/?t=breaking+bad&apikey=b8b526fa");
		System.out.println(json);

		ConvierteDatos conversor = new ConvierteDatos();
		var datos = conversor.obtenerDatos(json, DatosSerie.class);
		System.out.println(datos);

		var jsonEpisodio = consumoApi.getData("http://www.omdbapi.com/?t=breaking+bad&Season=1&episode=1&apikey=b8b526fa");
		var datosEpisodio = conversor.obtenerDatos(jsonEpisodio, DatosEpisodio.class);
		System.out.println(datosEpisodio);



	}
}
