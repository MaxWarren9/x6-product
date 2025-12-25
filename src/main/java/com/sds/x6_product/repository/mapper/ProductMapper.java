package com.sds.x6_product.repository.mapper;

import com.sds.x6_product.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Product(
                rs.getLong("id"),
                      rs.getString("name"),
                      rs.getBigDecimal("price"),
                      rs.getString("description"),
                      rs.getInt("stock")
                      );
    }
}
