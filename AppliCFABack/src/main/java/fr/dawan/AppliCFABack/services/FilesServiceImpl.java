package fr.dawan.AppliCFABack.services;

import java.io.File;

import org.springframework.stereotype.Service;

@Service
public class FilesServiceImpl implements FilesService{

	@Override
	public String[] getAllNamesByDirectoryAndId(String directory, long id) {
		
		File workingDirectoryFile = new File("./src/main/resources/files/" + directory + "/" + id);
		
		if(!workingDirectoryFile.exists())
			return null;		
		
		return workingDirectoryFile.list();
	}

}
