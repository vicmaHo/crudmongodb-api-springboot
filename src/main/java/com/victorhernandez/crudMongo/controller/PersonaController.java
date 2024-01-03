/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.victorhernandez.crudMongo.controller;

import com.victorhernandez.crudMongo.model.Persona;
import com.victorhernandez.crudMongo.service.PersonaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Victor
 */
@RestController // indico que esta clase será la encargada de manejar las peticiones
@RequestMapping("/api/personas") // indico la ruta 
public class PersonaController {
    
    @Autowired
    private PersonaService personaService;
    
    @GetMapping("/all")
    public ResponseEntity<List<Persona>> getAllPersonas(){
        List<Persona> personas = personaService.getAllPersonas();
        
    }
}
