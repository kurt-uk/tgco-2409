package org.gjco.models;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Customer {
    int id;
    String name;
    String surname;
}
