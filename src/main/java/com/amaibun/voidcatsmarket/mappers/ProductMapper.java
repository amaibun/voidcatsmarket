package com.amaibun.voidcatsmarket.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.amaibun.voidcatsmarket.dtos.ProductDTO;
import com.amaibun.voidcatsmarket.models.Product;

@Mapper(componentModel="spring")
public interface ProductMapper {
    @Mapping(source = "productId", target = "productId")
    ProductDTO productToProductDTO(Product product);

    @Mapping(source = "productId", target = "productId")
    Product productDTOToProduct(ProductDTO productDto);
}
