package hr.kingict.amadeus.controller;

import com.amadeus.resources.Location;
import hr.kingict.amadeus.dto.FlightSearchResultDto;
import hr.kingict.amadeus.dto.LocationDto;
import hr.kingict.amadeus.form.FlightSearchForm;
import hr.kingict.amadeus.mapper.LocationToLocationDtoMapper;
import hr.kingict.amadeus.service.AmadeusService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/flight-search")
public class FlightSearchController {

    @Autowired
    private AmadeusService amadeusService;

    @Autowired
    private LocationToLocationDtoMapper locationToLocationDtoMapper;

    @GetMapping("/airports/{keyword}")
    public ResponseEntity<List<LocationDto>> searchAirports(@PathVariable String keyword) {
        List<Location> locationList = amadeusService.searchAirports(keyword);

        return ResponseEntity
                .ok()
                .body(
                        amadeusService.searchAirports(keyword)
                                .stream()
                                .map(location -> locationToLocationDtoMapper.map(location))
                                .toList()
                );
    }

    @PostMapping("/flights")
    public ResponseEntity<List<FlightSearchResultDto>> searchFlights(@RequestBody @Valid FlightSearchForm flightSearchForm) {

        List<FlightSearchResultDto> flightSearchResultDtoList = amadeusService.searchFlights(
                flightSearchForm.getOriginLocationCode(),
                flightSearchForm.getDestinationLocationCode(),
                flightSearchForm.getDepartureDate(),
                flightSearchForm.getReturnDate(),
                flightSearchForm.getAdults());

        return ResponseEntity.ok().body(flightSearchResultDtoList);
    }
}
