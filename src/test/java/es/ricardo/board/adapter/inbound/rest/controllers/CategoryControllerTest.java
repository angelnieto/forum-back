package es.ricardo.board.adapter.inbound.rest.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import es.ricardo.board.adapter.inbound.rest.dto.CategoryDTO;
import es.ricardo.board.adapter.inbound.rest.mapper.CategoryMapper;
import es.ricardo.board.adapter.inbound.rest.mapper.CategoryMapperImpl;
import es.ricardo.board.domain.model.Category;
import es.ricardo.board.port.inbound.rest.CategoryService;

@ExtendWith(MockitoExtension.class)
public class CategoryControllerTest {
	
	@Mock
	private CategoryService service;
	@Mock
	private CategoryMapper mapper;
	@InjectMocks
	private CategoryController controller = new CategoryController(service, mapper);
	
	private static CategoryMapper mapperImpl;
	
	private static Long ID_1 = 1l;
	private static String DEFAULT_DESCRIPTION = "description";
	
	@BeforeAll
	public static void init() {
		mapperImpl = new CategoryMapperImpl();
	}
	
	@Test
	void get_categories_test() {
		// Given
		List<Category> categories = List.of(createDummyCategory());
				
		// When
		when(service.getCategories()).thenReturn(categories);
		when(mapper.mapAsList(anyList())).thenReturn(mapperImpl.mapAsList(categories));
		
		List<CategoryDTO> list = controller.getCategories();
		
		//Then
		assertEquals(list.get(0).getDescription(), DEFAULT_DESCRIPTION);
		assertEquals(list.get(0).getId(), ID_1);
		
		verify(service, times(1)).getCategories();
		verify(mapper, times(1)).mapAsList(anyList());
	}
	
	@Test
	void create_category_test() {
		// Given
		Category category = createDummyCategory();
		CategoryDTO dto = mapperImpl.map(category);
		
		// When
		when(service.createCategory(any(Category.class))).thenReturn(category);
		when(mapper.map(any(CategoryDTO.class))).thenReturn(category);
		when(mapper.map(any(Category.class))).thenReturn(dto);
		
		CategoryDTO resultDTO = controller.createCategory(dto);
		
		// Then
		assertEquals(resultDTO.getDescription(), DEFAULT_DESCRIPTION);
		assertEquals(resultDTO.getId(), ID_1);
		
		verify(service, times(1)).createCategory(any(Category.class));
		verify(mapper, times(1)).map(any(CategoryDTO.class));
		verify(mapper, times(1)).map(any(Category.class));
	}

	
	private Category createDummyCategory() {
		Category category = new Category();
		category.setId(ID_1);
		category.setDescription(DEFAULT_DESCRIPTION);

		
		return category;
	}
	
	
	

}
