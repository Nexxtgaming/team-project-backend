package teamproject.cs5.payload.request;

public class LanguageCourseRequest extends MapOfferRequest{
    private final String language;

    public LanguageCourseRequest(String title, String description, String city, Double longitude, Double latitude, String language) {
        super(title, description, city, longitude, latitude);
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }
}
