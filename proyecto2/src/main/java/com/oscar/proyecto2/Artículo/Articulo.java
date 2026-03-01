package com.oscar.proyecto2.Artículo;

import com.oscar.proyecto2.escritor.Escritor;
import jakarta.persistence.*;
import org.hibernate.annotations.DialectOverride;

import java.sql.Date;

/**
 * Entidad de artículo
 * @author oscar
 */
@Entity // Hibernate puede crear una tabla para esta entidad
public class Articulo {
    //Atributos
    /**
     * Clave primaria id del artículo (autoincrement)
     */
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    /**
     * Título del artículo
     */
    private String titulo;

    /**
     * Contiene el id del autor que escribe el artículo
     */
    @OneToOne(cascade = CascadeType.REMOVE)
    private Escritor escritor;

    /**
     * Fecha de publicación del artículo
     */
    private Date fechaPub;

    /**
     * Tema general del artículo
     */
    private String tema;

    /**
     * Editorial que gestiona el artículo
     */
    private String editorial;

    //Getters y setters

    public Integer getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getIdautor() {
        return escritor.getId();
    }

    public Date getFechaPub() {
        return fechaPub;
    }

    public String getTema() {
        return tema;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setIdautor(Escritor autor) {
        this.escritor=autor;
    }

    public void setFechaPub(Date fechaPub) {
        this.fechaPub = fechaPub;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }
}