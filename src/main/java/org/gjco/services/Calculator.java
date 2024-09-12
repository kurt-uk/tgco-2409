package org.gjco.services;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import org.gjco.models.Customer;
import org.gjco.models.Invoice;
import org.gjco.models.Spender;

public class Calculator {
    private final HttpHandler handler;
    private int topCustomerId = -1;

    public Calculator() {
        this.handler = new HttpHandler();
    }

    // Find the customer who spent the most in the invoices
    public Spender findTopSpender() throws IOException {
        List<Customer> customers = handler.fetchCustomers();
        List<Invoice> invoices = handler.fetchInvoices();

        // Map to store total spending per customer
        Map<Integer, Double> customerSpending = new HashMap<>();

        // Variables to track the top customer and max spending
        double maxSpending = 0.0;

        // Iterate through invoices to sum spending and find the max in a single pass
        for (Invoice invoice : invoices) {
            int customerId = invoice.getCustomerId();
            double currentSpent = customerSpending.getOrDefault(customerId, 0.0) + invoice.getAmount();
            customerSpending.put(customerId, currentSpent);

            if (currentSpent > maxSpending) {
                maxSpending = currentSpent;
                topCustomerId = customerId;
            }
        }

        // Build a map for quick customer lookup
        Map<Integer, Customer> customerMap = customers.stream()
                .collect(Collectors.toMap(Customer::getId, c -> c));

        // Create Spender object
        Spender spender = new Spender();
        spender.setAmountSpent(maxSpending);

        // Find the customer with the top spending
        Customer topCustomer = customerMap.get(topCustomerId);
        if (topCustomer != null) {
            spender.setFirstName(topCustomer.getName());
            spender.setLastName(topCustomer.getSurname());
        } else {
            // Handle the case where no customer was found (if needed)
            spender.setFirstName("Unknown");
            spender.setLastName("Unknown");
        }

        return spender;
    }
}
