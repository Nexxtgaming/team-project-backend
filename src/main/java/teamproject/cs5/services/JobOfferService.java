package teamproject.cs5.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import teamproject.cs5.models.JobOffer;
import teamproject.cs5.payload.request.JobOfferRequest;
import teamproject.cs5.repository.JobOfferRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class JobOfferService {
    final JobOfferRepository jobOfferRepository;


    @Autowired
    public JobOfferService(JobOfferRepository jobOfferRepository) {
        this.jobOfferRepository = jobOfferRepository;
    }

    public JobOffer save(JobOffer jobOffer){
        return jobOfferRepository.save(jobOffer);
    }
    public JobOffer getById(Long id){
        if(jobOfferRepository.findById(id).isPresent()){
            return jobOfferRepository.findById(id).get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "jobOffer not found");
        }
    }
    public List<JobOffer> findAll(){
        return jobOfferRepository.findAll();
    }
    public List<JobOffer> getByCity(String city){
        List<JobOffer> result = new ArrayList<>();
        jobOfferRepository.findAll().forEach(jobOffer -> {
            if(jobOffer.getCity().equals(city)){
                result.add(jobOffer);
            }
        });
        return result;
    }
    public JobOffer createFromRequest(JobOfferRequest request, Long userId){
        JobOffer jobOffer = new JobOffer(
                userId,
                request.getTitle(),
                request.getDescription(),
                request.getCity(),
                request.getAddress(),
                request.getSalary()
        );
        return jobOfferRepository.save(jobOffer);
    }
    public void deleteById(Long id){
        jobOfferRepository.deleteById(id);
    }
}
