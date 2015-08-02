
package de.as.roadRunners.app.entities;

import org.junit.Test;
import static org.junit.Assert.*;


public class DistanceCalculatorTest {

    /**
     * Algorithm based on: http://www.koordinaten.de/informationen/formel.shtml
     */
    @Test
    public void calculateDistance() {
        
        DistanceCalculator calculator = new DistanceCalculator();
        
        
        double distanceKm = calculator.calculateDistance(
                new City("Frankfurt", 50.11222f, 8.68194f),
                new City("Berlin", 52.52222f, 13.29750f)
        );
        
        assertEquals(418.34, distanceKm, 0.05);
    }

}