package teamproject.cs5.payload.request;



public class AccommodationOfferRequest extends MapOfferRequest {
    private final Double capacity;

    public AccommodationOfferRequest(String title, String description, String city, String address, Double capacity) {
        super(title, description, city, address);
        this.capacity = capacity;
    }

    public Double getCapacity(){return capacity; }
}
