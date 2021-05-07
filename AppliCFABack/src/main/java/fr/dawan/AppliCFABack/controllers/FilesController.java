package fr.dawan.AppliCFABack.controllers;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
	
	@GetMapping(value = "/{directory}/{id}/{name}", produces = "application/json")
	@ResponseBody
	public  ResponseEntity<File> getFileByDirectoryAndId(@PathVariable("directory") String directory, @PathVariable("id") long id, @PathVariable("name") String name) {
				
		if(!directory.equals("promotions") && !directory.equals("utilisateurs"))
			return null;
				
		return filesService.getFileByDirectoryAndId(directory, id, name);
	}
}
