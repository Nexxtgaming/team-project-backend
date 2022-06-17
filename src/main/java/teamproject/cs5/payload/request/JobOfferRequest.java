package teamproject.cs5.payload.request;

public class JobOfferRequest extends MapOfferRequest{
    private final Double salary;

    public JobOfferRequest(String title, String description, String city, String address, Double salary) {
        super(title, description, city, address);
        this.salary = salary;
    }

    public Double getSalary() {
        return salary;
    }
}
