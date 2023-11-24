package fr.dawan.AppliCFABack.services;


import fr.dawan.AppliCFABack.dto.SifaDto;

import java.util.List;

public interface SifaService {
    SifaDto getById(long id);

    List<SifaDto> getAll();

    void deleteById(long id);

    SifaDto saveOrUpdate(SifaDto rDto);
    void generateTabler();
}
