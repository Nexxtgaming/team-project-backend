package teamproject.cs5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import teamproject.cs5.models.LookingForOffer;
import teamproject.cs5.payload.request.LookingForRequest;
import teamproject.cs5.security.services.UserDetailsImpl;
import teamproject.cs5.services.LookingForService;

import java.util.List;

@RestController
@RequestMapping("/api/lookingForOffers")
@CrossOrigin("*")
public class LookingForController {
    final LookingForService lookingForService;

    @Autowired
    public LookingForController(LookingForService lookingForService) {
        this.lookingForService = lookingForService;
    }

    @PostMapping
    public ResponseEntity<LookingForOffer> create(@RequestBody LookingForRequest request, Authentication authentication){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        LookingForOffer offer = lookingForService.createFromRequest(request, userDetails.getId());
        return new ResponseEntity<>(offer, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<LookingForOffer> getById(@PathVariable("id") Long id){
        LookingForOffer offer = lookingForService.getById(id);
        return new ResponseEntity<>(offer, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<LookingForOffer> deleteById(@PathVariable("id") Long id){
        lookingForService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @GetMapping("/all")
    public List<LookingForOffer> findAll(){
        return lookingForService.findAll();
    }
    @GetMapping
    public List<LookingForOffer> findWithFilters(@RequestParam String type, @RequestParam String city){
        return lookingForService.findWithFilters(type, city);
    }
}
