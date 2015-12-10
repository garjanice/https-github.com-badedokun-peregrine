package com.depth1.grc.model;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import play.Logger;
import play.Play;

/**
 * This class is for printing a PDF of the procedure
 * @author Nilima
 */

public class PrintPdfProcedure {
	private PDDocument document;
	private PDPage page;
	private PDPageContentStream contentStream;
	private final String outputFileName = Play.application().configuration().getString("procedure.output.file.url");
	
/**
	 * Prints Procedure		
	 * @param pa procedure to print
	 */

public void printProcedure(Procedure pro){
	String outputFileName = "./public/pdf/Procedure.pdf";
	
	document = new PDDocument();
    page = new PDPage(PDPage.PAGE_SIZE_A4);
    
    document.addPage(page);
    contentStream = null;
    try {
    	contentStream = new PDPageContentStream(document, page);
    	int line = 0;	
        
		line = printProTitle(line);
		line += 50;
		line = printProBody(line, pro); 
		
		
		contentStream.close();
		document.save(outputFileName);
        document.close();
    }catch(Exception e) {
    	Logger.error("Error occurred while printing procedure criteria ", e);
    }
    
}
/**
 * This function prints the Title.
 * @param line - current position in pdf
 * @return the current line number (only 841 px/lines per page)
 * @throws Exception - error when printing to pdf
 */
private int printProTitle( int line)throws Exception{
	line += 30;
	contentStream.beginText();
    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 24);
    contentStream.moveTextPositionByAmount(200, 841 - line); // 
    contentStream.drawString("Procedure");
    contentStream.endText();
	
	return line;
}
/**
 * This function sets up a new page if you come to the end 
 * of the current page.
 * @throws Exception - error when printing to pdf
 */
private void getNewPDFPage() throws Exception{
	contentStream.close();
	page = new PDPage(PDPage.PAGE_SIZE_A4);
	document.addPage(page);
	contentStream = new PDPageContentStream(document, page);
}
/**
 * Prints the Procedure to pdf
 * @param text - the String to print
 * @param line - current position in pdf file
 * @throws Exception - error when printing to pdf
 */
private void printPdfProTitle(String text, int line)throws Exception{
	contentStream.beginText();
    contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
    contentStream.moveTextPositionByAmount(10, 841 - line); // 
    contentStream.drawString(text);
    contentStream.endText();
}

/**
 * This function prints the main body of the Pro.
 * @param line - the current line to print on
 * @param pro - the Procedure to print
 * @return the current line number.  
 * @throws Exception - error when printing to pdf
 */
private int printProBody(int line, Procedure pro)throws Exception{
	
/*	printPdfProTitle("Tenant Id:", line);
	
	line = printTextInBox(135, line, Integer.toString(pro.getTenantId()), 90);
    line += 25;
	if(line > 800){
		getNewPDFPage();
		line = 25;
	}
printPdfProTitle("Policy Id:", line);
	
line = printTextInBox(135, line, pro.getPolicyId().toString(), 90);
    line += 25;
	if(line > 800){
		getNewPDFPage();
		line = 25;
	}
	

	printPdfProTitle("Procedure Id:", line);
	
	 line = printTextInBox(135, line, pro.getProcedureId().toString(), 90);
    line += 25;
	if(line > 800){
		getNewPDFPage();
		line = 25;
	} */
	printPdfProTitle("Procedure Name:", line);
	line = printTextInBox(135, line, pro.getName(), 90);
    line += 25;
	if(line > 800){
		getNewPDFPage();
		line = 25;
	}
	printPdfProTitle("Version:", line);
	line = printTextInBox(135, line, pro.getVersion(), 90);
    line += 25;
	if(line > 800){
		getNewPDFPage();
		line = 25;
	}
	printPdfProTitle("Procedure Description:", line);
	line = printTextInBox(135, line, pro.getDescription(), 90);
    line += 25;
	if(line > 800){
		getNewPDFPage();
		line = 25;
	}
	printPdfProTitle("Author:", line);
	line = printTextInBox(135, line, pro.getAuthor(), 90);
    line += 25;
	if(line > 800){
		getNewPDFPage();
		line = 25;
	}
	printPdfProTitle("Approver:", line);
	line = printTextInBox(135, line, pro.getApprover(), 90);
    line += 25;
	if(line > 800){
		getNewPDFPage();
		line = 25;
	}
	printPdfProTitle("Owner:", line);
	line = printTextInBox(135, line, pro.getOwner(), 90);
    line += 25;
	if(line > 800){
		getNewPDFPage();
		line = 25;
	}
	printPdfProTitle("Subject:", line);
	line = printTextInBox(135, line, pro.getSubject(), 90);
    line += 25;
	if(line > 800){
		getNewPDFPage();
		line = 25;
	}
	
	printPdfProTitle("Policy Reference:", line);
	line = printTextInBox(135, line, pro.getReference(), 90);
    line += 25;
	if(line > 800){
		getNewPDFPage();
		line = 25;
	}
	
	return line;
}


/**
 * Prints text of Procedure in a boxed area
 * @param startWidth starting width for box on page
 * @param line current height on page
 * @param text information to print
 * @param letters maximum number of letters to print
 * @return the current line that is the height on the page
 * @throws Exception - error when printing to pdf
 */
private int printTextInBox(int startWidth ,int line, String text, int letters)throws Exception{
	if(text == null)
		return line;
	String textToPrint;
	int space = 0;
	int startChar = 0;
	int endChar = 0;
	for(int x = 0; x < text.length(); x++){
		if(text.charAt(x) == ' '){
			space = x;
		}
		if((x - startChar) > letters){
			endChar = space;
			x = endChar + 1;
			textToPrint = text.substring(startChar, endChar);
			contentStream.beginText();
	        contentStream.setFont(PDType1Font.HELVETICA, 10);
	        contentStream.moveTextPositionByAmount(startWidth, 841 - line); // 
	        contentStream.drawString(textToPrint);
	        contentStream.endText();
	        line += 15;
			startChar = x;
			if(line > 800){
				getNewPDFPage();
				line = 25;
			}
			
		}
		if((text.length()-1) == x) {
			textToPrint = text.substring(startChar, x + 1);
			
			contentStream.beginText();
	        contentStream.setFont(PDType1Font.HELVETICA, 10);
	        contentStream.moveTextPositionByAmount(startWidth, 841 - line); // 
	        contentStream.drawString(textToPrint);
	        contentStream.endText();
		}
		
	}
	
	return line;
}


}
	