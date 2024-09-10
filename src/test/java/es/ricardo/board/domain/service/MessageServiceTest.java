package es.ricardo.board.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import es.ricardo.board.domain.model.Category;
import es.ricardo.board.domain.model.Message;
import es.ricardo.board.port.inbound.rest.MessageService;
import es.ricardo.board.port.outbound.repository.MessageRepository;

@ExtendWith(MockitoExtension.class)
public class MessageServiceTest {
	
	@Mock 
	private MessageRepository repository;
	@InjectMocks
	private MessageService service = new MessageServiceImpl(repository);
	
	private static Long ID_1 = 1l;
	private static String DEFAULT_AUTHOR = "author";
	private static Date DEFAULT_DATE = new Date(System.currentTimeMillis()); 
	private static final String DEFAULT_TEXT = "text";
	private static String DEFAULT_DESCRIPTION = "description";

	@Test
	void get_messages_test() {
		// Given
		Message message = createDummuMessage();
		
		// When
		when(repository.findAllByCategory_IdOrderByDateAsc(anyLong())).thenReturn(List.of(message));
		
		List<Message> list = service.getMessages(ID_1);
		
		// Then
		assertEquals(list.get(0).getAuthor(), DEFAULT_AUTHOR);
		assertEquals(list.get(0).getId(), ID_1);
		assertEquals(list.get(0).getCategory().getId(), ID_1);
		assertEquals(list.get(0).getDate(), DEFAULT_DATE);
		assertEquals(list.get(0).getText(), DEFAULT_TEXT);
		
		verify(repository, times(1)).findAllByCategory_IdOrderByDateAsc(anyLong());
	}
	

	@Test
	void create_message_test() {
		// Given
		Message message = createDummuMessage();
		
		// When
		when(repository.save(any(Message.class))).thenReturn(message);
		
		Message result = service.createMessage(message);
		
		// Then
		assertEquals(result.getAuthor(), DEFAULT_AUTHOR);
		assertEquals(result.getId(), ID_1);
		assertEquals(result.getCategory().getId(), ID_1);
		assertEquals(result.getCategory().getDescription(), DEFAULT_DESCRIPTION);
		assertEquals(result.getDate(), DEFAULT_DATE);
		assertEquals(result.getText(), DEFAULT_TEXT);
		
		verify(repository, times(1)).save(any(Message.class));
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
