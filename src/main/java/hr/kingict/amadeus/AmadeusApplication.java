package hr.kingict.amadeus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //(scanBasePackages = {"hr.kingict.amadeus", "util"})
public class AmadeusApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmadeusApplication.class, args);
    }

}
