package fr.dawan.AppliCFABack.services.generic;

import fr.dawan.AppliCFABack.dto.BaseEntityDto;
import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.entities.BaseEntity;
import fr.dawan.AppliCFABack.tools.SaveInvalidException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public class GenericServiceImpl<T extends BaseEntity, TDto extends BaseEntityDto> implements GenericService<TDto> {

	protected JpaRepository<T, Long> repository;
	protected Class<TDto> dtoClazz;
	protected Class<T> clazz;

	public GenericServiceImpl(JpaRepository<T, Long> repository, Class<TDto> dtoClazz, Class<T> clazz) {
		this.repository = repository;
		this.dtoClazz = dtoClazz;
		this.clazz = clazz;
	}

	/**
     * Obtient une entité par son identifiant.
     *
     * @param id L'identifiant de l'entité.
     * @return L'objet DTO correspondant à l'entité trouvée, ou null si non trouvé.
     */
    @Override
    public TDto getById(long id) {
        Optional<T> opt = repository.findById(id);
        return opt.map(entity -> DtoTools.convert(entity, dtoClazz)).orElse(null);
    }

    /**
     * Enregistre ou met à jour une entité.
     *
     * @param tDto L'objet DTO à enregistrer ou mettre à jour.
     * @return L'objet DTO correspondant à l'entité enregistrée ou mise à jour.
     * @throws SaveInvalidException En cas d'erreur lors de l'enregistrement.
     */
    @Override
    public TDto saveOrUpdate(TDto tDto) throws SaveInvalidException {
        T entity = DtoTools.convert(tDto, clazz);
        entity = repository.saveAndFlush(entity);
        return DtoTools.convert(entity, dtoClazz);
    }

    /**
     * Compte le nombre total d'entités.
     *
     * @param search La chaîne de recherche (peut être ignorée selon le contexte).
     * @return Un objet CountDto contenant le nombre total d'entités.
     */
    @Override
    public CountDto count(String search) {
        Long count = repository.count();
        return new CountDto(count);
    }

    /**
     * Supprime une entité par son identifiant.
     *
     * @param id L'identifiant de l'entité à supprimer.
     */
    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }
	
}
