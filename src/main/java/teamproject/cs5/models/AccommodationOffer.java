package teamproject.cs5.models;

import javax.persistence.Entity;

@Entity
public class AccommodationOffer extends MapOffer {
    private Double capacity; //number of person who can be accommodated

    public AccommodationOffer(Long userId,
                              String title,
                              String description,
                              Double longitude,
                              Double latitude,
                              String city,
                              Double capacity){

        super(userId, title, description, longitude, latitude, city);
        this.capacity=capacity;
    }

    public AccommodationOffer() {

    }


    public Double getCapacity() {
        return capacity;
    }

    public void setCapacity(Double capacity) {
        this.capacity = capacity;
    }
}
