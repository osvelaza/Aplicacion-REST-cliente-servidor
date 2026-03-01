import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * @author oscar
 * Clase principal
 */
public class Main {
    static Scanner sc=new Scanner(System.in);
    /**
     * @param args
     */
    public static void main(String[] args){
        Boolean continuar=true;
        int selector = 0;

        while(continuar){

        System.out.println("¿Que quiere hacer?");
        System.out.println("1. Mostrar elementos");
        System.out.println("2. Crear elemento");
        System.out.println("3. Editar un valor");
        System.out.println("4. Eliminar una tupla");
        System.out.println("5. Salir del programa");
        System.out.println();

        try {
            selector = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Opción no válida. Intente de nuevo.");
            continue;
        }

            try {
                switch (selector){
                    case 1:
                        //select

                        switch (menuEntidades()){
                            case 1:
                                peticionGet("http://localhost:12345/escritor/all");
                                break;
                            case 2:
                                peticionGet("http://localhost:12345/articulo/all");
                                break;
                            case 3:
                                break;
                        }
                        break;
                    case 2:
                        //create

                        switch (menuEntidades()){
                            case 1:
                                System.out.println("Introduce el nombre del autor");
                                String nombre=sc.nextLine();

                                System.out.println("Introduce su email");
                                String email=sc.nextLine();

                                peticionPost("http://localhost:12345/escritor/add?name="+nombre+"&email="+email);
                                break;

                            case 2:
                                System.out.println("Introduce el nombre del artículo");
                                nombre=sc.nextLine();

                                //Listar los escritores para que el usuario pueda leer los id de los escritores
                                peticionGet("http://localhost:12345/escritor/all");
                                System.out.println();
                                System.out.println("Introduce el id del escritor");
                                System.out.println("Introduce 0 para añadir otro escritor");

                                int idautor=sc.nextInt();
                                if(idautor==0){
                                    sc.nextLine();
                                    System.out.println("Introduce el nombre del autor");
                                    nombre=sc.nextLine();

                                    System.out.println("Introduce su email");
                                    email=sc.nextLine();

                                    peticionPost("http://localhost:12345/escritor/add?name="+nombre+"&email="+email);

                                    //Listar los escritores para que el usuario pueda leer los id de los escritores
                                    peticionGet("http://localhost:12345/escritor/all");
                                    System.out.println();
                                    System.out.println("Introduce el id del escritor");
                                }

                                sc.nextLine();

                                System.out.println("Introduce la fecha en formato dd/mm/aa");
                                String fechat=sc.nextLine();


                                System.out.println("Introduce el tema del artículo");
                                String tema=sc.nextLine();

                                System.out.println("Introduce el nombre de la editorial");
                                String editorial=sc.nextLine();

                                System.out.println();

                                peticionPost("http://localhost:12345/articulo/add?titulo="+nombre+"&idautor="+idautor+"&fecha="+dateConverter(fechat)+"&tema="+tema+"&editorial="+editorial);
                                break;
                            case 3:
                                break;
                        }
                        break;
                    case 3:
                        //Update

                        switch (menuEntidades()){
                            case 1:
                                String act;

                                peticionGet(("http://localhost:12345/escritor/all"));
                                System.out.println("Introduce el id del escritor a editar");
                                int ideditar=sc.nextInt();

                                System.out.println("¿Que quiere editar?");
                                System.out.println("1. Nombre");
                                System.out.println("2. Email");
                                int menu=sc.nextInt();

                                sc.nextLine();

                                switch (menu){
                                    case 1:
                                        System.out.println("Introduce el nuevo nombre del escritor");
                                        act=sc.nextLine();
                                        peticionPost("http://localhost:12345/escritor/editar?id="+ideditar+"&name="+act+"&email");
                                        break;
                                    case 2:
                                        System.out.println("Introduce el nuevo email del escritor");
                                        act=sc.nextLine();
                                        peticionPost("http://localhost:12345/escritor/editar?id="+ideditar+"&name"+"&email="+act);
                                        break;
                                }

                                break;
                            case 2:
                                System.out.println("");
                                peticionGet(("http://localhost:12345/articulo/all"));
                                System.out.println("Introduce el id del artículo a editar");
                                ideditar=sc.nextInt();

                                System.out.println("¿Que quiere editar?");
                                System.out.println("1. Título");
                                System.out.println("2. Escritor");
                                System.out.println("3. Fecha");
                                System.out.println("4. Tema");
                                System.out.println("5. Editorial");
                                menu=sc.nextInt();

                                sc.nextLine();

                                switch (menu){
                                    case 1:
                                        System.out.println("Introduce el nuevo título del artículo");
                                        act=sc.nextLine();
                                        peticionPost("http://localhost:12345/articulo/editar?id="+ideditar+"&titulo=");     //+act+"&autor&fecha&tema&editorial

                                        break;
                                    case 2:
                                        System.out.println("Introduce el nuevo id del autor del artículo");
                                        peticionGet("http://localhost:12345/escritor/all");
                                        act=sc.nextLine();
                                        peticionPost("http://localhost:12345/articulo/editar?id="+ideditar+"&autor="+act);     //+"&fecha&tema&editorial"

                                        break;
                                    case 3:
                                        System.out.println("Introduce la nueva fecha de publicación del artículo en formato dd/mm/aa");
                                        act=sc.nextLine();
                                        Date fecha=dateConverter(act);
                                        peticionPost("http://localhost:12345/articulo/editar?id="+ideditar+"fecha="+fecha);      //"&titulo&autor&    "&tema&editorial"

                                        break;
                                    case 4:
                                        System.out.println("Introduce el nuevo tema del artículo");
                                        act=sc.nextLine();
                                        peticionPost("http://localhost:12345/articulo/editar?id="+ideditar+"&tema="+act);  //&titulo&autor&fecha    +"&editorial"

                                        break;
                                    case 5:
                                        System.out.println("Introduce la nueva editorial del artículo");
                                        act=sc.nextLine();
                                        peticionPost("http://localhost:12345/articulo/editar?id="+ideditar+"&editorial="+act);  //&titulo&autor&fecha&tema

                                        break;
                                }

                                break;
                        }
                        peticionGet("http://localhost:8486/articulo/all");
                        break;
                    case 4:
                        //Delete
                        int idaeliminar;

                        switch (menuEntidades()){
                            case 1:

                                peticionGet("http://localhost:12345/escritor/all");

                                System.out.println("Introduce el id a eliminar");
                                idaeliminar=sc.nextInt();
                                peticionDelete("http://localhost:12345/escritor/eliminar?id="+idaeliminar);
                                break;
                            case 2:

                                peticionGet("http://localhost:12345/articulo/all");

                                System.out.println("Introduce el id a eliminar");
                                idaeliminar=sc.nextInt();
                                peticionDelete("http://localhost:12345/articulo/eliminar?id="+idaeliminar);
                                break;
                            case 3:
                                break;
                        }
                        break;
                    case 5:
                        //Salir
                        continuar=false;
                        break;
                }
            } catch (IOException | RuntimeException | InterruptedException e) {
                e.getMessage();
            }
        }
    }

