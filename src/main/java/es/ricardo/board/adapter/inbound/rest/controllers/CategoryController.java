package es.ricardo.board.adapter.inbound.rest.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.ricardo.board.adapter.inbound.rest.dto.CategoryDTO;
import es.ricardo.board.adapter.inbound.rest.mapper.CategoryMapper;
import es.ricardo.board.domain.service.CategoryServiceImpl;
import es.ricardo.board.port.inbound.rest.CategoryService;

@RestController
@RequestMapping("/categories")
@CrossOrigin(origins = "http://localhost:4200")
public class CategoryController {

	private CategoryService service;
	private CategoryMapper mapper;
	
	public CategoryController(CategoryService service, CategoryMapper mapper) {
		this.service = service;
		this.mapper = mapper;
	}
		
	@GetMapping
	public List<CategoryDTO> getCategories(){
		return mapper.mapAsList(service.getCategories());
	}
	
	@PostMapping
	public CategoryDTO createCategory(@RequestBody CategoryDTO dto) {
		return mapper.map(service.createCategory(mapper.map(dto)));
	}
}
