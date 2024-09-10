package es.ricardo.board.adapter.inbound.rest.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import es.ricardo.board.adapter.inbound.rest.dto.MessageDTO;
import es.ricardo.board.adapter.inbound.rest.mapper.MessageMapper;
import es.ricardo.board.adapter.inbound.rest.mapper.MessageMapperImpl;
import es.ricardo.board.domain.model.Category;
import es.ricardo.board.domain.model.Message;
import es.ricardo.board.port.inbound.rest.MessageService;

@ExtendWith(MockitoExtension.class)
public class MessageControllerTest {
	
	
	@Mock
	private MessageService service;
	@Mock
	private MessageMapper mapper;
	@InjectMocks
	private MessageController controller = new MessageController(service, mapper);
	
	private static MessageMapper mapperImpl;
	
	private static Long ID_1 = 1l;
	private static String DEFAULT_AUTHOR = "author";
	private static Date DEFAULT_DATE = new Date(System.currentTimeMillis()); 
	private static final String DEFAULT_TEXT = "text";
	private static String DEFAULT_DESCRIPTION = "description";
	
	@BeforeAll
	public static void init() {
		mapperImpl = new MessageMapperImpl();
	}
	
	@Test
	void get_messages_test() {
		// Given 
		Message message = createDummuMessage();
		List<Message> messages = List.of(message);
		
		// When 
		when(service.getMessages(anyLong())).thenReturn(messages);
		when(mapper.mapAsList(anyList())).thenReturn(mapperImpl.mapAsList(messages));
		
		List<MessageDTO> list =  controller.getMessages(message.getId());
				
		// Then
		assertEquals(list.get(0).getAuthor(), DEFAULT_AUTHOR);
		assertEquals(list.get(0).getId(), ID_1);
		assertEquals(list.get(0).getCategory(), ID_1);
		assertEquals(list.get(0).getDate(), mapperImpl.map(message).getDate());
		assertEquals(list.get(0).getText(), DEFAULT_TEXT);
		
		verify(service, times(1)).getMessages(anyLong());
		verify(mapper, times(1)).mapAsList(anyList());
	}
	
	@Test
	void create_message_test() {
		// Given
		Message message = createDummuMessage();
		
		// When
		when(service.createMessage(any(Message.class))).thenReturn(message);
		when(mapper.map(any(MessageDTO.class))).thenReturn(message);
		when(mapper.map(any(Message.class))).thenReturn(mapperImpl.map(message));
		
		MessageDTO result = controller.createMessage(mapperImpl.map(message));
		
		// Then
		assertEquals(result.getAuthor(), DEFAULT_AUTHOR);
		assertEquals(result.getId(), ID_1);
		assertEquals(result.getCategory(), ID_1);
		assertEquals(result.getDate(), mapperImpl.map(message).getDate());
		assertEquals(result.getText(), DEFAULT_TEXT);
		
		verify(service, times(1)).createMessage(any(Message.class));
		verify(mapper, times(1)).map(any(MessageDTO.class));
	}

	private Message createDummuMessage() {
		Message message = new Message();
		message.setId(ID_1);
		message.setAuthor(DEFAULT_AUTHOR);
		message.setDate(DEFAULT_DATE);
		message.setText(DEFAULT_TEXT);
		message.setCategory(createDummyCategory());
		
		return message;
	}
	
	private Category createDummyCategory() {
		Category category = new Category();
		category.setId(ID_1);
		category.setDescription(DEFAULT_DESCRIPTION);

		
		return category;
	}
}
