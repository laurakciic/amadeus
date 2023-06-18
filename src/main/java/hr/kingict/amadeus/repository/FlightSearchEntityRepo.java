package hr.kingict.amadeus.repository;

import hr.kingict.amadeus.model.FlightSearchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface FlightSearchEntityRepo extends JpaRepository<FlightSearchEntity, Integer> {

    FlightSearchEntity findOneByDepartureCodeAndDestinationCodeAndDepartureDateAndReturnDateAndAdults
            (String departureCode, String destinationCode, LocalDate departureDate, LocalDate returnDate, Integer adults);
}
