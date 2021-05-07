package fr.dawan.AppliCFABack.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.AppliCFABack.services.FilesService;
import fr.dawan.AppliCFABack.tools.MediaTypeUtils;

@RestController
@RequestMapping("/AppliCFABack/files")
public class FilesController {

	@Autowired
	FilesService filesService;
	
	@Autowired
    private ServletContext servletContext;
	
	@Value("${app.storagefolder}")
	private String PARENT_DIRECTORY;
	
	@GetMapping(value = "/{directory}/{id}", produces = "application/json")
	public String[] getAllNamesByDirectoryAndId(@PathVariable("directory") String directory, @PathVariable("id") long id) {
				
		if(!directory.equals("promotions") && !directory.equals("utilisateurs"))
			return null;
		
		return filesService.getAllNamesByDirectoryAndId(directory, id);
	}
	
	@GetMapping(value = "/{directory}/{id}/{fileName}")
	public  ResponseEntity<ByteArrayResource> getFileByDirectoryAndId(
			 HttpServletResponse resonse,
             @PathVariable("directory") String directory, 
             @PathVariable("id") long id, 
             @PathVariable("fileName") String fileName) throws IOException {
				
		System.out.println("################# on entre");
		
		if(!directory.equals("promotions") && !directory.equals("utilisateurs"))
			return null;
		
		String workingDirectory = directory + "/" + id + "/";		
		MediaType mediaType = MediaTypeUtils.getMediaTypeForFileName(this.servletContext, fileName);
				
		Path path = Paths.get(PARENT_DIRECTORY + workingDirectory + fileName);
        byte[] data = Files.readAllBytes(path);
        
        System.out.println("data length = " + data.length);
        
        
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
