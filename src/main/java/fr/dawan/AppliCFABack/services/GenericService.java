package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.CountDto;

public interface GenericService<TDto> {

    TDto getById(long id);

    abstract TDto saveOrUpdate(TDto tDto) throws Exception;

    CountDto count(String search);
    
    void delete(long id);
}