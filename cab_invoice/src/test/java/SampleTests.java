package test.java;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

 import java.util.ArrayList;
 import java.util.List;

import main.java.com.example.Invoice;
import main.java.com.example.InvoiceGenerator;
import main.java.com.example.Ride;



public class SampleTests {
    private InvoiceGenerator invoiceGenerator;

     @BeforeEach
     void setUp() {
         invoiceGenerator = new InvoiceGenerator();
     }

    @Test
    void testCalculateFare() {
        double distance = 10.0;
        int time = 30;
        boolean isPremium = false;

        double fare = invoiceGenerator.calculateFare(distance, time, isPremium);

        assertEquals(130.0, fare, 0.001);
    }

    @Test
    void testCalculateFarePremium() {
        double distance = 10.0;
        int time = 30;
        boolean isPremium = true;

        double fare = invoiceGenerator.calculateFare(distance, time, isPremium);

        assertEquals(210.0, fare, 0.001);
    }

    @Test
    void testCalculateTotalFare() {
        List<Ride> rides = new ArrayList<>();
        rides.add(new Ride(10.0, 30));
        rides.add(new Ride(5.5, 15));
        boolean isPremium = false;

        double totalFare = invoiceGenerator.calculateTotalFare(rides, isPremium);

        assertEquals(200, totalFare, 0.001);
    }



    @Test
    void testGetInvoice() {
        List<Ride> rides = new ArrayList<>();
        rides.add(new Ride(10.0, 30));
        rides.add(new Ride(5.5, 15));
        boolean isPremium = false;

        Invoice invoice = invoiceGenerator.getInvoice(rides, isPremium);

        assertNotNull(invoice);
        assertEquals(2, invoice.getTotalRides());
        assertEquals(200.0, invoice.getTotalFare(), 0.001);
        assertEquals(100.0, invoice.getAverageFarePerRide(), 0.001);
    }



}
