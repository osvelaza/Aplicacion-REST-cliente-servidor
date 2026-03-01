package com.oscar.proyecto2;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;

/**
 * Clase principal simple que inicia el servidor
 * @author oscar
 */
@SpringBootApplication
public class Main {
    /**
     * Ejecuta Spring Boot
     * @param args
     */
    public static void main(String[] args)
    {
        SpringApplication.run(Main.class,args);
    }
}
