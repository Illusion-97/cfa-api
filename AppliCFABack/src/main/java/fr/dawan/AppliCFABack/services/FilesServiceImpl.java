package fr.dawan.AppliCFABack.services;

import java.io.File;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
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

	@Override
	public ResponseEntity<File> getFileByDirectoryAndId(String directory, long id, String name) {
		
		File workingDirectoryFile = new File("./src/main/resources/files/" + directory + "/" + id + "/" + name);
		
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
				"attachment; filename=\"" + file.getFilename() + "\"").body(file);
	}

}
