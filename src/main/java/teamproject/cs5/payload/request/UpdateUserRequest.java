package teamproject.cs5.payload.request;

public class UpdateUserRequest {
    private String username;
    private String name;
    private String surname;
    private String phoneNumber;
    private String email;

    public UpdateUserRequest(String username, String name, String surname, String phoneNumber, String email) {
        this.username = username;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

}
