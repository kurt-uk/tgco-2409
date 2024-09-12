package org.gjco.models;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Spender  {
    String firstName;
    String lastName;
    double amountSpent;
}
