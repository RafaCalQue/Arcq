package Project.Arcq.data.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name= "PRICES")
public class Product {
    @Id
    @Column(name = "ID")
    private Long Id;
    @ManyToOne
    @JoinColumn(name = "BRAND_ID")
    private Brands brand;
    @Column(name = "START_DATE")
    private LocalDateTime startDate;
    @Column(name = "END_DATE")
    private LocalDateTime endDate;
    @Column(name = "PRICE_LIST")
    private Long priceList;
    @Column(name = "PRODUCT_ID")
    private Long productId;
    @Column(name = "PRIORITY")
    private Long priority;
    @Column(name = "PRICE")
    private Double price;
    @Column(name = "CURR")
    private String curr;

}
