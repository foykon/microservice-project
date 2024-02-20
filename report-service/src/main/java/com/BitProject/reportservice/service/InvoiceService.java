package com.BitProject.reportservice.service;

import com.BitProject.reportservice.domain.Invoice;
import com.BitProject.reportservice.dto.FilterAndPageDto;
import com.BitProject.reportservice.dto.InvoiceDto;
import com.BitProject.reportservice.dto.InvoiceResponse;

import java.util.List;

public interface InvoiceService {
    //crud
    List<Invoice> getAllInvoices();
    InvoiceDto findById(Long id);
    Invoice createInvoice(Invoice invoice);
    Invoice updateInvoice(Invoice invoice);
    void deleteInvoice(Long id);

    //customized
    InvoiceResponse findAllInvoice(FilterAndPageDto filterAndPageDto);
}
