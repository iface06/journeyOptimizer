
package de.as.roadRunners.app.journeyCommands;

import de.as.roadRunners.app.entities.Journey;


public class JourneyOptimizationContext {
    
    private boolean success = true;
    private Journey originalJourney;
    private Journey optimizedJourney;
    

    public JourneyOptimizationContext(Journey journeyForOptimiziation) {
        originalJourney = journeyForOptimiziation;
    }

    public Journey getOriginalJourney() {
        return originalJourney;
    }

    public void setOriginalJourney(Journey originalJourney) {
        this.originalJourney = originalJourney;
    }

    public Journey getOptimizedJourney() {
        return optimizedJourney;
    }

    public void setOptimizedJourney(Journey optimizedJourney) {
        this.optimizedJourney = optimizedJourney;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
