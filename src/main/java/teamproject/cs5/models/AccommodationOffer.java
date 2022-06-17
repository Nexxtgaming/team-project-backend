package teamproject.cs5.models;

import javax.persistence.Entity;

@Entity
public class AccommodationOffer extends MapOffer {
    private Double capacity; //number of person who can be accommodated

    public AccommodationOffer(Long userId, String title, String description, String city, String address, Double capacity) {
        super(userId, title, description, city, address);
        this.capacity = capacity;
    }

    public AccommodationOffer(String address, Double capacity) {
        super(address);
        this.capacity = capacity;
    }

    public AccommodationOffer() {
        super();
    }

    public Double getCapacity() {
        return capacity;
    }

    public void setCapacity(Double capacity) {
        this.capacity = capacity;
    }
}
