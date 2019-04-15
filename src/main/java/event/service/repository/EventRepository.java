package event.service.repository;

import event.service.model.Event;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends ReactiveMongoRepository<Event, String> {
}
