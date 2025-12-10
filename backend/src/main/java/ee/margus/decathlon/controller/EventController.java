package ee.margus.decathlon.controller;

import ee.margus.decathlon.entity.Event;
import ee.margus.decathlon.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class EventController {
    @Autowired
    private EventService eventService;

    @GetMapping("events")
    public List<Event> getEvents(){
        return eventService.getEvents();
    }

}
