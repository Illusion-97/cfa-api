package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.ExperienceProfessionnelleDto;

import java.util.List;

public interface ExperienceProfessionnelleService {
    List<ExperienceProfessionnelleDto> getAll();

    ExperienceProfessionnelleDto getById(long id);

    ExperienceProfessionnelleDto saveOrUpdate(ExperienceProfessionnelleDto epDto);

    void deleteById(long id);
}
