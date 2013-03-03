package emcscanner.kth.se;

import static com.googlecode.javacv.cpp.opencv_imgproc.CV_INTER_LANCZOS4;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvResize;

import java.awt.image.BufferedImage;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

public class CreatePdf {
	private static String FILE = "user data/PDF/ITextTest.pdf";
	private static Font HEADER_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.BOLD);
	private static Font CAT_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
	//private static Font redFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,Font.NORMAL, BaseColor.RED);
	//private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16,Font.BOLD);
	private static Font SMALL_BOLD_FONT = new Font(Font.FontFamily.TIMES_ROMAN, 12,Font.BOLD);

	/**
	 * 
	 */
	public CreatePdf() {
		try {
			Rectangle pagesize = new Rectangle(SettingsPanel.scanPanel.scan.buffImage[SettingsPanel.scanPanel.scan.frequency.length/2].getWidth() + 0, 
											   SettingsPanel.scanPanel.scan.buffImage[SettingsPanel.scanPanel.scan.frequency.length/2].getHeight() + 600);
	        Document document = new Document(pagesize, 36f, 72f, 108f, 180f);
	        
			PdfWriter.getInstance(document, new FileOutputStream(FILE));
			document.open();
			addMetaData(document);
			addTitlePage(document);
			addContent(document);
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
	    }
	}
	
	/**
	 * iText allows to add metadata to the PDF which can be viewed in your Adobe Reader
	 * under File -> Properties
	 * 
	 * @param document
	 */
	private static void addMetaData(Document document) {
		document.addTitle("Summery Report");
	    document.addSubject("Summery");
	    document.addKeywords("Java, PDF, iText");
	    document.addAuthor("Jonas Andr�e");
	    document.addCreator("Jonas Andr�e");
	}
	
	/**
	 * 
	 * @param document
	 * @throws DocumentException
	 */
	private static void addTitlePage(Document document) throws DocumentException {
		Paragraph preface = new Paragraph();
		// We add one empty line
		addEmptyLine(preface, 1);
		// Lets write a big header
		Paragraph summery = new Paragraph("Summary Report", HEADER_FONT);
		summery.setAlignment(Element.ALIGN_CENTER);
		preface.add(summery);

		addEmptyLine(preface, 1);
		// Will create: Report generated by: _name, _date
		Paragraph date = new Paragraph("Report generated: " + new Date(), SMALL_BOLD_FONT);
		date.setAlignment(Element.ALIGN_CENTER);
		
		preface.add(date);
		Paragraph emptyline = new Paragraph();
		addEmptyLine(emptyline, 2);
		preface.add(emptyline);
		document.add(preface);
		
		Image frontPageImage = coloredPhoto(SettingsPanel.scanPanel.scan.frequency.length/2);
		
		frontPageImage.setAlignment(Element.ALIGN_CENTER);
		document.add(frontPageImage);
		
		// Start a new page
		document.newPage();
	}
	
	
	private static void addContent(Document document) throws DocumentException {
		Anchor anchor = new Anchor("Input Values", CAT_FONT);
	    anchor.setName("Input Values");

	    // Second parameter is the number of the chapter
	    Chapter catPart = new Chapter(new Paragraph(anchor), 1);
	    Paragraph emptyline = new Paragraph();
	    catPart.add(emptyline);
	    
	    catPart.add(new Paragraph("File Name:\u00a0" + SettingsPanel.FILE_NAME));
	    addEmptyLine(emptyline, 1);
	    catPart.add(new Paragraph("Frequency Start Value:\u00a0" + SettingsPanel.frequencyStartUserSelectedFloat));
	    catPart.add(new Paragraph("Frequency End Value:\u00a0" + SettingsPanel.frequencyEndUserSelectedFloat));
	    catPart.add(new Paragraph("Frequency dencity:\u00a0" + SettingsPanel.frequencyDensityUserSelectedInt));
	    addEmptyLine(emptyline, 1);
	    catPart.add(new Paragraph("Area Selected:\u00a0"));
	    catPart.add(new Paragraph("\u00a0\u00a0\u00a0Width:\u00a0" + SettingsPanel.AREA_SELECTED_IMAGE_DEPENDENT_WIDTH));
	    catPart.add(new Paragraph("\u00a0\u00a0\u00a0Height:\u00a0" + SettingsPanel.AREA_SELECTED_IMAGE_DEPENDENT_HEIGHT));

	    catPart.add(new Paragraph("Density Selected:\u00a0"));
	    catPart.add(new Paragraph("\u00a0\u00a0\u00a0Width:\u00a0" + SettingsPanel.DENSITY_SELECTED_WIDTH));
	    catPart.add(new Paragraph("\u00a0\u00a0\u00a0Height:\u00a0" + SettingsPanel.DENSITY_SELECTED_HEIGHT));
	    
	    // Now add all this to the document
	    document.add(catPart);

	    // Next section
	    anchor = new Anchor("Output Values", CAT_FONT);
	    anchor.setName("Output Values");

	    // Second parameter is the number of the chapter
	    catPart = new Chapter(new Paragraph(anchor), 2);
	    catPart.add(emptyline);
	    
	    FileInputStream[] textFile = new FileInputStream[SettingsPanel.scanPanel.scan.frequency.length];
	    for (int i = 0; i < SettingsPanel.scanPanel.scan.frequency.length; i++)
	    {
	    	catPart.add(new Paragraph("Frequency number " + (i + 1) + ":\u00a0\u00a0" + String.valueOf(SettingsPanel.scanPanel.scan.frequency[i]+ " MHz") ));
	    	catPart.add(Chunk.NEWLINE);
		    try {
		    	textFile[i] =  new FileInputStream(Scan.DEFULT_FILE_FREQUENCY_OUTPUT_LOCATION + SettingsPanel.FILE_NAME + " " + SettingsPanel.scanPanel.scan.frequency[i] + ".txt");
		    	File f = new File(Scan.DEFULT_FILE_FREQUENCY_OUTPUT_LOCATION + SettingsPanel.FILE_NAME + " " + SettingsPanel.scanPanel.scan.frequency[i] + ".txt");
		    	byte[] buffer = new byte[(int) f.length()];
		    	new DataInputStream(textFile[i]).readFully(buffer);
		    	textFile[i].close();
		    	String s = new String(buffer, "UTF-8");
		    	Paragraph p = new Paragraph(s);
		    	catPart.add(p);
		    	catPart.newPage();
		    	
		    	Image frequencyImage = coloredPhoto(i);
		    	frequencyImage.setAlignment(Element.ALIGN_CENTER);
		    	catPart.add(frequencyImage);
		    	catPart.newPage();
		    	
			} catch (IOException e) {
				e.printStackTrace();
			}
	    }

	    // Now add all this to the document
	    document.add(catPart);
	}
	/**
	 * 
	 * @param paragraph
	 * @param number
	 */
	private static void addEmptyLine(Paragraph paragraph, int number) {
	    for (int i = 0; i < number; i++) {
	    	paragraph.add(new Paragraph(" "));
		}
	}
	/*
	 * Resizes the Photo when needed to 
	 */
	public static Image coloredPhoto(int i){	
		Image frequencyImage = null;
		
		IplImage photo = Program.imagePanel.iplPhoto2;
		
        int width  = photo.width() * Program.TIONDELS_MILLI_METER_PIXEL;
        int height  = photo.height() * Program.TIONDELS_MILLI_METER_PIXEL;
        
        IplImage ipl = IplImage.create(width, height, photo.depth(), photo.nChannels());
        
        cvResize(photo, ipl, CV_INTER_LANCZOS4);
        
        BufferedImage tempBuff = ipl.getBufferedImage();
        tempBuff.createGraphics().drawImage(SettingsPanel.scanPanel.scan.buffImage[i], null, 0, 0);
		
		try {
			frequencyImage = Image.getInstance(tempBuff, null);
		} catch (BadElementException | IOException e) {
			e.printStackTrace();
		}
		
        return frequencyImage;
	}
}