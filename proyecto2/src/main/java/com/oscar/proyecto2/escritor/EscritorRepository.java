package com.oscar.proyecto2.escritor;

import org.springframework.data.repository.CrudRepository;

/**
 * Interfaz que hereda de CrudRepository
 * @see org.springframework.data.repository.CrudRepository
 * @author oscar
 */
public interface EscritorRepository extends CrudRepository<Escritor, Integer> {
    
}