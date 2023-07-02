package co.com.worldoffice.utils;

import org.springframework.stereotype.Component;

import co.com.worldoffice.dto.ProductDto;
import co.com.worldoffice.entities.ProductEntity;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class ProductConverter extends DataConverter<ProductEntity, ProductDto> {

    @Override
    public ProductEntity dtoToEntity(ProductDto dto) {
        return ProductEntity.builder().idProduct(dto.getIdProduct()).name(dto.getName()).trademark(dto.getTrademark())
                .price(dto.getPrice()).stock(dto.getStock()).status(dto.getStatus()).discount(dto.getDiscount()).build();
    }

    @Override
    public ProductDto entityToDto(ProductEntity entity) {
        return ProductDto.builder().idProduct(entity.getIdProduct()).name(entity.getName())
                .trademark(entity.getTrademark()).price(entity.getPrice()).stock(entity.getStock())
                .status(entity.getStatus()).discount(entity.getDiscount()).build();
    }

}