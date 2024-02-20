package com.BitProject.saleservice.service.impl;

import com.BitProject.saleservice.dao.CampaignRepository;
import com.BitProject.saleservice.dao.InvoiceRepository;
import com.BitProject.saleservice.dao.ProductRepository;
import com.BitProject.saleservice.domain.Campaign;
import com.BitProject.saleservice.domain.Invoice;
import com.BitProject.saleservice.domain.Product;
import com.BitProject.saleservice.dto.*;
import com.BitProject.saleservice.service.InvoiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final ProductRepository productRepository;
    private final CampaignRepository campaignRepository;


    @Override
    public InvoiceResponse createInvoice(InvoiceCreateRequest invoiceCreateRequest) {

        List<Product> products = findProductsByIds(invoiceCreateRequest.getListOfProductId());
        products.forEach(product -> ReduceStock(product));
        return MapToInvoiceResponse(
                invoiceRepository.save(Invoice.builder()
                        .date(new Date())
                        .products(products)
                        .totalCost(products.stream().mapToDouble(Product::getPrice).sum())
                        .campaign(campaignRepository.getReferenceById(invoiceCreateRequest.getCampaignId()))
                        .build()
                )
        );
    }

    /**
     * reducing stocks if stock is enough
     * @param product
     * @return boolean
     */
    private void ReduceStock(Product product){
        if(product.getStock()>0)
            product.setStock(product.getStock()-1);
    }

    /**
     * mapping param invoice to invoice response with builder method
     * @param invoice
     * @return InvoiceResponse
     */
    private InvoiceResponse MapToInvoiceResponse(Invoice invoice){
        return InvoiceResponse.builder()
                .id(invoice.getId())
                .date(invoice.getDate())
                .productResponses(invoice.getProducts().stream()
                        .map(product -> MapToProductResponse(product))
                        .collect(Collectors.toList()))
                .totalCost(invoice.getTotalCost())
                .campaignDto(MapToCampaignDto(invoice.getCampaign()))
                .build();
    }

    /**
     * mapping campaign to campaign response with builder method
     * @param campaign
     * @return CampaignResponse
     */
    private CampaignDto MapToCampaignDto(Campaign campaign){
        return CampaignDto.builder()
                .id(campaign.getId())
                .description(campaign.getDescription())
                .build();
    }

    /**
     * mapping product to product response with builder method
     * @param product
     * @return ProductResponse
     */
    private ProductResponse MapToProductResponse(Product product){
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .description(product.getDescription())
                .build();
    }

    /**
     * finds products from param Id's and checks whether null with optional
     * @param ListOfProductId
     * @return List<Product>
     */
    private List<Product> findProductsByIds (List<Long> ListOfProductId){
        return ListOfProductId.stream()
                .map(productRepository::findById)
                .map(Optional::orElseThrow)
                .collect(Collectors.toList());
    }



    @Override
    public Invoice updateInvoice(Invoice invoice) {
        return null;
    }

    @Override
    public void deleteInvoice(Long id) {

    }

    @Override
    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    @Override
    public Invoice findById(Long id) {
        return null;
    }
}
