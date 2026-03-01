package com.oscar.proyecto2.escritor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Entidad del autor
 * @author oscar
 */
@Entity // Hibernate puede crear una tabla para esta entidad
public class Escritor {
    //Atributos
    /**
     * Clave primaria id del artículo (autoincrement)
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    /**
     * Nombre del autor
     */
    private String name;

    /**
     * Correo electrónico del autor
     */
    private String email;

    //Getters y setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}