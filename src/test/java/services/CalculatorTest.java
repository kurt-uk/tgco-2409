package services;

import lombok.SneakyThrows;
import org.gjco.models.Customer;
import org.gjco.models.Invoice;
import org.gjco.models.Spender;
import org.gjco.services.Calculator;
import org.gjco.services.HttpHandler;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CalculatorTest {

    @Mock
    HttpHandler httpHandler;

    @InjectMocks
    Calculator calculator;

    @SneakyThrows
    @Test
    public void testFindTopSpenderReturnsResult() {
        // Prepare mock data
        List<Customer> customers = Arrays.asList(
                new Customer(1, "Alice", "Klark"),
                new Customer(2, "Bob", "Smith")
        );

        List<Invoice> invoices = Arrays.asList(
                new Invoice(1, 1, 100.0),
                new Invoice(2, 1, 200.0),
                new Invoice(3, 2, 150.0)
        );

        // Mock the behavior of HttpHandler
        when(httpHandler.fetchCustomers()).thenReturn(customers);
        when(httpHandler.fetchInvoices()).thenReturn(invoices);

        // Call the method to test
        Spender spender = calculator.findTopSpender();

        // Verify the results
        assertEquals(300.0, spender.getAmountSpent(), "Amount spent should be 300.0");
        assertEquals("Alice", spender.getFirstName(), "First name should be Alice");
        assertEquals("Klark", spender.getLastName(), "Last name should be Klark");
    }


    @Test
    @SneakyThrows
    public void testFindTopSpender_NoInvoices() {
        // Prepare mock data
        List<Customer> customers = Arrays.asList(
                new Customer(1, "Alice", "Klark"),
                new Customer(2, "Bob", "Smith")
        );

        List<Invoice> invoices = List.of();

        // Mock the behavior of HttpHandler
        when(httpHandler.fetchCustomers()).thenReturn(customers);
        when(httpHandler.fetchInvoices()).thenReturn(invoices);

        // Call the method to test
        Spender spender = calculator.findTopSpender();

        // Verify the results
        assertEquals(0.0, spender.getAmountSpent(), "Amount spent should be 0.0");
        assertEquals("Unknown", spender.getFirstName(), "First name should be Unknown");
        assertEquals("Unknown", spender.getLastName(), "Last name should be Unknown");
    }


    @Test
    @SneakyThrows
    public void testFindTopSpender_NoCustomers() {
        // Prepare mock data
        List<Customer> customers = List.of();
        List<Invoice> invoices = Arrays.asList(
                new Invoice(1, 1, 100.0),
                new Invoice(2, 1, 200.0),
                new Invoice(3, 2, 150.0)
        );

        // Mock the behavior of HttpHandler
        when(httpHandler.fetchCustomers()).thenReturn(customers);
        when(httpHandler.fetchInvoices()).thenReturn(invoices);

        // Call the method to test
        Spender spender = calculator.findTopSpender();

        // Verify the results
        assertEquals(300.0, spender.getAmountSpent(), "Amount spent should be 300.0");
        assertEquals("Unknown", spender.getFirstName(), "First name should be Unknown");
        assertEquals("Unknown", spender.getLastName(), "Last name should be Unknown");
    }

}
