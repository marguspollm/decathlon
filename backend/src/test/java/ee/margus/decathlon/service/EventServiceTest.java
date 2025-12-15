package ee.margus.decathlon.service;

import ee.margus.decathlon.entity.Event;
import ee.margus.decathlon.repository.EventRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EventServiceTest {
    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventService eventService;

    @Test
    void whenGetEvents_thenReturnAllEventsList() {
        List<Event> events = new ArrayList<>();
        Event event = new Event();
        event.setName("100m");
        event.setIsTrack(true);
        events.add(event);

        when(eventRepository.findAll()).thenReturn(events);

        assertEquals(events, eventService.getEvents());
    }

    @Test
    void givenExistingEventId_whenGetEventById_thenReturnEvent() {
        Event event = new Event();
        event.setId(1L);
        event.setName("100m");
        event.setIsTrack(true);

        when(eventRepository.findById(1L)).thenReturn(Optional.of(event));

        assertEquals(event, eventService.getEventById(1L));
    }

    @Test
    void givenNotExistingEventId_whenGetEventById_thenThrowException(){
        String message = assertThrows(RuntimeException.class, () -> eventService.getEventById(1L)).getMessage();
        assertEquals("Event doesn't exist!", message);
    }
}