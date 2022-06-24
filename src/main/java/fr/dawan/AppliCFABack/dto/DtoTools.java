package fr.dawan.AppliCFABack.dto;

import java.util.stream.Collectors;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import fr.dawan.AppliCFABack.dto.customdtos.EtudiantInfoInterventionDto;
import fr.dawan.AppliCFABack.dto.customdtos.LivretEvaluationDto;
import fr.dawan.AppliCFABack.dto.customdtos.NoteControleContinuDto;
import fr.dawan.AppliCFABack.dto.customdtos.PlanningEtudiantDto;
import fr.dawan.AppliCFABack.dto.customdtos.PromotionEtudiantDto;
import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.entities.Examen;

import fr.dawan.AppliCFABack.entities.Formation;

import fr.dawan.AppliCFABack.entities.Intervention;
import fr.dawan.AppliCFABack.entities.Note;
import fr.dawan.AppliCFABack.entities.Positionnement;
import fr.dawan.AppliCFABack.entities.Promotion;
import fr.dawan.AppliCFABack.entities.Utilisateur;
import fr.dawan.AppliCFABack.tools.DateConverter;
/**
*
* @author Valentin C, Feres BG.
* @see fr.dawan.appliCFABack.dto
* @since 1.0
* @version 1.0
* @return classe de convertion d'une entité vers DTO & vice-versa
*/
@Component
public class DtoTools {

    private static final ModelMapper myMapper = new ModelMapper();

    /**
     * @param obj
     * @param clazz
     * @param <TSource>
     * @param <TDestination>
     * @return l'objet de l'entité mappé en Dto de manière générique ou avec un Dto custom
     *          ou
     *          un objet dto mappé en un objet d'une entité
     */
    public static <TSource, TDestination> TDestination convert(TSource obj, Class<TDestination> clazz) {

        myMapper.typeMap(Note.class, NoteControleContinuDto.class).addMappings(mapper -> {
            mapper.map(src -> src.getId(), NoteControleContinuDto::setId);
            mapper.map(src -> src.getEtudiantNote().getId(), NoteControleContinuDto::setEtudiantId);
            mapper.map(src -> src.getExamen().getTitre(), NoteControleContinuDto::setExamen);
            mapper.map(src -> src.getExamen().getDateExamen(), NoteControleContinuDto::setDate);
//            mapper.map(src -> src.getExamen().getPromotions().getNom(), NoteControleContinuDto::setPromotion);
        });
        
       

//        myMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//        myMapper.getConfiguration().setAmbiguityIgnored(true);
//        myMapper.typeMap(NoteDto.class, Note.class).addMappings(mapper -> {
//            mapper.map(src -> src.getId(), Note::setId);
//            mapper.map(src -> src.getNoteObtenue(), Note::setNoteObtenue);
//            mapper.map(src -> src.getEtudiantNoteId(), (dest, v) -> dest.getEtudiantNote().setId((long) v));
//
//        });

        myMapper.typeMap(Etudiant.class, EtudiantInfoInterventionDto.class).addMappings(m ->{
        	m.map(src -> src.getId(), EtudiantInfoInterventionDto::setIdEtudiant);
        	m.map(src -> src.getUtilisateur().getNom(), EtudiantInfoInterventionDto::setNom);
        	m.map(src -> src.getUtilisateur().getPrenom(), EtudiantInfoInterventionDto::setPrenom);
        });
        if (obj == null) return null;
        return myMapper.map(obj, clazz);
    }
    

    public static <TSource, TDestination> TDestination convert(TSource obj, Class<TDestination> clazz, Converter converter) {
        ModelMapper mapper = new ModelMapper();
        mapper.createTypeMap(obj.getClass(), clazz).setConverter(converter);
        return mapper.map(obj, clazz);
    }

    @SuppressWarnings("unchecked")
	public static <TSource, TDestination> TDestination enumConvert(TSource Enum, Class<TDestination> clazz, Converter converter) {
        ModelMapper mapper = new ModelMapper();
        mapper.createTypeMap(Enum, clazz).setConverter(converter);
        return mapper.map(Enum, clazz);
    }
    Converter<Positionnement.Niveau, NiveauDto> NiveauEnumToNiveauDtoConverter = context -> {

    	Positionnement.Niveau niveau =  context.getSource();

    	NiveauDto ndto = new NiveauDto();
    	ndto.setValeur(niveau.getValeur());
    	ndto.setCodeCouleur(niveau.getCodeCouleur());
    	ndto.setDescription(niveau.getDescription());
		return ndto;

    };

    
    Converter<PromotionOrInterventionDG2Dto, Promotion> PromotionDG2DtoToPromotionConverter = context -> {
    	PromotionOrInterventionDG2Dto pDG2 = context.getSource();
    	Promotion promo = new Promotion();
    	promo.setIdDg2(pDG2.getId());
    	promo.setDateDebut(DateConverter.convertToLocalDate(pDG2.getDateStart()));
    	promo.setDateFin(DateConverter.convertToLocalDate(pDG2.getDateEnd()));
    	promo.setNom(pDG2.getSlug());
    	return promo;
    };
    
