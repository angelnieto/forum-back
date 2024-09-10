package es.ricardo.board.domain.service;

import java.util.List;

import org.springframework.stereotype.Service;

import es.ricardo.board.domain.model.Message;
import es.ricardo.board.port.inbound.rest.MessageService;
import es.ricardo.board.port.outbound.repository.MessageRepository;

@Service
public class MessageServiceImpl implements MessageService{
	
	private MessageRepository repository;

	public MessageServiceImpl(MessageRepository repository) {
		this.repository = repository;
	}

	public List<Message> getMessages(Long category) {
		return repository.findAllByCategory_IdOrderByDateAsc(category);
	}

	public Message createMessage(Message message) {
		return repository.save(message);
	}
}
