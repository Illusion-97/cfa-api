package fr.dawan.AppliCFABack.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import fr.dawan.AppliCFABack.dto.CountDto;
import fr.dawan.AppliCFABack.dto.DtoTools;
import fr.dawan.AppliCFABack.dto.FormateurDto;
import fr.dawan.AppliCFABack.dto.FormationDto;
import fr.dawan.AppliCFABack.dto.InterventionDto;
import fr.dawan.AppliCFABack.entities.Formateur;
import fr.dawan.AppliCFABack.entities.Intervention;
import fr.dawan.AppliCFABack.mapper.DtoMapper;
import fr.dawan.AppliCFABack.mapper.DtoMapperImpl;
import fr.dawan.AppliCFABack.repositories.FormateurRepository;
import fr.dawan.AppliCFABack.repositories.InterventionRepository;

@Service
@Transactional
public class FormateurServiceImpl implements FormateurService {
	@Autowired
	private FormateurRepository formateurRepository;
	@Autowired
	InterventionRepository interventionRepository;

	@Autowired
	private DtoMapper mapper = new DtoMapperImpl();

	@Override
	public List<FormateurDto> getAll() {
		List<Formateur> lst = formateurRepository.findAll();
		List<FormateurDto> lstDto = new ArrayList<FormateurDto>();
		for (Formateur f : lst) {
			lstDto.add(mapper.FormateurToFormateurDto(f));
		}
		return lstDto;
	}

	@Override
	public List<FormateurDto> getAllByPage(int page, int size) {
		List<Formateur> lst = formateurRepository.findAll(PageRequest.of(page, size)).get()
				.collect(Collectors.toList());
		List<FormateurDto> lstDto = new ArrayList<FormateurDto>();
		for (Formateur f : lst) {
			lstDto.add(mapper.FormateurToFormateurDto(f));
		}
		return lstDto;
	}

	@Override
	public List<FormateurDto> getAllByPageWithKeyword(int page, int size, String search) {
		List<Formateur> lstFor = formateurRepository
				.findAllByPrenomContainingOrNomContainingAllIgnoreCase(search, search, PageRequest.of(page, size)).get()
				.collect(Collectors.toList());
		List<FormateurDto> lstDto = new ArrayList<FormateurDto>();

		for (Formateur f : lstFor) {
			lstDto.add(mapper.FormateurToFormateurDto(f));
		}
		return lstDto;
	}

	@Override
	public FormateurDto getById(long id) {
		Optional<Formateur> i = formateurRepository.findById(id);
		if (i.isPresent())
			return mapper.FormateurToFormateurDto(i.get());

		return null;
	}

	@Override
	public FormateurDto saveOrUpdate(FormateurDto fDto) {
		Formateur formateur = DtoTools.convert(fDto, Formateur.class);
		formateur = formateurRepository.saveAndFlush(formateur);
		return mapper.FormateurToFormateurDto(formateur);
	}

	@Override
	public void deleteById(long id) {
		Optional<Formateur> formateur = formateurRepository.findById(id);
		List<Intervention> lstInterventions = formateur.get().getInterventions();

		if (formateur.isPresent())
			for (Intervention interv : lstInterventions) {
				for (Formateur form : interv.getFormateurs()) {
					if (form.getId() == formateur.get().getId()) {

					}
				}
			}
		formateurRepository.deleteById(id);

	}

	@Override
	public List<FormateurDto> getAllWithObject() {
		List<Formateur> lstFor = formateurRepository.findAll();
		List<FormateurDto> lstDto = new ArrayList<FormateurDto>();

		for (Formateur formateur : lstFor) {

			FormateurDto formateurDto = mapper.FormateurToFormateurDto(formateur);

			List<Intervention> lstInter = formateur.getInterventions();
			List<InterventionDto> lstInterDto = new ArrayList<InterventionDto>();
			for (Intervention intervention : lstInter) {
				if (intervention != null)
					lstInterDto.add(mapper.InterventionToInterventionDto(intervention));
			}
			formateurDto.setInterventionsDto(lstInterDto);
			lstDto.add(formateurDto);
		}
		return lstDto;
	}

