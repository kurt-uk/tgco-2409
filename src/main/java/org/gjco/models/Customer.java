package org.gjco.models;

public class Customer {
    int id;
    String name;
    String surname;

    public Customer(int id, String name, String surname) {}

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getSurname() {
        return surname;
    }
}
