package es.ricardo.board.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import es.ricardo.board.domain.model.Category;
import es.ricardo.board.port.inbound.rest.CategoryService;
import es.ricardo.board.port.outbound.repository.CategoryRepository;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

	@Mock 
	private CategoryRepository repository;
	@InjectMocks
	private CategoryService service = new CategoryServiceImpl(repository);
	
	private static Long ID_1 = 1l;
	private static String DEFAULT_DESCRIPTION = "description";
	private static final Long ID_UNKNOWN = 0l;
	private static final String EXCEPTION_MESSAGE = "No category was found";
	
	
	@Test
	void get_categories_test() {
		// Given 
		Category category = createDummyCategory();
		List<Category> categories = List.of(category);
		
		// When 
		when(repository.findAll()).thenReturn(categories);
			
		List<Category> list =  service.getCategories();
				
		// Then
		assertEquals(list.get(0).getDescription(), DEFAULT_DESCRIPTION);
		assertEquals(list.get(0).getId(), ID_1);
		
		verify(repository, times(1)).findAll();
	}
	
	@Test
	void when_known_category_test() {
		// Given 
		Category category = createDummyCategory();
				
		// When 
		when(repository.findById(anyLong())).thenReturn(Optional.of(category));
			
		Category result =  service.getCategory(ID_1);
				
		// Then
		assertEquals(result.getDescription(), DEFAULT_DESCRIPTION);
		assertEquals(result.getId(), ID_1);
		
		verify(repository, times(1)).findById(anyLong());
	}
	
	@Test
	void when_unknown_category_test() {
		// Given 
				
		// When 
		when(repository.findById(anyLong())).thenReturn(Optional.empty());
			
		Exception exception = assertThrows(ResponseStatusException.class, () -> {
			service.getCategory(ID_UNKNOWN);
	    });  
				
		// Then
		assertTrue(exception.getMessage().contains(EXCEPTION_MESSAGE));
		
		verify(repository, times(1)).findById(anyLong());
	}
	
	@Test
	void create_category_test() {
		// Given
		Category category = createDummyCategory();
		
		// When
		when(repository.save(any(Category.class))).thenReturn(category);
		
		Category result = service.createCategory(category);
		
		// Then

		assertEquals(result.getId(), ID_1);
		assertEquals(result.getDescription(), DEFAULT_DESCRIPTION);
		
		verify(repository, times(1)).save(any(Category.class));
	}
	
	private Category createDummyCategory() {
		Category category = new Category();
		category.setId(ID_1);
		category.setDescription(DEFAULT_DESCRIPTION);

		
		return category;
	}
}
