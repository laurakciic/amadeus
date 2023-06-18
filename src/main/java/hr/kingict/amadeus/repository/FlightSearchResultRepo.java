package hr.kingict.amadeus.repository;

import hr.kingict.amadeus.model.FlightSearchResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlightSearchResultRepo extends JpaRepository<FlightSearchResultEntity, Integer> {
}
