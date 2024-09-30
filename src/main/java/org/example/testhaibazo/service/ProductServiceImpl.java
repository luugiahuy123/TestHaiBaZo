package org.example.testhaibazo.service;

import org.example.testhaibazo.dto.ProductDTO;
import org.example.testhaibazo.model.Product;
import org.example.testhaibazo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<ProductDTO> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<ProductDTO> getProductById(Long id) {
        return productRepository.findById(id).map(this::convertToDto);
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDto) {
        Product product = convertToEntity(productDto);
        return convertToDto(productRepository.save(product));
    }

    @Override
    public ProductDTO updateProduct(Long id, ProductDTO productDto) {
        return productRepository.findById(id).map(product -> {
            product.setName(productDto.getName());
            product.setPrice(productDto.getPrice());
            product.setDescription(productDto.getDescription());
            product.setStock(productDto.getStock());
            product.setSize(productDto.getSize());
            product.setColor(productDto.getColor());
            return convertToDto(productRepository.save(product));
        }).orElse(null);
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    private ProductDTO convertToDto(Product product) {
        return new ProductDTO(product.getId(), product.getName(), product.getPrice(), product.getDescription(), product.getStock(), product.getSize(), product.getColor(), product.getCategory().getId());
    }

    private Product convertToEntity(ProductDTO productDto) {
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setDescription(productDto.getDescription());
        product.setStock(productDto.getStock());
        product.setSize(productDto.getSize());
        product.setColor(productDto.getColor());
        return product;
    }
}
