package com.sds.x6_product.repository;

import com.sds.x6_product.model.Product;
import com.sds.x6_product.repository.mapper.ProductMapper;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class ProductRepository {

    private static final String INSERT = """
            INSERT INTO x6_product.product (name, price, description, stock)
            VALUES (:name, :price, :description, :stock)
            RETURNING *;
            """;

    private static final String GET_BY_ID = """
            SELECT * FROM x6_product.product
            WHERE id = :id;
            """;

    private static final String UPDATE = """
            UPDATE x6_product.product 
            SET name = :name, price = :price, description = :description, stock = :stock
            WHERE id = :id
            RETURNING *;
            """;

    private static final String EXISTS = """
            SELECT EXISTS(SELECT 1 FROM x6_product.product WHERE id = :id);
            """;
    private final ProductMapper productMapper = new ProductMapper();
    NamedParameterJdbcTemplate jdbcTemplate;

    public Product insert(final Product product) {
        return jdbcTemplate.queryForObject(INSERT, productToSql(product), productMapper);
    }

    public Product getById(final long id) {
        return jdbcTemplate.queryForObject(GET_BY_ID, idParam(id), productMapper);
    }

    public Product update(final Product product) {
        MapSqlParameterSource params = productToSql(product)
                .addValue("id", product.id());
        return jdbcTemplate.queryForObject(UPDATE, params, productMapper);
    }

    public boolean isProductAvailable(final long id) {
        return jdbcTemplate.queryForObject(EXISTS, idParam(id), Boolean.class);
    }

    public MapSqlParameterSource productToSql(final Product product) {
        MapSqlParameterSource params = new MapSqlParameterSource();
        return params
                .addValue("name", product.name())
                .addValue("price", product.price())
                .addValue("description", product.description())
                .addValue("stock", product.stock())
                ;
    }
    private MapSqlParameterSource idParam(long id) {
        return new MapSqlParameterSource().addValue("id", id);
    }
}
