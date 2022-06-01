package teamproject.cs5.payload.response;

public class UserContactResponse {
    private String phoneNumber;
    private String email;
    private String name;
    private String surname;

    public UserContactResponse(String phoneNumber, String email, String name, String surname) {
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.name = name;
        this.surname = surname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}
