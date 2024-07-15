package com.mx.felipe.screenmatch.principal;

import com.mx.felipe.screenmatch.model.DatosEpisodio;
import com.mx.felipe.screenmatch.model.DatosSerie;
import com.mx.felipe.screenmatch.model.DatosTemporadas;
import com.mx.felipe.screenmatch.service.ConsumoAPI;
import com.mx.felipe.screenmatch.service.ConvierteDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {

    public void mostrarMenuPrincipal(){
        System.out.println("**********************************************************************************");
        System.out.println("********************* Bienvenido al portal ScreenMatch ***************************");
        System.out.println("**********************************************************************************");
    }

    public void mostrarMenuSecuandario() {
        System.out.println("**********************************************************************************");
        System.out.println("A continuación digíte la opción que desea realizar: \n");
        String menuPrincipal = """
                1) Obtener datos por Serie
                2) Obtener dator por Serie, numero de temporada y episodio
                3) Obtener todos los datos de la Serie
                4) Salir""";
        System.out.println("**********************************************************************************");
        System.out.println(menuPrincipal);
    }

    public void logicaPrograma() {
        var consumoApi = new ConsumoAPI();
        ConvierteDatos conversor = new ConvierteDatos();
        Scanner sc =  new Scanner(System.in);
        Principal menuUsuario = new Principal();
        menuUsuario.mostrarMenuPrincipal();
        final String URL_BASE = "http://www.omdbapi.com/?t=";
        final String API_KEY = "&apikey=b8b526fa";

        while (true) {

            menuUsuario.mostrarMenuSecuandario();
            int opcion = Integer.parseInt(sc.nextLine());

            if (opcion == 1) {

                System.out.println("Ingrese el nombre de la serie: ");
                var serie = sc.nextLine();
                var json =  consumoApi.getData(URL_BASE + serie.replace(" ", "+") + API_KEY);
                var datos = conversor.obtenerDatos(json, DatosSerie.class);
                System.out.println(datos);

            } else if (opcion == 2) {

                System.out.println("Ingrese el nombre de la serie: ");
                String serie = sc.nextLine();
                System.out.println("Ingrese el numero de la temporada: ");
                String numeroTemporada = sc.nextLine();
                System.out.println("Ingrese el numero de episodio: ");
                String numeroEpisodio = sc.nextLine();

                var jsonEpisodio = consumoApi.getData(URL_BASE + serie.replace(" ", "+") + "&Season=" + numeroTemporada + "&episode=" + numeroEpisodio + API_KEY);
                var datosEpisodio = conversor.obtenerDatos(jsonEpisodio, DatosEpisodio.class);
                System.out.println(datosEpisodio);

            } else if (opcion == 3) {

                System.out.println("Ingrese el nombre de la serie: ");
                var serie = sc.nextLine();
                var json =  consumoApi.getData(URL_BASE + serie.replace(" ", "+") + API_KEY);
                var datos = conversor.obtenerDatos(json, DatosSerie.class);

                List<DatosTemporadas> temporadas = new ArrayList<>();
                for (int i = 1; i <= Integer.parseInt(datos.totalDeTemporadas()); i++) {
                    var jsonTemporada =  consumoApi.getData(URL_BASE + serie.replace(" ", "+") + "&Season=" + i + API_KEY);
                    var datosTemporada = conversor.obtenerDatos(jsonTemporada, DatosTemporadas.class);
                    temporadas.add(datosTemporada);
                }

                //temporadas.forEach(System.out::println);
                // Mostrar datos de los episodios de cada temporada
//                for (int i = 0; i < Integer.parseInt(datos.totalDeTemporadas()); i++) {
//                    List<DatosEpisodio> datosEpisodios = temporadas.get(i).episodios();
//                    for (int j = 0; j < datosEpisodios.size() ; j++) {
//                        System.out.println("Temporada: " + temporadas.get(i).temporada()
//                                + ",Nombre del episodio: " + datosEpisodios.get(j).titulo()
//                        );
//                    }
//                }

                temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println("Temporada: " + t.temporada()
                        + ",Nombre del episodio: " + e.titulo())));


            } else if (opcion == 4) {

                System.out.println("**********************************************************************************");
                System.out.println("¡Esperamos verte pronto!");
                break;

            } else {
                System.out.println("**********************************************************************************");
                System.out.println("Ingrese la opcion correcta!");

            }

        }
    }


}
