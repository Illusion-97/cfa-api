package fr.dawan.AppliCFABack.services;


public interface GenericService<TDto> {

    TDto getById(long id);

    abstract TDto saveOrUpdate(TDto tDto) throws Exception;

    void delete(long id);
}