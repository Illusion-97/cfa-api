package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.BaseEntityDto;
import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.tools.SaveInvalidException;

public interface GenericService<TDto extends BaseEntityDto > {
	/**
	 * 
	 * @param id de l'entrée en base de donnée
	 * @return DTO de l'objet concerné
	 */
    TDto getById(long id);
    
    /**
     * 
     * @param DTO de l'entité à sauvegarder
     * @return DTO de l'entité une fois sauvegardé
     * @throws SaveInvalidException
     */
    TDto saveOrUpdate(TDto tDto) throws SaveInvalidException;
    
    /**
     * 
     * @param champs de recherche
     * @return un objet countDTO qui contient un pbject de type Long
     */
    CountDto count(String search);
    
    /**
     * 
     * @param id de l'entité à supprimer
     */
    void delete(long id);
}