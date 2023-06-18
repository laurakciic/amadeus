package hr.kingict.amadeus.model;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "flights_search")
public class FlightSearchEntity extends BaseEntity {

    private String departureCode;

    private String destinationCode;

    private LocalDate departureDate;

    private LocalDate returnDate;

    private Integer adults;

    @OneToMany(mappedBy = "flightSearchEntity")
    private List<FlightSearchResultEntity> flightSearchResultEntityList;

    public String getDepartureCode() {
        return departureCode;
    }

    public void setDepartureCode(String departureCode) {
        this.departureCode = departureCode;
    }

    public String getDestinationCode() {
        return destinationCode;
    }

    public void setDestinationCode(String destinationCode) {
        this.destinationCode = destinationCode;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public Integer getAdults() {
        return adults;
    }

    public void setAdults(Integer adults) {
        this.adults = adults;
    }

    public List<FlightSearchResultEntity> getFlightSearchResultEntityList() {
        return flightSearchResultEntityList;
    }

    public void setFlightSearchResultEntityList(List<FlightSearchResultEntity> flightSearchResultEntityList) {
        this.flightSearchResultEntityList = flightSearchResultEntityList;
    }
}
