package com.BitProject.saleservice.service;

import com.BitProject.saleservice.domain.Invoice;
import com.BitProject.saleservice.domain.Product;
import com.BitProject.saleservice.dto.InvoiceCreateRequest;
import com.BitProject.saleservice.dto.InvoiceResponse;

import java.util.List;

public interface InvoiceService {
    //crud
    List<Invoice> getAllInvoices();
    Invoice findById(Long id);
    InvoiceResponse createInvoice(InvoiceCreateRequest invoiceCreateRequest);
    Invoice updateInvoice(Invoice invoice);
    void deleteInvoice(Long id);



}
