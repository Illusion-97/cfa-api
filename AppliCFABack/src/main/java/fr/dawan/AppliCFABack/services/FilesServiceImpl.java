package fr.dawan.AppliCFABack.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FilesServiceImpl implements FilesService{

	@Value("${app.storagefolder}")
	private String PARENT_DIRECTORY;
	
	@Override
	public boolean createDirectory(String path) {
		
		Path test = Paths.get(PARENT_DIRECTORY + path);
		
		try {
			Files.createDirectories(test);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteDirectoryWithContent(String path) {
		File directory = new File(PARENT_DIRECTORY + path);
	
		return deleteDirectory(directory);
	}
	
	private boolean deleteDirectory(File directoryToBeDeleted) {
		File[] allContents = directoryToBeDeleted.listFiles();
	    if (allContents != null) {
	        for (File file : allContents) {
	            deleteDirectory(file);
	        }
	    }
	    return directoryToBeDeleted.delete();
	}

}
