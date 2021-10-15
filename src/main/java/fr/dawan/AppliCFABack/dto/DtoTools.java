package fr.dawan.AppliCFABack.dto;

import org.modelmapper.ModelMapper;

public class DtoTools {
	
	private static ModelMapper myMapper = new ModelMapper(); 
	
	public static <TSource, TDestination> TDestination convert(TSource obj, Class<TDestination> clazz) {
		
		// TODO ajout de config pour personnaliser le mapping dto<>entity
		// myMapper.typeMap(Contact.class, ContactDto.class)
		// .addMappings(mapper->{
		// mapper.map(src->src.getName(), ContactDto::setName);
		// mapper.map(src->src.getEmail(), ContactDto::setEmail);
		// });

		//myMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		//myMapper.getConfiguration().setAmbiguityIgnored(true);
		
		if(obj == null)
			return null;
		
		return myMapper.map(obj, clazz);
	}
}