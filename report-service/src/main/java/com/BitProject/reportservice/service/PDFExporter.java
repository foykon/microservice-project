package com.BitProject.reportservice.service;

import com.BitProject.reportservice.dto.InvoiceDto;
import com.BitProject.reportservice.dto.ProductResponse;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Service;

import javax.swing.*;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.awt.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class PDFExporter {
    private InvoiceDto invoiceDto;

    public PDFExporter(){

    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.RED);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("Product ID", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Name", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Price", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Description", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table, List<ProductResponse> productResponses) {
        for(ProductResponse productResponse : productResponses){
            table.addCell(String.valueOf(productResponse.getId()));
            table.addCell(String.valueOf(productResponse.getName()));
            table.addCell(String.valueOf(productResponse.getPrice()));
            table.addCell(String.valueOf(productResponse.getDescription()));
        }

    }

    private Paragraph writeTableTitle(){
        Paragraph paragraph = new Paragraph("32Bit Project\nFurkan Yıldız\nSakarya Üniversitesi\nBilgisayar ve Bilişim Fakültesi\nSerdivan/Sakarya\nTeşekkür Ederiz");
        paragraph.setAlignment(Paragraph.ALIGN_CENTER);
        return paragraph;
    }

    public void export(HttpServletResponse servletResponse, InvoiceDto invoiceDto) throws  IOException{
        Document document = new Document(PageSize.A5);

        PdfWriter.getInstance(document, servletResponse.getOutputStream());

        document.open();

        document.add(writeTableTitle());

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        document.add(new Paragraph("Date : " + currentDateTime));
        document.add(new Paragraph("Invoice no : " + invoiceDto.getId()));

        document.add(new Paragraph("List Of Products"));

        PdfPTable table = new PdfPTable(4);
        table.setWidthPercentage(100);
        table.setSpacingBefore(15);

        writeTableHeader(table);
        writeTableData(table, invoiceDto.getProductResponses());

        document.add(table);

        document.add(new Paragraph("Total cost : " + invoiceDto.getTotalCost()));
        document.add(new Paragraph("Campaign : " + invoiceDto.getCampaignResponse().getDescription()));


        document.close();
    }



}
