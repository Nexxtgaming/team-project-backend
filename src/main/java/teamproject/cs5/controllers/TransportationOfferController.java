package teamproject.cs5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import teamproject.cs5.models.TransportationOffer;
import teamproject.cs5.models.ERole;
import teamproject.cs5.payload.request.TransportationOfferRequest;
import teamproject.cs5.security.services.UserDetailsImpl;
import teamproject.cs5.services.TransportationOfferService;
import teamproject.cs5.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/transportationOffer")
@CrossOrigin("*")
public class TransportationOfferController {
    final TransportationOfferService transportationOfferService;

    final UserService userService;

    @Autowired
    public TransportationOfferController(TransportationOfferService transportationOfferService, UserService userService) {
        this.transportationOfferService = transportationOfferService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<TransportationOffer> create(@RequestBody TransportationOfferRequest request, Authentication authentication){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        if(userService.isRole(userDetails.getId(), ERole.ROLE_HELPER)){
            TransportationOffer transportationOffer = transportationOfferService.createFromRequest(request, userDetails.getId());
            return new ResponseEntity<>(transportationOffer, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransportationOffer> getById(@PathVariable("id") Long id){
        TransportationOffer transportationOffer = transportationOfferService.getById(id);
        return new ResponseEntity<>(transportationOffer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TransportationOffer> delete(@PathVariable Long id, Authentication authentication){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        TransportationOffer transportationOffer = transportationOfferService.getById(id);
        if(transportationOffer.getUserId().equals(userDetails.getId())){
            transportationOfferService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/all")
    public List<TransportationOffer> findAll(){
        return transportationOfferService.findAll();
    }

    @GetMapping
    public List<TransportationOffer> getByCity(@RequestParam String city){
        if(city.equals("all") || city.isBlank()){
            return transportationOfferService.findAll();
        }
        return transportationOfferService.getByCity(city);
    }

}
