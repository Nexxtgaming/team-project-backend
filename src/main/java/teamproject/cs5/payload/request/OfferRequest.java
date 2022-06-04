package teamproject.cs5.payload.request;

public class OfferRequest {
    private final String title;
    private final String description;
    private final String city;

    public OfferRequest(String title, String description, String city) {
        this.title = title;
        this.description = description;
        this.city = city;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCity() {
        return city;
    }
}
