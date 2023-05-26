package fr.dawan.AppliCFABack.tools;

import org.springframework.http.MediaType;

import javax.servlet.ServletContext;

public class MediaTypeUtils {
	
	private MediaTypeUtils() {
		
	}
	
	public static MediaType getMediaTypeForFileName(ServletContext servletContext, String fileName) {
        // application/pdf
        // application/xml
        // image/gif, ...
        String mineType = servletContext.getMimeType(fileName);
        try {
            //MediaType mediaType = MediaType.parseMediaType(mineType);
            return MediaType.parseMediaType(mineType);
        } catch (Exception e) {
            return MediaType.APPLICATION_OCTET_STREAM;
        }
    }
}
