package com.kimmy.two_database_connection.repository.product;

import com.kimmy.two_database_connection.model.product.Product;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends R2dbcRepository<Product, Integer> {
}
