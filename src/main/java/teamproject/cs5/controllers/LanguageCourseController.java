package teamproject.cs5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import teamproject.cs5.models.LanguageCourseOffer;
import teamproject.cs5.payload.request.LanguageCourseRequest;
import teamproject.cs5.security.services.UserDetailsImpl;
import teamproject.cs5.services.LanguageCourseService;
import teamproject.cs5.services.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/languageCourses")
public class LanguageCourseController {

    final LanguageCourseService languageCourseService;

    final UserService userService;

    @Autowired
    public LanguageCourseController(LanguageCourseService languageCourseService, UserService userService) {
        this.languageCourseService = languageCourseService;
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<LanguageCourseOffer> create(@RequestBody LanguageCourseRequest languageCourseRequest, Authentication authentication){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        LanguageCourseOffer offer = languageCourseService.createFromRequest(languageCourseRequest, userDetails.getId());
        return new ResponseEntity<>(offer, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<LanguageCourseOffer> getById(@PathVariable("id") Long id){
        LanguageCourseOffer offer = languageCourseService.getById(id);
        return new ResponseEntity<>(offer, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<LanguageCourseOffer> deleteById(@PathVariable Long id){
        languageCourseService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @GetMapping("/all")
    public List<LanguageCourseOffer> getAll(){
        return languageCourseService.findAll();
    }
    @GetMapping
    public List<LanguageCourseOffer> findWithFilters(@RequestParam String city, @RequestParam String lang){
        List<LanguageCourseOffer> firstFilter;
        final List<LanguageCourseOffer> result = new ArrayList<>();
        if(city.equals("all") || city.isBlank()){
            firstFilter = languageCourseService.findAll();
        }else {
            firstFilter = languageCourseService.getByCity(city);
        }
        if(lang.equals("all") || lang.isBlank()){
            return firstFilter;
        } else {
            firstFilter.forEach(languageCourseOffer -> {
                if(languageCourseOffer.getLanguage().equals(lang)){
                    result.add(languageCourseOffer);
                }
            });
        }
        return result;
    }
}
