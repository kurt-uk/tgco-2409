package org.gjco.models;

import lombok.Data;

@Data
public class Invoice {
    int id;
    int customerId;
    int amount;

}
