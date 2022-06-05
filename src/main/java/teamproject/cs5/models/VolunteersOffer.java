package teamproject.cs5.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class VolunteersOffer extends Offer{

    public VolunteersOffer() {

    }

    public VolunteersOffer(Long userId, String title, String description, String city) {
        super(userId, title, description, city);
    }
}
