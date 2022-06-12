package teamproject.cs5.payload.request;



public class AccommodationOfferRequest extends MapOfferRequest {
    private final Double capacity;

    public AccommodationOfferRequest(String title, String description, String city, Double longitude, Double latitude, Double capacity) {
        super(title, description, city, longitude, latitude);
        this.capacity = capacity;
    }

    public Double getCapacity(){return capacity; }
}
