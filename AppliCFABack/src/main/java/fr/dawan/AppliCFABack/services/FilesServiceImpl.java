package fr.dawan.AppliCFABack.services;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FilesServiceImpl implements FilesService{

	@Value("${app.storagefolder}")
	private String PARENT_DIRECTORY;
	
	@Override
	public String[] getAllNamesByDirectoryAndId(String directory, long id) {
		
		File workingDirectoryFile = new File(PARENT_DIRECTORY + directory + "/" + id);
		
		if(!workingDirectoryFile.exists())
			return null;		
		
		return workingDirectoryFile.list();
	}

	@Override
	public String deleteFileByDirectoryAndId(String filePath) {
		
		File file = new File(PARENT_DIRECTORY + filePath);
		
		if(file.delete())
			return "supression effectuée";
		else
			return "supression échouée";
		
	}

}
