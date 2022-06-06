package teamproject.cs5.payload.request;

import teamproject.cs5.models.Role;

import java.util.Set;

import javax.validation.constraints.*;

public class  SignupRequest {
    @NotBlank
    @Size(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(min = 1, max = 20)
    private String name;

    @NotBlank
    @Size(min = 1, max = 20)
    private String surname;

    @NotBlank
    @Size(min = 1, max = 13)
    private String phoneNumber;

    @NotBlank
    @Size(min = 1, max = 50)
    @Email
    private String email;

    private boolean verified;

    private Long rating;

    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<String> getRole() {
        return this.role;
    }

    public void setRole(Set<String> role) {
        this.role = role;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public boolean getVerified(){
        return verified;
    }
    public void setVerified(boolean verified){
        this.verified = verified;
    }
    public Long getRating(){
        return rating;
    }
    public void setRating(Long rating){
        this.rating = rating;
    }
}