package fr.dawan.AppliCFABack.dto;

import java.util.stream.Collectors;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import fr.dawan.AppliCFABack.entities.Etudiant;
import fr.dawan.AppliCFABack.entities.Examen;
import fr.dawan.AppliCFABack.entities.Note;

import java.util.List;
import java.util.stream.Collectors;

import fr.dawan.AppliCFABack.entities.Positionnement;
import fr.dawan.AppliCFABack.entities.Promotion;
import fr.dawan.AppliCFABack.entities.Utilisateur;
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

    private static ModelMapper myMapper = new ModelMapper();

    public static <TSource, TDestination> TDestination convert(TSource obj, Class<TDestination> clazz) {

        // TODO ajout de config pour personnaliser le mapping dto<>entity
        myMapper.typeMap(Note.class, NoteControleContinuDto.class).addMappings(mapper -> {
            mapper.map(src -> src.getId(), NoteControleContinuDto::setId);
            mapper.map(src -> src.getEtudiantNote().getId(), NoteControleContinuDto::setEtudiantId);
            mapper.map(src -> src.getExamen().getTitre(), NoteControleContinuDto::setExamen);
            mapper.map(src -> src.getExamen().getDateExamen(), NoteControleContinuDto::setDate);
            mapper.map(src -> src.getExamen().getPromotion().getNom(), NoteControleContinuDto::setPromotion);
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
    	ndto.setDescreption(niveau.getDescription());
		return ndto;
    	
    };

    Converter<Promotion, PromotionEtudiantDto> PromotionToPromotionEtudiantDtoConverter = context -> {

        Promotion p = context.getSource();
        //CursusEtudiantDto cDto = convert(c, CursusEtudiantDto.class);

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


    public PromotionEtudiantDto PromotionToPromotionEtudiantDto(Promotion promotion) {
        return convert(promotion, PromotionEtudiantDto.class, PromotionToPromotionEtudiantDtoConverter);
    };
    
    public NiveauDto NiveauToNiveauDto (Positionnement.Niveau niveau) {
    	
    	return enumConvert(niveau, NiveauDto.class, NiveauEnumToNiveauDtoConverter);
    }

    Converter<Examen, LivretEvaluationDto> ExamenToLivretEvaluationDtoConverter = context -> {
        Examen e = context.getSource();

        LivretEvaluationDto leDto = new LivretEvaluationDto();

        leDto.setPromotion(e.getPromotion().getNom());
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

    public LivretEvaluationDto ExamenToLivretEvaluationDto(Examen examen) {
        return convert(examen, LivretEvaluationDto.class, ExamenToLivretEvaluationDtoConverter);
    }


}