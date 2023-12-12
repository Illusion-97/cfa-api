package fr.dawan.AppliCFABack.controllers;

import fr.dawan.AppliCFABack.services.SifaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/sifa")
public class SifaController {
    @Autowired
    SifaService sifaService;
    @Value("${app.storagefolder}")
    private String storageFolder;
    @GetMapping(value = "/generate-sifa", produces = "text/plain")
    public ResponseEntity<byte[]> generateAndDownloadFile() throws IOException {
        sifaService.generateTabler();

        Path path = Paths.get(storageFolder);
        byte[] fileContent = Files.readAllBytes(path);

        String contentDisposition = "attachment; filename=sifa.txt";

        return ResponseEntity.ok().header("Content-Disposition", contentDisposition)
                .contentLength(fileContent.length).body(fileContent);
    }
}
