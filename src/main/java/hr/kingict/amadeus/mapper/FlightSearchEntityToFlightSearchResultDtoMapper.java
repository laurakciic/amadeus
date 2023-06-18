package hr.kingict.amadeus.mapper;

import hr.kingict.amadeus.dto.FlightSearchResultDto;
import hr.kingict.amadeus.model.FlightSearchResultEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class FlightSearchEntityToFlightSearchResultDtoMapper {

    public FlightSearchResultDto map(FlightSearchResultEntity flightSearchResultEntity) {
        FlightSearchResultDto flightSearchResultDto = new FlightSearchResultDto();

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(flightSearchResultEntity, flightSearchResultDto);

        return flightSearchResultDto;
    }
}
