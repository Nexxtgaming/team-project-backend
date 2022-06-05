package teamproject.cs5.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import teamproject.cs5.models.VolunteersOffer;
import teamproject.cs5.payload.request.VolunteersOfferRequest;
import teamproject.cs5.repository.VolunteersOfferRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VolunteersOfferService {
    final VolunteersOfferRepository volunteersOfferRepository;

    public VolunteersOfferService(VolunteersOfferRepository volunteersOfferRepository) {
        this.volunteersOfferRepository = volunteersOfferRepository;
    }

    public VolunteersOffer save(VolunteersOffer volunteersOffer){
        return volunteersOfferRepository.save(volunteersOffer);
    }
    public void deleteById(Long id){
        volunteersOfferRepository.deleteById(id);
    }
    public List<VolunteersOffer> findAll(){
        return volunteersOfferRepository.findAll();
    }
    public VolunteersOffer getById(Long id){
        Optional<VolunteersOffer> offer = volunteersOfferRepository.findById(id);
        if (offer.isPresent()){
            return offer.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "volunteers offer not found");
        }
    }
    public List<VolunteersOffer> getByCity(String city){
        List<VolunteersOffer> result = new ArrayList<>();
        volunteersOfferRepository.findAll().forEach(volunteersOffer -> {
            if(volunteersOffer.getCity().equals(city)){
                result.add(volunteersOffer);
            }
        });
        return result;
    }
    public VolunteersOffer createFromRequest(VolunteersOfferRequest request, Long userId){
        VolunteersOffer newVolunteersOffer = new VolunteersOffer(
                userId,
                request.getTitle(),
                request.getDescription(),
                request.getCity()
        );
        return volunteersOfferRepository.save(newVolunteersOffer);
    }
}
