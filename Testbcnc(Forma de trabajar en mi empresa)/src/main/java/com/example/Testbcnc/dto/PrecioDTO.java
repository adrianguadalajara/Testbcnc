
package com.example.Testbcnc.dto;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "prices")
public class PrecioDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @javax.persistence.Column(name = "brand_id")
    private Integer brandId;

    @javax.persistence.Column(name = "start_date")
    private LocalDateTime startDate;

    @javax.persistence.Column(name = "end_date")
    private LocalDateTime endDate;

    @javax.persistence.Column(name = "price_list")
    private Integer priceList;

    @javax.persistence.Column(name = "product_id")
    private Integer productId;

    @javax.persistence.Column(name = "priority")
    private Integer priority;

    @javax.persistence.Column(name = "price")
    private BigDecimal price;

    @javax.persistence.Column(name = "curr")
    private String curr;

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Integer getBrandId() { return brandId; }
    public void setBrandId(Integer brandId) { this.brandId = brandId; }

    public LocalDateTime getStartDate() { return startDate; }
    public void setStartDate(LocalDateTime startDate) { this.startDate = startDate; }

    public LocalDateTime getEndDate() { return endDate; }
    public void setEndDate(LocalDateTime endDate) { this.endDate = endDate; }

    public Integer getPriceList() { return priceList; }
    public void setPriceList(Integer priceList) { this.priceList = priceList; }

    public Integer getProductId() { return productId; }
    public void setProductId(Integer productId) { this.productId = productId; }

    public Integer getPriority() { return priority; }
    public void setPriority(Integer priority) { this.priority = priority; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public String getCurr() { return curr; }
    public void setCurr(String curr) { this.curr = curr; }
}
