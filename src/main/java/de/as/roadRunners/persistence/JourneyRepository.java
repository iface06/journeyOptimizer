
package de.as.roadRunners.persistence;

import de.as.roadRunners.app.entities.Journey;
import org.springframework.data.repository.CrudRepository;


public interface JourneyRepository extends CrudRepository<Journey, String>{

}
