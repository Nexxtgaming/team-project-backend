package teamproject.cs5.payload.request;

public class MapOfferRequest extends OfferRequest{
    
    public String address;

    public MapOfferRequest(String title, String description, String city, String address) {
        super(title, description, city);
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
