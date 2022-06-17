package teamproject.cs5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import teamproject.cs5.models.AccommodationOffer;
import teamproject.cs5.payload.request.AccommodationOfferRequest;
import teamproject.cs5.repository.AccommodationOfferRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccommodationOfferService {
    final AccommodationOfferRepository accommodationOfferRepository;

    @Autowired
    public AccommodationOfferService (AccommodationOfferRepository accommodationOfferRepository) { this.accommodationOfferRepository=accommodationOfferRepository;}

    public AccommodationOffer save(AccommodationOffer accommodationOffer) { return accommodationOfferRepository.save(accommodationOffer);}
    public AccommodationOffer getById(Long id){
        if(accommodationOfferRepository.findById(id).isPresent()){
            return accommodationOfferRepository.findById(id).get();
        }
        else {
            throw  new ResponseStatusException(HttpStatus.NOT_FOUND, "AccommodationOffer not found");
        }
    }

    public List<AccommodationOffer> findAll(){ return accommodationOfferRepository.findAll();}

    public List<AccommodationOffer> getByCity(String city){
        List<AccommodationOffer> result = new ArrayList<>();
        accommodationOfferRepository.findAll().forEach(accommodationOffer -> {
            if(accommodationOffer.getCity().equals(city)){
                result.add(accommodationOffer);
            }
        });
        return result;
    }

    public AccommodationOffer createFromRequest(AccommodationOfferRequest request, Long userId){
        AccommodationOffer accommodationOffer = new AccommodationOffer(
                userId,
                request.getTitle(),
                request.getDescription(),
                request.getCity(),
                request.getAddress(),
                request.getCapacity()

        );
        return accommodationOfferRepository.save(accommodationOffer);
    }
    public void deleteById(Long id){
        accommodationOfferRepository.deleteById(id);
    }
}


