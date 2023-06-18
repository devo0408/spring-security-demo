package com.devo.product.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.sql.Timestamp;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ProductOrderEntity extends BaseEntity {

    @ManyToOne
    private CustomerEntity customer;

    @OneToMany(cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private Set<ProductOrderLineEntity> productOrderLines;

    private OrderStatusEnum orderStatus = OrderStatusEnum.NEW;


    @Builder
    public ProductOrderEntity(Integer id, Long version, Timestamp createdDate, Timestamp lastModifiedDate, Set<ProductOrderLineEntity> productOrderLines, OrderStatusEnum orderStatus, CustomerEntity customer) {
        super(id, version, createdDate, lastModifiedDate);
        this.productOrderLines = productOrderLines;
        this.orderStatus = orderStatus;
        this.customer = customer;
    }

    public ProductOrderEntity resetId() {
        setId(null);
        return this;
    }

    public ProductOrderEntity withStatus(OrderStatusEnum orderStatus) {
        this.orderStatus = orderStatus;
        return this;
    }

    public ProductOrderEntity withCustomer(CustomerEntity customer) {
        this.customer = customer;
        return this;
    }
}
