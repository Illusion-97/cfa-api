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
import fr.dawan.AppliCFABack.dto.FormateurDto;
import fr.dawan.AppliCFABack.dto.FormationDto;
import fr.dawan.AppliCFABack.dto.InterventionDto;
import fr.dawan.AppliCFABack.dto.UtilisateurRoleDto;
import fr.dawan.AppliCFABack.entities.Formateur;
import fr.dawan.AppliCFABack.entities.Formation;
import fr.dawan.AppliCFABack.entities.Intervention;
import fr.dawan.AppliCFABack.entities.UtilisateurRole;
import fr.dawan.AppliCFABack.repositories.FormateurRepository;

@Service
@Transactional
public class FormateurServiceImpl implements FormateurService {
	@Autowired
	private FormateurRepository formateurRepository;

	@Override
	public List<FormateurDto> getAll() {
		List<Formateur> lst = formateurRepository.findAll();
		List<FormateurDto> lstDto = new ArrayList<FormateurDto>();
		for (Formateur f : lst) {
			lstDto.add(DtoTools.convert(f, FormateurDto.class));
		}
		return lstDto;
	}

	@Override
	public List<FormateurDto> getAllByPage(int page, int size) {
		List<Formateur> lst = formateurRepository.findAll(PageRequest.of(page, size)).get()
				.collect(Collectors.toList());
		List<FormateurDto> lstDto = new ArrayList<FormateurDto>();
		for (Formateur f : lst) {
			lstDto.add(DtoTools.convert(f, FormateurDto.class));
		}
		return lstDto;
	}

	@Override
	public FormateurDto getById(long id) {
		Optional<Formateur> i = formateurRepository.findById(id);
		if (i.isPresent())
			return DtoTools.convert(i.get(), FormateurDto.class);

		return null;
	}

	@Override
	public FormateurDto saveOrUpdate(FormateurDto fDto) {
		Formateur formateur = DtoTools.convert(fDto, Formateur.class);
		formateur = formateurRepository.saveAndFlush(formateur);
		return DtoTools.convert(formateur, FormateurDto.class);
	}

	@Override
	public void deleteById(long id) {
		formateurRepository.deleteById(id);
	}

	@Override
	public List<FormateurDto> getAllWithObject() {
		List<Formateur> lstFor = formateurRepository.findAll();
		List<FormateurDto> lstDto = new ArrayList<FormateurDto>();

		for (Formateur formateur : lstFor) {

			FormateurDto formateurDto = DtoTools.convert(formateur, FormateurDto.class);

			List<Intervention> lstInter = formateur.getInterventions();
			List<InterventionDto> lstInterDto = new ArrayList<InterventionDto>();
			for (Intervention intervention : lstInter) {
				if (intervention != null)
					lstInterDto.add(DtoTools.convert(intervention, InterventionDto.class));
			}
			formateurDto.setInterventionsDto(lstInterDto);
			lstDto.add(formateurDto);
		}
		return lstDto;
	}

	// Recupere les interventions par rapport a l'id du formateur
	@Override
	public FormateurDto getInterventionByIdFormateur(long id) {
		// methode findById dans le repository
		Optional<Formateur> i = formateurRepository.findById(id);
		if (i.isPresent()) {
			// On convertis un Optional<Formateur> en Formateur
			FormateurDto formateurDto = DtoTools.convert(i.get(), FormateurDto.class);

			// ici je recupere les intervention par rapport au formateur
			List<Intervention> lstInt = i.get().getInterventions();
			/**
			 * les interventions sont de type Intervention. Comme on passe par les Dto on
			 * doit convertir lstInt qui est de type Intervention en InterventionDto
			 **/
			List<InterventionDto> lstIntDto = new ArrayList<InterventionDto>();
			for (Intervention inter : lstInt) {
				/**
				 * La boucle va me permettre de recup les interventionDto du formateur. J'ai
				 * besoin aussi de recup les formation lier aux intervention du formateur.
				 * Pareil que par rapport aux intervention, je convertis la Formation en
				 * FormationDto
				 * 
				 */
				if (inter != null) {
					Formation formation = inter.getFormation();
					FormationDto formationDto = DtoTools.convert(formation, FormationDto.class);
					InterventionDto interDto = DtoTools.convert(inter, InterventionDto.class);
					interDto.setFormationDto(formationDto);
					lstIntDto.add(interDto);
				}
			}

			List<UtilisateurRole> lstRole = i.get().getRoles();
			List<UtilisateurRoleDto> lstRoleDto = new ArrayList<UtilisateurRoleDto>();
			for (UtilisateurRole role : lstRole) {
				if (role != null)
					lstRoleDto.add(DtoTools.convert(role, UtilisateurRoleDto.class));
			}
			formateurDto.setRolesDto(lstRoleDto);
			formateurDto.setInterventionsDto(lstIntDto);
			return formateurDto;
		}

		return null;
	}

}
