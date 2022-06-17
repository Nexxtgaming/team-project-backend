package teamproject.cs5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import teamproject.cs5.models.TransportationOffer;
import teamproject.cs5.payload.request.TransportationOfferRequest;
import teamproject.cs5.repository.TransportationOfferRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransportationOfferService {
    final TransportationOfferRepository transportationOfferRepository;


    @Autowired
    public TransportationOfferService(TransportationOfferRepository transportationOfferRepository) {
        this.transportationOfferRepository = transportationOfferRepository;
    }

    public TransportationOffer save(TransportationOffer transportationOffer){
        return transportationOfferRepository.save(transportationOffer);
    }
    public TransportationOffer getById(Long id){
        if(transportationOfferRepository.findById(id).isPresent()){
            return transportationOfferRepository.findById(id).get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Transportation Offer not found");
        }
    }
    public List<TransportationOffer> findAll(){
        return transportationOfferRepository.findAll();
    }
    public List<TransportationOffer> getByCity(String city){
        List<TransportationOffer> result = new ArrayList<>();
        transportationOfferRepository.findAll().forEach(transportationOffer -> {
            if(transportationOffer.getCity().equals(city)){
                result.add(transportationOffer);
            }
        });
        return result;
    }
    public TransportationOffer createFromRequest(TransportationOfferRequest request, Long userId){
        TransportationOffer transportationOffer = new TransportationOffer(
                userId,
                request.getTitle(),
                request.getDescription(),
                request.getCity(),
                request.getAddress(),
                request.getCityOfArrival(),
                request.getSeatsAvailable(),
                request.getDateOfDeparture(),
                request.getDateOfArrival(),
                request.getVehicleType()
        );
        return transportationOfferRepository.save(transportationOffer);
    }
    public void deleteById(Long id){
        transportationOfferRepository.deleteById(id);
    }

}
