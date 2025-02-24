package fr.dawan.AppliCFABack.services;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface FilesService {
	boolean deleteDirectoryWithContent(String path);
	boolean createDirectory(String path);
	String[] getAllNamesByDirectory(String path);
	boolean postFile(String filePath, List<MultipartFile> file);
	ResponseEntity<ByteArrayResource> getFile(String workingDirectory, String fileName);
	boolean deleteContentByDirectory(String path);
}
