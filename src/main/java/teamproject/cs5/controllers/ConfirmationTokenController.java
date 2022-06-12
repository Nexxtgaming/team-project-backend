package teamproject.cs5.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import teamproject.cs5.services.ConfirmationTokenService;

import java.time.LocalDateTime;

@Controller
@CrossOrigin("*")
@RequestMapping("/api/confirmationToken")
public class ConfirmationTokenController {
    public final ConfirmationTokenService confirmationTokenService;

    public ConfirmationTokenController(ConfirmationTokenService confirmationTokenService) {
        this.confirmationTokenService = confirmationTokenService;
    }

    @GetMapping("/{token}")
    public ResponseEntity<String> verifyUser(@PathVariable String token){
        String response = confirmationTokenService.validateToken(token, LocalDateTime.now());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
