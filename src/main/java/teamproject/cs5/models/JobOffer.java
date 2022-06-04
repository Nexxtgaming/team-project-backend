package teamproject.cs5.models;

import javax.persistence.Entity;

@Entity
public class JobOffer extends MapOffer{

    private Double salary;


    public JobOffer(Long userId, String title, String description, Double longitude, Double latitude, Double salary, String city) {
        super(userId, title, description, longitude, latitude, city);
        this.salary = salary;
    }

    public JobOffer() {

    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
