package com.devo.product.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.Set;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class CustomerEntity extends BaseEntity {

    @Builder
    public CustomerEntity(UUID id, Long version, Timestamp createdDate, Timestamp lastModifiedDate, String customerName,
                    UUID apiKey, Set<ProductOrderEntity> orders) {
        super(id, version, createdDate, lastModifiedDate);
        this.customerName = customerName;
        this.orders = orders;
    }


    private String customerName;

    @OneToMany(mappedBy = "customer")
    private Set<ProductOrderEntity> orders;

}
