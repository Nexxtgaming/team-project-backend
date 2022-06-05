package teamproject.cs5.payload.request;

public class LegalAdviceRequest extends MapOfferRequest{
    public LegalAdviceRequest(String title, String description, String city, Double longitude, Double latitude) {
        super(title, description, city, longitude, latitude);
    }
}