    Converter<FormationDG2Dto, Formation> FormationDG2DtoToFormationConverter = context -> {
    	FormationDG2Dto fDG2 = context.getSource();
    	Formation formation = new Formation();
    	formation.setTitre(fDG2.getTitle());
    	formation.setIdDg2(fDG2.getId());
    	formation.setSlug(fDG2.getSlug());
    	formation.setDuration(fDG2.getDuration());
    	formation.setObjectif(fDG2.getObjectives());
    	formation.setPrerequis(fDG2.getPrerequisites());
    	formation.setPlan(fDG2.getPlan());
    	return formation;
    };
    
    Converter<PromotionOrInterventionDG2Dto, Intervention> PromotionDG2DtoToInterventionConverter = context -> {
    	PromotionOrInterventionDG2Dto pDG2 = context.getSource();
    	Intervention intervention = new Intervention();

    	intervention.setIdDg2(pDG2.getId());
    	intervention.setDateDebut(DateConverter.convertToLocalDate(pDG2.getDateStart()));
    	intervention.setDateFin(DateConverter.convertToLocalDate(pDG2.getDateEnd()));
    	return intervention;
    };

    
    /**
     * permet de mapper un objet Promotion en PromotionEtudiantDto (dto customisé) -
     * return l'objet dto custom mappé
     */
    Converter<Promotion, PromotionEtudiantDto> PromotionToPromotionEtudiantDtoConverter = context -> {

        Promotion p = context.getSource();
        PromotionEtudiantDto cDto = new PromotionEtudiantDto();

        cDto.setCursusTitre(p.getCursus().getTitre());
        cDto.setCursusDescription(p.getCursus().getDescription());
        cDto.setCursusDuree(p.getCursus().getDuree());
        cDto.setNom(p.getNom());
        cDto.setDateDebut(p.getDateDebut());
        cDto.setDateFin(p.getDateFin());
        cDto.setPlanningsEtudiantDto(p.getInterventions().stream().map(i -> {
            Utilisateur u = i.getFormateurs().get(0).getUtilisateur();
            String identity = u.getPrenom() + " " + u.getNom();
            return new PlanningEtudiantDto(i.getDateDebut(), i.getDateFin(), i.getFormation().getTitre(), identity);
        }).collect(Collectors.toList()));
        return cDto;
    };

    /**
     * méthode appelée dans le PromotionServiceImpl
     * @param promotion
     * @return l'objet dto custom mappé
     */
    public PromotionEtudiantDto PromotionToPromotionEtudiantDto(Promotion promotion) {
        return convert(promotion, PromotionEtudiantDto.class, PromotionToPromotionEtudiantDtoConverter);
    }

    public NiveauDto NiveauToNiveauDto (Positionnement.Niveau niveau) {

    	return enumConvert(niveau, NiveauDto.class, NiveauEnumToNiveauDtoConverter);
    }
    
    public Promotion PromotionOrInterventionDG2DtoToPromotion(PromotionOrInterventionDG2Dto promoDG2Dto) {
    	return convert(promoDG2Dto, Promotion.class, PromotionDG2DtoToPromotionConverter);
    }
    
    public Formation FormationDG2DtoToFormation(FormationDG2Dto formationDG2Dto) {
    	return convert(formationDG2Dto, Formation.class, FormationDG2DtoToFormationConverter);
    }

    public Intervention PromotionOrInterventionDG2DtoToIntervention(PromotionOrInterventionDG2Dto promoDG2Dto) {
    	return convert(promoDG2Dto, Intervention.class, PromotionDG2DtoToInterventionConverter);
    }
    /**
     * permet de mapper un objet Examen en LivretEvaluationDto (dto customisé) -
     * return l'objet dto custom mappé
     */
    Converter<Examen, LivretEvaluationDto> ExamenToLivretEvaluationDtoConverter = context -> {

        Examen e = context.getSource();
        LivretEvaluationDto leDto = new LivretEvaluationDto();

//        leDto.setPromotion(e.getPromotion().getNom());
        leDto.setExamen(e.getTitre());

        leDto.setCompetences(e.getCompetencesProfessionnelles().stream().map(c -> {
            String competence = c.getLibelle();
            return competence;
        }).collect(Collectors.toList()));

        leDto.setSatisfactions(e.getNotes().stream().map(s -> {
            Note.Satisfaction satisfaction = s.getSatisfaction();
            return satisfaction;
        }).collect(Collectors.toList()));

        leDto.setObservations(e.getNotes().stream().map(n -> {
            String observation = n.getObservation();
            return observation;
        }).collect(Collectors.toList()));
        return leDto;
    };

    /**
     * méthode appelée dans l'ExamenServiceImpl
     * @param examen
     * @return l'objet dto custom mappé
     */
    public LivretEvaluationDto ExamenToLivretEvaluationDto(Examen examen) {
        return convert(examen, LivretEvaluationDto.class, ExamenToLivretEvaluationDtoConverter);
    }





}