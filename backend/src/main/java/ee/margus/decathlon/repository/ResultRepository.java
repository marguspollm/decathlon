package ee.margus.decathlon.repository;

import ee.margus.decathlon.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResultRepository extends JpaRepository<Result, Long> {
    List<Result> findByAthlete_Id(Long id);

    Optional<Result> findByAthlete_IdAndEvent_Id(Long athleteId, Long eventId);

}
