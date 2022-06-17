package teamproject.cs5.payload.request;

public class TranslationOfferRequest extends MapOfferRequest{

    private String language;

    public TranslationOfferRequest(String title, String description, String city, String address, String language) {
        super(title, description, city, address);
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
