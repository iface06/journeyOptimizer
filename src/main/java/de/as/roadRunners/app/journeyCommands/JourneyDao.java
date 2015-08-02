
package de.as.roadRunners.app.journeyCommands;

import de.as.roadRunners.app.entities.Journey;
import de.as.roadRunners.app.entities.User;
import java.util.List;


public interface JourneyDao {
    
    Journey save(Journey j);

    List<Journey> findJournies(User user);

    Journey findJourney(String journeyId);

}
