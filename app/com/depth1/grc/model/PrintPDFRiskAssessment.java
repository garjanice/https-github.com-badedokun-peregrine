package com.depth1.grc.model;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.edit.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import play.Logger;

/**
 * This class is for printing a PDF of the Risk Assessment
 * @author Benjamin J Cargile
 * @version 1.0 9/2/2012
 */
public class PrintPDFRiskAssessment {
		private PDDocument document;
		private PDPage page;
		private PDPageContentStream cos;
		
	/**
	 * This is the main enter point for printing the RA pdf	
	 * @param ra is the Risk Assessment to print
	 */
	public void printRiskAssessment(RiskAssessment ra){
		String outputFileName = "./public/pdf/RA.pdf";
		document = new PDDocument();
        page = new PDPage(PDPage.PAGE_SIZE_A4);
        
        document.addPage(page);
        cos = null;
        try {
        	cos = new PDPageContentStream(document, page);
        	int line = 0;	
            
    		line = printRATitle( line);
    		line += 50;
    		line = printRABody( line, ra); 
    		
    		
    		cos.close();
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
		cos.beginText();
        cos.setFont(PDType1Font.HELVETICA_BOLD, 24);
        cos.moveTextPositionByAmount(200, 841 - line); // 
        cos.drawString("Risk Assessment");
        cos.endText();
		
		return line;
	}
	/**
	 * This function sets up a new page if you come to the end 
	 * of the current page.
	 * @throws Exception
	 */
	private void getNewPDFPage() throws Exception{
		cos.close();
		page = new PDPage(PDPage.PAGE_SIZE_A4);
		document.addPage(page);
		cos = new PDPageContentStream(document, page);
	}
	/**
	 * This function prints the main body of the RA.
	 * @param line - the current line to print on
	 * @param ra - the Risk Assessment to print
	 * @return the current line number.  
	 * @throws Exception
	 */
	private int printRABody(int line, RiskAssessment ra)throws Exception{
		
		cos.beginText();
        cos.setFont(PDType1Font.HELVETICA_BOLD, 10);
        cos.moveTextPositionByAmount(10, 841 - line); // 
        cos.drawString("Risk:");
        cos.endText();
		
        line = printTextInBox(135, line, ra.getRisk(), 90);
        line += 25;
		if(line > 800){
			getNewPDFPage();
			line = 25;
		}
		cos.beginText();
        cos.setFont(PDType1Font.HELVETICA_BOLD, 10);
        cos.moveTextPositionByAmount(10, 841 - line); // 
        cos.drawString("Consequence:");
        cos.endText();
		
        line = printTextInBox(135, line, ra.getConsequence(), 90);
        line += 25;
		if(line > 800){
			getNewPDFPage();
			line = 25;
		}
		cos.beginText();
        cos.setFont(PDType1Font.HELVETICA_BOLD, 10);
        cos.moveTextPositionByAmount(10, 841 - line); // 
        cos.drawString("Tenant Id:");
        cos.endText();
		
        line = printTextInBox(135, line, Integer.toString(ra.getTenantId()), 90);
        line += 25;
		if(line > 800){
			getNewPDFPage();
			line = 25;
		}
		cos.beginText();
        cos.setFont(PDType1Font.HELVETICA_BOLD, 10);
        cos.moveTextPositionByAmount(10, 841 - line); // 
        cos.drawString("Assessment Id:");
        cos.endText();
		
        line = printTextInBox(135, line, ra.getAssessmentId().toString(), 90);
        line += 25;
		if(line > 800){
			getNewPDFPage();
			line = 25;
		}
		
		cos.beginText();
        cos.setFont(PDType1Font.HELVETICA_BOLD, 10);
        cos.moveTextPositionByAmount(10, 841 - line); // 
        cos.drawString("Vulnerability:");
        cos.endText();
		
        line = printTextInBox(135, line, Float.toString(ra.getVulnerability()), 90);
        line += 25;
		if(line > 800){
			getNewPDFPage();
			line = 25;
		}
		cos.beginText();
        cos.setFont(PDType1Font.HELVETICA_BOLD, 10);
        cos.moveTextPositionByAmount(10, 841 - line); // 
        cos.drawString("Speed of Onset:");
        cos.endText();
		
        line = printTextInBox(135, line, Float.toString(ra.getSpeedOfOnset()), 90);
        line += 25;
		if(line > 800){
			getNewPDFPage();
			line = 25;
		}
		cos.beginText();
        cos.setFont(PDType1Font.HELVETICA_BOLD, 10);
        cos.moveTextPositionByAmount(10, 841 - line); // 
        cos.drawString("Impact:");
        cos.endText();
		
        line = printTextInBox(135, line, Float.toString(ra.getImpact()), 90);
        line += 25;
		if(line > 800){
			getNewPDFPage();
			line = 25;
		}
		
		cos.beginText();
        cos.setFont(PDType1Font.HELVETICA_BOLD, 10);
        cos.moveTextPositionByAmount(10, 841 - line); // 
        cos.drawString("Likelihood:");
        cos.endText();
		
        line = printTextInBox(135, line, Float.toString(ra.getLikelihood()), 90);
        line += 25;
		if(line > 800){
			getNewPDFPage();
			line = 25;
		}
		
		cos.beginText();
        cos.setFont(PDType1Font.HELVETICA_BOLD, 10);
        cos.moveTextPositionByAmount(10, 841 - line); // 
        cos.drawString("Likelihood Description:");
        cos.endText();
		
        line = printTextInBox(135, line, ra.getLikelihoodDescription(), 90);
        line += 25;
		if(line > 800){
			getNewPDFPage();
			line = 25;
		}
		
		cos.beginText();
        cos.setFont(PDType1Font.HELVETICA_BOLD, 10);
        cos.moveTextPositionByAmount(10, 841 - line); // 
        cos.drawString("Severity:");
        cos.endText();
		
        line = printTextInBox(135, line, Float.toString(ra.getSeverity()), 90);
        line += 25;
		if(line > 800){
			getNewPDFPage();
			line = 25;
		}
		cos.beginText();
        cos.setFont(PDType1Font.HELVETICA_BOLD, 10);
        cos.moveTextPositionByAmount(10, 841 - line); // 
        cos.drawString("Severity Description:");
        cos.endText();
		
        line = printTextInBox(135, line, ra.getSeverityDescription(), 90);
        line += 25;
		if(line > 800){
			getNewPDFPage();
			line = 25;
		}
		
		cos.beginText();
        cos.setFont(PDType1Font.HELVETICA_BOLD, 10);
        cos.moveTextPositionByAmount(10, 841 - line); // 
        cos.drawString("Opportunity:");
        cos.endText();
		
        line = printTextInBox(135, line, ra.getOpportunity(), 90);
        line += 25;
		if(line > 800){
			getNewPDFPage();
			line = 25;
		}
		cos.beginText();
        cos.setFont(PDType1Font.HELVETICA_BOLD, 10);
        cos.moveTextPositionByAmount(10, 841 - line); // 
        cos.drawString("Trigger Event:");
        cos.endText();
		
        line = printTextInBox(135, line, ra.getTriggerEvent(), 90);
        line += 25;
		if(line > 800){
			getNewPDFPage();
			line = 25;
		}
		
		cos.beginText();
        cos.setFont(PDType1Font.HELVETICA_BOLD, 10);
        cos.moveTextPositionByAmount(10, 841 - line); // 
        cos.drawString("Risk Factor:");
        cos.endText();
		
        line = printTextInBox(135, line, ra.getRiskFactor(), 90);
        line += 25;
		if(line > 800){
			getNewPDFPage();
			line = 25;
		}
		
		return line;
	}
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
				cos.beginText();
		        cos.setFont(PDType1Font.HELVETICA, 10);
		        cos.moveTextPositionByAmount(startWidth, 841 - line); // 
		        cos.drawString(textToPrint);
		        cos.endText();
		        line += 15;
				startChar = x;
				if(line > 800){
					getNewPDFPage();
					line = 25;
				}
				
			}
			if((text.length()-1) == x) {
				textToPrint = text.substring(startChar, x + 1);
				
				cos.beginText();
		        cos.setFont(PDType1Font.HELVETICA, 10);
		        cos.moveTextPositionByAmount(startWidth, 841 - line); // 
		        cos.drawString(textToPrint);
		        cos.endText();
			}
			
		}
		
		return line;
	}
	

}
