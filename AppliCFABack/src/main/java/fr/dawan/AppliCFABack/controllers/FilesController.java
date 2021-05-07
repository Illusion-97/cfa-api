package fr.dawan.AppliCFABack.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.dawan.AppliCFABack.services.FilesService;

@RestController
@RequestMapping("/AppliCFABack/files")
public class FilesController {

	@Autowired
	FilesService filesService;
	
	@GetMapping(value = "/{directory}/{id}", produces = "application/json")
	public String[] getAllNamesByDirectoryAndId(@PathVariable("directory") String directory, @PathVariable("id") long id) {
				
		if(!directory.equals("promotions") && !directory.equals("utilisateurs"))
			return null;
		
		return filesService.getAllNamesByDirectoryAndId(directory, id);
	}
	
	@GetMapping(value = "/{directory}/{id}/{fileName}")
	public  void getFileByDirectoryAndId(
			 HttpServletRequest request, 
             HttpServletResponse response, 
             @PathVariable("directory") String directory, 
             @PathVariable("id") long id, 
             @PathVariable("fileName") String fileName) {
				
		if(!directory.equals("promotions") && !directory.equals("utilisateurs"))
			return ;
		
		String dataDirectory = request.getServletContext().getRealPath("./src/main/resources/files/" + directory + "/" + id);
        Path file = Paths.get(dataDirectory, fileName);
		
		if(!Files.exists(file))
			return ;	
		
		response.setContentType("application/pdf");
        response.addHeader("Content-Disposition", "attachment; filename="+fileName);
        try
        {
            Files.copy(file, response.getOutputStream());
            response.getOutputStream().flush();
        } 
        catch (IOException ex) {
            ex.printStackTrace();
        }
	}
}
