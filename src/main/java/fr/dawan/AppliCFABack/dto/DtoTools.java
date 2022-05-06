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

        if (obj == null) return null;
        return myMapper.map(obj, clazz);
    }

    public static <TSource, TDestination> TDestination convert(TSource obj, Class<TDestination> clazz, Converter converter) {
        ModelMapper mapper = new ModelMapper();
        mapper.createTypeMap(obj.getClass(), clazz).setConverter(converter);
        return mapper.map(obj, clazz);
    }

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
    }


}