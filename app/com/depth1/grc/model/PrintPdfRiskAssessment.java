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
 * This class is for printing a PDF of the Risk Assessment
 * @author Benjamin J Cargile
 * @version 1.0 9/2/2012
 */
public class PrintPdfRiskAssessment {
		private PDDocument document;
		private PDPage page;
		private PDPageContentStream contentStream;
		private final String outputFileName = Play.application().configuration().getString("risk.assessment.output.file.url");
		
	/**
	 * Prints Risk Assessment	
	 * @param ra Risk Assessment to print
	 */
	public void printRiskAssessment(RiskAssessment ra){
		//String outputFileName = "./public/pdf/RA.pdf";
		document = new PDDocument();
        page = new PDPage(PDPage.PAGE_SIZE_A4);
        
        document.addPage(page);
        contentStream = null;
        try {
        	contentStream = new PDPageContentStream(document, page);
        	int line = 0;	
            
    		line = printRATitle( line);
    		line += 50;
    		line = printRABody( line, ra); 
    		
    		
    		contentStream.close();
    		document.save(outputFileName);
            document.close();
        }catch(Exception e) {
        	Logger.error("Error occurred while printing risk assessment criteria ", e);
        }
        
	}
	/**
	 * This function prints the Title.
	 * @param line
	 * @return the current line number (only 841 px/lines per page)
	 * @throws Exception
	 */
	private int printRATitle( int line)throws Exception{
		line += 30;
		contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 24);
        contentStream.moveTextPositionByAmount(200, 841 - line); // 
        contentStream.drawString("Risk Assessment");
        contentStream.endText();
		
		return line;
	}
	/**
	 * This function sets up a new page if you come to the end 
	 * of the current page.
	 * @throws Exception
	 */
	private void getNewPDFPage() throws Exception{
		contentStream.close();
		page = new PDPage(PDPage.PAGE_SIZE_A4);
		document.addPage(page);
		contentStream = new PDPageContentStream(document, page);
	}
	/**
	 * Prints the main body of the RA.
	 * @param line current line to print on
	 * @param ra Risk Assessment to print
	 * @return the current line number.  
	 * @throws Exception
	 */
	private int printRABody(int line, RiskAssessment ra)throws Exception{
		
		contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
        contentStream.moveTextPositionByAmount(10, 841 - line); // 
        contentStream.drawString("Risk:");
        contentStream.endText();
		
        line = printTextInBox(135, line, ra.getRisk(), 90);
        line += 25;
		if(line > 800){
			getNewPDFPage();
			line = 25;
		}
		contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
        contentStream.moveTextPositionByAmount(10, 841 - line); // 
        contentStream.drawString("Consequence:");
        contentStream.endText();
		
        line = printTextInBox(135, line, ra.getConsequence(), 90);
        line += 25;
		if(line > 800){
			getNewPDFPage();
			line = 25;
		}
		contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
        contentStream.moveTextPositionByAmount(10, 841 - line); // 
        contentStream.drawString("Tenant Id:");
        contentStream.endText();
		
        line = printTextInBox(135, line, Integer.toString(ra.getTenantId()), 90);
        line += 25;
		if(line > 800){
			getNewPDFPage();
			line = 25;
		}
		contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
        contentStream.moveTextPositionByAmount(10, 841 - line); // 
        contentStream.drawString("Assessment Id:");
        contentStream.endText();
		
        line = printTextInBox(135, line, ra.getAssessmentId().toString(), 90);
        line += 25;
		if(line > 800){
			getNewPDFPage();
			line = 25;
		}
		
		contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
        contentStream.moveTextPositionByAmount(10, 841 - line); // 
        contentStream.drawString("Vulnerability:");
        contentStream.endText();
		
        line = printTextInBox(135, line, Float.toString(ra.getVulnerability()), 90);
        line += 25;
		if(line > 800){
			getNewPDFPage();
			line = 25;
		}
		contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
        contentStream.moveTextPositionByAmount(10, 841 - line); // 
        contentStream.drawString("Speed of Onset:");
        contentStream.endText();
		
        line = printTextInBox(135, line, Float.toString(ra.getSpeedOfOnset()), 90);
        line += 25;
		if(line > 800){
			getNewPDFPage();
			line = 25;
		}
		contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
        contentStream.moveTextPositionByAmount(10, 841 - line); // 
        contentStream.drawString("Impact:");
        contentStream.endText();
		
        line = printTextInBox(135, line, Float.toString(ra.getImpact()), 90);
        line += 25;
		if(line > 800){
			getNewPDFPage();
			line = 25;
		}
		
		contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
        contentStream.moveTextPositionByAmount(10, 841 - line); // 
        contentStream.drawString("Likelihood:");
        contentStream.endText();
		
        line = printTextInBox(135, line, Float.toString(ra.getLikelihood()), 90);
        line += 25;
		if(line > 800){
			getNewPDFPage();
			line = 25;
		}
		
		contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
        contentStream.moveTextPositionByAmount(10, 841 - line); // 
        contentStream.drawString("Likelihood Description:");
        contentStream.endText();
		
        line = printTextInBox(135, line, ra.getLikelihoodDescription(), 90);
        line += 25;
		if(line > 800){
			getNewPDFPage();
			line = 25;
		}
		
		contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
        contentStream.moveTextPositionByAmount(10, 841 - line); // 
        contentStream.drawString("Severity:");
        contentStream.endText();
		
        line = printTextInBox(135, line, Float.toString(ra.getSeverity()), 90);
        line += 25;
		if(line > 800){
			getNewPDFPage();
			line = 25;
		}
		contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
        contentStream.moveTextPositionByAmount(10, 841 - line); // 
        contentStream.drawString("Severity Description:");
        contentStream.endText();
		
        line = printTextInBox(135, line, ra.getSeverityDescription(), 90);
        line += 25;
		if(line > 800){
			getNewPDFPage();
			line = 25;
		}
		
		contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
        contentStream.moveTextPositionByAmount(10, 841 - line); // 
        contentStream.drawString("Opportunity:");
        contentStream.endText();
		
        line = printTextInBox(135, line, ra.getOpportunity(), 90);
        line += 25;
		if(line > 800){
			getNewPDFPage();
			line = 25;
		}
		contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
        contentStream.moveTextPositionByAmount(10, 841 - line); // 
        contentStream.drawString("Trigger Event:");
        contentStream.endText();
		
        line = printTextInBox(135, line, ra.getTriggerEvent(), 90);
        line += 25;
		if(line > 800){
			getNewPDFPage();
			line = 25;
		}
		
		contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 10);
        contentStream.moveTextPositionByAmount(10, 841 - line); // 
        contentStream.drawString("Risk Factor:");
        contentStream.endText();
		
        line = printTextInBox(135, line, ra.getRiskFactor(), 90);
        line += 25;
		if(line > 800){
			getNewPDFPage();
			line = 25;
		}
		
		return line;
	}
	
	/**
	 * Prints text of RA in a boxed area
	 * @param startWidth starting width for box on page
	 * @param line current height on page
	 * @param text information to print
	 * @param letters maximum number of letters to print
	 * @return the current line that is the height on the page
	 * @throws Exception
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
