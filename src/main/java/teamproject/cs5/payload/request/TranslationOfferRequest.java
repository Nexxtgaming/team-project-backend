package teamproject.cs5.payload.request;

public class TranslationOfferRequest extends MapOfferRequest{

    private String language;


    public TranslationOfferRequest(String title, String description, String city, Double longitude, Double latitude, String language) {
        super(title, description, city, longitude, latitude);
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
