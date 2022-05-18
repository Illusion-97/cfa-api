package fr.dawan.AppliCFABack.dto;

import fr.dawan.AppliCFABack.entities.*;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;
import fr.dawan.AppliCFABack.entities.Note;
/**
 *
 * @author Valentin C, Feres BG.
 * @see fr.dawan.appliCFABack.dto
 * @since 1.0
 * @version 1.0
 * @return classe de convertion d'une entité vers DTO & vice-versa
 */
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DtoTools {

    private static ModelMapper myMapper = new ModelMapper();

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

        if (obj == null) return null;
        return myMapper.map(obj, clazz);
    }

    public static <TSource, TDestination> TDestination convert(TSource obj, Class<TDestination> clazz, Converter converter) {
        ModelMapper mapper = new ModelMapper();
        mapper.createTypeMap(obj.getClass(), clazz).setConverter(converter);
        return mapper.map(obj, clazz);
    }

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

    /**
     * permet de mapper un objet Examen en LivretEvaluationDto (dto customisé) -
     * return l'objet dto custom mappé
     */
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

    /**
     * méthode appelée dans l'ExamenServiceImpl
     * @param examen
     * @return l'objet dto custom mappé
     */
    public LivretEvaluationDto ExamenToLivretEvaluationDto(Examen examen) {
        return convert(examen, LivretEvaluationDto.class, ExamenToLivretEvaluationDtoConverter);
    }


}