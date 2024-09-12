package services;

import org.gjco.models.Customer;
import org.gjco.models.Invoice;
import org.gjco.models.Spender;
import org.gjco.services.Calculator;
import org.gjco.services.HttpHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class CalculatorTest {

    @Mock
    private HttpHandler mockHandler;

    @InjectMocks
    private Calculator calculator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindTopSpender() throws IOException {
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
        when(mockHandler.fetchCustomers()).thenReturn(customers);
        when(mockHandler.fetchInvoices()).thenReturn(invoices);

        // Call the method to test
        Spender spender = calculator.findTopSpender();

        // Verify the results
        assertEquals(276.55, spender.getAmountSpent(), "Amount spent should be 276.55");
    }

    @Test
    public void testFindTopSpender_NoCustomers() throws IOException {
        // Prepare mock data
        List<Customer> customers = Arrays.asList();
        List<Invoice> invoices = Arrays.asList(
                new Invoice(1, 1, 100.0),
                new Invoice(2, 1, 200.0),
                new Invoice(3, 2, 150.0)
        );

        // Mock the behavior of HttpHandler
        when(mockHandler.fetchCustomers()).thenReturn(customers);
        when(mockHandler.fetchInvoices()).thenReturn(invoices);

        // Call the method to test
        Spender spender = calculator.findTopSpender();

        // Verify the results
        assertEquals(276.55, spender.getAmountSpent(), "Amount spent should be 276.55");
    }
}
