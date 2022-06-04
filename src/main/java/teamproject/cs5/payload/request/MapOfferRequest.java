package teamproject.cs5.payload.request;

public class MapOfferRequest extends OfferRequest{
    private final Double longitude;
    private final Double latitude;

    public MapOfferRequest(String title, String description, String city, Double longitude, Double latitude) {
        super(title, description, city);
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
