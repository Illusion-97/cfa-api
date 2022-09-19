package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.BaseEntityDto;
import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.tools.SaveInvalidException;

public interface GenericService<TDto extends BaseEntityDto > {

    TDto getById(long id);

    TDto saveOrUpdate(TDto tDto) throws SaveInvalidException;

    CountDto count(String search);
    
    void delete(long id);
}