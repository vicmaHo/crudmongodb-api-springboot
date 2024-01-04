/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.victorhernandez.crudMongo.controller;

import com.victorhernandez.crudMongo.model.Persona;
import com.victorhernandez.crudMongo.service.PersonaService;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Victor
 */
@RestController // indico que esta clase será la encargada de manejar las peticiones
@RequestMapping("/api/personas") // indico la ruta de acceso para los servicios
public class PersonaController {
    
    @Autowired // inyeccion de dependencias para el servicio de personas
    private PersonaService personaService;
    
    @GetMapping("/all")
    public ResponseEntity<List<Persona>> getAllPersonas(){
        List<Persona> personas = personaService.getAllPersonas();
        return ResponseEntity.ok().body(personas); // indico que el estado de la respuesta es correcto y devuelvo la lista de personas
    }

    @GetMapping("/byid/{id}") // indicamos entre corchetes un valor que sera el id de la persona que necesitamos
    public ResponseEntity<Persona> getPersonaById(@PathVariable Long id){ // con el pathvariable indico que este valor sera tomado de la url, en este caso el id que mandamos como parametro
        Persona persona = personaService.getPersonaById(id).orElse(null); // en este caso optional es un tipo de objeto que permite la comprobcion de la existencia de un valor en el para el manejo seguro de respuestas nulas 

        // Realizo la comprobacion de la existencia de los datos
        if (persona == null) {
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok().body(persona);
        }

        // return ResponseEntity.ok().body(persona);
    }

    @PostMapping("/save") // se recibe un objeto persona en parametros
    public ResponseEntity<Persona> savePersona(@RequestBody @Validated Persona persona){ // indico que la persona esta en el cuerpo de la petición post y valido 
        Persona personaGuardada = personaService.savePersona(persona);
        return ResponseEntity.status(HttpStatus.CREATED).body(personaGuardada); // devuelvo el estado 201 que significa creado con exito
    }

    @PutMapping("/update/{id}") // las actualizaciones se manejaran con PUT
    public ResponseEntity<Persona> updatePersona(@PathVariable Long id, @RequestBody @Validated Persona persona){
       Optional<Persona> personaOptional = personaService.getPersonaById(id);

       //Compruebo que la persona a modificar exista, en tal caso seteo el id y guardo a esta persona 
       if (personaOptional.isPresent()) {
            persona.setId(id);
            Persona updatePersona = personaService.savePersona(persona);
            return ResponseEntity.ok().body(updatePersona);
       }else {
            return ResponseEntity.notFound().build();
       }
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<Void> deletePersonaById(@PathVariable Long id){
        personaService.deletePersonaById(id);   
        return ResponseEntity.noContent().build(); // no respondo nada ya que el retorno es void
    }
}
