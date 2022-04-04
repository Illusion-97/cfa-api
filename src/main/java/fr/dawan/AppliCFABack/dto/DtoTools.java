package fr.dawan.AppliCFABack.dto;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import fr.dawan.AppliCFABack.entities.Note;

public class DtoTools {
	
	private static ModelMapper myMapper = new ModelMapper(); 
	
	public static <TSource, TDestination> TDestination convert(TSource obj, Class<TDestination> clazz) {
		
		// TODO ajout de config pour personnaliser le mapping dto<>entity
		// myMapper.typeMap(Contact.class, ContactDto.class)
		// .addMappings(mapper->{
		// mapper.map(src->src.getName(), ContactDto::setName);
		// mapper.map(src->src.getEmail(), ContactDto::setEmail);
		// });
		
//		myMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
//		myMapper.getConfiguration().setAmbiguityIgnored(true);
//		 myMapper.typeMap(NoteDto.class, Note.class)
//		 .addMappings(mapper->{
//		 mapper.map(src->src.getId(), Note::setId);
//		 mapper.map(src->src.getNoteObtenue(), Note::setNoteObtenue);
//		 mapper.map(src->src.getEtudiantNoteId(),  (dest , v) -> dest.getEtudiantNote().setId((long) v) );
//
//		 });

		if(obj == null)
			return null;
		
		return myMapper.map(obj, clazz);
	}
}