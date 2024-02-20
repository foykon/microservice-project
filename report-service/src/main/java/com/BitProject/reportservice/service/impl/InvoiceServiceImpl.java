package com.BitProject.reportservice.service.impl;

import com.BitProject.reportservice.dao.InvoiceRepository;
import com.BitProject.reportservice.domain.Campaign;
import com.BitProject.reportservice.domain.Invoice;
import com.BitProject.reportservice.domain.Product;
import com.BitProject.reportservice.dto.*;
import com.BitProject.reportservice.service.InvoiceService;
import com.BitProject.reportservice.specification.InvoiceSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;


    @Override
    public InvoiceDto findById(Long id) {

        return mapInvoiceToInvoiceDto(invoiceRepository.findById(id).orElseThrow(() -> new RuntimeException("Invoice not found with id: " + id)));
    }

    @Override
    public InvoiceResponse findAllInvoice(FilterAndPageDto filterAndPageDto) {
        Sort sort = filterAndPageDto.sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())
                ? Sort.by(filterAndPageDto.sortBy).ascending()
                : Sort.by(filterAndPageDto.sortBy).descending();

        // Create a Pageable instance
        Pageable pageable = PageRequest.of(filterAndPageDto.pageNo, filterAndPageDto.pageSize, sort);

        // sending filters
        final Specification<Invoice> specification =
                InvoiceSpecification.filterInvoice(filterAndPageDto);

        // calling repo
        final Page<Invoice> invoiceList = invoiceRepository.findAll(specification, pageable);

        List<Invoice> listOfInvoice = invoiceList.getContent();
        List<InvoiceDto> content = listOfInvoice.stream().map(invoice -> mapInvoiceToInvoiceDto(invoice)).toList();

        return InvoiceResponse.builder()
                .content(content)
                .pageNo(invoiceList.getNumber())
                .pageSize(invoiceList.getSize())
                .totalElements(invoiceList.getTotalElements())
                .totalPages(invoiceList.getTotalPages())
                .last(invoiceList.isLast())
                .build();
    }

    /**
     * mapping Invoice to InvoiceDto
     * @param invoice entity will be mapping
     * @return InvoiceDto
     */
    private InvoiceDto mapInvoiceToInvoiceDto(Invoice invoice) {
        return InvoiceDto.builder()
                .id(invoice.getId())
                .date(invoice.getDate())
                .totalCost(invoice.getTotalCost())
                .campaignResponse(mapCampaignToCampaignResponse(invoice.getCampaign()))
                .productResponses(invoice.getProducts().stream().map(this::mapProductToProductResponse).collect(Collectors.toList()))
                .build();

    }

    /**
     * mapping Product to ProductResponse
     * @param product entity will be mapping
     * @return ProductResponse
     */
    private ProductResponse mapProductToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .isDeleted(product.isDeleted())
                .name(product.getName())
                .price(product.getPrice())
                .stock(product.getStock())
                .description(product.getDescription())
                .build();
    }

    /**
     * mapping campaign to campaign response
     * @param campaign entity will be mapping
     * @return CampaignResponse
     */
    private CampaignResponse mapCampaignToCampaignResponse(Campaign campaign){
        return CampaignResponse.builder()
                .id(campaign.getId())
                .description(campaign.getDescription())
                .build();
    }




    @Override
    public List<Invoice> getAllInvoices() {
        return null;
    }

    @Override
    public Invoice createInvoice(Invoice invoice) {
        return null;
    }

    @Override
    public Invoice updateInvoice(Invoice invoice) {
        return null;
    }

    @Override
    public void deleteInvoice(Long id) {

    }

}