	@Override // nb de formateur
	public CountDto count(String search) {
		return new CountDto(formateurRepository.countByPrenomContainingOrNomContainingAllIgnoreCase(search, search));
	}

//	// Recupere les interventions par rapport a l'id du formateur
//	@Override
//	public FormateurDto getInterventionByIdFormateur(long id) {
//		// methode findById dans le repository
//		Optional<Formateur> i = formateurRepository.findById(id);
//		if (i.isPresent()) {
//			// On convertis un Optional<Formateur> en Formateur
//			FormateurDto formateurDto = DtoTools.convert(i.get(), FormateurDto.class);
//
//			// ici je recupere les intervention par rapport au formateur
//			List<Intervention> lstInt = i.get().getInterventions();
//			/**
//			 * les interventions sont de type Intervention. Comme on passe par les Dto on
//			 * doit convertir lstInt qui est de type Intervention en InterventionDto
//			 **/
//			List<InterventionDto> lstIntDto = new ArrayList<InterventionDto>();
//			for (Intervention inter : lstInt) {
//				/**
//				 * La boucle va me permettre de recup les interventionDto du formateur. J'ai
//				 * besoin aussi de recup les formation lié aux intervention du formateur. Pareil
//				 * que par rapport aux intervention, je convertis la Formation en FormationDto
//				 * 
//				 */
//				if (inter != null) {
//					Formation formation = inter.getFormation();
//					FormationDto formationDto = DtoTools.convert(formation, FormationDto.class);
//					InterventionDto interDto = DtoTools.convert(inter, InterventionDto.class);
//					interDto.setFormationDto(formationDto);
//					lstIntDto.add(interDto);
//				}
//			}
//
//			List<UtilisateurRole> lstRole = i.get().getRoles();
//			List<UtilisateurRoleDto> lstRoleDto = new ArrayList<UtilisateurRoleDto>();
//			for (UtilisateurRole role : lstRole) {
//				if (role != null)
//					lstRoleDto.add(DtoTools.convert(role, UtilisateurRoleDto.class));
//			}
//			formateurDto.setRolesDto(lstRoleDto);
//			formateurDto.setInterventionsDto(lstIntDto);
//			return formateurDto;
//		}
//
//		return null;
//	}

	/** ++++++++++++++ INTERVENTION FORMATEUR ++++++++++++++ **/
	@Override // affiche toute les interventions du formateur
	public List<InterventionDto> getAllInterventionsByFormateurIdPerPage(long id, int page, int size) {
		List<Intervention> lstIn = interventionRepository.findAllByFormateursId(id, PageRequest.of(page, size)).get()
				.collect(Collectors.toList());
		List<InterventionDto> lstInDto = new ArrayList<InterventionDto>();
		for (Intervention intervention : lstIn) {
			if (intervention != null) {
				InterventionDto interDto = mapper.InterventionToInterventionDto(intervention);

				FormationDto formDto = mapper.FormationToFormationDto(intervention.getFormation());

				interDto.setFormationDto(formDto);
				lstInDto.add(interDto);
			}
		}
		return lstInDto;
	}

	@Override // affiche toute les interventions du formateur + recherche par mot clé
	public List<InterventionDto> getAllInterventionsByFormateurIdPerPageByKeyword(long id, int page, int size,
			String search) {
		List<Intervention> lstIn = interventionRepository
				.findByFormateursIdAndFormationTitreContainingAllIgnoreCase(id, search, PageRequest.of(page, size))
				.get().collect(Collectors.toList());
		List<InterventionDto> lstInDto = new ArrayList<InterventionDto>();
		for (Intervention intervention : lstIn) {
			if (intervention != null) {
				InterventionDto interDto = mapper.InterventionToInterventionDto(intervention);

				FormationDto formDto = mapper.FormationToFormationDto(intervention.getFormation());

				interDto.setFormationDto(formDto);
				lstInDto.add(interDto);
			}
		}
		return lstInDto;
	}

	@Override // nb d'intervention du formateur avec recherche par mot clé
	public CountDto countInterventionById(long id, String search) {
		return new CountDto(
				formateurRepository.countByIdAndInterventionsFormationTitreContainingAllIgnoreCase(id, search));
	}

	@Override // nb interventions du formateur
	public CountDto countInterventionById(long id) {
		return new CountDto(interventionRepository.countByFormateursId(id));
	}


}
