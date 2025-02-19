package event.service.service.impl;

import event.service.repository.EventoRepository;
import event.service.entity.Evento;
import event.service.service.EventoService;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class EventoServiceImpl implements EventoService {

    private final EventoRepository eventoRepository;

    public EventoServiceImpl(EventoRepository eventoRepository) {
        this.eventoRepository = eventoRepository;
    }

    @Override
    public Evento salvarEvento(Evento evento) {
        if (evento.getInstituicao() == null) {
            throw new IllegalArgumentException("A instituição não pode ser nula.");
        }
        validarDatas(evento);
        evento.setAtivo(false);
        evento.setInstituicao(evento.getInstituicao());
        return eventoRepository.save(evento);
    }

    @Override
    public Evento buscarEvento(Long id) {
        return eventoRepository.findById(id).orElseThrow(() -> new RuntimeException("Evento não encontrado"));
    }


    private void validarDatas(Evento evento) {
        if (evento.getDataInicio().isAfter(evento.getDataFim())) {
            throw new IllegalArgumentException("Data de início não pode ser posterior à data final.");
        }
    }

    @Scheduled(cron = "0 0 * * * *")
    public void atualizarStatusEventos() {
        LocalDate hoje = LocalDate.now();
        List<Evento> eventos = eventoRepository.findAll();
        eventos.forEach(evento -> {
            boolean ativo = !hoje.isBefore(evento.getDataInicio()) && !hoje.isAfter(evento.getDataFim());
            if (evento.isAtivo() != ativo) {
                evento.setAtivo(ativo);
                eventoRepository.save(evento);
            }
        });
    }
}
