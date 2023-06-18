package hr.kingict.amadeus.service;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.referencedata.Locations;
import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.Location;
import hr.kingict.amadeus.dto.FlightSearchResultDto;
import hr.kingict.amadeus.dto.LocationDto;
import hr.kingict.amadeus.mapper.FlightOfferSearchToFlightSearchResultDtoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Service
public class AmadeusService {

    Logger logger = LoggerFactory.getLogger(AmadeusService.class);

    @Autowired
    private FlightOfferSearchToFlightSearchResultDtoMapper mapper;

    @Autowired
    private Amadeus amadeus;

    public List<Location> searchAirports(String keyword) {
        LocationDto locationDto = new LocationDto();

        try {
            Params params = Params
                    .with("subType", Locations.AIRPORT)
                    .and("keyword", keyword);

            return Arrays.asList(amadeus.referenceData.locations.get(params));
        } catch (Exception e) {
            logger.error("Search airports error", e);
            return Collections.emptyList();
        }
    }

    public List<FlightSearchResultDto> searchFlights(String originLocationCode, String destinationLocationCode,
                                                     LocalDate departureDate, LocalDate returnDate, Integer adults) {
        try {
            Params params = Params
                    .with("originLocationCode", originLocationCode)
                    .and("destinationLocationCode", destinationLocationCode)
                    .and("departureDate", departureDate.toString())
                    .and("adults", adults)
                    .and("nonStop", true)
                    .and("max", 5);

            if (returnDate != null) {
                params.and("returnDate", returnDate.toString());
            }

            List<FlightOfferSearch> flightOfferSearchList = Arrays.asList
                    (amadeus.shopping.flightOffersSearch.get(params));

            return flightOfferSearchList
                    .stream()
                    .map(flightOfferSearch -> mapper.map(flightOfferSearch))
                    .toList();

        } catch (Exception e) {
            logger.error("Search flight error", e);
            return Collections.emptyList();
        }
    }
}
