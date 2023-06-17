package hr.kingict.amadeus.config;

import com.amadeus.Amadeus;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class AmadeusConfig {

    @Bean
    public Amadeus getAmadeus() {
        return Amadeus
                .builder("RuRNwWDrX0F4G3nLKVGDGEZiOPGoIlew", "jOHSRFlXPsGwDFyz")
                .build();
    }
}
