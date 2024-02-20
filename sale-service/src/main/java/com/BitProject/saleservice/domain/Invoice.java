
package com.BitProject.saleservice.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "invoices", schema = "public")
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreatedDate
    private Date date;

    private double totalCost;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Product> products;

    @OneToOne(cascade = CascadeType.ALL)
    private Campaign campaign;

}