package teamproject.cs5.models;

import javax.persistence.Entity;

@Entity
public class LegalAdviceOffer extends MapOffer {

    public LegalAdviceOffer(Long userId, String title, String description, String city, String address) {
        super(userId, title, description, city, address);
    }

    public LegalAdviceOffer(String address) {
        super(address);
    }

    public LegalAdviceOffer() {

    }
}
