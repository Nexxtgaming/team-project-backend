package teamproject.cs5.payload.response;

public class UserPublicInfoResponse {
    private String username;
    private Long rating;

    public UserPublicInfoResponse(String username, Long rating) {
        this.username = username;
        this.rating = rating;
    }

    public String getUsername() {
        return username;
    }

    public Long getRating() {
        return rating;
    }
}