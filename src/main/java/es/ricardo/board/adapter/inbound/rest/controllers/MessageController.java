package es.ricardo.board.adapter.inbound.rest.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.ricardo.board.adapter.inbound.rest.dto.MessageDTO;
import es.ricardo.board.adapter.inbound.rest.mapper.MessageMapper;
import es.ricardo.board.port.inbound.rest.MessageService;

@RestController
@RequestMapping("/messages")
@CrossOrigin(origins = "http://localhost:4200")
public class MessageController {

	private MessageService service;
	private MessageMapper mapper;
	
	public MessageController(MessageService service, MessageMapper mapper) {
		this.service = service;
		this.mapper = mapper;
	}
	
	@GetMapping
	public List<MessageDTO> getMessages(@RequestParam Long category){
		return mapper.mapAsList(service.getMessages(category));
	}
	
	@PostMapping
	public MessageDTO createMessage(@RequestBody MessageDTO dto) {
		return mapper.map(service.createMessage(mapper.map(dto)));
	}
}