    /**
     * Crea una petición get con la url que recibe por parámetro y luego escribe la respuesta del servidor
     * @param url
     * @throws IOException
     * @throws InterruptedException
     */
    public static void peticionGet(String url) throws IOException, InterruptedException {
        // Create an HttpClient
        HttpClient client = HttpClient.newHttpClient();

        // Create an HttpRequest
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url.toString()))
                .build();

        // Send the request and receive the response
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Print the response body
        System.out.println(response.body());

        client.close();
    }

    /**
     * Recibe una url de un endpoint de una api y escribe el json de la respuesta
     * @param url
     * @throws IOException
     * @throws InterruptedException
     */
    public static void peticionPost(String url) throws IOException, InterruptedException {
        // Create an HttpClient
        HttpClient client = HttpClient.newHttpClient();

        // Create an HttpRequest
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url.toString()))
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        // Send the request and receive the response
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Print the response body
        System.out.println(response.body());

        client.close();
    }

    /**
     *
     * @param url
     * @throws IOException
     * @throws InterruptedException
     */
    public static void peticionDelete(String url) throws IOException, InterruptedException {
        // Create an HttpClient
        HttpClient client = HttpClient.newHttpClient();

        // Create an HttpRequest
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url.toString()))
                .DELETE()
                .build();

        // Send the request and receive the response
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Print the response body
        System.out.println(response.body());

        client.close();
    }

    /**
     * Esta función muestra un menú que permite elejir entre Escritor y Artículo al usuario para que este pueda elejir en que opción entrar.
     * @return Devuelve el número del menú que elige el usuario
     */
    public static int menuEntidades(){
        int selector=0;

        System.out.println("1. Escritor");
        System.out.println("2. Articulo");
        System.out.println();

        selector=sc.nextInt();

        sc.nextLine();

        return selector;
    }

    /**
     * Una función que convierte de String a Date de sql
     * @param fecha Texto en formato dd/mm/aa
     * @return Fecha en formato Date sql
     */
    public static Date dateConverter(String fecha){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate localDate = LocalDate.parse(fecha, formatter);
        java.sql.Date sqlDate = java.sql.Date.valueOf(localDate);

        return sqlDate;
    }
}