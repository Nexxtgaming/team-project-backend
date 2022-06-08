package teamproject.cs5.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import teamproject.cs5.models.ERole;
import teamproject.cs5.models.VolunteersOffer;
import teamproject.cs5.payload.request.VolunteersOfferRequest;
import teamproject.cs5.security.services.UserDetailsImpl;
import teamproject.cs5.services.UserService;
import teamproject.cs5.services.VolunteersOfferService;

import java.util.List;

@RestController
@RequestMapping("/api/volunteersOffer")
@CrossOrigin("*")
public class VolunteersOfferController {
    final VolunteersOfferService volunteersOfferService;
    final UserService userService;

    public VolunteersOfferController(VolunteersOfferService volunteersOfferService, UserService userService) {
        this.volunteersOfferService = volunteersOfferService;
        this.userService = userService;
    }
    @GetMapping("/{id}")
    public ResponseEntity<VolunteersOffer> getById(@PathVariable("id") Long id){
        VolunteersOffer volunteersOffer = volunteersOfferService.getById(id);
        return new ResponseEntity<>(volunteersOffer, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<VolunteersOffer> deleteById(@PathVariable("id") Long id, Authentication authentication){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        if (userService.isRole(userDetails.getId(), ERole.ROLE_HELPER)) {
            volunteersOfferService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
    @GetMapping("/all")
    public List<VolunteersOffer> findAll(){
        return volunteersOfferService.findAll();
    }
    @PostMapping
    public ResponseEntity<VolunteersOffer> create(@RequestBody VolunteersOfferRequest request, Authentication authentication){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        if(userService.isRole(userDetails.getId(), ERole.ROLE_HELPER)){

            volunteersOfferService.createFromRequest(request,userDetails.getId());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
    @GetMapping
    public List<VolunteersOffer> getByCity(@RequestParam String city){
        if(city.isEmpty() || city.equals("all")){
            return volunteersOfferService.findAll();
        }else {
            return volunteersOfferService.getByCity(city);
        }
    }
}
