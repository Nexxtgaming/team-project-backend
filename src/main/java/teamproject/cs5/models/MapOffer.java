package teamproject.cs5.models;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;

@MappedSuperclass
public class MapOffer extends Offer {

    @NotBlank
    private String address;

    public MapOffer(String address) {
        this.address = address;
    }

    public MapOffer(Long userId, String title, String description, String city, String address) {
        super(userId, title, description, city);
        this.address = address;
    }

    public MapOffer() {

    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
