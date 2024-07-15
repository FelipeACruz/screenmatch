package com.mx.felipe.screenmatch;

import com.mx.felipe.screenmatch.model.DatosEpisodio;
import com.mx.felipe.screenmatch.model.DatosSerie;
import com.mx.felipe.screenmatch.model.DatosTemporadas;
import com.mx.felipe.screenmatch.principal.Principal;
import com.mx.felipe.screenmatch.service.ConsumoAPI;
import com.mx.felipe.screenmatch.service.ConvierteDatos;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class ScreenmatchApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ScreenmatchApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Principal run = new Principal();
		run.logicaPrograma();


	}
}
