package teamproject.cs5.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
public class TransportationOffer extends MapOffer {

    @NotBlank
    private String cityOfArrival;
    @NotBlank
    private int seatsAvailable;
    @NotBlank
    private String dateOfDeparture;
    @NotBlank
    private String dateOfArrival;
    @NotBlank
    private String vehicleType;

    public TransportationOffer(Long userId, String title, String description, String city, String address, String cityOfArrival, int seatsAvailable, String dateOfDeparture, String dateOfArrival, String vehicleType) {
        super(userId, title, description, city, address);
        this.cityOfArrival = cityOfArrival;
        this.seatsAvailable = seatsAvailable;
        this.dateOfDeparture = dateOfDeparture;
        this.dateOfArrival = dateOfArrival;
        this.vehicleType = vehicleType;
    }

    public TransportationOffer(String address, String cityOfArrival, int seatsAvailable, String dateOfDeparture, String dateOfArrival, String vehicleType) {
        super(address);
        this.cityOfArrival = cityOfArrival;
        this.seatsAvailable = seatsAvailable;
        this.dateOfDeparture = dateOfDeparture;
        this.dateOfArrival = dateOfArrival;
        this.vehicleType = vehicleType;
    }

    public TransportationOffer() {

    }
}
