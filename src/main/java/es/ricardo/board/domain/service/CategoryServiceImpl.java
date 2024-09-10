package es.ricardo.board.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import es.ricardo.board.domain.model.Category;
import es.ricardo.board.port.inbound.rest.CategoryService;
import es.ricardo.board.port.outbound.repository.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService{
	
	private CategoryRepository repository;
	
	public CategoryServiceImpl(CategoryRepository repository) {
		this.repository = repository;
	}
	
	public Category getCategory(Long id) {
		return repository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, 
		        "No category was found"));
	}

	public List<Category> getCategories() {
		return repository.findAll();
	}

	public Category createCategory(Category category) {
		return repository.save(category);
	}
}
