package com.devo.product.repositories;

import com.devo.product.domain.ProductTypeEnum;
import com.devo.product.domain.ProductEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.UUID;

import static io.micrometer.common.util.StringUtils.isBlank;
import static org.springframework.data.jpa.domain.Specification.where;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, UUID>, JpaSpecificationExecutor<ProductEntity> {

    default Page<ProductEntity> searchAllOptionalParameters(String title, ProductTypeEnum productTypeEnum, Pageable pageable) {
        return findAll(
                where(titleOptional(title)).and(typeOptional(productTypeEnum)),
                pageable
        );
    }

    default Specification<ProductEntity> titleOptional(String title) {
        if (isBlank(title)) {
            return null;
        }
        return (root, cq, cb) -> cb.equal(root.get("title"), title);
    }

    default Specification<ProductEntity> typeOptional(ProductTypeEnum productTypeEnum) {
        if (productTypeEnum == null) {
            return null;
        }
        return (root, cq, cb) -> cb.equal(root.get("type"), productTypeEnum);
    }

}
