package teamproject.cs5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import teamproject.cs5.models.Article;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
}
