package com.oscar.proyecto2.escritor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Configura los endpoints de la entidad Escritor
 * @author oscar
 * Configura los endpoints a la entidad Escritor
 */
@Controller 
@RequestMapping(path="/escritor")
public class EscritorController {
    /**
     * Clase que hereda de la interfaz CRUD repository para usar sus metodos
     * @see org.springframework.data.repository.CrudRepository
     */
    @Autowired
    private EscritorRepository userRepository;

    /**
     * Configura el create sobre la entidad
     * @param name Nombre del escritor
     * @param email Correo electrónico del escritor
     * @return Textos informativos para indicar si la operación ha sido exitosa o no
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
     * @return Todos los escritores de la base de datos
     */
    @GetMapping(path="/all")
    public @ResponseBody Iterable<Escritor> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Configura el update sobre la entidad
     * @param id El id del escritor a editar
     * @param name Nombre del escritor
     * @param email El correo electrónico del escritor
     * @return Textos informativos para indicar si la operación ha sido exitosa o no
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
     * @param id Id del escritor a eliminar
     * @return Textos informativos para indicar si la operación ha sido exitosa o no
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