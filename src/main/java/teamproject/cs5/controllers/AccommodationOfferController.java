package teamproject.cs5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import teamproject.cs5.models.AccommodationOffer;
import teamproject.cs5.models.ERole;
import teamproject.cs5.payload.request.AccommodationOfferRequest;
import teamproject.cs5.security.services.UserDetailsImpl;
import teamproject.cs5.services.AccommodationOfferService;
import teamproject.cs5.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/accommodationOffer")
public class AccommodationOfferController {
    final AccommodationOfferService accommodationOfferService;
    final UserService userService;

    @Autowired
    public AccommodationOfferController(AccommodationOfferService accommodationOfferService, UserService userService) {
        this.accommodationOfferService = accommodationOfferService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<AccommodationOffer> create(@RequestBody AccommodationOfferRequest request, Authentication authentication){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        if(userService.isRole(userDetails.getId(), ERole.ROLE_HELPER)){

            AccommodationOffer accommodationOffer = accommodationOfferService.createFromRequest(request, userDetails.getId());
            return new ResponseEntity<>(accommodationOffer, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccommodationOffer> getById(@PathVariable("id") Long id){
        AccommodationOffer accommodationOffer = accommodationOfferService.getById(id);
        return new ResponseEntity<>(accommodationOffer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AccommodationOffer> delete(@PathVariable Long id, Authentication authentication){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        AccommodationOffer accommodationOffer = accommodationOfferService.getById(id);
        if(accommodationOffer.getUserId().equals(userDetails.getId())){
            accommodationOfferService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/all")
    public List<AccommodationOffer> findAll(){
        return accommodationOfferService.findAll();
    }
    @GetMapping
    public List<AccommodationOffer> getByCity(@RequestParam String city){
        if(city.equals("all") || city.isBlank()){
            return accommodationOfferService.findAll();
        }
        return accommodationOfferService.getByCity(city);
    }
}
