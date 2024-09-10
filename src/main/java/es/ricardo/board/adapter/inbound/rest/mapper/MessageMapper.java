package es.ricardo.board.adapter.inbound.rest.mapper;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import es.ricardo.board.adapter.inbound.rest.dto.MessageDTO;
import es.ricardo.board.domain.model.Message;

@Mapper(componentModel = "spring")
public interface MessageMapper {

	@Mapping(source="category.id", target="category")
	@Mapping(target = "date", source = "date", qualifiedByName = "dateToLocalDateTime")
	MessageDTO map(Message entity);

	@Mapping(target = "date", source = "date", qualifiedByName = "localDateTimeToDate")
	@Mapping(target="category.id", source="category")
	Message map(MessageDTO dto);
	
	List<MessageDTO> mapAsList(List<Message> list);
	
    @Named("localDateTimeToDate")
    default Date localDateTimeToDate(LocalDateTime localDateTime) {
    	Date date = null;
    	if(localDateTime != null) {
    		date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    	}
    	return date;
    }
    
    @Named("dateToLocalDateTime")
    default LocalDateTime dateToLocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

}
