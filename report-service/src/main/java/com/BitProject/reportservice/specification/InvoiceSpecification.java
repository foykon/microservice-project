package com.BitProject.reportservice.specification;


import com.BitProject.reportservice.domain.Campaign;
import com.BitProject.reportservice.domain.Invoice;
import com.BitProject.reportservice.domain.Product;
import com.BitProject.reportservice.dto.FilterAndPageDto;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

public class InvoiceSpecification {

    public static Specification<Invoice> filterInvoice(FilterAndPageDto filterAndPageDto) {

        return (root, query, criteriaBuilder) -> {

            // Filter for minimum invoice date
            Predicate minInvoiceDatePredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("date"), filterAndPageDto.minDate);

            // Filter for maximum invoice date
            Predicate maxInvoiceDatePredicate = criteriaBuilder.lessThanOrEqualTo(root.get("date"), filterAndPageDto.maxDate);

            // Filter for minimum invoice total cost
            Predicate minTotalCostPredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("totalCost"), filterAndPageDto.minTotalCost);

            // Filter for maximum invoice total cost
            Predicate maxTotalCostPredicate = criteriaBuilder.lessThanOrEqualTo(root.get("totalCost"), filterAndPageDto.maxTotalCost);

            if (filterAndPageDto.product == null && filterAndPageDto.campaign == null) {
                return criteriaBuilder.and(minTotalCostPredicate,maxTotalCostPredicate);
            }
            // Filter for product
            Join<Product, Invoice> invoiceProducts = root.join("products");
            Predicate productPredicate = criteriaBuilder.equal(invoiceProducts, filterAndPageDto.product);

            // Filter for campaign
            Join<Campaign,Invoice> invoiceCampaign = root.join("campaign");
            Predicate campaignPredicate = criteriaBuilder.equal(invoiceCampaign, filterAndPageDto.campaign);

            // Combine both filters using AND
            return criteriaBuilder.and(minTotalCostPredicate,maxTotalCostPredicate,productPredicate,campaignPredicate);
        };
    }

}
