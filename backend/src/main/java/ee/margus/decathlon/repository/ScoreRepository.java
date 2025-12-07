package ee.margus.decathlon.repository;

import ee.margus.decathlon.entity.Score;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findByAthlete_Id(Long id);
    Optional<Score> findByAthlete_IdAndEvent_Id(Long athleteId, Long eventId);

}
