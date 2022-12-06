package b2c.app.pdf;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import b2c.app.model.Test;
 
public class TestPdfExport {
    private List<Test> tests;
     
    public TestPdfExport(List<Test> tests) {
    	this.tests = tests;
    }
 
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);
         
        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);
         
        cell.setPhrase(new Phrase("Order number", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Test name", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Measuring span", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Result", font));
        table.addCell(cell);
         
        cell.setPhrase(new Phrase("Date", font));
        table.addCell(cell);       
    }
     
    private void writeTableData(PdfPTable table) {
        for (Test test : tests) {
            table.addCell(String.valueOf(test.getOrderNumber()));
            table.addCell(test.getTestName());
            table.addCell(test.getMeasuringSpan());
            table.addCell(test.getResult());
            table.addCell(String.valueOf(test.getTestDate()));
        }
    }
     
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());
         
        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);
         
        Paragraph p = new Paragraph("Tests", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
         
        document.add(p);
         
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(10);
         
        writeTableHeader(table);
        writeTableData(table);
         
        document.add(table);
         
        document.close();       
    }
}