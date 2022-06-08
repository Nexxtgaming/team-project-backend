package teamproject.cs5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import teamproject.cs5.models.ERole;
import teamproject.cs5.models.JobOffer;
import teamproject.cs5.models.User;
import teamproject.cs5.payload.request.JobOfferRequest;
import teamproject.cs5.security.services.UserDetailsImpl;
import teamproject.cs5.services.JobOfferService;
import teamproject.cs5.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/jobOffer")
@CrossOrigin("*")
public class JobOfferController {
    final JobOfferService jobOfferService;

    final UserService userService;

    @Autowired
    public JobOfferController(JobOfferService jobOfferService, UserService userService) {
        this.jobOfferService = jobOfferService;
        this.userService = userService;
    }
    @PostMapping
    public ResponseEntity<JobOffer> create(@RequestBody JobOfferRequest request, Authentication authentication){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        if(userService.isRole(userDetails.getId(), ERole.ROLE_HELPER)){

            JobOffer jobOffer = jobOfferService.createFromRequest(request, userDetails.getId());
            return new ResponseEntity<>(jobOffer, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<JobOffer> getById(@PathVariable("id") Long id){
        JobOffer jobOffer = jobOfferService.getById(id);
        return new ResponseEntity<>(jobOffer, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<JobOffer> delete(@PathVariable Long id, Authentication authentication){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        JobOffer jobOffer = jobOfferService.getById(id);
        if(jobOffer.getUserId().equals(userDetails.getId())){
            jobOfferService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        }else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
    @GetMapping("/all")
    public List<JobOffer> findAll(){
        return jobOfferService.findAll();
    }
    @GetMapping
    public List<JobOffer> getByCity(@RequestParam String city){
        if(city.equals("all") || city.isBlank()){
            return jobOfferService.findAll();
        }
        return jobOfferService.getByCity(city);
    }
}
