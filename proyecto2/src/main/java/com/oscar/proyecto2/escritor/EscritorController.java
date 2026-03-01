package com.oscar.proyecto2.escritor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author oscar
 * Congigura los endpoints a la entidad Escritor
 */
@Controller 
@RequestMapping(path="/escritor") // This means URL's start with /demo (after Application path)
public class EscritorController {
    /**
     * Clase que hereda de la interfaz CRUD repository para usar sus metodos
     * @see org.springframework.data.repository.CrudRepository
     */
    @Autowired
    private EscritorRepository userRepository;

    /**
     * Configura el create sobre la entidad
     * @param name
     * @param email
     * @return
     */
    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addNewUser (@RequestParam String name, @RequestParam String email) {
        Escritor n = new Escritor();
        n.setName(name);
        n.setEmail(email);
        userRepository.save(n);
        return "Saved";
    }

    /**
     * Configura el select sobre la entidad
     * @return
     */
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Escritor> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Configura el update sobre la entidad
     * @param id
     * @param name
     * @param email
     * @return
     */
    @PostMapping(path="/editar")
    public @ResponseBody String updateUser(@RequestParam int id,@RequestParam String name,@RequestParam String email) {

        if (!userRepository.existsById(id)) {
            return "no encontrado";
        }

        Escritor u = userRepository.findById(id).get();

        if(name==""){
            u.setEmail(email);
        } else if (email=="") {
            u.setName(name);
        }else{
            return "Error";
        }

        userRepository.save(u);

        return "actualizado";
    }

    /**
     * Configura el delete sobre la entidad
     * @param id
     * @return
     */
    @DeleteMapping(path="/eliminar")
    public @ResponseBody String deleteUser(@RequestParam int id) {
        if (!userRepository.existsById(id)) {
            return "no encontrado";
        }

        Escritor u = userRepository.findById(id).get();
        userRepository.delete(u);

        return "Eliminado";
    }
}