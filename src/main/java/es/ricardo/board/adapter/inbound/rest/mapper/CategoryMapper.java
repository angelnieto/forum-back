package es.ricardo.board.adapter.inbound.rest.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import es.ricardo.board.adapter.inbound.rest.dto.CategoryDTO;
import es.ricardo.board.domain.model.Category;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

	CategoryDTO map(Category entity);
	
	Category map(CategoryDTO dto);
	
	List<CategoryDTO> mapAsList(List<Category> list);

}
