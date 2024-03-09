package com.BitProject.saleservice.source;

import com.BitProject.saleservice.dto.*;
import com.BitProject.saleservice.service.CampaignService;
import com.BitProject.saleservice.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sale")
@RequiredArgsConstructor
public class SaleController {
    private final InvoiceService invoiceService;
    private final CampaignService campaignService;



    @PostMapping("/createInvoice")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<InvoiceResponse> CreateInvoice(@RequestBody InvoiceCreateRequest invoiceCreateRequest){
        return ResponseEntity.ok(invoiceService.createInvoice(invoiceCreateRequest));
    }

    //@PostMapping("/createInvoice")
    //@ResponseStatus(HttpStatus.CREATED)
    //public InvoiceCreateRequest CreateInvoice(@RequestBody InvoiceCreateRequest invoiceCreateRequest){
    //    return invoiceCreateRequest;
    //}

    @GetMapping
    public ResponseEntity<CampaignResponse> getCampaign(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = "asc", required = false) String sortDir) {
        return ResponseEntity.ok(campaignService.findAllCampaign(
                FilterAndPageDto.builder()
                        .pageNo(pageNo)
                        .pageSize(pageSize)
                        .sortBy(sortBy)
                        .sortDir(sortDir)
                        .build()));
    }

    /* create campaign
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CampaignDto CreateCampaign(@RequestBody CampaignRequest campaignRequest){
        return campaignService.createCampaign(campaignRequest);
    }
    */

    /* get invoice
    @GetMapping("")
    public List<Invoice> getAllInvoice(){
        return invoiceService.getAllInvoices();
    }
    */

}
