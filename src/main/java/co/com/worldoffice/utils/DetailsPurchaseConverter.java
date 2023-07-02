package co.com.worldoffice.utils;

import org.springframework.stereotype.Component;

import co.com.worldoffice.dto.DetailPurchaseDto;
import co.com.worldoffice.entities.DetailPurchaseEntity;

@Component
public class DetailsPurchaseConverter extends DataConverter<DetailPurchaseEntity, DetailPurchaseDto> {

    @Override
    public DetailPurchaseEntity dtoToEntity(DetailPurchaseDto dto) {
        return DetailPurchaseEntity.builder().idDetail(dto.getIdDetail()).idProduct(dto.getIdProduct())
                .idCart(dto.getIdCart()).amount(dto.getAmount()).price(dto.getPrice()).build();
    }

    @Override
    public DetailPurchaseDto entityToDto(DetailPurchaseEntity entity) {
        return DetailPurchaseDto.builder().idDetail(entity.getIdDetail()).idProduct(entity.getIdProduct())
                .idCart(entity.getIdCart()).amount(entity.getAmount()).price(entity.getPrice()).build();
    }

}