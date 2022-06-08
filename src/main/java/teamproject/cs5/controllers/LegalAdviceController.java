package teamproject.cs5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import teamproject.cs5.models.LegalAdviceOffer;
import teamproject.cs5.payload.request.LegalAdviceRequest;
import teamproject.cs5.security.services.UserDetailsImpl;
import teamproject.cs5.services.LegalAdviceService;
import teamproject.cs5.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/legalAdvices")
@CrossOrigin("*")
public class LegalAdviceController {
    final LegalAdviceService legalAdviceService;
    final UserService userService;

    @Autowired
    public LegalAdviceController(LegalAdviceService legalAdviceService, UserService userService) {
        this.legalAdviceService = legalAdviceService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<LegalAdviceOffer> create(@RequestBody LegalAdviceRequest request, Authentication authentication){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        LegalAdviceOffer offer = legalAdviceService.createFromRequest(request, userDetails.getId());
        return new ResponseEntity<>(offer, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<LegalAdviceOffer> getById(@PathVariable("id") Long id){
        LegalAdviceOffer offer = legalAdviceService.getById(id);
        return new ResponseEntity<>(offer, HttpStatus.OK);
    }
    @GetMapping("/all")
    public List<LegalAdviceOffer> findAll(){
        return legalAdviceService.findAll();
    }
    @GetMapping
    public List<LegalAdviceOffer> getByCity(@RequestParam String city){
        if(city.equals("all") || city.isBlank()){
            return legalAdviceService.findAll();
        }else {
            return legalAdviceService.getByCity(city);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<LegalAdviceOffer> deleteById(@PathVariable Long id){
        legalAdviceService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
