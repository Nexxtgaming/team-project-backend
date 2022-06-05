package teamproject.cs5.models;

import javax.persistence.Entity;

@Entity
public class LanguageCourseOffer extends MapOffer{

    private String Language;

    public LanguageCourseOffer(Long userId, String title, String description, Double longitude, Double latitude, String city, String language) {
        super(userId, title, description, longitude, latitude, city);
        Language = language;
    }

    public LanguageCourseOffer() {
        super();
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

}
