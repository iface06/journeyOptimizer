
package de.as.roadRunners.sessions;

import de.as.roadRunners.app.entities.Journey;
import de.as.roadRunners.app.entities.User;


public class RoadRunnerSession {
    
    private Journey journey;
    private User user;

    public Journey getJourney() {
        return journey;
    }

    public void setJourney(Journey journey) {
        this.journey = journey;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    

}
