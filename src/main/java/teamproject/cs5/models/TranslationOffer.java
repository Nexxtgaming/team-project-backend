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

    public TranslationOffer(Long userId, String title, String description, String city, String address, String language) {
        super(userId, title, description, city, address);
        this.language = language;
    }

    public TranslationOffer(String address, String language) {
        super(address);
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
