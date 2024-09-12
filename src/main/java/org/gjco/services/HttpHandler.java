package org.gjco.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.gjco.models.Customer;
import org.gjco.models.Invoice;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

/*
This class will handle fetching the customers and invoices from the servers
 */
public class HttpHandler {

    ObjectMapper om = new ObjectMapper();

    // Fetch customers from the customer API
    public List<Customer> fetchCustomers() throws IOException {

        // Parse the root node (top-level JSON object)
        JsonNode rootNode = om.readTree(clientGetRequest("http://localhost:9090"));

        // Extract the "customers" node (array)
        JsonNode customersNode = rootNode.get("customers");

        // Map the "customers" array to a List<Customer>
        return om.readValue(customersNode.toString(), new TypeReference<>() {});
    }

    // Fetch invoices from the invoice API
    public List<Invoice> fetchInvoices() throws IOException {
        // Parse the root node (top-level JSON object)
        JsonNode rootNode = om.readTree(clientGetRequest("http://localhost:9092"));

        // Extract the "invoices" node (array)
        JsonNode invoicesNode = rootNode.get("invoices");

        // Map the "invoices" array to a List<Customer>
        return om.readValue(invoicesNode.toString(), new TypeReference<>() {});
    }


    private String clientGetRequest(String url) {
        // Create an HttpClient
        HttpClient client = HttpClient.newHttpClient();
        String jsonResponse = null;
        // Create an HttpRequest (GET request)
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url)).GET().build();
        try {
            // Send the request and get the response
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Check if the response status is 200 OK
            if (response.statusCode() == 200) {
                jsonResponse= response.body();
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return jsonResponse;
    }

}
