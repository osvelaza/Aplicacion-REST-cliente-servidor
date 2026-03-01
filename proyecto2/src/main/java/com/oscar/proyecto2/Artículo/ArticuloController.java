package com.oscar.proyecto2.Artículo;

import com.oscar.proyecto2.escritor.Escritor;
import com.oscar.proyecto2.escritor.EscritorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Optional;

/**
 * Configura los endpoints de la entidad artículo
 * @author oscar
 */
@Controller 
@RequestMapping(path="/articulo")
public class ArticuloController {
    /**
     * Clase que hereda de la interfaz CRUD repository para usar sus metodos
     * @see CrudRepository
     */
    @Autowired
    private ArticuloRepository artRepository;

    @Autowired
    private EscritorRepository escritorRepository;

    /**
     * Configura el create sobre la entidad
     * @param titulo Titulo del artículo
     * @param idautor Autor que escribe el artículo
     * @param fecha Fecha de publicación del artículo
     * @param tema Tema del artículo
     * @param editorial Editorial que gestiona el artículo
     * @return
     */
    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addNewUser (@RequestParam String titulo, @RequestParam int idautor, @RequestParam Date fecha, @RequestParam String tema, @RequestParam String editorial) {
        Escritor e= escritorRepository.findById(idautor).orElse(null);
        if(e==null){
            return "No existe el escritor";
        }else{
            Articulo a = new Articulo();
            a.setTitulo(titulo);
            a.setIdautor(e);
            a.setFechaPub(fecha);
            a.setTema(tema);
            a.setEditorial(editorial);
            artRepository.save(a);
            return "Saved";
        }
    }

    /**
     * Configura el select sobre la entidad
     * @return
     */
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Articulo> getAllUsers() {
        return artRepository.findAll();
    }

    /**
     * Configura el update sobre la entidad
     * @param id Id del libro (autoincrement)
     * @param titulo Título del artículo
     * @param autor Autor que escribe el artículo
     * @param fecha Fecha de publicación del artículo
     * @param tema Tema del artículo
     * @param editorial Editorial que gestiona el artículo
     * @return
     */
    @PostMapping(path="/editar")
    public @ResponseBody String updateUser(@RequestParam int id,@RequestParam(required = false) String titulo, @RequestParam(required = false) String autor, @RequestParam(required = false) Date fecha, @RequestParam(required = false) String tema, @RequestParam(required = false) String editorial) {

        if (!artRepository.existsById(id)) {
            return "no encontrado";
        }

        Articulo a = artRepository.findById(id).get();

        if(titulo!=""){
            a.setTitulo(titulo);
        } else if (autor!="") {
            Escritor e= escritorRepository.findById(Integer.parseInt(autor)).orElse(null);
            if(e==null){
                return "No existe el escritor";
            }else{
                a.setIdautor(e);
            }
        } else if (tema!="") {
            a.setTema(tema);
        } else if (editorial!="") {
            a.setEditorial(editorial);
        } else {
            a.setFechaPub(fecha);
        }
        
        artRepository.save(a);

        return "actualizado";
    }

    /**
     * Configura el delete sobre la entidad
     * @param id Id del artículo a eliminar
     * @return
     */
    @DeleteMapping(path="/eliminar")
    public @ResponseBody String deleteUser(@RequestParam int id) {
        if (!artRepository.existsById(id)) {
            return "no encontrado";
        }

        Articulo a = artRepository.findById(id).get();
        artRepository.delete(a);

        return "Eliminado";
    }
}