package teamproject.cs5.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class TranslationOffer extends MapOffer {

    @NotBlank
    private String language;

    public TranslationOffer(Long userId, String title, String description, Double longitude, Double latitude, String city, String language) {
        super(userId, title, description, longitude, latitude, city);
        this.language = language;
    }

    public TranslationOffer() {

    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
