package fr.dawan.AppliCFABack.dto;

import fr.dawan.AppliCFABack.dto.customdtos.*;
import fr.dawan.AppliCFABack.entities.*;
import fr.dawan.AppliCFABack.tools.DateConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
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

    private static final ModelMapper myMapper = DtoTools.initMapper();

    private static ModelMapper initMapper() {
        ModelMapper mapper = new ModelMapper();
//        myMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//        myMapper.getConfiguration().setAmbiguityIgnored(true);
//        myMapper.typeMap(NoteDto.class, Note.class).addMappings(mapper -> {
//            mapper.map(src -> src.getId(), Note::setId);
//            mapper.map(src -> src.getNoteObtenue(), Note::setNoteObtenue);
//            mapper.map(src -> src.getEtudiantNoteId(), (dest, v) -> dest.getEtudiantNote().setId((long) v));
//
//        });

//    	myMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        mapper.getConfiguration().setAmbiguityIgnored(true);

        mapper.typeMap(Etudiant.class, EtudiantInfoInterventionDto.class).addMappings(m ->{
            m.map(BaseEntity::getId, EtudiantInfoInterventionDto::setIdEtudiant);
            m.map(src -> src.getUtilisateur().getNom(), EtudiantInfoInterventionDto::setNom);
            m.map(src -> src.getUtilisateur().getPrenom(), EtudiantInfoInterventionDto::setPrenom);
        });

        mapper.typeMap(FormationDG2Dto.class, Formation.class).addMappings(m ->{
            m.map(FormationDG2Dto::getId, Formation::setIdDg2);
            m.map(FormationDG2Dto::getTitle, Formation::setTitre);
            m.map(FormationDG2Dto::getSlug, Formation::setSlug);
            m.map(FormationDG2Dto::getDuration, Formation::setDuration);
            m.map(FormationDG2Dto::getObjectives, Formation::setObjectif);
            m.map(FormationDG2Dto::getPrerequisites, Formation::setPrerequis);
            m.map(FormationDG2Dto::getPlan, Formation::setPlan);
        });
        return mapper;
    }

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
    Converter<Positionnement.Niveau, NiveauDto> niveauEnumToNiveauDtoConverter = context -> {

        Positionnement.Niveau niveau =  context.getSource();

        NiveauDto ndto = new NiveauDto();
        ndto.setValeur(niveau.getValeur());
        ndto.setCodeCouleur(niveau.getCodeCouleur());
        ndto.setDescription(niveau.getDescription());
        return ndto;

    };


    Converter<PromotionOrInterventionDG2Dto, Promotion> promotionDG2DtoToPromotionConverter = context -> {
        PromotionOrInterventionDG2Dto pDG2 = context.getSource();
        Promotion promo = new Promotion();
        promo.setIdDg2(pDG2.getId());
        promo.setDateDebut(DateConverter.convertToLocalDate(pDG2.getDateStart()));
        promo.setDateFin(DateConverter.convertToLocalDate(pDG2.getDateEnd()));
        promo.setNom(pDG2.getSlug());
        return promo;
    };

    Converter<PromotionOrInterventionDG2Dto, Intervention> promotionDG2DtoToInterventionConverter = context -> {
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
    Converter<Promotion, PromotionEtudiantDto> promotionToPromotionEtudiantDtoConverter = context -> {

        Promotion p = context.getSource();
        PromotionEtudiantDto cDto = new PromotionEtudiantDto();

        cDto.setCursusTitre(p.getCursus().getTitre());
        cDto.setCursusDuree(p.getCursus().getDuree());
        cDto.setNom(p.getNom());
        cDto.setDateDebut(p.getDateDebut());
        cDto.setDateFin(p.getDateFin());
        cDto.setPlanningsEtudiantDto(p.getInterventions().stream().map(i -> {
            Utilisateur u = i.getFormateur().getUtilisateur();
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
    public PromotionEtudiantDto promotionToPromotionEtudiantDto(Promotion promotion) {
        return convert(promotion, PromotionEtudiantDto.class, promotionToPromotionEtudiantDtoConverter);
    }



    public NiveauDto niveauToNiveauDto (Positionnement.Niveau niveau) {

        return enumConvert(niveau, NiveauDto.class, niveauEnumToNiveauDtoConverter);
    }

    public Promotion promotionOrInterventionDG2DtoToPromotion(PromotionOrInterventionDG2Dto promoDG2Dto) {
        return convert(promoDG2Dto, Promotion.class, promotionDG2DtoToPromotionConverter);
    }

    public Intervention promotionOrInterventionDG2DtoToIntervention(PromotionOrInterventionDG2Dto promoDG2Dto) {
        return convert(promoDG2Dto, Intervention.class, promotionDG2DtoToInterventionConverter);
    }
    /**
     * permet de mapper un objet Examen en LivretEvaluationDto (dto customisé) -
     * return l'objet dto custom mappé
     */
    Converter<Promotion, EtudiantLivretEvaluationDto> promotionToLivretEvaluationDtoConverter = context -> {

        Promotion p = context.getSource();
        EtudiantLivretEvaluationDto leDto = new EtudiantLivretEvaluationDto();

        leDto.setCursusId(p.getCursus().getId());
        leDto.setCursus(p.getCursus().getTitre());
        return leDto;
    };

    /**
     * méthode appelée dans l'ExamenServiceImpl
     * @return l'objet dto custom mappé
     */
    public EtudiantLivretEvaluationDto promotionToLivretEvaluationDto(Promotion promotion) {
        return convert(promotion, EtudiantLivretEvaluationDto.class, promotionToLivretEvaluationDtoConverter);
    }

    /**
     * permet de mapper un objet Note en NoteControleContinuDto (dto customisé) -
     * return l'objet dto custom mappé
     */
    Converter<Note, NoteControleContinuDto> noteToNoteControleContinuDtoConverter = context -> {
        Note n = context.getSource();
        NoteControleContinuDto nDto = new NoteControleContinuDto();

        nDto.setId(n.getId());

        nDto.setEtudiantId(n.getEtudiantNote().getId());

        nDto.setNoteObtenue(n.getNoteObtenue());

        nDto.setExamen(n.getExamen().getTitre());

        nDto.setDate(n.getExamen().getDateExamen());

        nDto.setPromotions(n.getExamen().getPromotions().stream().map(
                Promotion::getNom
        ).collect(Collectors.toSet()));

        return nDto;
    };

    /**
     * méthode appelée dans NoteServiceImpl
     * @param note
     * @return l'objet dto custom mappé
     */
    public NoteControleContinuDto noteToNoteControleContinuDto(Note note){
        return convert(note, NoteControleContinuDto.class, noteToNoteControleContinuDtoConverter);
    }

    Converter<Etudiant, AccueilEtudiantDto> etudiantToAccueilEtudiantDtoConverter = context -> {
        Etudiant e = context.getSource();
        AccueilEtudiantDto a = new AccueilEtudiantDto();

        a.setNom(e.getUtilisateur().getNom());
        a.setPrenom(e.getUtilisateur().getPrenom());
        a.setLogin(e.getUtilisateur().getLogin());
        a.setTelephone(e.getUtilisateur().getTelephone());
        a.setVille(e.getUtilisateur().getAdresse().getVille());
        a.setProjets(e.getGroupes().stream().map(groupeEtudiant -> {
            List<Projet> projetList = groupeEtudiant.getProjets();
            List<String> projetnoms = new ArrayList<>();
            for(Projet p : projetList) {
                projetnoms.add(p.getNom());
            }
            return projetnoms;
        }).collect(Collectors.toList()));
        a.setPromotion(e.getPromotions().stream().filter(promotion ->
                (promotion.getDateDebut().getYear() == ZonedDateTime.now().getYear() || promotion.getDateFin().getYear() == ZonedDateTime.now().getYear())).map(Promotion::getNom).findAny().orElse(null));
        a.setGroupes(e.getGroupes().stream().map(GroupeEtudiant::getNom).collect(Collectors.toList()));
        a.setManagerNom(Objects.requireNonNull(e.getPromotions().stream().filter(promotion ->
                (promotion.getDateDebut().getYear() == ZonedDateTime.now().getYear() || promotion.getDateFin().getYear() == ZonedDateTime.now().getYear())).map(Promotion::getCef).findAny().orElse(null)).getUtilisateur().getNom());
        a.setManagerPrenom(Objects.requireNonNull(e.getPromotions().stream().filter(promotion ->
                (promotion.getDateDebut().getYear() == ZonedDateTime.now().getYear() || promotion.getDateFin().getYear() == ZonedDateTime.now().getYear())).map(Promotion::getCef).findAny().orElse(null)).getUtilisateur().getPrenom());
        a.setManagerRole(Objects.requireNonNull(e.getPromotions().stream().filter(promotion ->
                (promotion.getDateDebut().getYear() == ZonedDateTime.now().getYear() || promotion.getDateFin().getYear() == ZonedDateTime.now().getYear())).map(Promotion::getCef).findAny().orElse(null)).getUtilisateur().getRoles().get(0).getIntitule());
        a.setManagerEmail(Objects.requireNonNull(e.getPromotions().stream().filter(promotion ->
                (promotion.getDateDebut().getYear() == ZonedDateTime.now().getYear() || promotion.getDateFin().getYear() == ZonedDateTime.now().getYear())).map(Promotion::getCef).findAny().orElse(null)).getUtilisateur().getLogin());
        a.setMembreEtudiantDtos(e.getGroupes().stream().map(groupeEtudiant -> groupeEtudiant.getEtudiants().stream().map(etudiant -> new MembreEtudiantDto(etudiant.getUtilisateur().getNom(), etudiant.getUtilisateur().getPrenom(), etudiant.getUtilisateur().getRoles().stream().map(UtilisateurRole::getIntitule)))));
        a.setProchainCours(Objects.requireNonNull(e.getPromotions().stream().filter(promotion ->
                (promotion.getDateDebut().getYear() == ZonedDateTime.now().getYear() || promotion.getDateFin().getYear() == ZonedDateTime.now().getYear())).map(Promotion::getInterventions).findAny().orElse(null)).stream().map(intervention -> {
            Utilisateur u = intervention.getFormateur().getUtilisateur();
            String identity = u.getPrenom() + " " + u.getNom();
            return new PlanningEtudiantDto(intervention.getDateDebut(), intervention.getDateFin(), intervention.getFormation().getTitre(), identity);
        }).collect(Collectors.toList()).stream().filter(planningEtudiantDto ->
                planningEtudiantDto.getInterventionDateDebut().isAfter(ZonedDateTime.now().toLocalDate())));
        return a;
    };

    public AccueilEtudiantDto etudiantToAccueilEtudiantDto(Etudiant etudiant) {
        return convert(etudiant, AccueilEtudiantDto.class, etudiantToAccueilEtudiantDtoConverter);
    }


    public static List<ExperienceProfessionnelle> convert(List<ExperienceProfessionnelleDto> dtos) {
        List<ExperienceProfessionnelle> experienceProfessionnelles = new ArrayList<>();
        for (ExperienceProfessionnelleDto dto : dtos) {
            ExperienceProfessionnelle experienceProfessionnelle = new ExperienceProfessionnelle();
            // Convertir les propriétés du DTO vers l'objet ExperienceProfessionnelle
            // ...
            experienceProfessionnelles.add(experienceProfessionnelle);
        }
        return experienceProfessionnelles;
    }


}
