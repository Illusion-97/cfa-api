package fr.dawan.AppliCFABack.tools;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

import com.itextpdf.html2pdf.HtmlConverter;


@SuppressWarnings("serial")
public class ToPdf extends Exception{

	/**
	 * 
	 * @param htmlContent : contenu du fichier en HTML 
	 * @param outputPdf : emplacement du fichier
	 * @throws Exception
	 */
	public static void convertHtmlToPdf(String htmlContent,  String outputPdf) throws Exception {
		
		try(OutputStream os = new BufferedOutputStream(new FileOutputStream(outputPdf))){
			HtmlConverter.convertToPdf(htmlContent, os);
		}
	}
	
	public ToPdf (String message) {
		super.getMessage();
	}

}
