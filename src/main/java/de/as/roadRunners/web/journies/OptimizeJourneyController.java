
package de.as.roadRunners.web.journies;

import de.as.roadRunners.app.entities.Journey;
import de.as.roadRunners.app.journeyCommands.CalculateJourneyCommand;
import de.as.roadRunners.app.journeyCommands.JourneyOptimizationContext;
import de.as.roadRunners.logging.Logger;
import de.as.roadRunners.sessions.RoadRunnerSession;
import de.as.roadRunners.sessions.SessionManager;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class OptimizeJourneyController {
    
    public SessionManager session;
    public Logger logger;
    
    @RequestMapping(value="/myJourney/optimize", method=RequestMethod.GET)
    public Journey optimize(){
        RoadRunnerSession s = (RoadRunnerSession) session.get("journey");
        JourneyOptimizationContext context = new JourneyOptimizationContext(s.getJourney());
        try {
            CalculateJourneyCommand command = new CalculateJourneyCommand(context);
            command.execute();
        } catch (Exception e) {
            handleException(e, context);
        }
        
        return context.getOptimizedJourney();
    }

    protected void handleException(Exception e, JourneyOptimizationContext context) {
        logger.error(e, "Error while executing OptimizeJourneyController!");
        context.setSuccess(false);
    }

}
