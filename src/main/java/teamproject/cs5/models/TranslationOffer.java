package teamproject.cs5.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class TranslationOffer {
    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    private Long userId;

    @NotBlank
    @Size(min = 3, max = 150)
    private String title;

    @NotBlank
    private String description;

    @NotBlank
    private String city;

    @NotBlank
    private String language;

    private Double latitude;

    private Double longitude;

    public TranslationOffer(Long userId, String title, String description, String city, String language, Double latitude, Double longitude) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.city = city;
        this.language = language;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public TranslationOffer() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
