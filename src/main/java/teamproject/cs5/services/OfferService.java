package teamproject.cs5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import teamproject.cs5.models.Offer;
import teamproject.cs5.payload.request.OfferRequest;
import teamproject.cs5.repository.OfferRepository;

@Service
public class OfferService {
    final OfferRepository repository;

    @Autowired
    public OfferService(OfferRepository repository) {
        this.repository = repository;
    }
    public Offer save(Offer offer){
        return repository.save(offer);
    }
    public Offer createFromRequest(OfferRequest offerRequest, Long userId){
        Offer offer = new Offer(
                userId,
                offerRequest.getTitle(),
                offerRequest.getDescription(),
                offerRequest.getCity()
        );
        return repository.save(offer);
    }
}
