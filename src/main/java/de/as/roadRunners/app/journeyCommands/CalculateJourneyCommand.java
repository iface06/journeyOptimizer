
package de.as.roadRunners.app.journeyCommands;

import de.as.roadRunners.app.Command;
import de.as.roadRunners.app.entities.Journey;


public class CalculateJourneyCommand implements Command{
    private JourneyOptimizer planer;
    private final JourneyOptimizationContext context;

    public CalculateJourneyCommand(JourneyOptimizationContext context) {
        this.context = context;
        planer = new JourneyOptimizer(context.getOriginalJourney());
    }

    @Override
    public void execute() {
        try {
            executeCalculation();
        } catch(RuntimeException ex) {
            handleException(ex);
        }
    }

    private void executeCalculation() {
        Journey optimizedJourney = planer.apply();
        context.getOriginalJourney().setDestinations(optimizedJourney.getDestinations());
        context.getOriginalJourney().setDistance(optimizedJourney.getDistance());
        
        context.setOptimizedJourney(context.getOriginalJourney());
    }
    
    protected void handleException(RuntimeException ex) {
        throw ex;
    }

    protected void setPlaner(JourneyOptimizer planer) {
        this.planer = planer;
    }
}
