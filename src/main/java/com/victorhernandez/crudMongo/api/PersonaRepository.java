
package com.victorhernandez.crudMongo.api;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.victorhernandez.crudMongo.model.Persona;
/**
 *
 * @author Victor
 */
// es la representacipon de las Personas en mi base de datos, todo lo que traiga será Persona y me proporciona los metodos crud
// en base a los datos que harán referencia a las personas
public interface PersonaRepository extends MongoRepository<Persona, Long>{
    
}
    