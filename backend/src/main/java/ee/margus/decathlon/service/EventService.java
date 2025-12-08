package ee.margus.decathlon.service;

import ee.margus.decathlon.entity.Event;
import ee.margus.decathlon.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {
    @Autowired
    private EventRepository eventRepository;

    public List<Event> getEvents(){
        return eventRepository.findAll();
    }

    public Event getEventById(Long id){
        return eventRepository.findById(id).orElseThrow(() -> new RuntimeException("Event doesn't exist!"));
    }
}
