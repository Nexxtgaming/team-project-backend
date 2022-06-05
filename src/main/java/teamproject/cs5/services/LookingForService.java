package teamproject.cs5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import teamproject.cs5.models.LookingForOffer;
import teamproject.cs5.models.LookingForTypes;
import teamproject.cs5.payload.request.LookingForRequest;
import teamproject.cs5.repository.LookingForRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LookingForService {

    final LookingForRepository lookingForRepository;

    @Autowired
    public LookingForService(LookingForRepository lookingForRepository) {
        this.lookingForRepository = lookingForRepository;
    }

    public LookingForOffer createFromRequest(LookingForRequest request, Long userId){
        LookingForOffer offer = new LookingForOffer(
                userId,
                request.getTitle(),
                request.getDescription(),
                request.getCity(),
                request.getType()
        );
        return lookingForRepository.save(offer);
    }
    public LookingForOffer getById(Long id){
        Optional<LookingForOffer> offer = lookingForRepository.findById(id);
        if(offer.isPresent()){
            return offer.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "looking for offer not found");
        }
    }
    public void deleteById(Long id){
        lookingForRepository.deleteById(id);
    }
    public List<LookingForOffer> findAll(){
        return lookingForRepository.findAll();
    }
    public List<LookingForOffer> findWithFilters(String type, String city){
        List<LookingForOffer> firstFilter;
        if (type.isBlank() || type.equals("all")){
            firstFilter = lookingForRepository.findAll();
        } else {
            firstFilter = new ArrayList<>();
            lookingForRepository.findAll().forEach(lookingForOffer -> {
                if(lookingForOffer.getType().name().equals(type)){
                    firstFilter.add(lookingForOffer);
                }
            });
        }
        if (city.equals("all") || city.isBlank()) return firstFilter;
        List<LookingForOffer> result = new ArrayList<>();
        firstFilter.forEach(lookingForOffer -> {
            if(lookingForOffer.getCity().equals(city)){
                result.add(lookingForOffer);
            }
        });
        return result;
    }
}
