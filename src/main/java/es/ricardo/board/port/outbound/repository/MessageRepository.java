package es.ricardo.board.port.outbound.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.ricardo.board.domain.model.Message;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>{

	List<Message> findAllByCategory_IdOrderByDateAsc(Long id);

}
