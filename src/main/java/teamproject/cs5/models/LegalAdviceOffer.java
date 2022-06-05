package teamproject.cs5.models;

import javax.persistence.Entity;

@Entity
public class LegalAdviceOffer extends MapOffer {
    public LegalAdviceOffer(Long userId, String title, String description, Double longitude, Double latitude, String city) {
        super(userId, title, description, longitude, latitude, city);
    }

    public LegalAdviceOffer() {

    }
}
