package fr.dawan.AppliCFABack.services;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

@Service
public class FileService {

	@Value("${app.storagefolder}")
	private String filePath; 
	
	public Resource download(String fileName, String sousPath) throws Exception {
		try {
			String fullPath = filePath + sousPath;
			
			Path file = Paths.get(fullPath).resolve(fileName);
			
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists()) {
				return resource;
			}
			else {
				throw new Exception("File not found");
			}
			
		} catch (Exception e) {
			throw new Exception("File not found");
		}
		
		
	}
}
