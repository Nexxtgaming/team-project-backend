package teamproject.cs5.models;

import javax.persistence.Entity;

@Entity
public class JobOffer extends MapOffer{

    private Double salary;

    public JobOffer(Long userId, String title, String description, String city, String address, Double salary) {
        super(userId, title, description, city, address);
        this.salary = salary;
    }

    public JobOffer(String address, Double salary) {
        super(address);
        this.salary = salary;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }
}
