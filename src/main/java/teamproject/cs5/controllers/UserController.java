package teamproject.cs5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import teamproject.cs5.models.User;
import teamproject.cs5.payload.request.ChangeRatingRequest;
import teamproject.cs5.payload.request.UpdateUserRequest;
import teamproject.cs5.payload.response.MessageResponse;
import teamproject.cs5.payload.response.UserContactResponse;
import teamproject.cs5.payload.response.UserPublicInfoResponse;
import teamproject.cs5.repository.UserRepository;
import teamproject.cs5.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PatchMapping("/me")
    public ResponseEntity<MessageResponse> updateUser(Authentication authentication, @RequestBody UpdateUserRequest newUser){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User oldUser = this.userRepository.findById(userDetails.getId())
                .orElseThrow(()->new RuntimeException("Error: User not found"));
        oldUser.setEmail(newUser.getEmail());
        oldUser.setName(newUser.getName());
        oldUser.setSurname(newUser.getSurname());
        oldUser.setUsername(newUser.getUsername());
        oldUser.setPhoneNumber(newUser.getPhoneNumber());
        userRepository.save(oldUser);
        MessageResponse messageResponse = new MessageResponse("user updated");
        return new ResponseEntity<>(messageResponse, HttpStatus.OK);
    }
    @GetMapping("/{id}/contact")
    public ResponseEntity<UserContactResponse> getContact(@PathVariable("id") Long id){
        User user = this.userRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Error: User not found"));
        String phoneNumber = user.getPhoneNumber();
        String email = user.getEmail();
        String name = user.getName();
        String surname = user.getSurname();
        UserContactResponse contact = new UserContactResponse(phoneNumber,email,name,surname);
        return new ResponseEntity<UserContactResponse>(contact, HttpStatus.OK);
    }

    @GetMapping("/{id}/publicInfo")
    public ResponseEntity<UserPublicInfoResponse> getPublicInfo(@PathVariable("id") Long id){
        User user = this.userRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Error: User not found"));
        String username = user.getUsername();
        Long rating = user.getRating();
        UserPublicInfoResponse publicInfo = new UserPublicInfoResponse(username, rating);
        return new ResponseEntity<UserPublicInfoResponse>(publicInfo, HttpStatus.OK);
    }

    @PatchMapping("/{id}/changeRating")
    @PreAuthorize("hasRole('USER') or hasRole('REFUGEE') or hasRole('HELPER')")
    public ResponseEntity<MessageResponse> changeRating(@PathVariable("id") Long id, @RequestBody ChangeRatingRequest ratingInfo){
        User oldUser = this.userRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Error: User not found"));
        if(ratingInfo.getShouldBeIncreased()){
            oldUser.setRating(oldUser.getRating() + 1);
        }
        else{
            oldUser.setRating(oldUser.getRating() - 1);
        }
        userRepository.save(oldUser);
        MessageResponse messageResponse = new MessageResponse("rating updated");
        return new ResponseEntity<MessageResponse>(messageResponse, HttpStatus.OK);
    }
}
