package org.gjco;

import lombok.SneakyThrows;
import org.gjco.models.Spender;
import org.gjco.services.Calculator;

import java.io.IOException;

public class Main {
    private Calculator calc = new Calculator();

    public Main() throws IOException {
    }

    public static void main(String[] args) {}

    @SneakyThrows
    public Spender calculateHighestSpender() {
        return calc.findTopSpender();
    }
}
