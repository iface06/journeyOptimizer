
package de.as.roadRunners.persistence;

import de.as.roadRunners.app.entities.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, String> {

}
