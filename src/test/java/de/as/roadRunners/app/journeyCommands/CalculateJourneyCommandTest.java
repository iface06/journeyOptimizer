package de.as.roadRunners.app.journeyCommands;


import de.as.roadRunners.app.entities.Journey;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class CalculateJourneyCommandTest {

    private JourneyOptimizationContext context;

    @Test
    public void successfulCalculation() {
        CalculateJourneyCommand interactor = new CalculateJourneyCommand(context);
        interactor.setPlaner(new JourneyOptimizer(context.getOriginalJourney()) {

            @Override
            public Journey apply() {
                return new Journey();
            }

        });
        interactor.execute();

        
    }

    @Test(expected = RuntimeException.class)
    public void notSuccessfulCalculation() {
        CalculateJourneyCommand interactor = new CalculateJourneyCommand(context);
        interactor.setPlaner(new JourneyOptimizer(context.getOriginalJourney()) {

            @Override
            public Journey apply() {
                throw new RuntimeException("Something goes wrong!");
            }

        });
        interactor.execute();

        
    }

    @Before
    public void before() {
        context = createContext();
    }

    private JourneyOptimizationContext createContext() {
        final Journey journeyForOptimiziation = new Journey();
        JourneyOptimizationContext ctx = new JourneyOptimizationContext(journeyForOptimiziation);
        return ctx;
    }
}
