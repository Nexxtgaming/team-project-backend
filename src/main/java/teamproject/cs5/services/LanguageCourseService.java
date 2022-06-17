package teamproject.cs5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import teamproject.cs5.models.LanguageCourseOffer;
import teamproject.cs5.payload.request.LanguageCourseRequest;
import teamproject.cs5.repository.LanguageCourseRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class LanguageCourseService {
    final LanguageCourseRepository languageCourseRepository;

    @Autowired
    public LanguageCourseService(LanguageCourseRepository languageCourseRepository) {
        this.languageCourseRepository = languageCourseRepository;
    }

    public LanguageCourseOffer save(LanguageCourseOffer languageCourseOffer){
        return languageCourseRepository.save(languageCourseOffer);
    }

    public LanguageCourseOffer createFromRequest(LanguageCourseRequest request, Long userId){
        LanguageCourseOffer offer = new LanguageCourseOffer(
                userId,
                request.getTitle(),
                request.getDescription(),
                request.getCity(),
                request.getAddress(),
                request.getLanguage()
        );
        return languageCourseRepository.save(offer);
    }
    public LanguageCourseOffer getById(Long id){
        if(languageCourseRepository.findById(id).isPresent()){
            return languageCourseRepository.findById(id).get();
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"unable to find language course");
        }
    }
    public List<LanguageCourseOffer> findAll(){
        return languageCourseRepository.findAll();
    }
    public List<LanguageCourseOffer>  getByLanguage(String language){
        List<LanguageCourseOffer> result = new ArrayList<>();

        languageCourseRepository.findAll().forEach(languageCourseOffer -> {
            if (languageCourseOffer.getLanguage().equals(language)) {
                result.add(languageCourseOffer);
            }
        });
        return result;
    }
    public List<LanguageCourseOffer>  getByCity(String city){
        List<LanguageCourseOffer> result = new ArrayList<>();

        languageCourseRepository.findAll().forEach(languageCourseOffer -> {
            if (languageCourseOffer.getCity().equals(city)) {
                result.add(languageCourseOffer);
            }
        });
        return result;
    }
    public void deleteById(Long id){
        languageCourseRepository.deleteById(id);
    }
}
