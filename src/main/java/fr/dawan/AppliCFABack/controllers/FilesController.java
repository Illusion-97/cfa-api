package fr.dawan.AppliCFABack.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import fr.dawan.AppliCFABack.services.FilesService;

@MultipartConfig
@RestController
@RequestMapping("/files")
public class FilesController {	
	
	@Autowired
	FilesService fileService;

	@Value("${app.storagefolder}")
	private String PARENT_DIRECTORY;
	
	private String users = "utilisateur";
	private String promotions = "promotions";
	private String projets = "projets";
	

	@GetMapping(value = "/{directory}/{id}", produces = "application/json")
	public String[] getAllNamesByDirectoryAndId(@PathVariable("directory") String directory,
			@PathVariable("id") long id) {

		if (!directory.equals(promotions) && !directory.equals(users) && !directory.equals(projets))
			return new String[0];
		
		return fileService.getAllNamesByDirectory(directory + "/" + id);	
	}
	@GetMapping(value = "/{directory}/{id}/{directory2}", produces = "application/json")
	public String[] getAllNamesByDirectoryAndIdAndDirectory(@PathVariable("directory") String directory,@PathVariable("directory2") String directory2,
			@PathVariable("id") long id) {

		if (!directory.equals(promotions) && !directory.equals(users) && !directory.equals(projets))
			return new String[0];
		
		return fileService.getAllNamesByDirectory(directory + "/" + id +"/"+directory2);	
	}

	@GetMapping(value = "/{directory}/{id}/{fileName}")
	public ResponseEntity<ByteArrayResource> getFileByDirectoryAndId(HttpServletResponse resonse,
			@PathVariable("directory") String directory, @PathVariable("id") long id,
			@PathVariable("fileName") String fileName) {

		if (!directory.equals(promotions) && !directory.equals(users) && !directory.equals(projets))
			return null;

		String workingDirectory = directory + "/" + id + "/";
		
		return fileService.getFile(workingDirectory, fileName);	

	}@GetMapping(value = "/{directory}/{id}/{directory2}/{fileName}")
	public ResponseEntity<ByteArrayResource> getFileByDirectoryAndIdAndDirectory(HttpServletResponse resonse,
			@PathVariable("directory") String directory,@PathVariable("directory2") String directory2, @PathVariable("id") long id,
			@PathVariable("fileName") String fileName) {

		if (!directory.equals(promotions) && !directory.equals(users) && !directory.equals(projets))
			return null;

		String workingDirectory = directory + "/" + id + "/" + directory2 + "/";
		
		return fileService.getFile(workingDirectory, fileName);	

	}
	

	@PostMapping(value = "/{directory}/{id}", consumes = "multipart/form-data")
	public String postFileByDirectoryAndId(@PathVariable("directory") String directory, @PathVariable("id") long id,
			@RequestParam("file") MultipartFile file) {

		String filePath = directory + "/" + id + "/";
		
		if(fileService.postFile(filePath, file))
			return "OK";
		else
			return "KO";
	}
	
	/*
	 * Enregistre un fichier dans une arborescence de deux fichier
	 * directory reste fix
	 * newDirectory : nom du dossier que l'on crée
	 * 
	 * */
	@PostMapping(value = "/test/{directory}/{newDirectory}", consumes = "multipart/form-data")
	public String postFileByDirectoryAndDirectory(@PathVariable("directory") String directory, 
			@RequestParam(name = "newDirectory") String newDirectory,
			@RequestParam("file") MultipartFile file) {

		String filePath = directory + "/" + newDirectory + "/";
		
		fileService.createDirectory(filePath);
		try {
			Files.createDirectories(Paths.get(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(fileService.postFile(filePath, file))
			return "OK";
		else
			return "KO";
	}

	@DeleteMapping(value = "/{directory}/{id}/{fileName}")
	public String deleteFileByDirectoryAndId(@PathVariable("directory") String directory, @PathVariable("id") long id,
			@PathVariable("fileName") String fileName) {

		String filePath = directory + "/" + id + "/" + fileName;

		if (fileService.deleteDirectoryWithContent(filePath))
			return "supression effectuée";
		else
			return "supression échouée";

	}
	@DeleteMapping(value = "/{directory}/{id}/{directory2}/{fileName}")
	public String deleteFileByDirectoryAndIdAndDirectory(@PathVariable("directory") String directory,@PathVariable("directory2") String directory2, @PathVariable("id") long id,
			@PathVariable("fileName") String fileName) {

		String filePath = directory + "/" + id + "/" + directory2+ "/"+ fileName;

		if (fileService.deleteDirectoryWithContent(filePath))
			return "supression effectuée";
		else
			return "supression échouée";

	}
}
