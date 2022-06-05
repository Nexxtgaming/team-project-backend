package teamproject.cs5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import teamproject.cs5.models.Offer;

public interface OfferRepository extends JpaRepository<Offer, Long> {
}
