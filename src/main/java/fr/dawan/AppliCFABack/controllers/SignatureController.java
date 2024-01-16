package fr.dawan.AppliCFABack.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fr.dawan.AppliCFABack.controllers.generic.GenericController;
import fr.dawan.AppliCFABack.dto.SignatureDto;
import fr.dawan.AppliCFABack.services.SignatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@RestController
@RequestMapping("/signatures")
public class SignatureController extends GenericController<SignatureDto> {
	
	@Value("${app.storagefolder}")
	private String storageFolder;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	public SignatureController(SignatureService service) {
		super(service);
	}


	@GetMapping(value = "/utilisateur/{uId}" ,produces = "application/json")
	public SignatureDto getByUtilisateurId(@PathVariable("uId") long uId) {
		return ((SignatureService) service).getByUtilisateurId(uId);
	}


	@PostMapping(value ="/file",consumes = "multipart/form-data", produces = "application/json")
	ResponseEntity<SignatureDto> save(@RequestParam("signature")String signatureStr, @RequestParam("file") MultipartFile file) throws Exception {

		File f =  new File(storageFolder + "/signatures/" +file.getOriginalFilename() );
		try(BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(f))){
			bos.write(file.getBytes());
		}
		SignatureDto  sDto = objectMapper.readValue(signatureStr,SignatureDto.class);
		sDto.setPieceJointe(file.getOriginalFilename());
		SignatureDto result = service.saveOrUpdate(sDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}



   
	


}
