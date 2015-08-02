
package de.as.roadRunners.app.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class City {
    
    @Id
    private String id;
    private int geodataEntityId;
    private String name;
    private float lat;
    private float lng;
    private String state;
    private String county;

    public City() {
    }

    public City(String name, float lat, float lng) {
        this.name = name;
        this.lng = lng;
        this.lat = lat;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public float getLat() {
        return lat;
    }

    public float getLng() {
        return lng;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public int getGeodataEntityId() {
        return geodataEntityId;
    }

    public void setGeodataEntityId(int geodataEntityId) {
        this.geodataEntityId = geodataEntityId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + this.geodataEntityId;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final City other = (City) obj;
        if (this.geodataEntityId != other.geodataEntityId) {
            return false;
        }
        return true;
    }
    
    
}
