
package de.as.roadRunners.persistence;

import de.as.roadRunners.app.entities.Journey;
import de.as.roadRunners.app.entities.User;
import de.as.roadRunners.app.journeyCommands.JourneyDao;
import java.util.ArrayList;
import java.util.List;


public class JourneyMongoDbDao implements JourneyDao{

    private JourneyRepository repository;

    public JourneyMongoDbDao(JourneyRepository repository) {
        this.repository = repository;
    }
    
    @Override
    public Journey save(Journey j) {
        return repository.save(j);
    }

    @Override
    public List<Journey> findJournies(User user) {
        Iterable<Journey> journies = repository.findAll();
        List<Journey> journiesAsList = new ArrayList<Journey>();
        for (Journey journy : journies) {
            journiesAsList.add(journy);
        }
        
        return journiesAsList;
    }

    @Override
    public Journey findJourney(String journeyId) {
        return repository.findOne(journeyId);
    }
    
    

}
