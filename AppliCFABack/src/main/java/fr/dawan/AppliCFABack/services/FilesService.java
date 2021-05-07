package fr.dawan.AppliCFABack.services;

import java.io.File;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;

public interface FilesService {

	String[] getAllNamesByDirectoryAndId(String directory, long id);

	ResponseEntity<File> getFileByDirectoryAndId(String directory, long id, String name);

}
