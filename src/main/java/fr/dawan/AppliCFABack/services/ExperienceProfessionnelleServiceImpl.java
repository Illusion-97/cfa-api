package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.ExperienceProfessionnelleDto;
import fr.dawan.AppliCFABack.entities.ExperienceProfessionnelle;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.ExperienceProfessionnelleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ExperienceProfessionnelleServiceImpl implements ExperienceProfessionnelleService {

    @Autowired
    private ExperienceProfessionnelleRepository expProRepository;

    @Autowired
    private DtoMapper mapper = new DtoMapperImpl();

    @Override
    public List<ExperienceProfessionnelleDto> getAll() {
        List<ExperienceProfessionnelle> lst = expProRepository.findAll();
        List<ExperienceProfessionnelleDto> lstDto = new ArrayList<>();
        for (ExperienceProfessionnelle exp : lst) {
            lstDto.add(mapper.ExperienceProfessionnelleToExperienceProfessionnelleDto(exp));
        }
        return lstDto;
    }

    @Override
    public ExperienceProfessionnelleDto getById(long id) {
        Optional<ExperienceProfessionnelle> e = expProRepository.findById(id);
        if(e.isPresent())
            return DtoTools.convert(e.get(), ExperienceProfessionnelleDto.class);
        return null;
    }

    @Override
    public ExperienceProfessionnelleDto saveOrUpdate(ExperienceProfessionnelleDto epDto) {
        ExperienceProfessionnelle e = DtoTools.convert(epDto, ExperienceProfessionnelle.class);
        ExperienceProfessionnelle epInDb = expProRepository.saveAndFlush(e);
        return DtoTools.convert(epInDb, ExperienceProfessionnelleDto.class);
    }

    @Override
    public void deleteById(long id) {
        expProRepository.deleteById(id);
    }
}
