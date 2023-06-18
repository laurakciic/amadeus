package hr.kingict.amadeus.controller;

import com.amadeus.resources.Location;
import hr.kingict.amadeus.dto.LocationDto;
import hr.kingict.amadeus.mapper.LocationLocationDtoMapper;
import hr.kingict.amadeus.service.AmadeusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/flight-search")
public class FlightSearchController {

    @Autowired
    private AmadeusService amadeusService;

    @Autowired
    private LocationLocationDtoMapper locationLocationDtoMapper;

    @GetMapping("/airports/{keyword}")
    public ResponseEntity<List<LocationDto>> searchAirports(@PathVariable String keyword) {
        List<Location> locationList = amadeusService.searchAirports(keyword);

        return ResponseEntity
                .ok()
                .body(
                        amadeusService.searchAirports(keyword)
                                .stream()
                                .map(location -> locationLocationDtoMapper.map(location))
                                .toList()
                );
    }
}
