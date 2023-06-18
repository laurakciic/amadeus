package hr.kingict.amadeus.mapper;

import hr.kingict.amadeus.dto.FlightSearchResultDto;
import hr.kingict.amadeus.model.FlightSearchResultEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class FlightSearchResultDtoToFlightSearchResultMapper {

    public FlightSearchResultEntity map(FlightSearchResultDto flightSearchResultDto) {
        if (flightSearchResultDto == null) return null;

        FlightSearchResultEntity flightSearchResultEntity = new FlightSearchResultEntity();

        ModelMapper modelMapper = new ModelMapper();
        modelMapper.map(flightSearchResultDto, flightSearchResultEntity);

        return flightSearchResultEntity;
    }
}
