package com.oscar.proyecto2.Artículo;

import org.springframework.data.repository.CrudRepository;

/**
 * Hereda de CrudRepository
 * @see org.springframework.data.repository.CrudRepository
 * @author oscar
 */
public interface ArticuloRepository extends CrudRepository<Articulo, Integer> {
    
}