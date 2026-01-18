package com.sds.x6_product.controller;

import com.sds.x6_product.model.Product;
import com.sds.x6_product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/product")
@AllArgsConstructor
@Tag(name = "Продукт")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @Operation(summary = "Создать продукт")
    public Product create(@RequestBody final Product product) {
        return productService.create(product);
    }

    @GetMapping("/{id}/exists")
    public boolean isUserAvailable(@PathVariable long id) {
        return productService.isAvailable(id);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Получить ппродукт")
    public Product getById(@PathVariable("id") Long id) {
        return productService.getById(id);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Обновить продукт")
    public Product update(@PathVariable long id, @RequestBody Product product) {
        return productService.update(id, product);
    }

    @PostMapping("/exists")
    @Operation(summary = "Проверить существование товаров по списку id")
    public boolean areProductsAvailable(@RequestBody List<Long> ids) {
        return productService.areProductsAvailable(ids);
    }
}
