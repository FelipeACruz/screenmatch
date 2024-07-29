package com.mx.felipe.screenmatch.principal;

import com.mx.felipe.screenmatch.model.*;
import com.mx.felipe.screenmatch.service.ConsumoAPI;
import com.mx.felipe.screenmatch.service.ConvierteDatos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

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
                2) Obtener datos por Serie, numero de temporada y episodio
                3) Obtener todos los datos de la Serie
                4) Obtener mejores episodios de la Serie
                5) Obtener episodios de una serie apartir de una fecha
                6) Obtener episodio por pedazo de su nombre
                7) Obtener valoraciones de la serie.
                8) Salir""";
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
                var datosSerie = conversor.obtenerDatos(json, DatosSerie.class);

                var datosTemporadas = new Temporadas(datosSerie, consumoApi, conversor, API_KEY);
                var temporadas = datosTemporadas.obtenerDatosTemporada(URL_BASE, serie);

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

//                temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println("Temporada: " + t.temporada()
//                                + ",Nombre del episodio: " + e.titulo())));

                // Convirtiendo los datos a una lista tipo episodio
                List<Episodio> episodios = temporadas.stream()
                        .flatMap(t -> t.episodios().stream()
                                .map(datosEpisodio ->
                                        new Episodio(Integer.parseInt(t.temporada()), datosEpisodio)))
                        .collect(Collectors.toList());

                episodios.forEach(System.out::println);


            } else if (opcion == 4) {
                System.out.println("Ingrese el nombre de la serie: ");
                var serie = sc.nextLine();
                var json =  consumoApi.getData(URL_BASE + serie.replace(" ", "+") + API_KEY);
                var datosSerie = conversor.obtenerDatos(json, DatosSerie.class);

                var datosTemporadas = new Temporadas(datosSerie, consumoApi, conversor, API_KEY);
                var temporadas = datosTemporadas.obtenerDatosTemporada(URL_BASE, serie);

                // Convertir toda la informacion de las temporadas a una lista del tipo DatosEpisodio
                List<Episodio> episodios = temporadas.stream()
                        .flatMap(t -> t.episodios().stream()
                                .map(datosEpisodio ->
                                        new Episodio(Integer.parseInt(t.temporada()), datosEpisodio)))
                        .collect(Collectors.toList());


//                List<DatosEpisodio> datosEpisodios =  temporadas.stream()
//                        .flatMap(t -> t.episodios().stream())
//                        .collect(Collectors.toList());

                System.out.println("**********************************************************************************");
                System.out.println("¡Top 5 episodios!");
                episodios.stream()
                        .sorted(Comparator.comparing(Episodio::getEvaluacion).reversed())
                        .filter(episodio -> !episodio.getEvaluacion().toString().equalsIgnoreCase("N/A"))
                        .limit(5)
                        .forEach(System.out::println);


            } else if (opcion == 5) {

                System.out.println("Ingrese el nombre de la serie: ");
                var serie = sc.nextLine();
                var json =  consumoApi.getData(URL_BASE + serie.replace(" ", "+") + API_KEY);
                var datosSerie = conversor.obtenerDatos(json, DatosSerie.class);

                var datosTemporadas = new Temporadas(datosSerie, consumoApi, conversor, API_KEY);
                var temporadas = datosTemporadas.obtenerDatosTemporada(URL_BASE, serie);

                // Convertir toda la informacion de las temporadas a una lista del tipo DatosEpisodio
                List<Episodio> episodios = temporadas.stream()
                        .flatMap(t -> t.episodios().stream()
                                .map(datosEpisodio ->
                                        new Episodio(Integer.parseInt(t.temporada()), datosEpisodio)))
                        .collect(Collectors.toList());

                System.out.println("**********************************************************************************");
                System.out.println("Indica el año a partir del cual deseas ver los episodios");
                var fecha = sc.nextInt();
                sc.nextLine();

                LocalDate fechaBusqueda = LocalDate.of(fecha, 1, 1);

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                episodios.stream()
                        .filter(e -> e.getFechaLanzamiento() != null && e.getFechaLanzamiento().isAfter(fechaBusqueda))
                        .forEach(e -> System.out.println(
                                "Temporada: " + e.getTemporada() +
                                        ", Episodio: " + e.getTitulo() +
                                        ", Fecha de Lanzamiento: " + e.getFechaLanzamiento().format(formatter)
                        ));

            } else if (opcion == 6) {

                System.out.println("Ingrese el nombre de la serie: ");
                var serie = sc.nextLine();
                var json =  consumoApi.getData(URL_BASE + serie.replace(" ", "+") + API_KEY);
                var datosSerie = conversor.obtenerDatos(json, DatosSerie.class);

                var datosTemporadas = new Temporadas(datosSerie, consumoApi, conversor, API_KEY);
                var temporadas = datosTemporadas.obtenerDatosTemporada(URL_BASE, serie);


                List<Episodio> episodios = temporadas.stream()
                        .flatMap(t -> t.episodios().stream()
                                .map(datosEpisodio ->
                                        new Episodio(Integer.parseInt(t.temporada()), datosEpisodio)))
                        .collect(Collectors.toList());


                //Busca episodios por un pedazo del título
                System.out.println("Por favor escriba un pedazo del titulo del episodio que desea ver: ");
                var pedazoTitulo = sc.nextLine();
                Optional<Episodio> episodioBuscado = episodios.stream()
                        .filter(e -> e.getTitulo().toUpperCase().contains(pedazoTitulo.toUpperCase()))
                        .findFirst();
                if(episodioBuscado.isPresent()){
                    System.out.println("Episodio encontrado");
                    System.out.println("Los datos son: " + episodioBuscado.get());
                } else {
                    System.out.println("Episodio no encontrado");
                }

            } else if (opcion == 7) {

                System.out.println("**********************************************************************************");
                System.out.println("Obtener valoraciones de la serie");

                System.out.println("Ingrese el nombre de la serie: ");
                var serie = sc.nextLine();
                var json =  consumoApi.getData(URL_BASE + serie.replace(" ", "+") + API_KEY);
                var datosSerie = conversor.obtenerDatos(json, DatosSerie.class);

                var datosTemporadas = new Temporadas(datosSerie, consumoApi, conversor, API_KEY);
                var temporadas = datosTemporadas.obtenerDatosTemporada(URL_BASE, serie);

                List<Episodio> episodios = temporadas.stream()
                        .flatMap(t -> t.episodios().stream()
                                .map(datosEpisodio ->
                                        new Episodio(Integer.parseInt(t.temporada()), datosEpisodio)))
                        .collect(Collectors.toList());

                Map<Integer , Double> evaluacionesPorTemporada = episodios.stream()
                        .filter(e -> e.getEvaluacion() > 0.0)
                        .collect(Collectors.groupingBy(Episodio::getTemporada,
                                Collectors.averagingDouble(Episodio::getEvaluacion)));

                evaluacionesPorTemporada.forEach((k, v) -> System.out.println("Temporada: " + k + ", evaluacion: " + v));

                DoubleSummaryStatistics est = episodios.stream()
                        .filter(e -> e.getEvaluacion() > 0.0)
                        .collect(Collectors.summarizingDouble(Episodio::getEvaluacion));
                System.out.println("Evaluacion promedio de la Serie: " + est.getAverage());
                System.out.println("Calificación Episodio Mejor evaluado: " + est.getMax() + est.toString());



            } else if (opcion == 8) {
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
