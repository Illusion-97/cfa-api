package fr.dawan.AppliCFABack.services;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface FilesService {
	boolean deleteDirectoryWithContent(String path);
	boolean createDirectory(String path);
	String[] getAllNamesByDirectory(String string);
	boolean postFile(String filePath, MultipartFile file);
	ResponseEntity<ByteArrayResource> getFile(String workingDirectory, String fileName);
}
