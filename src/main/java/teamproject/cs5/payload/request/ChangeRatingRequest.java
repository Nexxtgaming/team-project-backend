package teamproject.cs5.payload.request;

public class ChangeRatingRequest {
    private boolean shouldBeIncreased;

    public ChangeRatingRequest(){};

    public ChangeRatingRequest(boolean shouldBeIncreased){
        this.shouldBeIncreased = shouldBeIncreased;
    }

    public boolean getShouldBeIncreased(){ return shouldBeIncreased; }
}
