package de.as.roadRunners.app.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "journies")
public class Journey implements Iterable<City> {
    public static Comparator<? super Journey> FITTEST_COMPARATOR = new Comparator<Journey>() {

        @Override
        public int compare(Journey o1, Journey o2) {
            return Double.compare(o1.calculateDistance(), o2.calculateDistance());
        }
    };

    @Id
    private String id;
    private String title;
    private Date createdDate = new Date();
    private List<City> destinations = new LinkedList<City>();
    private DistanceCalculator c = new DistanceCalculator();
    private double distance = 0.0;
    
    @DBRef
    private User user;

    public Journey(Journey journey) {
        destinations = new ArrayList<City>(journey.destinations);
    }

    public Journey() {
    }
    
    public double calculateDistance() {
        if (distance == 0.0) {
            City lastStartCity = null;
            for (City destination : destinations) {
                if (lastStartCity != null) {
                    distance += c.calculateDistance(lastStartCity, destination);
                }
                lastStartCity = destination;

            }
        }
        return distance;
    }

    public boolean addDestination(City city) {
        if(!destinations.contains(city) 
                && destinations.add(city)){
            distance = 0.0;
            return true;
        }
        return false;
    }
    
    

    @Override
    public Iterator<City> iterator() {
        return destinations.iterator();
    }

    public void addDestinations(List<City> cities) {
        destinations.addAll(cities);
        distance = 0.0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<City> getDestinations() {
        return destinations;
    }
    
    @JsonIgnore
    public City getFirstCity(){
        return !destinations.isEmpty() ? destinations.get(0) : new City();
    }
    
    @JsonIgnore
    public City getLastCity(){
        return !destinations.isEmpty() ? destinations.get(destinations.size() - 1) : new City();
    }

    public void setDestinations(List<City> destinations) {
        this.destinations = destinations;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int numberOfDestinations() {
        return destinations.size();
    }

    public boolean removeCity(City cityForDeleting) {
        return destinations.remove(cityForDeleting);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public boolean deleteDestination(City city) {
        return destinations.remove(city);
    }

    @JsonIgnore
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
