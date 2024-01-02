package main.java.com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InvoiceGenerator {
    private static final double COST_PER_KM = 10.0;
    private static final double COST_PER_MINUTE = 1.0;
    private static final double MINIMUM_FARE = 5.0;

    private static final double COST_PER_KM_PREMIUM = 15.0;
    private static final double COST_PER_MINUTE_PREMIUM = 2.0;
    private static final double MINIMUM_FARE_PREMIUM = 20.0;

    public HashMap<String, List<Ride>> userRide;

    public InvoiceGenerator() {
        this.userRide = new HashMap<>();
    }

    public double calculateFare(double distance, int time, boolean isPremium) {
        double costPerKm = isPremium ? COST_PER_KM_PREMIUM : COST_PER_KM;
        double costPerMinute = isPremium ? COST_PER_MINUTE_PREMIUM : COST_PER_MINUTE;
        double minimumFare = isPremium ? MINIMUM_FARE_PREMIUM : MINIMUM_FARE;

        double fare = distance * costPerKm + time * costPerMinute;
        return Math.max(fare, minimumFare);
    }

    public double calculateTotalFare(List<Ride> rides,boolean isPremium) {
        double totalFare = 0.0;
        for (Ride ride : rides) {
            totalFare += calculateFare(ride.getDistance(), ride.getTime(),isPremium);
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

    public Invoice getInvoice(List<Ride> rides, boolean isPremium) {

        int totalRides = rides.size();
        double totalFare = calculateTotalFare(rides, isPremium);
        double averageFare = totalRides > 0 ? totalFare / totalRides : 0;

        return new Invoice(totalRides, totalFare, averageFare);

    }

}
