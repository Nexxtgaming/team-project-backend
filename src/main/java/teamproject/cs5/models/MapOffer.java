package teamproject.cs5.models;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

@MappedSuperclass
public class MapOffer extends Offer {
    @NotBlank
    private Double longitude;
    @NotBlank
    private Double latitude;

    public MapOffer(Double longitude, Double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public MapOffer(Long userId, String title, String description, Double longitude, Double latitude, String city) {
        super(userId, title, description, city);
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public MapOffer() {
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }
}
