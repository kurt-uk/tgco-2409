import org.gjco.Main;
import org.junit.jupiter.api.Test;

import java.io.IOException;

// Make sure the node commands have been run before starting tests
public class MainTest {
    private Main main = new Main();

    public MainTest() throws IOException {
    }

    /*
    This test will display the challenge result
     */
    @Test
    public void highestSpenderReturned() {
        System.out.println("highest spender = " + main.calculateHighestSpender());
    }

}
