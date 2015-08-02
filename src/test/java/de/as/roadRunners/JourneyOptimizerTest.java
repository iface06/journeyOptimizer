package de.as.roadRunners;

import de.as.roadRunners.app.journeyCommands.JourneyOptimizer;
import de.as.roadRunners.app.entities.City;
import de.as.roadRunners.app.entities.Journey;
import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Ignore;

public class JourneyOptimizerTest {

    /*
     new City("Nürnberg", 49.4478f, 11.0683f),
     new City("Berlin", 52.5167f, 13.4f),
     new City("München", 48.15f, 11.5833f),
     new City("Köln", 50.9333f, 6.95f),
     new City("Dortmund", 51.5161f, 7.46833f),
     new City("Stuttgart", 48.7667f, 9.18333f),
     new City("Hamburg", 53.5544f, 9.99458f),
     new City("Dresden", 51.05f, 13.75f),
     new City("Hanover", 50.7333f, 7.1f)
     */
    @Test
    public void testTourWithTwoCities() {
        City erlangen = new City("Erlangen", 49.5977f, 11.0039f);
        City uffenheim = new City("Uffenheim", 49.5333f, 10.25f);

        List<City> destinations = Arrays.asList(
                erlangen,
                uffenheim
        );

        Journey journey = new Journey();
        journey.addDestinations(destinations);
        
        JourneyOptimizer opitmizer = new JourneyOptimizer(journey);
        Journey betterJourney = opitmizer.apply();

        double km = betterJourney.calculateDistance();

        assertEquals(54.9, km, 0.5);

    }

    
    @Test
    public void testTourWithThreeCities() {
        City erlangen = new City("Dortmund", 51.5161f, 7.46833f);
        City uffenheim = new City("Nürnberg", 49.4478f, 11.0683f);
        City stuttgart = new City("München", 48.15f, 11.5833f);

        List<City> destinations = Arrays.asList(
                erlangen,
                uffenheim,
                stuttgart
        );

        Journey journey = new Journey();
        journey.addDestinations(destinations);
        JourneyOptimizer planer = new JourneyOptimizer(journey);
        Journey betterJourney = planer.apply();

        double km = betterJourney.calculateDistance();

//        printJourney(km, betterJourney);
        
        assertEquals(492.8, km, 0.5);

    }

    @Ignore
    @Test
    public void testTourCities() {
        

        List<City> destinations = Arrays.asList(
                new City("Erlangen", 49.5977f, 11.0039f),
                new City("Berlin", 52.5167f, 13.4f),
                new City("München", 48.15f, 11.5833f),
                new City("Hannover", 52.3667f, 9.71667f),
                new City("Köln", 50.9333f, 6.95f),
                new City("Nürnberg", 49.4478f, 11.0683f),
                new City("Dortmund", 51.5161f, 7.46833f),
                new City("Stuttgart", 48.7667f, 9.18333f),
                new City("Hamburg", 53.5544f, 9.99458f),
                new City("Dresden", 51.05f, 13.75f)
        );

        Journey journey = new Journey();
        journey.addDestinations(destinations);
        
        JourneyOptimizer planer = new JourneyOptimizer(journey);
        planer.apply();

        double km = journey.calculateDistance();

//        printJourney(km, journey);
        assertEquals(1581.09, km, 60);

    }

    public void printJourney(double km, Journey journey) {
        System.out.println("Test with more cities");
        System.out.println("Distance " + km);
        for (City city : journey) {
            System.out.println(city.getName());
        }
    }

}
