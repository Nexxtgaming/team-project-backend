package teamproject.cs5.models;

import javax.persistence.Entity;

@Entity
public class LanguageCourseOffer extends MapOffer{

    private String Language;

    public LanguageCourseOffer(Long userId, String title, String description, String city, String address, String language) {
        super(userId, title, description, city, address);
        Language = language;
    }

    public LanguageCourseOffer(String address, String language) {
        super(address);
        Language = language;
    }

    public LanguageCourseOffer() {

    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

}
