package fr.dawan.AppliCFABack.controllers;

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

	@GetMapping(value = "/{directory}/{id}", produces = "application/json")
	public String[] getAllNamesByDirectoryAndId(@PathVariable("directory") String directory,
			@PathVariable("id") long id) {

		if (!directory.equals("promotions") && !directory.equals("utilisateurs") && !directory.equals("projets"))
			return null;
		
		return fileService.getAllNamesByDirectory(directory + "/" + id);	
	}
	@GetMapping(value = "/{directory}/{id}/{directory2}", produces = "application/json")
	public String[] getAllNamesByDirectoryAndIdAndDirectory(@PathVariable("directory") String directory,@PathVariable("directory2") String directory2,
			@PathVariable("id") long id) {

		if (!directory.equals("promotions") && !directory.equals("utilisateurs") && !directory.equals("projets"))
			return null;
		
		return fileService.getAllNamesByDirectory(directory + "/" + id +"/"+directory2);	
	}

	@GetMapping(value = "/{directory}/{id}/{fileName}")
	public ResponseEntity<ByteArrayResource> getFileByDirectoryAndId(HttpServletResponse resonse,
			@PathVariable("directory") String directory, @PathVariable("id") long id,
			@PathVariable("fileName") String fileName) {

		if (!directory.equals("promotions") && !directory.equals("utilisateurs") && !directory.equals("projets"))
			return null;

		String workingDirectory = directory + "/" + id + "/";
		
		return fileService.getFile(workingDirectory, fileName);	

	}@GetMapping(value = "/{directory}/{id}/{directory2}/{fileName}")
	public ResponseEntity<ByteArrayResource> getFileByDirectoryAndIdAndDirectory(HttpServletResponse resonse,
			@PathVariable("directory") String directory,@PathVariable("directory2") String directory2, @PathVariable("id") long id,
			@PathVariable("fileName") String fileName) {

		if (!directory.equals("promotions") && !directory.equals("utilisateurs") && !directory.equals("projets"))
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
	@PostMapping(value = "/{directory}/{id}/{directory2}", consumes = "multipart/form-data")
	public String postFileByDirectoryAndIdAndDirectory(@PathVariable("directory") String directory,@PathVariable("directory2") String directory2, @PathVariable("id") long id,
			@RequestParam("file") MultipartFile file) {

		String filePath = directory + "/" + id + "/" + directory2 + "/";
		fileService.createDirectory(filePath);
		
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
