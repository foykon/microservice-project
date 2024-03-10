package com.BitProject.reportservice.source;

import com.BitProject.reportservice.dao.CampaignRepository;
import com.BitProject.reportservice.dao.ProductRepository;
import com.BitProject.reportservice.dto.FilterAndPageDto;
import com.BitProject.reportservice.dto.InvoiceDto;
import com.BitProject.reportservice.dto.InvoiceResponse;
import com.BitProject.reportservice.service.InvoiceService;
import com.BitProject.reportservice.service.PDFExporter;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/report")
@RequiredArgsConstructor
public class ReportController {
    private final InvoiceService invoiceService;
    private final CampaignRepository campaignRepository;
    private final ProductRepository productRepository;
    private final PDFExporter pdfExporter;


    @GetMapping("/find/{id}")
    @ResponseStatus(HttpStatus.OK)
    public InvoiceDto GetInvoiceById(@PathVariable("id") Long id){
        return invoiceService.findById(id);
    }

    @GetMapping
    public ResponseEntity<InvoiceResponse> getInvoices(
            @RequestParam(value = "minTotalCost", defaultValue = "0", required = false) double minTotalCost,
            @RequestParam(value = "maxTotalCost", defaultValue = "100000", required = false) double maxTotalCost,
            @RequestParam(value = "campaignId", defaultValue = "-1",required = false) Long campaignId,
            @RequestParam(value = "productId", defaultValue = "-1",required = false) Long productId,
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
        return ResponseEntity.ok(invoiceService.findAllInvoice(
                FilterAndPageDto.builder()
                        //.minDate()
                        //.maxDate()
                        .minTotalCost(minTotalCost)
                        .maxTotalCost(maxTotalCost)
                        .campaign(campaignId != -1 ? campaignRepository.getReferenceById(campaignId) : null)
                        .product(productId!= -1 ? productRepository.getReferenceById(productId) : null)
                        .pageNo(pageNo)
                        .pageSize(pageSize)
                        .sortBy(sortBy)
                        .sortDir(sortDir)
                        .build()));
    }

    @GetMapping("/pdf/{id}")
    public void exportToPdf(@PathVariable("id") Long id, HttpServletResponse httpServletResponse) throws IOException {

        httpServletResponse.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");

        String currentDateTime = dateFormatter.format(new Date());
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Invoice_" + id + "_" + currentDateTime +".pdf";

        httpServletResponse.setHeader(headerKey,headerValue);



        pdfExporter.export(httpServletResponse, invoiceService.findById(id));

    }







}
