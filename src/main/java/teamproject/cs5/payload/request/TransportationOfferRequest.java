package teamproject.cs5.payload.request;

import teamproject.cs5.models.TransportationOffer;

public class TransportationOfferRequest extends MapOfferRequest {

    private String cityOfArrival;
    private int seatsAvailable;
    private String dateOfDeparture;
    private String dateOfArrival;
    private String vehicleType;

    public TransportationOfferRequest(String title, String description, String city, String address, String cityOfArrival, int seatsAvailable, String dateOfDeparture, String dateOfArrival, String vehicleType) {
        super(title, description, city, address);
        this.cityOfArrival = cityOfArrival;
        this.seatsAvailable = seatsAvailable;
        this.dateOfDeparture = dateOfDeparture;
        this.dateOfArrival = dateOfArrival;
        this.vehicleType = vehicleType;
    }

    public String getCityOfArrival() { return cityOfArrival; }

    public void setCityOfArrival(String cityOfArrival) { this.cityOfArrival = cityOfArrival; }

    public int getSeatsAvailable() { return seatsAvailable; }

    public void setSeatsAvailable(int seatsAvailable) { this.seatsAvailable = seatsAvailable; }

    public String getDateOfDeparture() { return dateOfDeparture; }

    public void setDateOfDeparture(String dateOfDeparture) { this.dateOfDeparture = dateOfDeparture; }

    public String getDateOfArrival() { return dateOfArrival; }

    public void setDateOfArrival(String dateOfArrival) { this.dateOfArrival = dateOfArrival; }

    public String getVehicleType() { return vehicleType; }

    public void setVehicleType(String vehicleType) { this.vehicleType = vehicleType; }

}
