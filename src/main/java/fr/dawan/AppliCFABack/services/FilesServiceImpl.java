package fr.dawan.AppliCFABack.services;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import fr.dawan.AppliCFABack.tools.MediaTypeUtils;

@Service
public class FilesServiceImpl implements FilesService{

	@Autowired
	private ServletContext servletContext;
	
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

	@Override
	public String[] getAllNamesByDirectory(String string) {
		File workingDirectoryFile = new File(PARENT_DIRECTORY + string);

		if (!workingDirectoryFile.exists())
			return null;
		
		return workingDirectoryFile.list();
	}
	

	@Override
	public boolean postFile(String filePath, MultipartFile file) {
		try {
			File f = new File(PARENT_DIRECTORY + filePath + file.getOriginalFilename());
			try (BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream(f))) {
				bw.write(file.getBytes());
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public ResponseEntity<ByteArrayResource> getFile(String workingDirectory, String fileName) {
		
		MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileName);

		Path path = Paths.get(PARENT_DIRECTORY + workingDirectory + fileName);
		byte[] data = null;

		try {
			data = Files.readAllBytes(path);
		} catch (IOException e) {
			e.printStackTrace();
		}

		ByteArrayResource resource = new ByteArrayResource(data);		
		
		return ResponseEntity.ok()
				// Content-Disposition
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + path.getFileName().toString())
				// Content-Type
				.contentType(mediaType) //
				// Content-Lengh
				.contentLength(data.length) //
				.body(resource);
		
	}

}
