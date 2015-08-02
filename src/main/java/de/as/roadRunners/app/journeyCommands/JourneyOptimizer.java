package de.as.roadRunners.app.journeyCommands;

import de.as.roadRunners.app.entities.City;
import de.as.roadRunners.app.entities.Journey;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The BestJourneyPlaner encapsulate an algorithmn for the Traveling Salesman
 * Problem. It uses a genetic algorithm to solve the Problem quick and
 * economical. In the first phase it creates a generation of possible journies.
 * Each journey consists of several cities.  Atthe end of the algorithm the 
 * most probable best journey was found.
 * 
 * At the moment there is no mutation function implemented. Maybe it would improve the
 * algorithm.
 *
 */
public class JourneyOptimizer {

    private static final int POPULATIONSIZE_FACTOR = 1000;
    private static final int NUMBER_OF_FITTEST_FOR_REPRODUCTION = 5;

    private List<City> cities;
    private List<Journey> population = new ArrayList<Journey>();

    public JourneyOptimizer(Journey journey) {
        cities = journey.getDestinations();
    }

    public Journey apply() {
        population = createPopulationSortedByFittness();
        int generations = population.size() * 2;

        for (int i = 0; i < generations; i++) {
            List<Journey> fittests = findFittestForReproduction();
            List<Journey> children = reproduction(fittests);

            population.addAll(children);
            population.sort(Journey.FITTEST_COMPARATOR);
            dispatchUnfittest(children);
        }
        return population.get(0);
    }

    private void dispatchUnfittest(List<Journey> children) {
        for (int j = 0; j < children.size(); j++) {
            population.remove(population.size() - 1);
        }
    }

    private List<Journey> reproduction(List<Journey> fittests) {
        List<Journey> children = new ArrayList<Journey>();
        Journey fittest = fittests.get(0);
        for (int x = 1; x < fittests.size(); x++) {
            Journey child = crossover(fittest, fittests.get(x));
            children.add(child);
        }
        return children;
    }

    private List<Journey> findFittestForReproduction() {
        List<Journey> fittests = new ArrayList<Journey>();
        for (int j = 0; j < NUMBER_OF_FITTEST_FOR_REPRODUCTION; j++) {
            fittests.add(population.get(j));
        }
        return fittests;
    }

    private List<Journey> createPopulationSortedByFittness() {
        int populationSize = calculatePopulationSize();
        for (int i = 0; i < populationSize; i++) {
            Journey j = new Journey();
            j.addDestinations(cities);
            population.add(j);
            shuffelCities();
        }

        population.sort(Journey.FITTEST_COMPARATOR);
        return population;
    }

    private void shuffelCities() {
        List<City> shuffeledCities = new ArrayList<City>(cities);
        Collections.shuffle(shuffeledCities);
        cities = shuffeledCities;
    }

    /**
     * (n-1)! / 2
     *
     * @return
     */
    private int calculatePopulationSize() {
        return cities.size() * POPULATIONSIZE_FACTOR;
    }

    private Journey crossover(Journey fittest, Journey partner) {
        int sizeOfTheFittest = fittest.getDestinations().size();

        int start = 1;
        int end = sizeOfTheFittest;
        if (sizeOfTheFittest > 3) {
            end = sizeOfTheFittest - 2;
        }

        Journey child1 = new Journey(fittest);
        Journey child2 = new Journey(partner);

        for (int i = start; i < end; i++) {
            child1.getDestinations().add(i, partner.getDestinations().get(i));
            child2.getDestinations().add(i, fittest.getDestinations().get(i));
        }

        return child1.calculateDistance() > child2.calculateDistance() ? child1 : child2;

    }

}
