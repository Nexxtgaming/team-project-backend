package teamproject.cs5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import teamproject.cs5.models.TranslationOffer;
import teamproject.cs5.payload.request.TranslationOfferRequest;
import teamproject.cs5.repository.TranslationOfferRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class TranslationOfferService {
    final TranslationOfferRepository translationOfferRepository;


    public TranslationOfferService(TranslationOfferRepository translationOfferRepository) {
        this.translationOfferRepository = translationOfferRepository;
    }
    public TranslationOffer save(TranslationOffer translationOffer){
        return translationOfferRepository.save(translationOffer);
    }
    public TranslationOffer getById(Long id){
        return translationOfferRepository.getById(id);
    }
    public void deleteById(Long id){
        translationOfferRepository.deleteById(id);
    }
    public List<TranslationOffer> findAll(){
        return translationOfferRepository.findAll();
    }
    public List<TranslationOffer> findByCity(String city){
        List<TranslationOffer> result = new ArrayList<>();
        translationOfferRepository.findAll().forEach(translationOffer -> {
            if (translationOffer.getCity().equals(city)){
                result.add(translationOffer);
            }
        });
        return result;
    }
    public List<TranslationOffer> findByLanguage(String language){
        List<TranslationOffer> result = new ArrayList<>();
        translationOfferRepository.findAll().forEach(translationOffer -> {
            if (translationOffer.getLanguage().equals(language)){
                result.add(translationOffer);
            }
        });
        return result;
    }
    public TranslationOffer saveFromRequest(TranslationOfferRequest request, Long userId){
        TranslationOffer translationOffer = new TranslationOffer(
                userId,
                request.getTitle(),
                request.getDescription(),
                request.getCity(),
                request.getLanguage(),
                request.getLatitude(),
                request.getLongitude()
                );
        return translationOfferRepository.save(translationOffer);
    }
}
