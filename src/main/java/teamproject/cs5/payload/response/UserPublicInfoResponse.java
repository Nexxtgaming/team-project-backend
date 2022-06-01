package teamproject.cs5.payload.response;

import teamproject.cs5.models.Rating;

import java.util.Set;

public class UserPublicInfoResponse {
    private String username;
    private Long totalRating;
    private Set<Rating> messages;

    public UserPublicInfoResponse(String username, Long rating, Set<Rating> messages) {
        this.username = username;
        this.totalRating = rating;
        this.messages = messages;
    }

    public String getUsername() {
        return username;
    }

    public Long getTotalRating() {
        return totalRating;
    }

    public Set<Rating> getMessages() {
        return messages;
    }
}