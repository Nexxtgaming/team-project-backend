package teamproject.cs5.controllers;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('REFUGEE') or hasRole('HELPER')")
    public String userAccess() {
        return "User Content.";
    }
    @GetMapping("/refugee")
    @PreAuthorize("hasRole('REFUGEE')")
    public String refugeeAccess() {
        return "Refugee Board.";
    }
    @GetMapping("/helper")
    @PreAuthorize("hasRole('HELPER')")
    public String helperAccess() {
        return "Helper Board.";
    }
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }
}