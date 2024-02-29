package com.kimmy.two_database_connection.service;

import com.kimmy.two_database_connection.config.TransactionManagerInspector;
import com.kimmy.two_database_connection.model.product.Product;
import com.kimmy.two_database_connection.repository.product.ProductRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final TransactionManagerInspector transactionManagerInspector;

    private final Logger logger = LoggerFactory.getLogger(ProductService.class);

    public ProductService(ProductRepository productRepository, TransactionManagerInspector transactionManagerInspector) {
        this.productRepository = productRepository;
        this.transactionManagerInspector = transactionManagerInspector;
    }

    public Mono<Product> addProduct(Product product) {
        logger.info("Saving a product..");
        TransactionManagerInspector.inspectTransactionManager(productRepository);
        return productRepository.save(product);
    }
    public Mono<Product> findProduct(int id) {
        logger.info("Finding a product..");
        return productRepository.findById(id)
                .defaultIfEmpty(new Product());
    }

}
