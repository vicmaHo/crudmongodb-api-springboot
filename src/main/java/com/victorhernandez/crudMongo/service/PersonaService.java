
package com.victorhernandez.crudMongo.service;

import com.victorhernandez.crudMongo.model.Persona;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Victor
 */
public interface PersonaService {
    // Metodos abstractos que hacen referencia a las acciones crud para el apartado de personas en la base de datos
    List<Persona> getAllPersonas();
    Optional<Persona> getPersonaById(Long id);
    Persona savePersona(Persona persona);
    void deletePersonaById(Long id);
}
