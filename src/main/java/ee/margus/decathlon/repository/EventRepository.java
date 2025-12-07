package ee.margus.decathlon.repository;

import ee.margus.decathlon.entity.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, Long> {
}
