package fr.dawan.AppliCFABack.tools;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;

import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.jsoup.nodes.Document;

import com.itextpdf.html2pdf.HtmlConverter;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;


@SuppressWarnings("serial")
public class ToPdf extends Exception{

	public static void convertHtmlToPdf(String htmlContent,  String outputPdf) throws Exception {
		
		//création du outputStream pour le stockage
		OutputStream os = new BufferedOutputStream(new FileOutputStream(outputPdf));
		HtmlConverter.convertToPdf(htmlContent, os);
        System.out.println( "PDF Created!" );
		os.close();	
	}
	
	public ToPdf (String message) {
		super.getMessage();
	}

}
