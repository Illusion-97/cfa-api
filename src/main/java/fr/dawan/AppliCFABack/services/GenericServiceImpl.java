package fr.dawan.AppliCFABack.services;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.dawan.AppliCFABack.dto.BaseEntityDto;
import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.entities.BaseEntity;
import fr.dawan.AppliCFABack.tools.SaveInvalidException;

public class GenericServiceImpl<T extends BaseEntity, TDto extends BaseEntityDto> implements GenericService<TDto> {

	protected JpaRepository<T, Long> repository;
	protected Class<TDto> dtoClazz;
	protected Class<T> clazz;



	public GenericServiceImpl(JpaRepository<T, Long> repository, Class<TDto> dtoClazz, Class<T> clazz) {
		this.repository = repository;
		this.dtoClazz = dtoClazz;
		this.clazz = clazz;
	}



	@Override
	public TDto getById(long id) {
		 Optional<T> opt = repository.findById(id);
	        if (opt.isPresent()) 
	        	return DtoTools.convert(opt.get(), dtoClazz);

		return null;
	}



	@Override
	public TDto saveOrUpdate(TDto tDto) throws SaveInvalidException {
		T o = DtoTools.convert(tDto, clazz);
		o = repository.saveAndFlush(o);
		return DtoTools.convert(o, dtoClazz);
	}



	@Override
	public CountDto count(String search) {
		 Long count = repository.count();
	        return new CountDto(count);
	}



	@Override
	public void delete(long id) {
		repository.deleteById(id);
		
	}
	
}