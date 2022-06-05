package teamproject.cs5.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

@Entity
public class LookingForOffer extends Offer{
    @NotBlank
    private LookingForTypes type;

    public LookingForOffer(Long userId, String title, String description, String city, LookingForTypes type) {
        super(userId, title, description, city);
        this.type = type;
    }

    public LookingForOffer() {
        super();
    }

    public LookingForTypes getType() {
        return type;
    }

    public void setType(LookingForTypes type) {
        this.type = type;
    }
}
