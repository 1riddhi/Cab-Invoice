package main.java.com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InvoiceGenerator {
    private static final double COST_PER_KM = 10.0;
    private static final double COST_PER_MINUTE = 1.0;
    private static final double MINIMUM_FARE = 5.0;

    public HashMap<String, List<Ride>> userRide;

    InvoiceGenerator() {
        this.userRide = new HashMap<>();
    }

    public double calculateFare(double distance, double time) {
        double fare = distance * COST_PER_KM + time * COST_PER_MINUTE;
        return Math.max(fare, MINIMUM_FARE);
    }

    public double calculateTotalFare(List<Ride> rides) {
        double totalFare = 0.0;
        for (Ride ride : rides) {
            totalFare += calculateFare(ride.getDistance(), ride.getTime());
        }
        return totalFare;
    }

    public void addUserInvoice(String user, Ride ride) {

        if (userRide.containsKey(user) == false) {
            userRide.put(user, new ArrayList<Ride>());
        }

        userRide.get(user).add(ride);
    }

    public List<Ride> getUserInvoice(String user, Ride ride) {

        return userRide.get(user);
    }

    public Invoice getInvoice(List<Ride> rides) {

        int totalRides = rides.size();
        double totalFare = calculateTotalFare(rides);
        double averageFare = totalRides > 0 ? totalFare / totalRides : 0;

        return new Invoice(totalRides, totalFare, averageFare);

    }

}
