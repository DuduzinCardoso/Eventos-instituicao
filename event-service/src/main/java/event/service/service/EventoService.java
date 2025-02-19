package event.service.service;

import event.service.entity.Evento;

public interface EventoService {
    Evento salvarEvento(Evento evento);

    Evento buscarEvento(Long id);
}
