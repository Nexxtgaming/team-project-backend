package teamproject.cs5.payload.request;

public class LanguageCourseRequest extends MapOfferRequest{
    private final String language;

    public LanguageCourseRequest(String title, String description, String city, String address, String language) {
        super(title, description, city, address);
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }
}
