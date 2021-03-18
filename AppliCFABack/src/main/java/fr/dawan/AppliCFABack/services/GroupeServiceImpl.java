package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.GroupeDto;
import fr.dawan.AppliCFABack.entities.Groupe;
import fr.dawan.AppliCFABack.repositories.GroupeRepository;

@Service
@Transactional
public class GroupeServiceImpl implements GroupeService {

	@Autowired
	GroupeRepository groupeRepository;

	@Override
	public List<GroupeDto> getAllGroupe() {
		List<Groupe> lst = groupeRepository.findAll();
		List<GroupeDto> res = new ArrayList<GroupeDto>();

		for (Groupe g : lst)
			res.add(DtoTools.convert(g, GroupeDto.class));

		return res;
	}

	@Override
	public GroupeDto getById(long id) {
		Optional<Groupe> g = groupeRepository.findById(id);

		if (g.isPresent())
			return DtoTools.convert(g.get(), GroupeDto.class);

		return null;
	}

	@Override
	public GroupeDto saveOrUpdate(GroupeDto g) {
		Groupe groupe = groupeRepository.saveAndFlush(DtoTools.convert(g, Groupe.class));
		return DtoTools.convert(groupe, GroupeDto.class);
	}

	@Override
	public void deleteById(long id) {
		groupeRepository.deleteById(id);

	}

}
