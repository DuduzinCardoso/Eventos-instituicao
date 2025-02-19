package event.service.controller;

import event.service.entity.Evento;
import event.service.service.EventoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/eventos")
public class EventoController {
    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @PostMapping
    public ResponseEntity<Evento> criarEvento(@RequestBody Evento evento) {
        return ResponseEntity.status(201).body(eventoService.salvarEvento(evento));
    }

    @GetMapping("/{id}")
    public Evento buscarEvento(@PathVariable Long id) {
        return eventoService.buscarEvento(id);
    }
}
