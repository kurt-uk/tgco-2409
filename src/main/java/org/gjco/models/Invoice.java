package org.gjco.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Invoice {
    @JsonProperty("ID")
    int id;
    int customerId;
    double amount;
}
