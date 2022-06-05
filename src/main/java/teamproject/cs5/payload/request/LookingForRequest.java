package teamproject.cs5.payload.request;

import teamproject.cs5.models.LookingForTypes;

public class LookingForRequest extends OfferRequest{

    private final LookingForTypes type;

    public LookingForRequest(String title, String description, String city, LookingForTypes type) {
        super(title, description, city);
        this.type = type;
    }

    public LookingForTypes getType() {
        return type;
    }
}
