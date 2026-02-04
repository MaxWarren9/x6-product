package com.sds.x6_product.repository.mapper;

import com.sds.x6_product.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;



public class ProductMapper implements RowMapper<Product> {
    private static final String FIELD_ID = "id";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_PRICE = "price";
    private static final String FIELD_DESCRIPTION = "description";
    private static final String FIELD_STOCK = "stock";

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Product(
                rs.getLong(FIELD_ID),
                      rs.getString(FIELD_NAME),
                      rs.getBigDecimal(FIELD_PRICE),
                      rs.getString(FIELD_DESCRIPTION),
                      rs.getInt(FIELD_STOCK)
                      );
    }
}
