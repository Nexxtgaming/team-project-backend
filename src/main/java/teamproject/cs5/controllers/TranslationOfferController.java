package teamproject.cs5.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import teamproject.cs5.models.ERole;
import teamproject.cs5.models.TranslationOffer;
import teamproject.cs5.models.User;
import teamproject.cs5.payload.request.TranslationOfferRequest;
import teamproject.cs5.security.services.UserDetailsImpl;
import teamproject.cs5.services.TranslationOfferService;
import teamproject.cs5.services.UserService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/translationOffer")
@CrossOrigin("*")
public class TranslationOfferController {
    @Autowired
    TranslationOfferService translationOfferService;

    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<TranslationOffer> getById(@PathVariable("id") Long id){
        TranslationOffer translationOffer = translationOfferService.getById(id);
        return new ResponseEntity<>(translationOffer, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<TranslationOffer> deleteById(@PathVariable("id") Long id){
        translationOfferService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
    @PostMapping
    public ResponseEntity<TranslationOffer> create(@RequestBody TranslationOfferRequest request, Authentication authentication){
        UserDetailsImpl user = (UserDetailsImpl) authentication.getPrincipal();
        if(userService.isRole(user.getId(), ERole.ROLE_HELPER)){
            TranslationOffer translationOffer = translationOfferService.saveFromRequest(request, user.getId());
            return new ResponseEntity<>(translationOffer, HttpStatus.CREATED);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }
    @GetMapping("/all")
    public List<TranslationOffer> getAll(){
        return translationOfferService.findAll();
    }
    @GetMapping
    public List<TranslationOffer> getWithFilters(@RequestParam String citi, @RequestParam String language){
        List<TranslationOffer> firstFilteredOffers;
        var ref = new Object() {
            List<TranslationOffer> result = new ArrayList<>();
        };
        if(citi.isEmpty() || citi.equals("all")){
            firstFilteredOffers = translationOfferService.findAll();
        }else {
            firstFilteredOffers = translationOfferService.findByCity(citi);
        }
        if(language.isEmpty() || language.equals("all")){
            ref.result = firstFilteredOffers;
        }else {
            firstFilteredOffers.forEach(translationOffer -> {
                if (translationOffer.getLanguage().equals(language)){
                    ref.result.add(translationOffer);
                }
            });
        }
        return ref.result;
    }
}
