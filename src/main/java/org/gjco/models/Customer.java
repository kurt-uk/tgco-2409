package org.gjco.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString
public class Customer {
    @JsonProperty("ID")
    int id;
    String name;
    String surname;
}
