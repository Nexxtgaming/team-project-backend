package teamproject.cs5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teamproject.cs5.models.LegalAdviceOffer;

public interface LegalAdviceRepository extends JpaRepository<LegalAdviceOffer, Long> {
}
