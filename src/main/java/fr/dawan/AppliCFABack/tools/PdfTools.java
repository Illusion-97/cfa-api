package fr.dawan.AppliCFABack.tools;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.jsoup.Jsoup;
import org.jsoup.helper.W3CDom;
import org.jsoup.nodes.Document;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;


public class PdfTools extends Exception{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void generatePdfFromHtml(String outputPdf, String htmlContent) throws Exception {
        OutputStream os = new BufferedOutputStream(new FileOutputStream(outputPdf));
        // objet permettant de builder le pdf
        PdfRendererBuilder builder = new PdfRendererBuilder();
        builder.withUri(outputPdf);
        builder.toStream(os);
        // parsing du html reçu
        Document documentHtml = Jsoup.parse(htmlContent, "UTF-8");
        builder.withW3cDocument(new W3CDom().fromJsoup(documentHtml), "/");
        builder.run();
        os.close();
    }
    
    public PdfTools(String message) {
		super.getMessage();
	}


}
