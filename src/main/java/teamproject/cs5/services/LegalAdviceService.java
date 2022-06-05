package teamproject.cs5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import teamproject.cs5.models.LegalAdviceOffer;
import teamproject.cs5.payload.request.LegalAdviceRequest;
import teamproject.cs5.repository.LegalAdviceRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LegalAdviceService {

    final LegalAdviceRepository legalAdviceRepository;

    @Autowired
    public LegalAdviceService(LegalAdviceRepository legalAdviceRepository) {
        this.legalAdviceRepository = legalAdviceRepository;
    }

    public LegalAdviceOffer createFromRequest(LegalAdviceRequest request, Long userId){
        LegalAdviceOffer offer = new LegalAdviceOffer(
                userId,
                request.getTitle(),
                request.getDescription(),
                request.getLongitude(),
                request.getLatitude(),
                request.getCity()
                );
        return legalAdviceRepository.save(offer);
    }

    public LegalAdviceOffer getById(Long id){
        Optional<LegalAdviceOffer> offer = legalAdviceRepository.findById(id);
        if (offer.isPresent()){
            return offer.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "legal advice offer not found");
        }
    }

    public List<LegalAdviceOffer> findAll(){
        return legalAdviceRepository.findAll();
    }
    public List<LegalAdviceOffer> getByCity(String city){
        List<LegalAdviceOffer> result = new ArrayList<>();
        legalAdviceRepository.findAll().forEach(legalAdviceOffer -> {
            if (legalAdviceOffer.getCity().equals(city)){
                result.add(legalAdviceOffer);
            }
        });
        return result;
    }
    public void deleteById(Long id){
        legalAdviceRepository.deleteById(id);
    }
}
