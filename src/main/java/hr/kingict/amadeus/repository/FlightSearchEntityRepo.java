package hr.kingict.amadeus.repository;

import hr.kingict.amadeus.model.FlightSearchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightSearchEntityRepo extends JpaRepository<FlightSearchEntity, Integer> {
}
