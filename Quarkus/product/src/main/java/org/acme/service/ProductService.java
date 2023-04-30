package org.acme.service;

import org.acme.dto.ProductDTO;
import org.acme.entity.ProductEntity;
import org.acme.repository.ProductRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ProductService {

    @Inject
    private ProductRepository productRepository;

    public List<ProductDTO> getAllProducts() {
        List<ProductDTO> products = new ArrayList<>();
        productRepository.findAll().stream()
                .forEach(item -> products.add(mapProductEntityToDTO(item)));

        return products;
    }

    public void createNewProduct(ProductDTO productDTO) {
        productRepository.persist(mapProductDtoToEntity(productDTO));
    }

    public void changeProduct(Long id, ProductDTO productDTO) {
        ProductEntity productEntity = productRepository.findById(id);

        productEntity.setCategory(productDTO.getCategory());
        productEntity.setName(productDTO.getName());
        productEntity.setModel(productDTO.getModel());
        productEntity.setDescription(productDTO.getDescription());
        productEntity.setPrice(productDTO.getPrice());

        productRepository.persist(mapProductDtoToEntity(productDTO));
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    private ProductDTO mapProductEntityToDTO(ProductEntity productEntity) {
        return ProductDTO.builder()
                .id(productEntity.getId())
                .category(productEntity.getCategory())
                .description(productEntity.getDescription())
                .model(productEntity.getModel())
                .name(productEntity.getName())
                .price(productEntity.getPrice())
                .build();
    }

    private ProductEntity mapProductDtoToEntity(ProductDTO productDTO) {
        return ProductEntity.builder()
                .category(productDTO.getCategory())
                .description(productDTO.getDescription())
                .model(productDTO.getModel())
                .name(productDTO.getName())
                .price(productDTO.getPrice())
                .build();
    }

    public ProductDTO findProductById(Long id) {
        return Optional.of(mapProductEntityToDTO(productRepository.findById(id)))
                .orElseThrow(NullPointerException::new);
    }
}
