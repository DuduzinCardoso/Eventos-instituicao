package event.service.repository;

import event.service.entity.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EventoRepository  extends JpaRepository<Evento, Long> {
    List<Evento> findByAtivo(boolean ativo);
}
