package com.sds.x6_product.service;

import com.sds.x6_product.exception.ProductException;
import com.sds.x6_product.model.Product;
import com.sds.x6_product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;

    @Transactional(rollbackFor = Exception.class)
    public Product create(final Product product) {
        try {
            return productRepository.insert(product);
        } catch (Exception e) {
            throw new ProductException("Ошибка при создании продукта: " + e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    @Cacheable(value = "product", key = "#id")
    public Product getById(final long id) {
        try {
            return productRepository.getById(id);
        } catch (Exception e) {
            throw new ProductException("Продукт с id=" + id + " не найден");
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @CachePut(value = "product", key = "#result.id()")
    public Product update(long id, Product product) {
        try {
            Product updated = new Product(
                    id,
                    product.name(),
                    product.price(),
                    product.description(),
                    product.stock()
            );
            return productRepository.update(updated);
        } catch (Exception e) {
            throw new ProductException("Ошибка при обновлении продукта: " + e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    public boolean isAvailable(final long id) {
        return productRepository.isProductAvailable(id);
    }

    @Transactional(readOnly = true)
    public boolean areProductsAvailable(List<Long> ids) {
        return productRepository.areProductsAvailable(ids);
    }
}
