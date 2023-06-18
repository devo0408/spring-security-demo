package com.devo.product.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Setter
@Getter
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
//    @GeneratedValue(generator = "UUID")
//    @GenericGenerator(
//            name = "UUID",
//            strategy = "org.hibernate.id.UUIDGenerator"
//    )
    //@Column(length = 36, columnDefinition = "varchar", updatable = false, nullable = false)
    //@Column(updatable = false, nullable = false)
    private Integer id;

    @Version
    private Long version;

    @CreationTimestamp
    @Column(updatable = false)
    //@Temporal(TemporalType.TIMESTAMP)
    //@Column(name = "create_date", updatable = false)
    private Timestamp createdDate;

    @UpdateTimestamp
    // @Column
    //@Temporal(TemporalType.TIMESTAMP)
    //@Column(name = "modify_date")
    private Timestamp lastModifiedDate;


    public BaseEntity(Integer id, Long version, Timestamp createdDate, Timestamp lastModifiedDate) {
        this.id = id;
        this.version = version;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }
}
