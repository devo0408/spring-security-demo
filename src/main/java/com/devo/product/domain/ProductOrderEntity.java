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
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class ProductOrderEntity extends BaseEntity {

    @Builder
    public ProductOrderEntity(UUID id, Long version, Timestamp createdDate, Timestamp lastModifiedDate, Set<ProductOrderLineEntity> productOrderLines, OrderStatusEnum orderStatus) {
        super(id, version, createdDate, lastModifiedDate);
        this.productOrderLines = productOrderLines;
        this.orderStatus = orderStatus;
    }

    // todo: implement Customers part
//    @ManyToOne
//    private Customer customer;

    @OneToMany(mappedBy = "productOrderEntity", cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private Set<ProductOrderLineEntity> productOrderLines;

    private OrderStatusEnum orderStatus = OrderStatusEnum.NEW;
}
