package teamproject.cs5.payload.request;

public class TranslationOfferRequest {
    private String title;
    private String description;
    private String city;
    private String language;
    private Double latitude;

    public TranslationOfferRequest(String title,
                                   String description,
                                   String city,
                                   String language, Double latitude, Double longitude) {
        this.title = title;
        this.description = description;
        this.city = city;
        this.language = language;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    private Double longitude;


    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCity() {
        return city;
    }

    public String getLanguage() {
        return language;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
