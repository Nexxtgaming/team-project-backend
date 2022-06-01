package teamproject.cs5.payload.request;

public class JobOfferRequest extends MapOfferRequest{
    private final Double salary;

    public JobOfferRequest(String title, String description, String city, Double longitude, Double latitude, Double salary) {
        super(title, description, city, longitude, latitude);
        this.salary = salary;
    }

    public Double getSalary() {
        return salary;
    }
}
