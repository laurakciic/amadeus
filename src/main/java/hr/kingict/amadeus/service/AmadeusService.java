package hr.kingict.amadeus.service;

import com.amadeus.Amadeus;
import com.amadeus.Params;
import com.amadeus.referencedata.Locations;
import com.amadeus.resources.FlightOfferSearch;
import com.amadeus.resources.Location;
import hr.kingict.amadeus.dto.FlightSearchResultDto;
import hr.kingict.amadeus.dto.LocationDto;
import hr.kingict.amadeus.mapper.FlightOfferSearchToFlightSearchResultDtoMapper;
import hr.kingict.amadeus.mapper.FlightSearchEntityToFlightSearchResultDtoMapper;
import hr.kingict.amadeus.mapper.FlightSearchResultDtoToFlightSearchResultMapper;
import hr.kingict.amadeus.model.FlightSearchEntity;
import hr.kingict.amadeus.model.FlightSearchResultEntity;
import hr.kingict.amadeus.repository.FlightSearchEntityRepo;
import hr.kingict.amadeus.repository.FlightSearchResultRepo;
import jakarta.transaction.Transactional;
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

    @Autowired
    private FlightSearchEntityRepo flightSearchEntityRepo;

    @Autowired
    private FlightSearchResultRepo flightSearchResultRepo;

    @Autowired
    private FlightSearchResultDtoToFlightSearchResultMapper flightSearchResultDtoToFlightSearchResultMapper;

    @Autowired
    private FlightSearchEntityToFlightSearchResultDtoMapper flightSearchEntityToFlightSearchResultDtoMapper;


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

    @Transactional
    public List<FlightSearchResultDto> searchFlights(String originLocationCode, String destinationLocationCode,
                                                     LocalDate departureDate, LocalDate returnDate, Integer adults) {
        try {
            FlightSearchEntity existingFlightSearch = flightSearchEntityRepo.findOneByDepartureCodeAndDestinationCodeAndDepartureDateAndReturnDateAndAdults
                    (originLocationCode, destinationLocationCode, departureDate, returnDate, adults);

            if (existingFlightSearch != null) {
                List<FlightSearchResultEntity> flightSearchResultEntityList = existingFlightSearch.getFlightSearchResultEntityList();

                logger.warn("Fetched data from database.");

                return flightSearchResultEntityList.stream()
                        .map(flightSearchResultEntity -> flightSearchEntityToFlightSearchResultDtoMapper.map(flightSearchResultEntity))
                        .toList();
            }

            FlightSearchEntity flightSearchEntity = new FlightSearchEntity();
            flightSearchEntity.setDestinationCode(destinationLocationCode);
            flightSearchEntity.setDepartureCode(originLocationCode);
            flightSearchEntity.setDepartureDate(departureDate);
            flightSearchEntity.setReturnDate(returnDate);
            flightSearchEntity.setAdults(adults);

            flightSearchEntity.setDateCreated(LocalDate.now());
            flightSearchEntity.setUserCreated("Laura");

            flightSearchEntityRepo.save(flightSearchEntity);

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

            List<FlightSearchResultDto> flightSearchResultDtoList =
                    flightOfferSearchList
                        .stream()
                        .map(flightOfferSearch -> mapper.map(flightOfferSearch))
                        .toList();

            flightSearchResultDtoList.stream()
                    .map(flightSearchResultDto -> flightSearchResultDtoToFlightSearchResultMapper.map(flightSearchResultDto))
                    .forEach(flightSearchResultEntity -> {
                        flightSearchResultEntity.setFlightSearchEntity(flightSearchEntity);
                        flightSearchResultRepo.save(flightSearchResultEntity);
                    });

            logger.warn("Fetched data from Amadeus API.");

            return flightSearchResultDtoList;

        } catch (Exception e) {
            logger.error("Search flight error", e);
            return Collections.emptyList();
        }
    }
}
