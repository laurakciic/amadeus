package hr.kingict.amadeus.mapper;

import com.amadeus.resources.Location;
import hr.kingict.amadeus.dto.LocationDto;
import org.springframework.stereotype.Component;

@Component
public class LocationLocationDtoMapper {

    public LocationDto map(Location location) {
        if (location == null) return null;

        LocationDto locationDto = new LocationDto();
        locationDto.setIataCode(location.getIataCode());
        locationDto.setName(location.getName());
        locationDto.setDetailedName(location.getDetailedName());

        return locationDto;
    }
}
