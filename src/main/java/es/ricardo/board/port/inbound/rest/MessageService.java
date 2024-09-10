package es.ricardo.board.port.inbound.rest;

import java.util.List;

import es.ricardo.board.domain.model.Message;

public interface MessageService {

	List<Message> getMessages(Long category);
	
	Message createMessage(Message message);
}
