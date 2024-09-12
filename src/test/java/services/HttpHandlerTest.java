package services;

import lombok.SneakyThrows;
import org.gjco.models.Customer;
import org.gjco.models.Invoice;
import org.gjco.services.HttpHandler;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HttpHandlerTest {
    private final HttpHandler httpHandler = new HttpHandler();

    @Test
    @SneakyThrows
    public void testGetCustomerListReturnsExpected() {
        List<Customer> result = httpHandler.fetchCustomers();
        assertThat(result.size()).isEqualTo(5);
    }

    @Test
    @SneakyThrows
    public void testGetInvoiceListReturnsExpected() {
        List<Invoice> result = httpHandler.fetchInvoices();
        assertThat(result.size()).isEqualTo(15);
    }
}
