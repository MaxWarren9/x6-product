package com.sds.x6_product;

import com.sds.x6_product.model.Product;
import com.sds.x6_product.repository.ProductRepository;
import com.sds.x6_product.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    ProductRepository repository;

    @InjectMocks
    ProductService service;

    @Test
    void getById_ok() {
        Product product = new Product(
                1L,
                "Milk",
                BigDecimal.valueOf(100),
                "Desc",
                10
        );

        when(repository.getById(1L)).thenReturn(product);

        Product result = service.getById(1L);

        assertEquals(product, result);
    }

    @Test
    void getById_notFound() {
        when(repository.getById(1L))
                .thenThrow(new EmptyResultDataAccessException(1));

        assertThrows(EmptyResultDataAccessException.class,
                () -> service.getById(1L));
    }

    @Test
    void create_ok() {
        Product product = new Product(
                null,
                "Milk",
                BigDecimal.valueOf(100),
                "Desc",
                10
        );

        Product saved = new Product(
                1L,
                "Milk",
                BigDecimal.valueOf(100),
                "Desc",
                10
        );

        when(repository.insert(product)).thenReturn(saved);

        Product result = service.create(product);

        assertEquals(saved, result);
    }

    @Test
    void update_ok() {
        Product input = new Product(
                null,
                "Milk",
                BigDecimal.valueOf(120),
                "New",
                5
        );

        Product updated = new Product(
                1L,
                "Milk",
                BigDecimal.valueOf(120),
                "New",
                5
        );

        when(repository.update(any())).thenReturn(updated);

        Product result = service.update(1L, input);

        assertEquals(updated, result);
    }

    @Test
    void isAvailable_true() {
        when(repository.isProductAvailable(1L)).thenReturn(true);

        assertTrue(service.isAvailable(1L));
    }
}