package es.ricardo.board.port.inbound.rest;

import java.util.List;

import es.ricardo.board.domain.model.Category;

public interface CategoryService {

	Category getCategory(Long id);
	
	List<Category> getCategories();
	
	Category createCategory(Category category);
}
