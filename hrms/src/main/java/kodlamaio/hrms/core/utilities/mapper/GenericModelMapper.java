package kodlamaio.hrms.core.utilities.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class GenericModelMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public <E,T> List<T> convert(List<E> entityList,Class<T> dto) {
		return entityList.stream().map(entity->this.modelMapper.map(entityList, dto)).collect(Collectors.toList());
	}
}
