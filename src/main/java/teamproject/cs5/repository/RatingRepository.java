package teamproject.cs5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teamproject.cs5.models.Rating;

@Repository
public interface RatingRepository extends JpaRepository<Rating, Long> {
}
