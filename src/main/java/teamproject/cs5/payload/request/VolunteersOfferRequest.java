package teamproject.cs5.payload.request;

public class VolunteersOfferRequest {

    private String title;
    private String description;
    private String city;

    public VolunteersOfferRequest(String title, String description, String city) {
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
