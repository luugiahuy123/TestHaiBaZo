package org.example.testhaibazo.service;

import org.example.testhaibazo.dto.ProductDTO;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<ProductDTO> getAllProducts();

    Optional<ProductDTO> getProductById(Long id);

    ProductDTO createProduct(ProductDTO productDto);

    ProductDTO updateProduct(Long id, ProductDTO productDto);

    void deleteProduct(Long id);
}
