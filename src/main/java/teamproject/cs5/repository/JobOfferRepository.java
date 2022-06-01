package teamproject.cs5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teamproject.cs5.models.JobOffer;

@Repository
public interface JobOfferRepository extends JpaRepository<JobOffer, Long> {
}
