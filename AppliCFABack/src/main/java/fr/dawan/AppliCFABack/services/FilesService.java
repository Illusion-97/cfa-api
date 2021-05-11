package fr.dawan.AppliCFABack.services;

public interface FilesService {
	boolean deleteDirectoryWithContent(String path);
	boolean createDirectory(String path);
}
