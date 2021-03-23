package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.UtilisateurDto;
import fr.dawan.AppliCFABack.entities.Utilisateur;
import fr.dawan.AppliCFABack.repositories.UtilisateurRepository;

@Service
@Transactional
public class UtilisateurServiceImpl implements UtilisateurService {
	@Autowired
	private UtilisateurRepository utilisateurRepository;

	@Override
	public List<UtilisateurDto> getAll() {
		List<Utilisateur> users = utilisateurRepository.findAll();
		List<UtilisateurDto> res = new ArrayList<UtilisateurDto>();
		for (Utilisateur u : users) {
			res.add(DtoTools.convert(u, UtilisateurDto.class));
		}
		return res;
	}

	@Override
	public List<UtilisateurDto> getAll(int page, int size) {
		List<Utilisateur> users = utilisateurRepository.findAll(PageRequest.of(page, size)).get()
				.collect(Collectors.toList());
		List<UtilisateurDto> res = new ArrayList<UtilisateurDto>();
		for (Utilisateur u : users) {
			res.add(DtoTools.convert(u, UtilisateurDto.class));
		}
		return res;
	}

	@Override
	public UtilisateurDto getById(long id) {
		Optional<Utilisateur> userOpt = utilisateurRepository.findById(id);
		if (userOpt.isPresent())
			return DtoTools.convert(userOpt.get(), UtilisateurDto.class);
		return null;
	}

	@Override
	public UtilisateurDto getName(String name) {
		Utilisateur user = utilisateurRepository.findByName(name);
		if (user != null)
			return DtoTools.convert(user, UtilisateurDto.class);
		return null;
	}

	@Override
	public UtilisateurDto insertUpdate(UtilisateurDto uDto) {
		Utilisateur user = DtoTools.convert(uDto, Utilisateur.class);
		utilisateurRepository.saveAndFlush(user);
		return DtoTools.convert(user, UtilisateurDto.class);
	}

	@Override
	public void deleteById(long id) {
		utilisateurRepository.deleteById(id);
	}

	@Override
	public List<UtilisateurDto> findByAdresse(String ville) {
		List<Utilisateur> users = utilisateurRepository.findByAdresse(ville);
		List<UtilisateurDto> res = new ArrayList<UtilisateurDto>();
		for (Utilisateur u : users) {
			res.add(DtoTools.convert(u, UtilisateurDto.class));
		}
		return res;
	}

	@Override
	public List<UtilisateurDto> findByEntreprise(long idEntreprise) {
		List<Utilisateur> users = utilisateurRepository.findByEntreprise(idEntreprise);
		List<UtilisateurDto> res = new ArrayList<UtilisateurDto>();
		for (Utilisateur u : users) {
			res.add(DtoTools.convert(u, UtilisateurDto.class));
		}
		return res;
	}

}
