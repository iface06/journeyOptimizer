package de.as.roadRunners.app.entities;

import de.as.roadRunners.app.entities.City;
import static java.lang.Math.*;

class DistanceCalculator {

    private final static double RAD_180 = 180.0;
    private final static double EQUATORIAL_RADIUS = 6378.137;

    public double calculateDistance(City startpoint, City destination) {

        double latStartPoint = convertToRad(startpoint.getLat());
        double latDestination = convertToRad(destination.getLat());
        double lngStartPoint = convertToRad(startpoint.getLng());
        double lngDestination = convertToRad(destination.getLng());

        double sinLat = calculateSinLat(latStartPoint, latDestination);
        double cosLat = calculateCosLat(latStartPoint, latDestination);
        double cosLng = cos(lngDestination - lngStartPoint);
        
        double e = acos(sinLat + cosLat * cosLng);
        return e * EQUATORIAL_RADIUS;   
    }

    private double calculateCosLat(double latStartPoint, double latDestination) {
        double cosLatStartPoint = cos(latStartPoint);
        double cosLatDestination = cos(latDestination);
        double cosLat = cosLatStartPoint * cosLatDestination;
        return cosLat;
    }

    private double calculateSinLat(double latStartPoint, double latDestination) {
        double sinLatStartPoint = sin(latStartPoint);
        double sinLatDestination = sin(latDestination);
        double sinLat = sinLatStartPoint * sinLatDestination;
        return sinLat;
    }

    protected double convertToRad(double degree) {
        return degree / RAD_180 * PI;
    }
}
