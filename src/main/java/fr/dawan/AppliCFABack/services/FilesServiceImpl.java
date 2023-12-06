package fr.dawan.AppliCFABack.services;

import fr.dawan.AppliCFABack.tools.MediaTypeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class FilesServiceImpl implements FilesService{

	@Autowired
	private ServletContext servletContext;
	
	@Value("${app.storagefolder}")
	private String myParentdirectory;
	
	private static final Logger logger = Logger.getGlobal();
	
	@Override
	public boolean createDirectory(String path) {
		
		Path test = Paths.get(myParentdirectory + path);
		
		try {
			Files.createDirectories(test);
			return true;
		} catch (IOException e) {
			logger.log(Level.WARNING, "create failed", e);
		}
		return false;
	}

	@Override
	public boolean deleteDirectoryWithContent(String path) {
		File directory = new File(myParentdirectory + path);
	
		return deleteDirectory(directory);
	}
	
	@Override
	public boolean deleteContentByDirectory(String path) {

		File fileInDirectory = new File(myParentdirectory + path );
		
		boolean deleted = fileInDirectory.delete();
		
		// Vérifier si la suppression a réussi
	    if (deleted) {
	        System.out.println("Le fichier  a été supprimé du répertoire ");
	        return true;
	    } else {
	        System.out.println("La suppression du fichier  a échoué dans le répertoire ");
	        return false;
	    }
		
	}
	
	/*
	 * Méthode servant de base aux autres méthodes delete
	 * 
	 * 
	 * */
	private boolean deleteDirectory(File directoryToBeDeleted) {
		File[] allContents = directoryToBeDeleted.listFiles();
	    if (allContents != null) {
	        for (File file : allContents) {
	            deleteDirectory(file);
	        }
	    }
	    return directoryToBeDeleted.delete();
	}
	/*
	 * 
	 * 
	 * */

	@Override
	public String[] getAllNamesByDirectory(String path) {
		File workingDirectoryFile = new File(myParentdirectory + path);

		if (!workingDirectoryFile.exists())
			return new String[0];
		
		return workingDirectoryFile.list();
	}
	

	//permet de créer un sous dossier ou de l'update + envoi d'un ou plusieurs fichiers a la fois
	
	@Override
	public boolean postFile(String filePath, List<MultipartFile> files) {
		try {
			for (MultipartFile file : files) {
				File f = new File(myParentdirectory + filePath + file.getOriginalFilename());

				try (BufferedOutputStream bw = new BufferedOutputStream(new FileOutputStream(f))) {
					bw.write(file.getBytes());
				}
			}
			
			return true;
		} catch (Exception e) {
			logger.log(Level.WARNING, "postFile failed", e);
			return false;
		}
	}

	@Override
	public ResponseEntity<ByteArrayResource> getFile(String workingDirectory, String fileName) {
		
		MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileName);

		Path path = Paths.get(myParentdirectory + workingDirectory + fileName);
		byte[] data = null;

		try {
			data = Files.readAllBytes(path);
		} catch (IOException e) {
			logger.log(Level.WARNING, "getFile failed", e);
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
